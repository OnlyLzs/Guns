/**
 * @date 11:18:03 AM
 * ImageUtil.java
 * Administrator
 * TODO
 */
package cn.stylefeng.guns.core.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * @class ImageUtil	
 * @author Warren
 * @description
 * @date Apr 19, 2019 
 */
public class ImageUtil {
	
	public static String saveToDiskFromMultipartFile(MultipartFile file, String path) throws Exception{
		if(path==null || path.equals("")) {
			throw new Exception("path is null or empty");
		}
		
		if(file==null || file.isEmpty()) {
			throw new Exception("MultipartFile file is null or empty");
		}
		
		String suffix = getImageSuffix(file);
		String newName = UUID.randomUUID().toString().replaceAll("-", "");
		String imageAbsolute = path + newName + "." + suffix;
		File f = new File(imageAbsolute);
		file.transferTo(f);
		return newName + "." + suffix;
		//return imageAbsolute;
	}
	
	//获得图片类型
	public static String getImageSuffix(MultipartFile file) {
		int length = file.getContentType().length();
		int bIndex = file.getContentType().indexOf("/") + 1;
		return file.getContentType().substring(bIndex, length);
	}
	
	//判断上传的是否为图片
	public static boolean isValidate(MultipartFile imgFile) {
		//file.getContentType()	image/jpeg
		String contentType = imgFile.getContentType();
		int index = contentType.indexOf("/");
		String suffix = contentType.substring(0, index);
		return suffix.equals("image");
	}
}
