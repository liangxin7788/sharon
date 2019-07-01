package com.fun.business.sharon.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Package: com.fun.business.sharon.utils
 * @ClassName: Md5Util
 * @Description: MD5加密工具类
 * @Author: liangxin
 * @CreateDate: 2019/6/20 9:58
 * @UpdateDate: 2019/6/20 9:58
 */
public class Md5Util {

    /**
     * 普通生成32位md5码
     *
     * @param dest
     * @return
     */
    public static String md5(String dest) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(dest.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算，当byte要转化为int的时候，高的24位必然会补1，这样，其二进制补码其实已经不一致了，
                // & 0xff可以将高的24位置为0，低8位保持原样。这样做的目的就是为了保证二进制数据的一致性。
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            try {
                throw new NoSuchAlgorithmException();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 自定义盐值加密(此处原意设计为用户id为盐值，再与输入密码进行加密保存)
     *
     * @param password 原样密码
     * @param salt     自定义盐值
     * @return
     */
    public static String generate(String password, String salt) {
        String saltHex = md5Hex(salt);
        password = md5Hex(password + saltHex);
        return new String(password);
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

}
