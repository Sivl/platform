package sivl.platform.common.constant;

/**
 * 文件操作枚举
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月15日
 * @version 1.0
 */
public enum CFileOptionEnum {
	/** 操作成功.*/
	FO_SUCCESS("操作成功","f_success",1,"文件创建成功",null),
	/** 超出大小.*/
	FO_MAX_SIZE("超出大小","f_max_size",2,"文件超出配置大小",null),
	/** 无权限.*/
	FO_NO_PERMISSION("无权限","f_no_permission",3,"无文件操作权限",null),
	/** 失败.*/
	FO_FAILED_CREATE("失败","f_failed_create",4,"文件创建失败",null),
	/** io异常.*/
	FO_IO_ERROR("io异常","f_io_error",5,"文件创建io异常",null),
	/** 不允许的文件类型. */
	FO_IO_NOT_ALLOW_FILE_TYPE("不允许的文件类型","f_io_not_allow_file_type",8,"ue执行错误：不允许的文件类型",null),
	/** 被阻止的远程主机. */
	FO_IO_PREVENT_HOST("被阻止的远程主机","f_io_prevent_host",201,"ue执行错误：被阻止的远程主机",null),
    /** 远程连接出错. */
	FO_IO_CONNECTION_ERROR("远程连接出错","f_io_connection_error",202,"ue执行错误：远程连接出错",null),
    /** 抓取远程图片失败. */
	FO_IO_REMOTE_FAIL("抓取远程图片失败","f_io_remote_fail",203,"ue执行错误：抓取远程图片失败",null),
	NULL(null,null,null,null,null);
	
	private String name;
	private String code;
	private Integer value;
	private String remark;
	private Object object;
	CFileOptionEnum(String name,String code,Integer value,String remark,Object object){
		this.name = name;
		this.code = code;
		this.value= value;
		this.remark=remark;
		this.object=object;
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
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
