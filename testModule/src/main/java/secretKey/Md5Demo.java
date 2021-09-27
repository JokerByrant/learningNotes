package secretKey;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 散列算法1 ---> Md5加密
 * @author sxh
 * @date 2021/9/27
 */
public class Md5Demo {
    private static final char[] HEX_CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

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
        char[] chars = encodeHex(digest);
        return new String(chars);
    }

    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String content = "Hello world!";
        System.out.println(encode(content, "MD5"));
        // 直接调用org.apache.commons.codec包中的Md5工具类
        System.out.println(DigestUtils.md5Hex(content));
    }
}
