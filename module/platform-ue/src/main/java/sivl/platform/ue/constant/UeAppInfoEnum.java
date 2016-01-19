package sivl.platform.ue.constant;

import sivl.platform.common.utils.StringUtil;

public enum UeAppInfoEnum {
	/** 成功. */
	UE_APPINFO_SUCCESS("成功","",0,"处理成功"),
	/** 文件大小超出限制. */
	UE_APPINFO_MAX_SIZE("文件大小超出限制","",1,"ue执行错误：文件大小超出限制"),
	/** 权限不足. */
	UE_APPINFO_PERMISSION_DENIED("权限不足","",2,"ue执行错误：权限不足"),
	/** 创建文件失败. */
	UE_APPINFO_FAILED_CREATE_FILE("创建文件失败","",3,"ue执行错误：创建文件失败"),
	/** IO错误. */
	UE_APPINFO_IO_ERROR("IO错误","",4,"ue执行错误：IO错误"),
	/** 上传表单不是multipart/form-data类型. */
	UE_APPINFO_NOT_MULTIPART_CONTENT("上传表单不是multipart/form-data类型","",5,"ue执行错误：上传表单不是multipart/form-data类型"),
	/** 解析上传表单错误. */
	UE_APPINFO_PARSE_REQUEST_ERROR("解析上传表单错误","",6,"ue执行错误：未找到上传数据"),
	/** 未找到上传数据. */
	UE_APPINFO_NOTFOUND_UPLOAD_DATA("未找到上传数据","",7,"ue执行错误：未找到上传数据"),
	/** 不允许的文件类型. */
	UE_APPINFO_NOT_ALLOW_FILE_TYPE("不允许的文件类型","",8,"ue执行错误：不允许的文件类型"),
	/** 无效的Action. */
	UE_APPINFO_INVALID_ACTION("无效的Action","",101,"ue执行错误：无效的Action"),
	/** 配置文件初始化失败. */
    UE_APPINFO_CONFIG_ERROR("配置文件初始化失败","",102,"ue执行错误：配置文件初始化失败"),
    /** 被阻止的远程主机. */
    UE_APPINFO_PREVENT_HOST("被阻止的远程主机","",201,"ue执行错误：被阻止的远程主机"),
    /** 远程连接出错. */
    UE_APPINFO_CONNECTION_ERROR("远程连接出错","",202,"ue执行错误：远程连接出错"),
    /** 抓取远程图片失败. */
    UE_APPINFO_REMOTE_FAIL("抓取远程图片失败","",203,"ue执行错误：抓取远程图片失败"),
    /** 指定路径不是目录. */
    UE_APPINFO_NOT_DIRECTORY("指定路径不是目录","",301,"ue执行错误：指定路径不是目录"),
    /** 指定路径并不存在. */
    UE_APPINFO_NOT_EXIST("指定路径并不存在","",302,"ue执行错误：指定路径并不存在"),
    /** Callback参数名不合法. */
    UE_APPINFO_ILLEGAL("Callback参数名不合法","",401,"ue执行错误：Callback参数名不合法"),
    /** . */
    NULL(null,null,null,null);
	
    private String name;
    private String code;
    private Integer value;
    private String remark;
    
    UeAppInfoEnum(String name,String code,Integer value,String remark){
    	this.name = name;
    	this.code = code;
    	this.value= value;
    	this.remark=remark;
    }

	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public Integer getValue() {
		return value;
	}
	public String getRemark() {
		return remark;
	}
    
    public static UeAppInfoEnum getUeAppInfo(String code){
    	if(StringUtil.isEmpty(code)){
    		return UeAppInfoEnum.NULL;
    	}
    	UeAppInfoEnum[] _ueAppInfos = UeAppInfoEnum.values();
		for(UeAppInfoEnum ueAppInfo : _ueAppInfos){
			if(ueAppInfo.getCode().equals(code)){
				return ueAppInfo;
			}
		}
		return UeAppInfoEnum.NULL;
    }
    
}
