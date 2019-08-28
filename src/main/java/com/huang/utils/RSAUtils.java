package com.huang.utils;

import com.google.common.collect.Maps;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * @author huang
 * @Classname RSAUtils
 * @Description RSA非对称加密 非对称加密分为publicKey和privateKey 和druid一样的 druid就是一种非对称加密 就是RSA加密
 * @Date 2019/7/2 20:41
 * @Created by huang
 */
public class RSAUtils {

    /** 加密算法名称 */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 密钥长度 默认1024 必须是64的整数倍 在512--65536之间
     */
    private static final Integer KEY_LENGTH = 1024;

    /**
     * 自定义public_key
     */
    private static final String PUBLIC_KEY = "publicKey";

    /**
     * 自定义private_key
     */
    private static final String PRIVATE_KEY = "privateKey";

    /**
     * 加密算法签名 防止被篡改
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 初始化key集合
     * @return
     */
    public static Map<String, Object> initKey(){
        try {
            //初始化key生成器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_LENGTH);
            //生成密钥对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //获取rsaPublicKey
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            //获取rsaPrivateKey
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keys = Maps.newHashMap();
            keys.put(PUBLIC_KEY, publicKey);
            keys.put(PRIVATE_KEY, privateKey);
            return keys;

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * privateKey加密
     * @param data 数据
     * @param key 私钥 pkcs8格式
     * @return
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key){
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
        try {
            //实例化密钥factory
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //获取私有key
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            //设置加密模式
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        } catch (InvalidKeyException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (IllegalBlockSizeException e) {
            return null;
        }
    }

    /**
     * publicKey加密
     * @param data 数据
     * @param key 公钥 509
     * @return
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key){
        try {
            //实列化keyFactory
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //key转换
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
            //获取publicKey
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            //设置加密模式
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        } catch (InvalidKeyException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (IllegalBlockSizeException e) {
            return null;
        }
    }

    /**
     * publicKey解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key){
        try {
            //实列化keyFactory
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //key转换
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
            //获取publicKey
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            //设置解密模式
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        } catch (InvalidKeyException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (IllegalBlockSizeException e) {
            return null;
        }
    }
    /**
     * privateKey解密
     * @param data 数据
     * @param key 私钥
     * @return
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key){
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
        try {
            //实例化密钥factory
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //获取私有key
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            //设置加密模式
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        } catch (InvalidKeyException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (IllegalBlockSizeException e) {
            return null;
        }
    }

    /**
     * 获取公钥
     * @param keyMap
     * @return
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap){
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 获取私钥
     * @param keyMap
     * @return
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap){
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }


    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = Base64Utils.decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(Base64Utils.decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // 对密钥解密
        byte[] keyBytes = Base64Utils.decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    //将pkcs1密钥转成pkcs8密钥
    public static String pkcs1ConvertPkcs8(String rawPublicKey) throws Exception {
        String result = null;
        if(!StringUtils.isEmpty(rawPublicKey)){
            byte[] encodeByte = Base64Utils.decryptBASE64(rawPublicKey);
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag);
            ASN1Object asn1Object = ASN1Object.fromByteArray(encodeByte);
            PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, asn1Object);
            byte[] pkcs8Bytes = privateKeyInfo.getDEREncoded();
        }
        return result;
    }


    public static void main(String[] args) {
        Map map = initKey();
        String msg = "huanggeshuai666";
        byte[] publicKey = getPublicKey(map);
        byte[] privateKey = getPrivateKey(map);
        System.out.println("公有:"+Base64Utils.encryptBASE64(publicKey));
        System.out.println("私有:"+Base64Utils.encryptBASE64(privateKey));
        byte[] data = encryptByPrivateKey(msg.getBytes(),privateKey);
        System.out.println("私钥加密数据:"+Base64Utils.encryptBASE64(data));
        byte[] data1 = decryptByPublicKey(data,publicKey);
        System.out.println("公钥还原数据:"+new String(data1));
        byte[] data2 = encryptByPublicKey(msg.getBytes(),publicKey);
        System.out.println("公钥加密数据:"+Base64Utils.encryptBASE64(data2));
        byte[] data3 = decryptByPrivateKey(data2,privateKey);
        System.out.println("私钥还原数据:"+new String(data3));

    }
}
