package sivl.platform.ue.utils;


/**
 * UE文件工具类
 * 目的：
 * @author lixuefeng
 * @date 2016年1月15日
 * @version 1.0
 */
public class UeFileUtils {
	
	public static String getFileName(String filePath){
		filePath = filePath.substring(filePath.lastIndexOf("/")+1, filePath.lastIndexOf("."));
		return filePath;
	}
}
