package com.niudi.security;


import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author Zhangyz
 * @description
 * @date 2019/9/24
 */
public class RSASignature {


    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String ENCODE_ALGORITHM = "SHA-256";


    public static String sign(String data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);

        return Base64.encode(sign(privateK, data));
    }

    /**
     * 签名
     *
     * @param privateKey 私钥
     * @param plain_text 明文
     * @return
     */
    private static byte[] sign(PrivateKey privateKey, String plain_text) {
        MessageDigest messageDigest;
        byte[] signed = null;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            try {
                messageDigest.update(plain_text.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
            byte[] outputDigest_sign = messageDigest.digest();
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(outputDigest_sign);
            signed = Sign.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
        }
        return signed;
    }

    /**
     * bytes[]换成16进制字符串
     *
     * @param src
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
