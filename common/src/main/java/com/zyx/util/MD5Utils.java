//package com.zyx.util;
//
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.apache.shiro.util.ByteSource;
//
///**
// * Created by YuXingZh on 19-3-9
// * MD5工具类
// */
//public class MD5Utils {
//    private static final String SALT = "mrbird";
//
//    private static final String ALGORITH_NAME = "md5";
//
//    private static final int HASH_ITERATIONS = 2;
//
//    /**
//     * 使用密码加密
//     * @param password
//     * @return
//     */
//    public static String encrypt(String password) {
//        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
//        return newPassword;
//    }
//
//    /**
//     * 使用用户名和密码加密
//     * @param username
//     * @param password
//     * @return
//     */
//    public static String encrypt(String username, String password) {
//        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + SALT),
//                HASH_ITERATIONS).toHex();
//        return newPassword;
//    }
//    public static void main(String[] args) {
//        System.out.println(MD5Utils.encrypt("YuXingZh", "zhengyuxing"));
//    }
//
//}
//
