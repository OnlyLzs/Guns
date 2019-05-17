/**
 * @date 3:03:09 PM
 * MybatisPlusGenerator.java
 * Administrator
 * TODO
 */
package cn.stylefeng.guns.core.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;

/**
 * @class MybatisPlusGenerator	
 * @author Warren
 * @description
 * @date Apr 12, 2019 
 */
public class MybatisPlusGenerator {
	
	public static void main(String[] args) {
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		
		//s全局配置
		gc.setAuthor("Jason");
		gc.setOpen(true);
		gc.setOutputDir(projectPath + "/src/main/java");
		
		//s数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUrl("jdbc:mysql://localhost:3306/guns?useSSL=false&serverTimezone=UTC");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		dsc.setSchemaName("guns");
		dsc.setTypeConvert(new MySqlTypeConvert());
		
		//s包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("cn.stylefeng.guns.modular");
		pc.setModuleName("wedding");
		
		//s策略配置
		StrategyConfig sc = new StrategyConfig();
		//sc.setColumnNaming(NamingStrategy.underline_to_camel);
		sc.setInclude(new String[] {"app_order"});
		sc.setEntityLombokModel(true);
		sc.setEntityTableFieldAnnotationEnable(true);
		
		AutoGenerator ag = new AutoGenerator();
		ag.setDataSource(dsc);
		ag.setGlobalConfig(gc);
		ag.setPackageInfo(pc);
		ag.setStrategy(sc);
		ag.execute();
	}
}
