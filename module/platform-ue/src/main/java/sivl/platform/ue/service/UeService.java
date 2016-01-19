package sivl.platform.ue.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sivl.platform.ue.model.UeFileResultModel;
import sivl.platform.ue.model.UeMultiResultModel;

/**
 * ue 调用服务
 * 目的：
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
public interface UeService {
	
	/**
	 * ue上传文件
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月14日
	 * @param request
	 * @param fileConfig
	 * @return
	 */
	UeFileResultModel ueUpload(HttpServletRequest request,Map<String,Object> fileConfig);
	
	/**
	 * 多文件捕获上传
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月14日
	 * @param fileNameList
	 * @param fileConfig
	 * @return
	 */
	UeMultiResultModel ueUpload(String[] fileNameList,Map<String,Object> fileConfig);
	
	/**
	 * 文件管理
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月14日
	 * @param fileIndex
	 * @param fileConfig
	 * @return
	 */
	UeMultiResultModel ueFileManage(int fileIndex,Map<String,Object> fileConfig);
}
