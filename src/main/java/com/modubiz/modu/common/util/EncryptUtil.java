package com.modubiz.modu.common.util;

import org.springframework.util.StopWatch;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;

public class EncryptUtil {

    private final static String CHARSET = Charset.defaultCharset().displayName();
    public final static int BASE64 = 0;
    public final static int HEXSTRING = 1;
    public final static int KMS = 2;

    /********************************************************/
    /* MD2, MD5, SHA256, SHA384, SHA512 ( Enc, Check )		*/

    /********************************************************/

    public static boolean checkMD2(String orgStr, String encStr, String charset) throws Exception {
        return encStr.equals(getMD2(orgStr, charset));
    }

    public static String getMD2(String str, String charset) throws Exception {
        return getEncrypt(str, "MD2", "", charset);
    }

    public static boolean checkMD2Salt(String orgStr, String encStr, String salt, String charset) throws Exception {
        return encStr.equals(getMD2Salt(orgStr, salt, charset));
    }

    public static String getMD2Salt(String str, String salt, String charset) throws Exception {
        return getEncrypt(str, "MD2", salt, charset);
    }

    public static boolean checkMD5(String orgStr, String encStr, String charset) throws Exception {
        return encStr.equals(getMD5(orgStr, charset));
    }

    public static String getMD5(String str, String charset) throws Exception {
        return getEncrypt(str, "MD5", "", charset);
    }

    public static String getMD5(String str) throws Exception {
        return getMD5(str, "UTF-8");
    }

    public static boolean checkMD5Salt(String orgStr, String encStr, String salt, String charset) throws Exception {
        return encStr.equals(getMD5Salt(orgStr, salt, charset));
    }

    public static String getMD5Salt(String str, String salt, String charset) throws Exception {
        return getEncrypt(str, "MD5", salt, charset);
    }

    public static boolean checkSHA1(String orgStr, String encStr, String charset) throws Exception {
        return encStr.equals(getSHA1(orgStr, charset));
    }

    public static String getSHA1(String str, String charset) throws Exception {
        return getEncrypt(str, "SHA-1", "", charset);
    }

    public static String getSHA1Salt(String str, String salt, String charset) throws Exception {
        return getEncrypt(str, "SHA-1", salt, charset);
    }

    public static boolean checkSHA1Salt(String orgStr, String encStr, String salt, String charset) throws Exception {
        return encStr.equals(getSHA1Salt(orgStr, salt, charset));
    }

    public static boolean checkSHA256(String orgStr, String encStr, String charset) throws Exception {
        return encStr.equals(getSHA256(orgStr, charset));
    }

    public static String getSHA256(String str, String charset) throws Exception {
        return getEncrypt(str, "SHA-256", "", charset);
    }    public static String getSHA256(String str) throws Exception {
        return getSHA256(str, "UTF-8");
    }



    public static String getSHA256Salt(String str, String salt, String charset) throws Exception {
        return getEncrypt(str, "SHA-256", salt, charset);
    }

    public static boolean checkSHA256Salt(String orgStr, String encStr, String salt, String charset) throws Exception {
        return encStr.equals(getSHA256Salt(orgStr, salt, charset));
    }

    public static boolean checkSHA384(String orgStr, String encStr, String charset) throws Exception {
        return encStr.equals(getSHA384(orgStr, charset));
    }

    public static String getSHA384(String str, String charset) throws Exception {
        return getEncrypt(str, "SHA-384", "", charset);
    }

    public static String getSHA384Salt(String str, String salt, String charset) throws Exception {
        return getEncrypt(str, "SHA-384", salt, charset);
    }

    public static boolean checkSHA384Salt(String orgStr, String encStr, String salt, String charset) throws Exception {
        return encStr.equals(getSHA384Salt(orgStr, salt, charset));
    }

    public static boolean checkSHA512(String orgStr, String encStr, String charset) throws Exception {
        return encStr.equals(getSHA512(orgStr, charset));
    }

