package com.weds.site.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.weds.core.utils.coder.Coder;

public class ZipUtils {

	/*
	 * 
	 * 将一张网络图片转化为base64字符串
	 */
	public static String GetImageStrFromUrl(String imgURL) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			// 创建url
			URL url = new URL(imgURL);
			// 创建链接
			byte[] by = new byte[1024];
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);

			int imageLegth = conn.getContentLength();
			if (imageLegth < 51200) {
				InputStream is = conn.getInputStream();
				// 将内容读取到内存中
				int len = -1;
				while ((len = is.read(by)) != -1) {
					data.write(by, 0, len);
				}
				// 关闭流
				is.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组base64编码
		return Coder.encryptBASE64(data.toByteArray());
	}

	public static String img2Base64(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return Base64.encodeBase64String(IOUtils.toByteArray(fis));
	}

	/*
	 * 使用gzip进行压缩
	 */
	public static String gzip(String primstr) {
		if (primstr == null || primstr.length() == 0) {
			return primstr;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primstr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		return Coder.encryptBASE64(out.toByteArray());
	}

	/**
	 * 加密方法
	 * 
	 * @param content
	 *            要加密的字符串
	 * @throws Exception
	 * 
	 */
	public static String encrypt(String content, String keyBytes) throws Exception {
		String encryptedText = "";
		SecretKeySpec skeySpec = getKey(keyBytes);
		IvParameterSpec iv = new IvParameterSpec(keyBytes.getBytes());
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			encryptedText = Coder.encryptBASE64(cipher.doFinal(keyBytes.getBytes("utf-8")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return encryptedText;
		// return AESCoder.encrypt(content, keyBytes, keyBytes);
	}

	private static SecretKeySpec getKey(String strKey) throws Exception {
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

		return skeySpec;
	}

}
