package com.baidu;

import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName DeilFile
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/15 22:37
 * @Version 1.0
 **/
public class DeilFile {
    public ArrayList<String> getDataFromFile(String filePath){
        isFile(filePath);
        BufferedReader reader = null;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null){
                arrayList.add(tempString);
            }
            return arrayList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void saveDataToFile(String savePath,ArrayList<String > contentLit){
        BufferedWriter writer = null;
        try {
            System.out.println("文件写入开始！！！");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath,true),"UTF-8"));
            for (int i = 0; i < contentLit.size(); i++) {
                writer.write(contentLit.get(i) + "\r\n");
            }
            System.out.println("文件写入完成!!!");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 创建文件夹
     * @param folderPath 文件夹路径
     */
    public static void createDirectory(String folderPath){
        File folder = new File(folderPath);
        if(!(folder.exists() && folder.isDirectory())){
            folder.mkdirs();
            System.out.println("文件夹不存在，创建文件夹");
        }else {
            System.out.println("文件夹已存在");
        }
    }
    /**
     * 创建文件
     * @param filePath 文件路径
     */
    public static void createFile(String filePath){
        File folder = new File(filePath);
        if(!(folder.exists() && folder.isFile())){
            try {
                folder.createNewFile();
                System.out.println("文件不存在，创建文件");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println("文件已存在");
        }
    }
    /**
     * 是否是文件
     * @param filePath 文件路径
     */
    public static void isFile(String filePath){
        File folder = new File(filePath);
        if(!(folder.exists() && folder.isFile())){
            throw new RuntimeException("文件不存在，创建文件");
        }else {
            System.out.println("文件路径真实存在");
        }
    }
}