    public static String getSHA512(String str, String charset) throws Exception {
        return getEncrypt(str, "SHA-512", "", charset);
    }

    public static String getSHA512(String str) throws Exception {
        return getSHA512(str, "UTF-8");
    }

    public static String getSHA512Salt(String str, String salt, String charset) throws Exception {
        return getEncrypt(str, "SHA-512", salt, charset);
    }

    public static boolean checkSHA512Salt(String orgStr, String encStr, String salt, String charset) throws Exception {
        return encStr.equals(getSHA512Salt(orgStr, salt, charset));
    }

    private static String getEncrypt(String str, String encType, String salt, String charset) throws Exception {
        if (charset == null) charset = CHARSET;
        String enc = null;
        try {
            MessageDigest md = MessageDigest.getInstance(encType);
            md.reset();
            md.update(salt.getBytes(charset));
            byte byteData[] = md.digest(str.getBytes(charset));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            enc = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            enc = null;
        }
        return enc;
    }

    /********************************************************/
    /* Aes128, Aes256, Des, TripleDes ( Enc, Dec )			*/

    /********************************************************/

    private static byte[] getArrayCopyByte(String strData, int iSize, String charset) throws Exception {
        if (strData == null) return null;
        final byte[] digestOfPassword = strData.getBytes(charset);
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, iSize);
        for (int j = 0, k = digestOfPassword.length; digestOfPassword.length + j < iSize; ) {
            keyBytes[k++] = keyBytes[j++];
        }
        return keyBytes;
    }

    private static Cipher getCipher(String strSecretKey, String ivParam, String cipherInstanceName, int cryptMode, String charset) throws Exception {
        SecretKey secureKey = null;
        int iv_len = 8;
        switch (cipherInstanceName.split("/")[0]) {
            case "AES":
                secureKey = new SecretKeySpec(strSecretKey.getBytes(charset), "AES");
                iv_len = 16;
                break;
            case "DES":
                DESKeySpec desKeySpec = new DESKeySpec(getArrayCopyByte(strSecretKey, 16, charset));
                secureKey = SecretKeyFactory.getInstance("DES").generateSecret(desKeySpec);
                break;
            case "DESede":
                DESedeKeySpec desedeKeySpec = new DESedeKeySpec(getArrayCopyByte(strSecretKey, 24, charset));
                secureKey = SecretKeyFactory.getInstance("DESede").generateSecret(desedeKeySpec);
                break;
        }

        IvParameterSpec iv = null;
        if (ivParam != null) iv = new IvParameterSpec(getArrayCopyByte(ivParam, iv_len, charset));
        Cipher cipher = Cipher.getInstance(cipherInstanceName);
        if (iv == null) {
            cipher.init(cryptMode, secureKey);
        } else {
            cipher.init(cryptMode, secureKey, iv);
        }
        return cipher;
    }

    private static String CipherEncrypt(String strData, String strSecretKey, String ivParam, String cipherInstanceName, String charset, int returnType) throws Exception {
        if (charset == null) charset = CHARSET;
        if (strSecretKey == null || strSecretKey.length() == 0 || strData == null || strData.length() == 0) return "";
        Cipher cipher = getCipher(strSecretKey, ivParam, cipherInstanceName, Cipher.ENCRYPT_MODE, charset);
        byte[] bytes = cipher.doFinal(strData.getBytes(charset));
        String rtnString = null;
        switch (returnType) {
            case EncryptUtil.BASE64:
                rtnString = Base64.getEncoder().encodeToString(bytes);
                break;
            case EncryptUtil.HEXSTRING:
                rtnString = ConvertUtil.BytesToHexString(bytes);
                break;
            case EncryptUtil.KMS:
                rtnString = ByteUtil.byteArrayToHex(bytes);
                break;
        }
        return rtnString;
    }

    private static String CipherDecrypt(String strData, String strSecretKey, String ivParam, String cipherInstanceName, String charset, int returnType) throws Exception {
        if (charset == null) charset = CHARSET;
        if (strSecretKey == null || strSecretKey.length() == 0 || strData == null || strData.length() == 0) return "";
        Cipher cipher = getCipher(strSecretKey, ivParam, cipherInstanceName, Cipher.DECRYPT_MODE, charset);
        byte[] decBtyes = null;
        switch (returnType) {
            case EncryptUtil.BASE64:
                decBtyes = Base64.getDecoder().decode(strData);
                break;
            case EncryptUtil.HEXSTRING:
                decBtyes = ConvertUtil.HexStringToBytes(strData);
                break;
            case EncryptUtil.KMS:
                decBtyes = ByteUtil.hexToByteArray(strData);
                break;
        }
        byte[] bytes = cipher.doFinal(decBtyes);
        return new String(bytes, charset);
    }


    // AES128
    public static String Aes128Encrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        strSecretKey = strSecretKey.substring(0, 16);
        return CipherEncrypt(strData, strSecretKey, ivParam, "AES/CBC/PKCS5Padding", charset, returnType);
    }

    public static String Aes128Decrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        strSecretKey = strSecretKey.substring(0, 16);
        return CipherDecrypt(strData, strSecretKey, ivParam, "AES/CBC/PKCS5Padding", charset, returnType);
    }

    public static boolean checkAes128_CBC(String orgStr, String encStr, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return encStr.equals(Aes128Encrypt_CBC(orgStr, strSecretKey, ivParam, charset, returnType));
    }

    public static String Aes128Encrypt_ECB(String strData, String strSecretKey, String charset, int returnType) throws Exception {
        return CipherEncrypt(strData, strSecretKey, null, "AES/ECB/PKCS5Padding", charset, returnType);
    }

    public static String Aes128Decrypt_ECB(String strData, String strSecretKey, String charset, int returnType) throws Exception {
        return CipherDecrypt(strData, strSecretKey, null, "AES/ECB/PKCS5Padding", charset, returnType);
    }

    public static boolean checkAes128_ECB(String orgStr, String encStr, String strSecretKey, String charset, int returnType) throws Exception {
        String ivParam = strSecretKey;
        return encStr.equals(Aes128Encrypt_CBC(orgStr, strSecretKey, ivParam, charset, returnType));
    }


    // AES256
    public static String Aes256Encrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        strSecretKey = strSecretKey.substring(0, 32);
        return CipherEncrypt(strData, strSecretKey, ivParam, "AES/CBC/PKCS5Padding", charset, returnType);
    }

    public static String Aes256Decrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        strSecretKey = strSecretKey.substring(0, 32);
        return CipherDecrypt(strData, strSecretKey, ivParam, "AES/CBC/PKCS5Padding", charset, returnType);
    }

    public static boolean checkAes256_CBC(String orgStr, String encStr, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return encStr.equals(Aes256Encrypt_CBC(orgStr, strSecretKey, ivParam, charset, returnType));
    }


    // DES_CBC
    public static String DesEncrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return CipherEncrypt(strData, strSecretKey, ivParam, "DES/CBC/PKCS5Padding", charset, returnType);
    }

    public static String DesDecrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return CipherDecrypt(strData, strSecretKey, ivParam, "DES/CBC/PKCS5Padding", charset, returnType);
    }

    public static boolean checkDes_CBC(String orgStr, String encStr, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return encStr.equals(DesEncrypt_CBC(orgStr, strSecretKey, ivParam, charset, returnType));
    }


    // DES_ECB
    public static String DesEncrypt_ECB(String strData, String strSecretKey, String charset, int returnType) throws Exception {
        return CipherEncrypt(strData, strSecretKey, null, "DES/ECB/PKCS5Padding", charset, returnType);
    }

    public static String DesDecrypt_ECB(String strData, String strSecretKey, String charset, int returnType) throws Exception {
        return CipherDecrypt(strData, strSecretKey, null, "DES/ECB/PKCS5Padding", charset, returnType);
    }

    public static boolean checkDes_ECB(String orgStr, String encStr, String strSecretKey, String charset, int returnType) throws Exception {
        return encStr.equals(DesEncrypt_ECB(orgStr, strSecretKey, charset, returnType));
    }


    // TripleDes_CBC
    public static String TripleDesEncrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return CipherEncrypt(strData, strSecretKey, ivParam, "DESede/CBC/PKCS5Padding", charset, returnType);
    }

    public static String TripleDesDecrypt_CBC(String strData, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return CipherDecrypt(strData, strSecretKey, ivParam, "DESede/CBC/PKCS5Padding", charset, returnType);
    }

    public static boolean checkTripleDes_CBC(String orgStr, String encStr, String strSecretKey, String ivParam, String charset, int returnType) throws Exception {
        return encStr.equals(TripleDesEncrypt_CBC(orgStr, strSecretKey, ivParam, charset, returnType));
    }


    // TripleDes_ECB
    public static String TripleDesEncrypt_ECB(String strData, String strSecretKey, String charset, int returnType) throws Exception {
        return CipherEncrypt(strData, strSecretKey, null, "DESede/ECB/PKCS5Padding", charset, returnType);
    }

    public static String TripleDesDecrypt_ECB(String strData, String strSecretKey, String charset, int returnType) throws Exception {
        return CipherDecrypt(strData, strSecretKey, null, "DESede/ECB/PKCS5Padding", charset, returnType);
    }

    public static boolean checkTripleDes_ECB(String orgStr, String encStr, String strSecretKey, String charset, int returnType) throws Exception {
        return encStr.equals(TripleDesEncrypt_ECB(orgStr, strSecretKey, charset, returnType));
    }

    // ============================================================================================


    private static void StopWatchLog(StopWatch sw, String msg) {
        sw.stop();
        System.out.println(">>>>> watch : " + sw.getLastTaskTimeMillis() + " ms");
        if (msg != null) sw.start(msg);
    }


    @SuppressWarnings("unused")
    private static void main(String[] args) throws Exception {

        final String CHARSET = null;

        final int RetrunType = EncryptUtil.BASE64;

        StopWatch sw = new StopWatch();
        sw.start("initializing");

        System.out.println("\n=================== Init Data ====================");
        System.out.println("Default Charset \t :: " + Charset.defaultCharset().displayName());
        String data = MessageFormat.format("SURFGOLD{0}COUPONSEND{1}{2}{3}{4}", "127.0.0.1", "2017121223595900001", "1350", "55680", "20171212ㄱ");
        String salt = "test";
        System.out.println("Data\t\t\t :: " + data);
        System.out.println("SaltKey\t\t\t :: " + salt);

        System.out.println("\n=================== MD2, MD5, SHA256, SHA256-Salt ====================");
        StopWatchLog(sw, "getMD2");
        String md2String = getMD2(data, CHARSET);
        System.out.println("MD2\t\t\t :: " + md2String);
        System.out.println("MD2 Check\t\t :: " + checkMD2(data, md2String, CHARSET));
        String md2SaltString = getMD2Salt(data, salt, CHARSET);
        System.out.println("MD2 Salt\t\t :: " + md2SaltString);
        System.out.println("MD2 Salt Check\t\t :: " + checkMD2Salt(data, md2SaltString, salt, CHARSET));

        StopWatchLog(sw, "getMD5");
        String md5String = getMD5(data, CHARSET);
        System.out.println("MD5\t\t\t :: " + md5String);
        System.out.println("MD5 Check\t\t :: " + checkMD5(data, md5String, CHARSET));
        String md5SaltString = getMD5Salt(data, salt, CHARSET);
        System.out.println("MD5 Salt\t\t :: " + md5SaltString);
        System.out.println("MD5 Salt Check\t\t :: " + checkMD5Salt(data, md5SaltString, salt, CHARSET));

        StopWatchLog(sw, "getSHA1");
        String sha1String = getSHA1(data, CHARSET);
        System.out.println("SHA1\t\t\t :: " + sha1String);
        System.out.println("SHA1 Check\t\t :: " + checkSHA1(data, sha1String, CHARSET));
        String sha1SaltString = getSHA1Salt(data, salt, CHARSET);
        System.out.println("SHA1 Salt\t\t :: " + sha1SaltString);
        System.out.println("SHA1 Salt Check\t\t :: " + checkSHA1Salt(data, sha1SaltString, salt, CHARSET));

        StopWatchLog(sw, "getSHA256");
        String sha256String = getSHA256(data, CHARSET);
        System.out.println("SHA256\t\t\t :: " + sha256String);
        System.out.println("SHA256 Check\t\t :: " + checkSHA256(data, sha256String, CHARSET));
        String sha256SaltString = getSHA256Salt(data, salt, CHARSET);
        System.out.println("SHA256 Salt\t\t :: " + sha256SaltString);
        System.out.println("SHA256 Salt Check\t :: " + checkSHA256Salt(data, sha256SaltString, salt, CHARSET));

        StopWatchLog(sw, "getSHA384");
        String sha384String = getSHA384(data, CHARSET);
        System.out.println("SHA384\t\t\t :: " + sha384String);
        System.out.println("SHA384 Check\t\t :: " + checkSHA384(data, sha384String, CHARSET));
        String sha384SaltString = getSHA384Salt(data, salt, CHARSET);
        System.out.println("SHA384 Salt\t\t :: " + sha384SaltString);
        System.out.println("SHA384 Salt Check\t :: " + checkSHA384Salt(data, sha384SaltString, salt, CHARSET));

        StopWatchLog(sw, "getSHA512");
        String sha512String = getSHA512(data, CHARSET);
        System.out.println("SHA512\t\t\t :: " + sha512String);
        System.out.println("SHA512 Check\t\t :: " + checkSHA512(data, sha512String, CHARSET));
        String sha512SaltString = getSHA512Salt(data, salt, CHARSET);
        System.out.println("SHA512 Salt\t\t :: " + sha512SaltString);
        System.out.println("SHA512 Salt Check\t :: " + checkSHA512Salt(data, sha512SaltString, salt, CHARSET));

        System.out.println("\n=================== Aes Encrypt Decrypt ====================");
        data = "abcdef ABCDEF ㄱㄴㄷㄹㅁㅂㅅ 가나다라마바사 ㅏㅑㅓㅕㅗㅕ";

        String strSecretKey = "12345678901234567890123456789012";
        System.out.println("Aes SecretKey\t\t :: " + strSecretKey);
        String ivParam = "DEP11.,/";
        System.out.println("Aes ivParam\t\t :: " + ivParam);

        data = "0553137913";
        ivParam = "";

        // tested site = https://www.devglan.com/online-tools/aes-encryption-decryption
        StopWatchLog(sw, "Aes128Encrypt_CBC");
        String AesEncString = Aes128Encrypt_CBC(data, strSecretKey, ivParam, CHARSET, RetrunType);
        System.out.println("Aes128 CBC Encrypt\t\t :: " + AesEncString);
        String AesDecString = Aes128Decrypt_CBC(AesEncString, strSecretKey, ivParam, CHARSET, RetrunType);
        System.out.println("Aes128 CBC Decrypt\t\t :: " + AesDecString);
        System.out.println("Aes128 CBC Check\t\t :: " + checkAes128_CBC(data, AesEncString, strSecretKey, ivParam, CHARSET, RetrunType));

        StopWatchLog(sw, "Aes256Encrypt_CBC");
        String AesEncStringE = Aes256Encrypt_CBC(data, strSecretKey, ivParam, CHARSET, RetrunType);
        System.out.println("Aes256 CBC Encrypt\t\t :: " + AesEncStringE);
        String AesDecStringE = Aes256Decrypt_CBC(AesEncStringE, strSecretKey, ivParam, CHARSET, RetrunType);
        System.out.println("Aes256 CBC Decrypt\t\t :: " + AesDecStringE);
        System.out.println("Aes256 CBC Check\t\t :: " + checkAes256_CBC(data, AesEncStringE, strSecretKey, ivParam, CHARSET, RetrunType));

        System.out.println("\n=================== Des Encrypt Decrypt ====================");
        data = "abcdef ABCDEF ㄱㄴㄷㄹㅁㅂㅅ 가나다라마바사 ㅏㅑㅓㅕㅗㅕ";

        String strSecretKey1 = "akdlzmfhthvmxmro";
        System.out.println("Des SecretKey\t\t :: " + strSecretKey1);
        String ivParam2 = "DEP11.,/";
        System.out.println("Des ivParam\t\t :: " + ivParam2);

        StopWatchLog(sw, "DesEncrypt_CBC");
        String desEncString = DesEncrypt_CBC(data, strSecretKey1, ivParam2, CHARSET, RetrunType);
        System.out.println("Des CBC Encrypt\t\t :: " + desEncString);
        String desDecString = DesDecrypt_CBC(desEncString, strSecretKey1, ivParam2, CHARSET, RetrunType);
        System.out.println("Des CBC Decrypt\t\t :: " + desDecString);
        System.out.println("Des CBC Check\t\t :: " + checkDes_CBC(data, desEncString, strSecretKey1, ivParam2, CHARSET, RetrunType));

        StopWatchLog(sw, "DesEncrypt_ECB");
        String desEncStringE = DesEncrypt_ECB(data, strSecretKey1, CHARSET, RetrunType);
        System.out.println("Des ECB Encrypt\t\t :: " + desEncStringE);
        String desDecStringE = DesDecrypt_ECB(desEncStringE, strSecretKey1, CHARSET, RetrunType);
        System.out.println("Des ECB Decrypt\t\t :: " + desDecStringE);
        System.out.println("Des CBC Check\t\t :: " + checkDes_ECB(data, desEncStringE, strSecretKey1, CHARSET, RetrunType));

        System.out.println("\n=================== TripleDes Encrypt Decrypt ====================");
        data = "tkfkdgoyo";

        String strSecretKey2 = "akdlzmfhthvmxmro";
        System.out.println("TripleDes SecretKey\t :: " + strSecretKey2);
        String ivParam3 = "DEP11.,/";
        System.out.println("TripleDes ivParam\t :: " + ivParam3);

        StopWatchLog(sw, "TripleDesEncrypt_CBC");
        String desEncString2 = TripleDesEncrypt_CBC(data, strSecretKey2, ivParam3, CHARSET, RetrunType);
        System.out.println("TripleDes CBC Encrypt\t :: " + desEncString2);
//		desEncString2 = "GP6wskmaXXOGJEKUFpfoLQ==";
        String desDecString2 = TripleDesDecrypt_CBC(desEncString2, strSecretKey2, ivParam3, CHARSET, RetrunType);
        System.out.println("TripleDes CBC Decrypt\t :: " + desDecString2);
        System.out.println("TripleDes CBC Check\t :: " + checkTripleDes_CBC(data, desEncString2, strSecretKey2, ivParam3, CHARSET, RetrunType));

        StopWatchLog(sw, "TripleDesEncrypt_ECB");
        String desEncStringE2 = TripleDesEncrypt_ECB(data, strSecretKey2, CHARSET, RetrunType);
        System.out.println("TripleDes ECB Encrypt\t :: " + desEncStringE2);
        String desDecStringE2 = TripleDesDecrypt_ECB(desEncStringE2, strSecretKey2, CHARSET, RetrunType);
        System.out.println("TripleDes ECB Decrypt\t :: " + desDecStringE2);
        System.out.println("TripleDes ECB Check\t :: " + checkTripleDes_ECB(data, desEncStringE2, strSecretKey2, CHARSET, EncryptUtil.BASE64));


        StopWatchLog(sw, null);
        System.out.println();
        System.out.println();
        System.out.println(sw.prettyPrint());
    }


}