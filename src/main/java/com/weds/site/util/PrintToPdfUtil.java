package com.weds.site.util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 将多张图片合并转为PDF；需要用到iTextpdf包，
 * 
 * @author sjm
 * 
 */
public class PrintToPdfUtil {

	/**
	 * 
	 * @param imageFolderPath
	 *            图片文件夹地址
	 * @param pdfPath
	 *            PDF文件保存地址
	 * 
	 */
	public static void toPdf(File[] imageFiles, String pdfPath) {
		try {
			// 图片文件夹地址
			// String imageFolderPath = "D:/Demo/ceshi/";
			// 图片地址
			String imagePath = null;
			// PDF文件保存地址
			// String pdfPath = "D:/Demo/ceshi/hebing.pdf";
			File file = new File(pdfPath);  
			File fileParent = file.getParentFile();  
			if(!fileParent.exists()){  
			    fileParent.mkdirs();  
			}  
			file.createNewFile();
			// 输入流
			FileOutputStream fos = new FileOutputStream(pdfPath);
			// 创建文档
			Document doc = new Document(null, 0, 0, 0, 0);
			//doc.open();
			// 写入PDF文档
			PdfWriter.getInstance(doc, fos);
			// 读取图片流
			BufferedImage img = null;
			// 实例化图片
			Image image = null;
			// 获取图片文件夹对象
			//File file = new File(imageFolderPath);
			int len = imageFiles.length;
			//File[] files = file.listFiles();
			// 循环获取图片文件夹内的图片
			for (int i = 0; i < len; i++) {
				if (imageFiles[i].getName().endsWith(".png")
						|| imageFiles[i].getName().endsWith(".jpg")
						|| imageFiles[i].getName().endsWith(".gif")
						|| imageFiles[i].getName().endsWith(".jpeg")
						|| imageFiles[i].getName().endsWith(".tif")) {
					// System.out.println(file1.getName());
					//imagePath = imageFolderPath + file1.getName();
					System.out.println(imageFiles[i].getName());
					// 读取图片流
					img = ImageIO.read(new File(imageFiles[i].getAbsolutePath()));
					// 根据图片大小设置文档大小
					doc.setPageSize(new Rectangle(img.getWidth(), img
							.getHeight()));
					// 实例化图片
					image = Image.getInstance(imageFiles[i].getAbsolutePath());
					image.setAlignment(Image.ALIGN_CENTER);
					image.scaleAbsolute(597, 844);// 直接设定显示尺寸
	                // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
	                //document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
					doc.setPageSize(new Rectangle(597, 844));
	                doc.newPage();
					// 添加图片到文档
					doc.open();
					doc.add(image);
				}
			}
			// 关闭文档
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
/*	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		toPdf("D:/Demo/ceshi/", "D:/Demo/pdf/hebing.pdf");
		long time2 = System.currentTimeMillis();
		int time = (int) ((time2 - time1)/1000);
		System.out.println("执行了："+time+"秒！");
	}*/

	
	
}
