//package com.baidu.batch.ant;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class AntWsdBasicInfoApplyStep extends SpecialSyncTransService<AntBasicInfoWsdApplyCheck,BaseRes>{
//    @Autowired
//    private TransLimitApplyMapper transLimitApplyMapper;
//    @Autowired
//    private TransLimitWsdBasicInfoMapper transLimitWsdBasicInfoMapper;
//    public void step(FsgContext context){
////        对授信单号判断是否存在，不存在直接抛异常
//        String applyNo = context.getString(APPLY_NO);
//        if(applyNo.isEmpty()){
//            context.setData(RETPY,"N");
//            throw new BaseException("0002",PARAM_EMPTY);
//        }
////         进件申请信息表实体类
//        TransLimitApply transLimitApply = new TransLimitApply();
////        网商贷初审信息表实体类
//        TransLimitWsdBasicInfo transLimitWsdBasicInfo = new TransLimitWsdBasicInfo();
//        try{
////            将context数据赋值给实体类
//            setLimitApplyEntity(transLimitApply,context);
////            调用mapper执行 入库主表 操作
//            transLimitApplyMapper.saveApplyInfo(transLimitApply);
////            入库初审信息表，设置初审信息表数据
//            setTransLimitWsdBasic(transLimitWsdBasicInfo,context);
//            transLimitWsdBasicInfoMapper.insert(transLimitWsdBasicInfo);
//        }catch(Exception e){
//            context.setData(RETRY,"N");
//            throw new BaseException("0002",PARAM_EMPTY);
//
//        }
////        生成唯一流水号：当前时间+UUID生成随机数
//        String serialNumber = AccountingEntryUtil.getSerialNumber();
////        保存流水号
//        context.setData("seqNo",serialNumber);
////        网贷个性化数据组装
//        Map extInfo = JSON.parseObject((String) context.getData("extInfo"),Map.class);
//        context.setDataMap(extInfo);
//        context.setTransCode(Constants.TransCodeCanstants.ANT_WSD_BASIC_INFO_APPLY);
//        modifyReqContext(transLimitWsdBasicInfo,context);
//    }
//}
