package sivl.platform.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import sivl.platform.common.constant.CFileOptionEnum;

/**
 * 公用文件操作工具类
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月15日
 * @version 1.0
 */
public class ComFileUtils {

	/**
	 * 文件路径统一清理
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param filePath
	 * @return
	 */
	public static String cleanFilePath(String filePath)
    {
        return filePath.replace("\\", "/");
    }
	
	/**
	 * 根据远程url保存文件
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param urlPath url请求地址
	 * @param physicalPath 保存路径
	 * @param fileName
	 * @param whiteListHost 容许的主机地址
	 * @param whiteExtList 容许的文件类型
	 * @param maxSize 文件容许最大的值
	 * @return
	 */
	public static CFileOptionEnum saveFileByRemoteData(String urlPath,String physicalPath,String fileName,List<String> whiteListHost,List<String> whiteExtList,Long maxSize){
		HttpURLConnection connection = null;
		URL url = null;
		try {
			url = new URL(urlPath);
			if(!isTrueHost(url.getHost())){
				//不是真实主机或被阻止
				return CFileOptionEnum.FO_IO_PREVENT_HOST;
			}
			if(null != whiteListHost && !whiteListHost.isEmpty() && !isWhiteListHost(url.getHost(),whiteListHost)){
				//不是白名单主机
				return CFileOptionEnum.FO_IO_PREVENT_HOST;
			}
			connection = (HttpURLConnection)url.openConnection();
	        connection.setInstanceFollowRedirects(true);
	        connection.setUseCaches(true);
	        //连接是否成功
	        if(connection.getResponseCode() != 200){
	        	return CFileOptionEnum.FO_IO_CONNECTION_ERROR;
	        }
	        String extName = getFileExt(connection.getContentType());
	        //物理路径添加文件扩展名
	        physicalPath += extName;
	        if(null != whiteExtList && !whiteExtList.isEmpty() && !isWhiteExtList(extName,whiteExtList)){
	        	//不为白名单文件类型
	        	return CFileOptionEnum.FO_IO_NOT_ALLOW_FILE_TYPE;
	        }
	        if(null != maxSize && !validSize(connection.getContentLength(),maxSize)){
	        	//文件超出大小
	        	return CFileOptionEnum.FO_MAX_SIZE;
	        }
	        physicalPath = filePathParse(physicalPath,fileName);
	        CFileOptionEnum fileEnum = saveFile(connection.getInputStream(),physicalPath);
	        fileEnum.setObject(extName);
	        return fileEnum;
		} catch (MalformedURLException e) {
			return CFileOptionEnum.FO_IO_REMOTE_FAIL;
		} catch (IOException e) {
			return CFileOptionEnum.FO_IO_ERROR;
		}
	}
	
	/**
	 * 保存文件
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param InputStream
	 * @param path
	 */
	public static CFileOptionEnum saveFile(InputStream is , String path){
		File file = new File(path);
		File parentPath = file.getParentFile();
		if(!parentPath.exists() && !parentPath.mkdirs()){
			//TODO 记录日志  父路径不存在并且创建目录失败
			return CFileOptionEnum.FO_FAILED_CREATE;
		}
		if(!parentPath.canWrite()){
			//TODO 记录日志  创建目录失败,权限不足
			return CFileOptionEnum.FO_NO_PERMISSION;
		}
		try {
			FileCopyUtils.copy(input2byte(is), new FileOutputStream(path));
		} catch (IOException e) {
			// TODO 记录日志
			return CFileOptionEnum.FO_IO_ERROR;
		}
		return CFileOptionEnum.FO_SUCCESS;
	}
	
	/**
	 * 保存文件
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param Multifile
	 * @param path
	 */
	public static CFileOptionEnum saveFile(MultipartFile Multifile , String path){
		File file = new File(path);
		File parentPath = file.getParentFile();
		if(!parentPath.exists() && !parentPath.mkdirs()){
			//TODO 记录日志  父路径不存在并且创建目录失败
			return CFileOptionEnum.FO_FAILED_CREATE;
		}
		if(!parentPath.canWrite()){
			//TODO 记录日志  创建目录失败,权限不足
			return CFileOptionEnum.FO_NO_PERMISSION;
		}
		try {
			FileCopyUtils.copy(Multifile.getBytes(), new FileOutputStream(path));
		} catch (IOException e) {
			// TODO 记录日志
			return CFileOptionEnum.FO_IO_ERROR;
		}
		return CFileOptionEnum.FO_SUCCESS;
	}
	
	/**
	 * 保存文件
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param fileData
	 * @param path
	 */
	public static CFileOptionEnum saveFile(byte[] fileData , String path){
		File file = new File(path);
		File parentPath = file.getParentFile();
		if(!parentPath.exists() && !parentPath.mkdirs()){
			//TODO 记录日志  父路径不存在并且创建目录失败
			return CFileOptionEnum.FO_FAILED_CREATE;
		}
		if(!parentPath.canWrite()){
			//TODO 记录日志  创建目录失败,权限不足
			return CFileOptionEnum.FO_NO_PERMISSION;
		}
		try {
			FileCopyUtils.copy(fileData, new FileOutputStream(path));
		} catch (IOException e) {
			// TODO 记录日志
			return CFileOptionEnum.FO_IO_ERROR;
		}
		return CFileOptionEnum.FO_SUCCESS;
	}
	
