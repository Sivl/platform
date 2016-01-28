package sivl.platform.pay.enums;

public enum Channel {

	ALIPAY_WEB(10, "支付宝WEB支付", "alipayWebService",true), 
	ALIPAY_WAP(11,"支付宝WAP支付", "alipayWapService",false),
	ALIPAY_APP(12, "支付宝APP支付","alipayAppService",false),

	CHINAPAY_WEB(13, "银联WEB支付", "chinapayWebService",true), 
	CHINAPAY_WAP(14,"银联WAP支付", "chinapayWapService",false), 
	CHINAPAY_APP(15, "银联APP支付","chinapayAppService",false),

	TENPAY_WEB(16, "财付通WEB支付", "tenpayWebService",true), 
	TENPAY_WAP(17,"财付通WAP支付", "tenpayWapService",false), 
	TENPAY_APP(18, "财付通APP支付","tenpayAppService",false),

	WECHAT_NATIVE(19, "微信扫码支付", "wechatNativeService",true), 
	WECHAT_PUBLIC(20,"微信公众号支付", "wechatPublicService",false), 
	WECHAT_APP(21, "微信APP支付","wechatAppService",false), 
	WECHAT_WAP(22, "微信WAP支付","wechatWapService",false), 
	
    NULL(null, null, null,false);

	private Integer code;//渠道编号
	private String name;//渠道名称
	private String bean;//实现bean名称
	private boolean check;//是否对账

	public static Channel getChannel(Integer code) {
		if (code == null) {
			return Channel.NULL;
		}
		Channel[] _channel = Channel.values();
		for(Channel channel:_channel){
			if(channel.getCode()==code){
				return channel;
			}
		}
		return Channel.NULL;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}
	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	Channel(Integer code, String name, String bean,Boolean check) {
		this.code = code;
		this.name = name;
		this.bean = bean;
		this.check = check;
	}

}
