package sivl.platform.datebase.extend3core;

import sivl.platform.datebase.constant.CDataSourcesEnum;

/**
 * 数据源上下文持有者
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月28日
 * @version 1.0
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<CDataSourcesEnum> contextHolder = new ThreadLocal<CDataSourcesEnum>(); // 线程本地环境  
    // 设置数据源类型  
    public static void setDataSourceType(CDataSourcesEnum dataSourceType) {  
        contextHolder.set(dataSourceType);  
    }  
  
    // 获取数据源类型  
    public static CDataSourcesEnum getDataSourceType() {  
        return contextHolder.get();  
    }  
  
    // 清除数据源类型  
    public static void clearDataSourceType() {  
        contextHolder.remove();  
    }  
}
