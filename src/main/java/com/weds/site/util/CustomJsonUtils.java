package com.weds.site.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weds.core.utils.FileUtils;
import com.weds.core.utils.StringUtils;
import com.weds.core.utils.coder.AES7Coder;
import com.weds.core.utils.coder.Coder;
import com.weds.site.annotation.ColumnProperties;

public class CustomJsonUtils {

	public static String parseObjToJson(Object obj, String key, String imageRoot, String imageType) throws Exception {
		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
		builder.excludeFieldsWithModifiers(Modifier.PROTECTED);
		Gson gson = builder.create();
		encryptObject(obj, key, imageRoot, imageType);
		return gson.toJson(obj);
	}

	private static void encryptObject(Object obj, String key, String imageRoot, String imageType) throws Exception {
		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			boolean flag = field.isAnnotationPresent(ColumnProperties.class);
			if (!flag) {
				continue;
			}
			ColumnProperties columnProperties = field.getAnnotation(ColumnProperties.class);
			if (columnProperties.aes()) {
				field.setAccessible(true);
				Object o = field.get(obj);
				if (o != null) {
					if (o instanceof List) {
						List list = (List) o;
						for (Object o1 : list) {
							encryptObject(o1, key, imageRoot, imageType);
						}
					} else {
						field.set(obj, AES7Coder.encrypt(o.toString(), key));
					}
				}
			}

			if (columnProperties.image()) {
				field.setAccessible(true);
				Object o = field.get(obj);
				if (o != null) {
					if (o instanceof List) {
						List list = (List) o;
						for (Object o1 : list) {
							encryptObject(o1, key, imageRoot, imageType);
						}
					} else {
						field.set(obj, "");
						if (!StringUtils.isBlank(o.toString())) {
							String imagePath = imageRoot + File.separator + o.toString().substring(2);
							File imageFile = new File(imagePath);
							if (imageFile.exists() && imageFile.isFile()) {
								String imageBase64 = imageType
										+ Coder.encryptBASE64(FileUtils.readFileToByteArray(imageFile));
								field.set(obj, imageBase64);
							}
						}
					}
				}
			}
		}
	}

	public static void decryptObject(Object obj, String key) throws Exception {
		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			boolean flag = field.isAnnotationPresent(ColumnProperties.class);
			if (!flag) {
				continue;
			}
			ColumnProperties columnProperties = field.getAnnotation(ColumnProperties.class);
			if (columnProperties.aes()) {
				field.setAccessible(true);
				Object o = field.get(obj);
				if (o != null) {
					if (o instanceof List) {
						List list = (List) o;
						for (Object o1 : list) {
							decryptObject(o1, key);
						}
					} else {
						if (!StringUtils.isBlank(o.toString())) {
							field.set(obj, AES7Coder.decrypt(o.toString(), key));
						}
					}
				}
			}
		}
	}
}
