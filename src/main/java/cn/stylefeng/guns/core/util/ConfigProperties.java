/**
 * @date 5:09:38 PM
 * ConfigProperties.java
 * Administrator
 * TODO
 */
package cn.stylefeng.guns.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @class ConfigProperties	
 * @author Warren
 * @description
 * @date Apr 29, 2019 
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigProperties {
	
	@Value("${picture.location}")
	private String pictureLocation;	
	
	@Value("${picture.server}")
	private String pictureServer;
}