	/**
	 * InputStream 转 byte[]
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static final byte[] input2byte(InputStream inStream){  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        try {
			while ((rc = inStream.read(buff, 0, 100)) > 0) {  
			    swapStream.write(buff, 0, rc);  
			}
		} catch (IOException e) {
			// TODO 记录日志
		}  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    } 
	
	/**
	 * 取得文件扩展名<br/>
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param fileFullName 文件全面
	 * @return
	 */
	public static String getFileExt(String fileFullName){
		return fileFullName.substring(fileFullName.lastIndexOf(".")).toLowerCase();
	}
	
	/**
	 * 校验数据长度是否合法<br/>
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月14日
	 * @param data
	 * @param length
	 * @return
	 */
	public static boolean validSize(byte[] data, long length){
        return (long)data.length <= length;
    }
	
	/**
	 * 校验数据长度是否合法<br/>
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月14日
	 * @param currentLength
	 * @param length
	 * @return
	 */
	public static boolean validSize(int currentLength, long length){
        return currentLength <= length;
    }
	
	/**
	 * 为白名单文件类型
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param ext
	 * @param filters
	 * @return
	 */
	public static boolean isWhiteExtList(String ext,List<String> filters){
		return filters.contains(ext);
	}
	
	/**
	 * 解析文件路径
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param savePath
	 * @param fileAllName
	 * @return
	 */
	public static String filePathParse(String savePath, String fileAllName)
    {
        Pattern pattern = Pattern.compile("\\{([^\\}]+)\\}", 2);
        Matcher matcher = pattern.matcher(savePath);
        String matchStr = null;
        Date currentDate = new Date();
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) 
        {
            matchStr = matcher.group(1);
            if(matchStr.indexOf("filename") != -1)
            {
            	fileAllName = fileAllName.replace("$", "\\$").replaceAll("[\\/:*?\"<>|]", "");
                matcher.appendReplacement(sb, fileAllName);
            } else
            {
                matcher.appendReplacement(sb, filePathFormat(matchStr,currentDate));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
	
	/**
	 * 格式化文件路径
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param pattern
	 * @param date
	 * @return
	 */
	private static String filePathFormat(String pattern, Date date){
		pattern = pattern.toLowerCase();
        if(pattern.indexOf("time") != -1)
            return getTimestamp();
        if(pattern.indexOf("yyyy") != -1)
            return getFullYear(date);
        if(pattern.indexOf("yy") != -1)
            return getYear(date);
        if(pattern.indexOf("mm") != -1)
            return getMonth(date);
        if(pattern.indexOf("dd") != -1)
            return getDay(date);
        if(pattern.indexOf("hh") != -1)
            return getHour(date);
        if(pattern.indexOf("ii") != -1)
            return getMinute(date);
        if(pattern.indexOf("ss") != -1)
            return getSecond(date);
        if(pattern.indexOf("rand") != -1)
            return getRandom(pattern);
        else
            return pattern;
	}
	
	private static String getTimestamp()
    {
        return (new StringBuilder(String.valueOf(System.currentTimeMillis()))).toString();
    }

    private static String getFullYear(Date date)
    {
        return (new SimpleDateFormat("yyyy")).format(date);
    }

    private static String getYear(Date date)
    {
        return (new SimpleDateFormat("yy")).format(date);
    }

    private static String getMonth(Date date)
    {
        return (new SimpleDateFormat("MM")).format(date);
    }

    private static String getDay(Date date)
    {
        return (new SimpleDateFormat("dd")).format(date);
    }

    private static String getHour(Date date)
    {
        return (new SimpleDateFormat("HH")).format(date);
    }

    private static String getMinute(Date date)
    {
        return (new SimpleDateFormat("mm")).format(date);
    }

    private static String getSecond(Date date)
    {
        return (new SimpleDateFormat("ss")).format(date);
    }

    private static String getRandom(String pattern)
    {
        int length = 0;
        pattern = pattern.split(":")[1].trim();
        length = Integer.parseInt(pattern);
        return (new StringBuilder(String.valueOf(Math.random()))).toString().replace(".", "").substring(0, length);
    }
	
	/**
	 * 是否为真实主机
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param hostname
	 * @return
	 */
	private static boolean isTrueHost(String hostname){
        try {
			InetAddress ip = InetAddress.getByName(hostname);
			if(ip.isSiteLocalAddress()){
				return false;
			}
		} catch (UnknownHostException e) {
			return false;
		}
        return true;
    }
	
	/**
	 * 为白名单主机
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月15日
	 * @param hostname
	 * @param filters
	 * @return
	 */
	private static boolean isWhiteListHost(String hostname,List<String> filters){
		return filters.contains(hostname);
	}
	
}
