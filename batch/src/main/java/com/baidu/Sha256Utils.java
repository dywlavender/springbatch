package com.baidu;


// import com.alibaba.druid.sql.visitor.functions.Hex;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Sha2Crypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Utils {
    public static String String2SHA256(String str){
        MessageDigest messageDigest;
        String encode = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encode = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return encode;
    }

    public static void main(String[] args) {
        String str = "2";
        System.out.println(Sha256Utils.String2SHA256(str));
    }
}
