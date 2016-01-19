package sivl.platform.ue.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import sivl.platform.common.constant.CBoolean;
import sivl.platform.common.constant.Constant;
import sivl.platform.common.constant.CFileOptionEnum;
import sivl.platform.common.encrypt.Encrypt;
import sivl.platform.common.utils.ComFileUtils;
import sivl.platform.ue.constant.UeAppInfoEnum;
import sivl.platform.ue.model.UeFileResultModel;
import sivl.platform.ue.model.UeMultiResultModel;
import sivl.platform.ue.service.UeService;
import sivl.platform.ue.utils.UeFileUtils;

@Service("ueService")
public class UeServiceImpl implements UeService {

	@Override
	public UeFileResultModel ueUpload(HttpServletRequest request,
			Map<String, Object> fileConfig) {
		byte[] fileData = null;
		UeFileResultModel result = null;
		//base64位图片上传
		if(CBoolean.TRUE.getCode().equals(fileConfig.get("isBase64"))){
			fileData = Encrypt.base64Encrypt(request.getParameter((String)fileConfig.get("fieldName")));
			long maxSize = ((Long)fileConfig.get("maxSize")).longValue();
			if(!ComFileUtils.validSize(fileData,maxSize)){
				result = new UeFileResultModel();
				result.setState(UeAppInfoEnum.UE_APPINFO_MAX_SIZE.getRemark());
				return result;
			}
			String fileExt = ".jpg";
			//保存路径
			String savePath = (String)fileConfig.get("savePath");
			String fileAllName = (String)fileConfig.get("filename");
		    savePath = ComFileUtils.filePathParse(savePath, fileAllName);
		    savePath = (new StringBuilder(String.valueOf(savePath))).append(fileExt).toString();
		    String physicalPath = (new StringBuilder(String.valueOf((String)fileConfig.get("rootPath")))).append(savePath).toString();
		    //保存文件
		    CFileOptionEnum fiEnum = ComFileUtils.saveFile(fileData, physicalPath);
		    if(!CFileOptionEnum.FO_SUCCESS.equals(fiEnum)){
		    	result = new UeFileResultModel();
	        	result.setState(fiEnum.getRemark());
				return result;
		    }
		    result = new UeFileResultModel();
		    result.setSize(fileData.length+"");
	        result.setTitle(UeFileUtils.getFileName(savePath));
	        result.setUrl(savePath);
	        result.setType(fileExt);
	        result.setOriginal("");
			return result;
		}
		//非文件上传
		if(!ServletFileUpload.isMultipartContent(request)){
			result = new UeFileResultModel();
			result.setState(UeAppInfoEnum.UE_APPINFO_NOT_MULTIPART_CONTENT.getRemark());
			return result;
		}
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
		//1.创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		if(isAjaxUpload){
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding(Constant.UTF8.getValue());
		}
		//2、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个fileStream，
		FileItemStream fileStream = null;
		try {
			FileItemIterator iterator = upload.getItemIterator(request);
			while(iterator.hasNext()){
				fileStream = iterator.next();
				//每一个fileStream对应一个Form表单的输入项
				if(!fileStream.isFormField()){
					break;
				}
				fileStream = null;
			}
			if(fileStream == null){
				result = new UeFileResultModel();
				result.setState(UeAppInfoEnum.UE_APPINFO_NOTFOUND_UPLOAD_DATA.getRemark());
				return result;
			}
			//3.保存文件前进行预处理
			//保存路径
			String savePath = (String)fileConfig.get("savePath");
			//文件全名
			String fileAllName = fileStream.getName();
			//文件扩展名
	        String fileExt = ComFileUtils.getFileExt(fileAllName);
	        //校验文件扩展名
	        if(!ComFileUtils.isWhiteExtList(fileExt, Arrays.asList((String[])fileConfig.get("allowFiles")))){
	        	result = new UeFileResultModel();
	        	result.setState(UeAppInfoEnum.UE_APPINFO_NOT_ALLOW_FILE_TYPE.getRemark());
				return result;
	        }
	        fileAllName = fileAllName.substring(0, fileAllName.length() - fileExt.length());
	        savePath = (new StringBuilder(String.valueOf(savePath))).append(fileExt).toString();
	        Long maxSize = ((Integer)fileConfig.get("maxSize")).longValue();
	        //对保存路径进行解析
	        savePath = ComFileUtils.cleanFilePath(savePath);
	        savePath = ComFileUtils.filePathParse(savePath, fileAllName);
	        //物理路径
	        String physicalPath = (new StringBuilder(String.valueOf((String)fileConfig.get("rootPath")))).append(savePath).toString();
	        InputStream is = fileStream.openStream();
	        fileData = ComFileUtils.input2byte(is);
	        if(!ComFileUtils.validSize(fileData,maxSize)){
				result = new UeFileResultModel();
				result.setState(UeAppInfoEnum.UE_APPINFO_MAX_SIZE.getRemark());
				return result;
			}
	        //保存文件
	        CFileOptionEnum fiEnum = ComFileUtils.saveFile(fileData, physicalPath);
	        if(!CFileOptionEnum.FO_SUCCESS.equals(fiEnum)){
	        	//文件保存失败
	        	result = new UeFileResultModel();
	        	result.setState(fiEnum.getRemark());
				return result;
	        }
	        result = new UeFileResultModel();
	        result.setState(UeAppInfoEnum.UE_APPINFO_SUCCESS.getRemark());
	        result.setSize(fileData.length+"");
	        result.setTitle(UeFileUtils.getFileName(savePath));
	        result.setUrl(savePath);
	        result.setType(fileExt);
	        result.setOriginal((new StringBuilder(String.valueOf(fileAllName))).append(fileExt).toString());
		} catch (FileUploadException e) {
			//TODO 记录日志
			result = new UeFileResultModel();
			result.setState(UeAppInfoEnum.UE_APPINFO_PARSE_REQUEST_ERROR.getRemark());
			return result;
		} catch (IOException e) {
			//TODO 记录日志
			result = new UeFileResultModel();
			result.setState(UeAppInfoEnum.UE_APPINFO_IO_ERROR.getRemark());
			return result;
		}
		return result;
	}
	
	@Override
	public UeMultiResultModel ueUpload(String[] fileNameList,
			Map<String, Object> fileConfig) {
		String filename = (String)fileConfig.get("filename");
		String savePath = (String)fileConfig.get("savePath");
		String rootPath = (String)fileConfig.get("rootPath");
		Long maxSize = ((Integer)fileConfig.get("maxSize")).longValue();
		List<String> whiteExtList = Arrays.asList((String[])fileConfig.get("allowFiles"));
		List<String> whiteListHost = Arrays.asList((String[])fileConfig.get("filter"));
		UeMultiResultModel result = new UeMultiResultModel();
		result.setList(new ArrayList<UeFileResultModel>());
		String physicalPath = rootPath + savePath;
		UeFileResultModel itemResult = null;
		for (String urlPath : fileNameList) {
			CFileOptionEnum fileEnum = ComFileUtils.saveFileByRemoteData(urlPath, physicalPath, filename, whiteListHost, whiteExtList, maxSize);
			itemResult = new UeFileResultModel();
			if(CFileOptionEnum.FO_SUCCESS.equals(fileEnum)){
				itemResult.setUrl(savePath + fileEnum.getObject());
				itemResult.setSource(urlPath);
			}
			result.getList().add(itemResult);
		}
		return result;
	}

	@Override
	public UeMultiResultModel ueFileManage(int fileIndex,
			Map<String, Object> fileConfig) {
		String rootPath = ((String)fileConfig.get("rootPath"));
		String dir = (rootPath + (String)fileConfig.get("dir"));
		String[] allowFiles = (String[])fileConfig.get("allowFiles");
		int count = ((Integer)fileConfig.get("count")).intValue();
		UeMultiResultModel result = null;
		//对文件进行处理开始
		File file = new File(dir);
		if (!file.exists()) {
			result = new UeMultiResultModel();
			result.setState(UeAppInfoEnum.UE_APPINFO_NOT_EXIST.getRemark());
		    return result;
		}
		if (!file.isDirectory()) {
			result = new UeMultiResultModel();
			result.setState(UeAppInfoEnum.UE_APPINFO_NOT_DIRECTORY.getRemark());
		    return result;
		}
		//对应目录下文件集合
		Collection<File> list = FileUtils.listFiles(file, allowFiles, true);
		if ((fileIndex < 0) || (fileIndex > list.size())) {
			result = new UeMultiResultModel();
			result.setState(UeAppInfoEnum.UE_APPINFO_SUCCESS.getRemark());
		} else {
		    Object[] fileList = Arrays.copyOfRange(list.toArray(), fileIndex, fileIndex + count);
		    result = getUeMultiResultModel(fileList, rootPath);
		}
		result.setStart((long)fileIndex);
		result.setTotal((long)list.size());
		return result;
	}
	
	/**
	 * 取得结果集合
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param files
	 * @param rootPath
	 * @return
	 */
	private UeMultiResultModel getUeMultiResultModel(Object[] files,String rootPath){
		UeMultiResultModel result = new UeMultiResultModel();
		result.setList(new ArrayList<UeFileResultModel>());
		result.setState(UeAppInfoEnum.UE_APPINFO_SUCCESS.getRemark());
	    File file = null;
	    UeFileResultModel itemResult = null;
	    for (Object obj : files) {
	    	if (obj == null) {
	    		break;
	    	}
	    	file = (File)obj;
	    	itemResult = new UeFileResultModel();
	    	itemResult.setState(UeAppInfoEnum.UE_APPINFO_SUCCESS.getRemark());
	    	itemResult.setUrl(ComFileUtils.cleanFilePath(getPath(file,rootPath)));
	    	result.getList().add(itemResult);
	    }
	    return result;
	}
	
	private String getPath(File file,String rootPath){
	    String path = file.getAbsolutePath();
	    return path.replace(rootPath, "/");
	}
}
