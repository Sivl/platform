package sivl.platform.datebase.extend3core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据源
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月28日
 * @version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		 // 在进行DAO操作前，通过上下文环境变量，获得数据源的类型  
		 return DataSourceContextHolder.getDataSourceType().getValue();
	}

}
