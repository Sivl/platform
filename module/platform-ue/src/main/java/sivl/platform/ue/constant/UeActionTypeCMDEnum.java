package sivl.platform.ue.constant;


/**
 * 
 * 目的：
 * @author lixuefeng
 * @date 2016年1月13日
 * @version 1.0
 */
public enum UeActionTypeCMDEnum {
	/** 取得配置. */
	UE_CONFIG_CMD("配置","config",0),
	/** 上传图片. */
	UE_UPLOAD_IMAGE_CMD("上传图片","uploadimage",1),
	/** 上传涂鸦. */
	UE_UPLOAD_SCRAWL_CMD("上传涂鸦","uploadscrawl",2),
	/** 上传视频. */
	UE_UPLOAD_VIDEO_CMD("上传视频","uploadvideo",3),
	/** 上传文件. */
	UE_UPLOAD_FILE_CMD("上传文件","uploadfile",4),
	/** 抓取图片. */
	UE_CATCH_IMAGE_CMD("抓取图片","catchimage",5),
	/** 文件集合. */
	UE_LIST_FILE_CMD("文件集合","listfile",6),
	/** 图片集合. */
	UE_LIST_IMAGE_CMD("图片集合","listimage",7),
	NULL(null,null,null);
	
	private String name;
	private String code;
	private Integer value;
	
	UeActionTypeCMDEnum(String name,String code,Integer value){
		this.name = name;
		this.code = code;
		this.value= value;
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
	
	public static UeActionTypeCMDEnum getUeActionTypeCmd(String code){
		if(null == code){
			return UeActionTypeCMDEnum.NULL;
		}
		UeActionTypeCMDEnum[] _cmds = UeActionTypeCMDEnum.values();
		for(UeActionTypeCMDEnum actionCmd : _cmds){
			if(actionCmd.getCode().equals(code)){
				return actionCmd;
			}
		}
		return UeActionTypeCMDEnum.NULL;
	}
	
	
}
