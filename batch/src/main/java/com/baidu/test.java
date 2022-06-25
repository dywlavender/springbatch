package com.baidu;


import lombok.Data;
import lombok.Getter;
import lombok.val;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class test  {

    public static void main(String[] args) {
        String st = "12.2";
        System.out.println(Float.parseFloat(st));
            // System.out.println(Collections.singleton(WYDBizFileEnum.WYD_ZIP_FILE_OK.getFileType()));
        // List<String> list = new ArrayList<>();
        // list.add("12");
        // list.add("42");
        // list.add("42");
        // list.add("22");
        // String want = "42";
        // String find = list.stream().filter(x -> want.equals(x)).findFirst().orElse(null);
        // System.out.println(find);
    }
}
@Getter
enum WYDBizFileEnum {
    WYD_ZIP_FILE_OK("微业贷ok文件","EAST","wyd_zip_file_ok",""),
    WYD_ZIP_IMG_FILE("影像文件","IMG","wyd_zip_img_file","");
    private String desc;
    private String fileFolder;
    private String fileType;
    private String tableName;
    WYDBizFileEnum(String desc,String fileFolder,String fileType,String tableName){
        this.desc = desc;
        this.fileFolder = fileFolder;
        this.fileType = fileType;

        this.tableName = tableName;
    }
    public static List<String> getToDataFileTypeList(){
        return Arrays.stream(WYDBizFileEnum.values())
                .filter(x -> !WYD_ZIP_FILE_OK.equals(x))
                .map(WYDBizFileEnum::getFileType)
                .collect(Collectors.toList());
    }
}
