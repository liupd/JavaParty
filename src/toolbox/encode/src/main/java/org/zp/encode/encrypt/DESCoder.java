package org.zp.encode.encrypt;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @Title DESCoder
 * @Description DES安全编码：是经典的对称加密算法。密钥仅56位，且迭代次数偏少。已被视为并不安全的加密算法。
 * @Author victor zhang
 * @Date 2016年7月14日
 */
public class DESCoder {
    public static final String KEY_ALGORITHM_DES = "DES";
    public static final String CIPHER_DES_DEFAULT = "DES";
    public static final String CIPHER_DES_ECB_PKCS5PADDING = "DES/ECB/PKCS5Padding"; // 算法/模式/补码方式
    public static final String CIPHER_DES_CBC_PKCS5PADDING = "DES/CBC/PKCS5Padding";
    public static final String CIPHER_DES_CBC_NOPADDING = "DES/CBC/NoPadding";
    private static final String SEED = "%%%today is nice***"; // 用于生成随机数的种子

    private Key key;
    private Cipher cipher;
    private String transformation;

    public DESCoder() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        this.key = initKey();
        this.cipher = Cipher.getInstance(CIPHER_DES_DEFAULT);
        this.transformation = CIPHER_DES_DEFAULT;
    }

    public DESCoder(String transformation)
            throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        this.key = initKey();
        this.cipher = Cipher.getInstance(transformation);
        this.transformation = transformation;
    }

    /**
     * @Title decrypt
     * @Description 解密
     * @Author victor zhang
     * @Date 2016年7月20日
     * @param input 密文
     * @return byte[] 明文
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public byte[] decrypt(byte[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        if (transformation.equals(CIPHER_DES_CBC_PKCS5PADDING) || transformation.equals(CIPHER_DES_CBC_NOPADDING)) {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV()));
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        return cipher.doFinal(input);
    }

    /**
     * @Title encrypt
     * @Description 加密
     * @Author victor zhang
     * @Date 2016年7月20日
     * @param input 明文
     * @return byte[] 密文
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public byte[] encrypt(byte[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        if (transformation.equals(CIPHER_DES_CBC_PKCS5PADDING) || transformation.equals(CIPHER_DES_CBC_NOPADDING)) {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getIV()));
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        return cipher.doFinal(input);
    }

    /**
     * @Title initKey
     * @Description 根据随机数种子生成一个密钥
     * @Author victor zhang
     * @Date 2016年7月14日
     * @Return Key
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    private Key initKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        // 根据种子生成一个安全的随机数
        SecureRandom secureRandom = null;
        secureRandom = new SecureRandom(SEED.getBytes());

        KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM_DES);
        keyGen.init(secureRandom);
        return keyGen.generateKey();
    }

    private byte[] getIV() {
        String iv = "01234567"; // IV length: must be 8 bytes long
        return iv.getBytes();
    }

    public static void main(String[] args) throws Exception {
        DESCoder aes = new DESCoder(CIPHER_DES_CBC_PKCS5PADDING);

        String msg = "Hello World!";
        System.out.println("原文: " + msg);
        byte[] encoded = aes.encrypt(msg.getBytes("UTF8"));
        String encodedBase64 = Base64.toBase64String(encoded);
        System.out.println("密文: " + encodedBase64);

        byte[] decodedBase64 = Base64.decode(encodedBase64);
        byte[] decoded = aes.decrypt(decodedBase64);
        System.out.println("明文: " + new String(decoded));
    }
}
