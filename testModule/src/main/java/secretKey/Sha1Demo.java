package secretKey;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 散列算法2 ---> SHA1加密
 * @author sxh
 * @date 2021/9/27
 */
public class Sha1Demo {
    /**
     * 利用指定加密算法，将content加密，返回加密后的摘要字符
     * @param content 内容
     * @param algorithm 加密类型
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String encode(String content, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] digest = messageDigest.digest(content.getBytes());
        return encodeHex(digest);
    }

    private static String encodeHex(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (byte aByte : bytes) {
            int val = ((int) aByte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String content = "Hello world!";
        System.out.println(encode(content, "SHA1"));
        // 直接调用org.apache.commons.codec包中的SHA1工具类
        System.out.println(DigestUtils.sha1Hex(content));
    }
}
