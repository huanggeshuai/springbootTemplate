package com.huang.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @ClassName Base64Utils
 * @Description base64编码解码工具
 * @auther huang
 * @create 2019-07-22 16:29
 */
public class Base64Utils {
    /**
     * 使用base64解码
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * 使用base64编码
     * @param bytes
     * @return
     */
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
}
