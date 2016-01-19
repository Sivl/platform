package sivl.platform.ue.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import sivl.platform.common.model.CommonModel;

/**
 * ue配置载体
 * 目的：
 * @author lixuefeng
 * @date 2016年1月13日
 * @version 1.0
 */
public class UeConfig implements CommonModel,Serializable {

	private static final long serialVersionUID = 1L;
	/****************************** 上传图片配置项 ******************************/
	/** 执行上传图片的action名称 */
	private String imageActionName = "uploadimage";
	/** 提交的图片表单名称 */
	private String imageFieldName = "upfile";
	/** 上传大小限制，单位B 2M */
	private int imageMaxSize = 2048000;
	/** 上传图片格式显示 */
	private String[] imageAllowFiles  = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
	/** 是否压缩图片,默认是true */
	private boolean imageCompressEnable = true;
	/** 图片压缩最长边限制 */
	private int imageCompressBorder = 1600;
	/** 插入的图片浮动方式 */
	private String imageInsertAlign = "none";
	/** 图片访问路径前缀 */
	private String imageUrlPrefix = "";
	/** 上传保存路径,可以自定义保存路径和文件名格式 
	 * {filename} 会替换成原文件名,配置这项需要注意中文乱码问题 
     * {rand:6} 会替换成随机数,后面的数字是随机数的位数
     * {time} 会替换成时间戳 
     * {yyyy} 会替换成四位年份 
     * {yy} 会替换成两位年份
     * {mm} 会替换成两位月份
     * {dd} 会替换成两位日期 
     * {hh} 会替换成两位小时
     * {ii} 会替换成两位分钟 
     * {ss} 会替换成两位秒 
     * 非法字符 \ : * ? " < > | */
	private String imagePathFormat = "file/up_image/{yyyy}{mm}{dd}/{time}-{rand:6}";
	/****************************** 涂鸦图片上传配置项 ******************************/
	/** 执行上传涂鸦的action名称 */
	private String scrawlActionName = "uploadscrawl";
	/** 提交的图片表单名称 */
	private String scrawlFieldName = "upfile";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String scrawlPathFormat = "file/up_scrawl/{yyyy}{mm}{dd}/{time}-{rand:6}";
	/** 上传大小限制，单位B 2M*/
	private int scrawlMaxSize = 2048000;
	/** 图片访问路径前缀 */
	private String scrawlUrlPrefix = "";
	/** 插入的图片浮动方式 */
	private String scrawlInsertAlign = "none";
	/****************************** 截图工具上传******************************/
	/** 执行上传截图的action名称 */
	private String snapscreenActionName = "uploadimage";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String snapscreenPathFormat = "file/up_snapscreen/{yyyy}{mm}{dd}/{time}-{rand:6}";
	/** 图片访问路径前缀 */
	private String snapscreenUrlPrefix = "";
	/** 插入的图片浮动方式 */
	private String snapscreenInsertAlign = "none";
	/****************************** 抓取远程图片配置 ******************************/
	private String[] catcherLocalDomain = {"127.0.0.1", "localhost", "img.baidu.com"};
	/** 执行抓取远程图片的action名称 */
	private String catcherActionName = "catchimage";
	/** 提交的图片列表表单名称 */
	private String catcherFieldName = "source";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String catcherPathFormat = "file/up_catcher/{yyyy}{mm}{dd}/{time}-{rand:6}";
	/** 图片访问路径前缀 */
	private String catcherUrlPrefix = "";
	/** 上传大小限制，单位B 2M*/
	private int catcherMaxSize = 2048000;
	/** 抓取图片格式显示 */
	private String[] catcherAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
	/****************************** 上传视频配置 ******************************/
	/** 执行上传视频的action名称 */
	private String videoActionName = "uploadvideo";
	/** 提交的视频表单名称 */
	private String videoFieldName = "upfile";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String videoPathFormat = "file/up_video/{yyyy}{mm}{dd}/{time}-{rand:6}";
	/** 视频访问路径前缀 */
	private String videoUrlPrefix = "";
	/** 上传大小限制，单位B，默认100MB */
	private int videoMaxSize = 1024*1000*100;
	/** 上传视频格式显示 */
	private String[] videoAllowFiles = {".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
	        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"};
	/****************************** 上传文件配置 ******************************/
	/** controller里,执行上传视频的action名称 */
	private String fileActionName = "uploadfile";
	/** 提交的文件表单名称 */
	private String fileFieldName = "upfile";
	/** 上传保存路径,可以自定义保存路径和文件名格式 */
	private String filePathFormat = "file/{yyyy}{mm}{dd}/{time}-{rand:6}";
	/** 文件访问路径前缀 */
	private String fileUrlPrefix = "";
	/** 上传大小限制，单位B，默认50MB */
	private int fileMaxSize = 512*1000*100;
	/** 上传文件格式显示 */
	private String[] fileAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp",
	        ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
	        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
	        ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
	        ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"};
	/****************************** 列出指定目录下的图片 ******************************/
	/** 执行图片管理的action名称 */
	private String imageManagerActionName = "listimage";
	/** 指定要列出图片的目录 */
	private String imageManagerListPath = "file/image/";
	/** 每次列出文件数量 */
	private int imageManagerListSize = 20;
	/** 图片访问路径前缀 */
	private String imageManagerUrlPrefix = "";
	/** 插入的图片浮动方式 */
	private String imageManagerInsertAlign = "none";
	/** 列出的文件类型 */
	private String[] imageManagerAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
	/****************************** 列出指定目录下的文件 ******************************/
	/** 执行文件管理的action名称 */
	private String fileManagerActionName = "listfile";
	/** 指定要列出文件的目录 */
	private String fileManagerListPath = "file/";
	/** 文件访问路径前缀 */
	private String fileManagerUrlPrefix = "";
	/** 每次列出文件数量 */
	private int fileManagerListSize = 20;
	/** 列出的文件类型 */
	private String[] fileManagerAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp",
	        ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
	        ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
	        ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
	        ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"};
	/** 保存根目录. */
	private String rootPath = "C:/Sivl/";
	/**
	 * 取得UEconfig
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月13日
	 * @param type
	 * @return
	 */
	public Map<String, Object> getUeConfig(int type){
	    Map<String,Object> conf = new HashMap<String,Object>();
	    String savePath = null;
	    switch (type)
	    {
		    case 4:
		      conf.put("isBase64", "false");
		      conf.put("maxSize", fileMaxSize);
		      conf.put("allowFiles", fileAllowFiles);
		      conf.put("fieldName", fileFieldName);
		      savePath = filePathFormat;
		      break;
		    case 1:
		      conf.put("isBase64", "false");
		      conf.put("maxSize", imageMaxSize);
		      conf.put("allowFiles", imageAllowFiles);
		      conf.put("fieldName", imageFieldName);
		      savePath = imagePathFormat;
		      break;
		    case 3:
		      conf.put("maxSize", videoMaxSize);
		      conf.put("allowFiles", videoAllowFiles);
		      conf.put("fieldName", videoFieldName);
		      savePath = videoPathFormat;
		      break;
		    case 2:
		      conf.put("filename", "scrawl");
		      conf.put("maxSize", scrawlMaxSize);
		      conf.put("fieldName", scrawlFieldName);
		      conf.put("isBase64", "true");
		      savePath = scrawlPathFormat;
		      break;
		    case 5:
		      conf.put("filename", "remote");
		      conf.put("filter", catcherLocalDomain);
		      conf.put("maxSize", catcherMaxSize);
		      conf.put("allowFiles", catcherAllowFiles);
		      conf.put("fieldName", catcherFieldName + "[]");
		      savePath = catcherPathFormat;
		      break;
		    case 7:
		      conf.put("allowFiles", imageManagerAllowFiles);
		      conf.put("dir", imageManagerListPath);
		      conf.put("count", imageManagerListSize);
		      break;
		    case 6:
		      conf.put("allowFiles", fileManagerAllowFiles);
		      conf.put("dir", fileManagerListPath);
		      conf.put("count", fileManagerListSize);
	    }
	    conf.put("savePath", savePath);
	    conf.put("rootPath", rootPath);
	    return conf;
	}
	
	public String getImageActionName() {
		return imageActionName;
	}
	public void setImageActionName(String imageActionName) {
		this.imageActionName = imageActionName;
	}
	public String getImageFieldName() {
		return imageFieldName;
	}
	public void setImageFieldName(String imageFieldName) {
		this.imageFieldName = imageFieldName;
	}
	public int getImageMaxSize() {
		return imageMaxSize;
	}
	public void setImageMaxSize(int imageMaxSize) {
		this.imageMaxSize = imageMaxSize;
	}
	public String[] getImageAllowFiles() {
		return imageAllowFiles;
	}
	public void setImageAllowFiles(String[] imageAllowFiles) {
		this.imageAllowFiles = imageAllowFiles;
	}
	public boolean isImageCompressEnable() {
		return imageCompressEnable;
	}
	public void setImageCompressEnable(boolean imageCompressEnable) {
		this.imageCompressEnable = imageCompressEnable;
	}
	public int getImageCompressBorder() {
		return imageCompressBorder;
	}
	public void setImageCompressBorder(int imageCompressBorder) {
		this.imageCompressBorder = imageCompressBorder;
	}
	public String getImageInsertAlign() {
		return imageInsertAlign;
	}
	public void setImageInsertAlign(String imageInsertAlign) {
		this.imageInsertAlign = imageInsertAlign;
	}
	public String getImageUrlPrefix() {
		if(null == imageUrlPrefix){
			return "";
		}
		return imageUrlPrefix;
	}
	public void setImageUrlPrefix(String imageUrlPrefix) {
		this.imageUrlPrefix = imageUrlPrefix;
	}
	public String getImagePathFormat() {
		return imagePathFormat;
	}
	public void setImagePathFormat(String imagePathFormat) {
		this.imagePathFormat = imagePathFormat;
	}
	public String getScrawlActionName() {
		return scrawlActionName;
	}
	public void setScrawlActionName(String scrawlActionName) {
		this.scrawlActionName = scrawlActionName;
	}
	public String getScrawlFieldName() {
		return scrawlFieldName;
	}
	public void setScrawlFieldName(String scrawlFieldName) {
		this.scrawlFieldName = scrawlFieldName;
	}
	public String getScrawlPathFormat() {
		return scrawlPathFormat;
	}
	public void setScrawlPathFormat(String scrawlPathFormat) {
		this.scrawlPathFormat = scrawlPathFormat;
	}
	public int getScrawlMaxSize() {
		return scrawlMaxSize;
	}
	public void setScrawlMaxSize(int scrawlMaxSize) {
		this.scrawlMaxSize = scrawlMaxSize;
	}
	public String getScrawlUrlPrefix() {
		if(null == scrawlUrlPrefix){
			return "";
		}
		return scrawlUrlPrefix;
	}
	public void setScrawlUrlPrefix(String scrawlUrlPrefix) {
		this.scrawlUrlPrefix = scrawlUrlPrefix;
	}
	public String getScrawlInsertAlign() {
		return scrawlInsertAlign;
	}
	public void setScrawlInsertAlign(String scrawlInsertAlign) {
		this.scrawlInsertAlign = scrawlInsertAlign;
	}
	public String getSnapscreenActionName() {
		return snapscreenActionName;
	}
	public void setSnapscreenActionName(String snapscreenActionName) {
		this.snapscreenActionName = snapscreenActionName;
	}
	public String getSnapscreenPathFormat() {
		return snapscreenPathFormat;
	}
	public void setSnapscreenPathFormat(String snapscreenPathFormat) {
		this.snapscreenPathFormat = snapscreenPathFormat;
	}
	public String getSnapscreenUrlPrefix() {
		if(null == snapscreenUrlPrefix){
			return "";
		}
		return snapscreenUrlPrefix;
	}
	public void setSnapscreenUrlPrefix(String snapscreenUrlPrefix) {
		this.snapscreenUrlPrefix = snapscreenUrlPrefix;
	}
	public String getSnapscreenInsertAlign() {
		return snapscreenInsertAlign;
	}
	public void setSnapscreenInsertAlign(String snapscreenInsertAlign) {
		this.snapscreenInsertAlign = snapscreenInsertAlign;
	}
	public String[] getCatcherLocalDomain() {
		return catcherLocalDomain;
	}
	public void setCatcherLocalDomain(String[] catcherLocalDomain) {
		this.catcherLocalDomain = catcherLocalDomain;
	}
	public String getCatcherActionName() {
		return catcherActionName;
	}
	public void setCatcherActionName(String catcherActionName) {
		this.catcherActionName = catcherActionName;
	}
	public String getCatcherFieldName() {
		return catcherFieldName;
	}
	public void setCatcherFieldName(String catcherFieldName) {
		this.catcherFieldName = catcherFieldName;
	}
	public String getCatcherPathFormat() {
		return catcherPathFormat;
	}
	public void setCatcherPathFormat(String catcherPathFormat) {
		this.catcherPathFormat = catcherPathFormat;
	}
	public String getCatcherUrlPrefix() {
		if(null == catcherUrlPrefix){
			return "";
		}
		return catcherUrlPrefix;
	}
	public void setCatcherUrlPrefix(String catcherUrlPrefix) {
		this.catcherUrlPrefix = catcherUrlPrefix;
	}
	public int getCatcherMaxSize() {
		return catcherMaxSize;
	}
	public void setCatcherMaxSize(int catcherMaxSize) {
		this.catcherMaxSize = catcherMaxSize;
	}
	public String[] getCatcherAllowFiles() {
		return catcherAllowFiles;
	}
	public void setCatcherAllowFiles(String[] catcherAllowFiles) {
		this.catcherAllowFiles = catcherAllowFiles;
	}
	public String getVideoActionName() {
		return videoActionName;
	}
	public void setVideoActionName(String videoActionName) {
		this.videoActionName = videoActionName;
	}
	public String getVideoFieldName() {
		return videoFieldName;
	}
	public void setVideoFieldName(String videoFieldName) {
		this.videoFieldName = videoFieldName;
	}
	public String getVideoPathFormat() {
		return videoPathFormat;
	}
	public void setVideoPathFormat(String videoPathFormat) {
		this.videoPathFormat = videoPathFormat;
	}
	public String getVideoUrlPrefix() {
		if(null == videoUrlPrefix){
			return "";
		}
		return videoUrlPrefix;
	}
	public void setVideoUrlPrefix(String videoUrlPrefix) {
		this.videoUrlPrefix = videoUrlPrefix;
	}
	public int getVideoMaxSize() {
		return videoMaxSize;
	}
	public void setVideoMaxSize(int videoMaxSize) {
		this.videoMaxSize = videoMaxSize;
	}
	public String[] getVideoAllowFiles() {
		return videoAllowFiles;
	}
	public void setVideoAllowFiles(String[] videoAllowFiles) {
		this.videoAllowFiles = videoAllowFiles;
	}
	public String getFileActionName() {
		return fileActionName;
	}
	public void setFileActionName(String fileActionName) {
		this.fileActionName = fileActionName;
	}
	public String getFileFieldName() {
		return fileFieldName;
	}
	public void setFileFieldName(String fileFieldName) {
		this.fileFieldName = fileFieldName;
	}
	public String getFilePathFormat() {
		return filePathFormat;
	}
	public void setFilePathFormat(String filePathFormat) {
		this.filePathFormat = filePathFormat;
	}
	public String getFileUrlPrefix() {
		if(null == fileUrlPrefix){
			return "";
		}
		return fileUrlPrefix;
	}
	public void setFileUrlPrefix(String fileUrlPrefix) {
		this.fileUrlPrefix = fileUrlPrefix;
	}
	public int getFileMaxSize() {
		return fileMaxSize;
	}
	public void setFileMaxSize(int fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}
	public String[] getFileAllowFiles() {
		return fileAllowFiles;
	}
	public void setFileAllowFiles(String[] fileAllowFiles) {
		this.fileAllowFiles = fileAllowFiles;
	}
	public String getImageManagerActionName() {
		return imageManagerActionName;
	}
	public void setImageManagerActionName(String imageManagerActionName) {
		this.imageManagerActionName = imageManagerActionName;
	}
	public String getImageManagerListPath() {
		return imageManagerListPath;
	}
	public void setImageManagerListPath(String imageManagerListPath) {
		this.imageManagerListPath = imageManagerListPath;
	}
	public int getImageManagerListSize() {
		return imageManagerListSize;
	}
	public void setImageManagerListSize(int imageManagerListSize) {
		this.imageManagerListSize = imageManagerListSize;
	}
	public String getImageManagerUrlPrefix() {
		if(null == imageManagerUrlPrefix){
			return "";
		}
		return imageManagerUrlPrefix;
	}
	public void setImageManagerUrlPrefix(String imageManagerUrlPrefix) {
		this.imageManagerUrlPrefix = imageManagerUrlPrefix;
	}
	public String getImageManagerInsertAlign() {
		return imageManagerInsertAlign;
	}
	public void setImageManagerInsertAlign(String imageManagerInsertAlign) {
		this.imageManagerInsertAlign = imageManagerInsertAlign;
	}
	public String[] getImageManagerAllowFiles() {
		return imageManagerAllowFiles;
	}
	public void setImageManagerAllowFiles(String[] imageManagerAllowFiles) {
		this.imageManagerAllowFiles = imageManagerAllowFiles;
	}
	public String getFileManagerActionName() {
		return fileManagerActionName;
	}
	public void setFileManagerActionName(String fileManagerActionName) {
		this.fileManagerActionName = fileManagerActionName;
	}
	public String getFileManagerListPath() {
		return fileManagerListPath;
	}
	public void setFileManagerListPath(String fileManagerListPath) {
		this.fileManagerListPath = fileManagerListPath;
	}
	public String getFileManagerUrlPrefix() {
		if(null == fileManagerUrlPrefix){
			return "";
		}
		return fileManagerUrlPrefix;
	}
	public void setFileManagerUrlPrefix(String fileManagerUrlPrefix) {
		this.fileManagerUrlPrefix = fileManagerUrlPrefix;
	}
	public int getFileManagerListSize() {
		return fileManagerListSize;
	}
	public void setFileManagerListSize(int fileManagerListSize) {
		this.fileManagerListSize = fileManagerListSize;
	}
	public String[] getFileManagerAllowFiles() {
		return fileManagerAllowFiles;
	}
	public void setFileManagerAllowFiles(String[] fileManagerAllowFiles) {
		this.fileManagerAllowFiles = fileManagerAllowFiles;
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public String toString() {
		return "UeConfig [imageActionName=" + imageActionName
				+ ", imageFieldName=" + imageFieldName + ", imageMaxSize="
				+ imageMaxSize + ", imageAllowFiles="
				+ Arrays.toString(imageAllowFiles) + ", imageCompressEnable="
				+ imageCompressEnable + ", imageCompressBorder="
				+ imageCompressBorder + ", imageInsertAlign="
				+ imageInsertAlign + ", imageUrlPrefix=" + imageUrlPrefix
				+ ", imagePathFormat=" + imagePathFormat
				+ ", scrawlActionName=" + scrawlActionName
				+ ", scrawlFieldName=" + scrawlFieldName
				+ ", scrawlPathFormat=" + scrawlPathFormat + ", scrawlMaxSize="
				+ scrawlMaxSize + ", scrawlUrlPrefix=" + scrawlUrlPrefix
				+ ", scrawlInsertAlign=" + scrawlInsertAlign
				+ ", snapscreenActionName=" + snapscreenActionName
				+ ", snapscreenPathFormat=" + snapscreenPathFormat
				+ ", snapscreenUrlPrefix=" + snapscreenUrlPrefix
				+ ", snapscreenInsertAlign=" + snapscreenInsertAlign
				+ ", catcherLocalDomain=" + Arrays.toString(catcherLocalDomain)
				+ ", catcherActionName=" + catcherActionName
				+ ", catcherFieldName=" + catcherFieldName
				+ ", catcherPathFormat=" + catcherPathFormat
				+ ", catcherUrlPrefix=" + catcherUrlPrefix
				+ ", catcherMaxSize=" + catcherMaxSize + ", catcherAllowFiles="
				+ Arrays.toString(catcherAllowFiles) + ", videoActionName="
				+ videoActionName + ", videoFieldName=" + videoFieldName
				+ ", videoPathFormat=" + videoPathFormat + ", videoUrlPrefix="
				+ videoUrlPrefix + ", videoMaxSize=" + videoMaxSize
				+ ", videoAllowFiles=" + Arrays.toString(videoAllowFiles)
				+ ", fileActionName=" + fileActionName + ", fileFieldName="
				+ fileFieldName + ", filePathFormat=" + filePathFormat
				+ ", fileUrlPrefix=" + fileUrlPrefix + ", fileMaxSize="
				+ fileMaxSize + ", fileAllowFiles="
				+ Arrays.toString(fileAllowFiles) + ", imageManagerActionName="
				+ imageManagerActionName + ", imageManagerListPath="
				+ imageManagerListPath + ", imageManagerListSize="
				+ imageManagerListSize + ", imageManagerUrlPrefix="
				+ imageManagerUrlPrefix + ", imageManagerInsertAlign="
				+ imageManagerInsertAlign + ", imageManagerAllowFiles="
				+ Arrays.toString(imageManagerAllowFiles)
				+ ", fileManagerActionName=" + fileManagerActionName
				+ ", fileManagerListPath=" + fileManagerListPath
				+ ", fileManagerUrlPrefix=" + fileManagerUrlPrefix
				+ ", fileManagerListSize=" + fileManagerListSize
				+ ", fileManagerAllowFiles="
				+ Arrays.toString(fileManagerAllowFiles) + ", rootPath="
				+ rootPath + "]";
	}
}
