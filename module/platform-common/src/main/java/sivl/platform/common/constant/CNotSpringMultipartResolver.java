package sivl.platform.common.constant;
/**
 * 不进行spring文件提交包装处理
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月18日
 * @version 1.0
 */
public enum CNotSpringMultipartResolver {

	/** UE不进行分装处理. */
	UE_NOT_RESOLVER("UE不处理","ueditorHandle","ueditorHandle","UE不进行分装处理"),
	
	NULL(null,null,null,null);
	
	private String name;
	private String code;
	private String value;
	private String remark;
	CNotSpringMultipartResolver(String name,String code,String value,String remark){
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
	public String getValue() {
		return value;
	}
	public String getRemark() {
		return remark;
	}
	
}
