package com.example.managesystem.utils;

import cn.hutool.core.codec.Base64;

public class Base64Util {

    public static void main(String[] args) {
//        String params = "{\"userId\":123456,\"accountName\": \"XXXXXXX\",\"role\": \"admin\"}";
        String params = "{\"userId\":123456,\"accountName\": \"XXXXXXX\",\"role\": \"user\"}";
        //加密
        String encode = Base64.encode(params);
        System.out.println("encode== " + encode);
        //解密
        System.out.println("decode== " + Base64.decodeStr(encode));
    }

}
