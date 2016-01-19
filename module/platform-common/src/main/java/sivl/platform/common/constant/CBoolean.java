package sivl.platform.common.constant;

/**
 * boolan 枚举
 * 目的：
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
public enum CBoolean {
	
	TRUE("是","true",1,true),
	FALSE("否","false",0,false),
	NULL(null,null,-1,null);
	
	private String name;
	private String code;
	private int intValue;
	private java.lang.Boolean value;
	
	CBoolean(String name,String code,int intValue,java.lang.Boolean value){
		this.name = name;
		this.code = code;
		this.value= value;
		this.intValue=intValue;
	}

	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public int getIntValue() {
		return intValue;
	}
	public java.lang.Boolean isValue() {
		return value;
	}
	
}
