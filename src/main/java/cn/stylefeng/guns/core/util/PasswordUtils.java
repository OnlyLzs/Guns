package cn.stylefeng.guns.core.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtils {
	
	/**
	 * 
	 * @author Arwen Liu
	 * @date 2018-10-19
	 * @param source 加密的字符串
	 * @return String
	 */
	public static String encodPassword(String source){
		if(null != source && !"".equals(source)){
			return encodPassword("MD5",source);
		}
		return null;
	}
	
	/**
	 * 
	 * @author Arwen Liu
	 * @date 2018-10-19
	 * @param algorithmName 加密的方式
	 * @param source 加密的字符串
	 * @return String
	 */
	public static String encodPassword(String algorithmName,String source ){
		if(null == algorithmName || "".endsWith(algorithmName) ){
			algorithmName = "MD5";
		}
		if(null != source && !"".equals(source)){
			return encodPassword(algorithmName, source, null);
		}
		return null;
	}
	
	/**
	 * 
	 * @author Arwen Liu
	 * @date 2018-10-19
	 * @param algorithmName 加密方式
	 * @param source 需要加密的字符串
	 * @param salt 盐值
	 * @return String
	 */
	public static String encodPassword(String algorithmName,String source,Object salt ){
		if(null == algorithmName || "".equals(algorithmName) ){
			algorithmName = "MD5";
		}
		if(null != source && !"".equals(source)){
			return encodPassword(algorithmName, source, salt,1);
		}
		return null;
		 
	}
	
	/**
	 * 
	 * @author Arwen Liu
	 * @date 2018-10-19
	 * @param algorithmName 加密的方式
	 * @param source 需要加密的字符串
	 * @param salt 盐值
	 * @param hashIterations 加密的次数
	 * @return String
	 */
	public static String encodPassword(String algorithmName,String source,Object salt,Integer hashIterations ){
		if(null == algorithmName || "".equals(algorithmName) ){
			algorithmName = "MD5";
		}
		if(null == hashIterations || hashIterations < 0){
			hashIterations = 1;
		}
		if(null != source && !"".equals(source)){
			Object result = new SimpleHash(algorithmName, source, null, hashIterations);
			return result.toString();
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		//加密的方式
		String algorithmName = "md5";
		//需要加密的字符串
		String source = "12345";
		
		//盐值，它的作用就是，即是两个人的原明文密码一样，可以通过这个值的控制，使加密后的密码不一样
		Object salt = null;
		
		//需要加密的次数
		int hashIterations = 1;
		
		String result = encodPassword(algorithmName, source, salt, hashIterations);
		System.out.println(result);
	}
	*/
	
}
