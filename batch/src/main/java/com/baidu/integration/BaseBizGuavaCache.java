package com.baidu.integration;

/* *
* 缓存基类 Guava Cache 实现
*  */

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class BaseBizGuavaCache<K, V> {

    // private Logger logger;
    /**
     * 最大缓存数量，当缓存key的数量达到该值时，会根据设置的过期规则清除容器中的数据
     */
    private long maximumSize = 10000;

    /**
     * 过期时间，缓存key值在一开始存入缓存中时开始计算时间，当存在时间到达该值时，自动清除
     */
    private Integer expireAfterWriteDuration = -1;
    /**
     * 自动刷新时间
     */
    private Integer refreshDuration = 5;
    /**
     * 设置时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MINUTES;
    /**
     * 定时刷新异步线程池
     */
    private static ListeningExecutorService refreshPool = MoreExecutors.
            listeningDecorator(Executors.newFixedThreadPool(20));
    /**
     * 重新获取数据的方法，如果获取数据异常，则返回原始数据
     */
    protected  abstract V getValueWhenExpired(K key) throws Exception;

    /**
     * 缓存实例
     */
    private volatile LoadingCache<K,V> cache;

    /**
     * 设置参数，过期或定时刷新机制
     */
    public LoadingCache<K,V> getCache(){
        if(cache == null){
            synchronized (this){
                if(cache == null){
                    CacheBuilder<Object,Object> cacheBuilder = CacheBuilder.newBuilder()
                            .maximumSize(maximumSize).recordStats();
                    //自动刷新
                    if(refreshDuration > 0){
                        cacheBuilder = cacheBuilder.refreshAfterWrite(refreshDuration,timeUnit);
                    }
                //   自动过期
                    if(expireAfterWriteDuration > 0 ){
                        cacheBuilder = cacheBuilder.expireAfterWrite(expireAfterWriteDuration,timeUnit);
                    }
                    cache = cacheBuilder.build(new CacheLoader<K, V>() {
                        @Override
                        public V load(K k) throws Exception {
                            System.out.println("过期加载缓存成功---------> key = "+k);
                            return getValueWhenExpired(k);
                        }
                        @Override
                        public ListenableFuture<V> reload(final K key,V oldValue) throws Exception{
                            return refreshPool.submit(new Callable<V>() {
                                @Override
                                public V call() throws Exception {
                                    System.out.println("定时刷新缓存成功-------> key = " + key);
                                    return getValueWhenExpired(key);
                                }
                            });
                        }
                    });

                }
            }
        }
        return cache;
    }


    /**
     * 获取缓存数据的方式
     */
    public V getValue(K key){
        try {
            return getCache().get(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 将数据存入缓存
     */
    public void putValue(K key, V value){
        try{
            getCache().put(key, value);
        }catch (Exception e){
            System.out.println("存入缓存异常，K = "+key+",Value = "+value+" 异常： "+e);
        }
    }
    /**
     * 清楚缓存中指定的数据
     */
    public void removeValue(K key){
        try{
            getCache().invalidate(key);
        }catch (Exception e){
            System.out.println("移除缓存异常，K = "+key+" 异常： "+e);
        }
    }
    /**
     * 清楚缓存中全部的数据
     */
    public void clearAllValue(){
        getCache().invalidateAll();
    }
    /**
     * 批量存入缓存
     */
    public void putAll(Map<K,V> mapValue){
        getCache().putAll(mapValue);
    }
}
