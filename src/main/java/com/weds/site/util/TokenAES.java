package com.weds.site.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ngh
 * AES128 算法
 *
 * CBC 模式
 *
 * PKCS7Padding 填充模式
 *
 * CBC模式需要添加一个参数iv
 *
 * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
 * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
 */
public class TokenAES {  /**
     * 
     * 使用gzip进行压缩
     */
    public static byte[] gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return null;
        }
 
 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
 
 
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return out.toByteArray();
    }
 
 
    /**
     *
     * <p>
     * Description:使用gzip进行解压缩
     * </p>
     * 
     * @param compressedStr
     * @return
     */
    public static String gunzip(byte[] compressedStr) {
        if (compressedStr == null) {
            return null;
        }
 
 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = compressedStr;
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
 
 
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
 
 
        return decompressed;
    }
    /**
	  * 加密方法
	  *
	  * @param content
	  *            要加密的字符串
	  * @param keyBytes
	  *            加密密钥
	  * @return
	 * @throws UnsupportedEncodingException 
	  */
	 public static String encrypt(byte[]  content, String keyBytes) {
		  String  encryptedText = "";
		  init(keyBytes.getBytes());
		  try {
			   cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			   encryptedText = (new BASE64Encoder()).encode(cipher.doFinal(content)); 
		  } catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  }
		  return encryptedText;
	 }
	 /**
	  * 解密方法
	  *
	  * @param encryptedData
	  *            要解密的字符串
	  * @param keyBytes
	  *            解密密钥
	  * @return
	 * @throws UnsupportedEncodingException 
	  */
	 public static byte[] decrypt(String Data, String keyBytes)  {
		  byte[] encryptedText = null;
		  init(keyBytes.getBytes());
		  
		  try {
			   cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			   byte[] encryptedData =  (new BASE64Decoder()).decodeBuffer(Data);
			   encryptedText = cipher.doFinal(encryptedData);
		  } catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  }
		  return encryptedText;
	 }
	 static final String KEY_ALGORITHM = "AES";
	 // 加解密算法/模式/填充方式
	 static final String algorithmStr = "AES/CBC/PKCS7Padding";
	 //
	 static private Key key;
	 static private Cipher cipher;
	 static boolean isInited = false;
	 
	 static byte[] iv ;
	
	 public static void  init(byte[] keyBytes) {

		  // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
		  int base = 16;
		  if (keyBytes.length % base != 0) {
			   int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
			   byte[] temp = new byte[groups * base];
			   Arrays.fill(temp, (byte) 0);
			   System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
			   keyBytes = temp;
			   iv = keyBytes;
		  }
		  
		  // 初始化
		  Security.addProvider(new BouncyCastleProvider());
		  // 转化成JAVA的密钥格式
		  key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		  try {
			   // 初始化cipher
			   cipher = Cipher.getInstance(algorithmStr, "BC");//BC库 国密加密
		  } catch (NoSuchAlgorithmException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  } catch (NoSuchPaddingException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  } catch (NoSuchProviderException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		  }
	 }}