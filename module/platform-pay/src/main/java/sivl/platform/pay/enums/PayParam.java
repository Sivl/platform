package sivl.platform.pay.enums;

import sivl.platform.common.utils.StringUtil;

public enum PayParam {
	
	//系统域名
	PAY_HOST("pay.host","系统域名","http://zhoujobs.oicp.net/platform-admin"),
	//证书路径
	PAY_CERTS_PATH("pay.certs_path","系统证书路径","D:/develop/paycerts"),
	//支付宝参数
	PAY_ALIPAY_PARTNER("pay.alipay.partner","支付宝商户号","2088911130299423"),
	PAY_ALIPAY_KEY("pay.alipay.key","支付宝交易安全检验码","yiptzwcwk3nsbh2h3h3s0eiv4kynulcp"),
	PAY_ALIPAY_SELLER("pay.alipay.seller","支付宝卖家帐户","BDFLY6@163.COM"),
	PAY_ALIPAY_WAP_PUBLIC_KEY("pay.alipay.wap.public.key","支付宝WAP交易公钥","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnNgIudK1kM3d41qhLdR3x6TQe4r9+vp/C3p/ayL65B1M7EhY7s5oZJxjYnXUSiSxZXBXDHzWKEGM0/Z2gHzad2GRtmW9fMX38lvrvRcV8mvUH/Fj2JMRS3daPnVmajTbb+JnBGAeii6BHTh7J/iwrY1rCYpxQRQTIEVHZATUgWQIDAQAB"),
	PAY_ALIPAY_PRIVATE_KEY("pay.alipay.private.key","支付宝交易私钥","MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALv/BKdXURNP3RN1BqSEsbVKZT0jIMBZPAQKMnGewhh+tlI+SfKWfpzF9AzHbBw2LQhujL9qug4Aq+2swLUCKBaokDASRjoSd95vHDwbO+8732m1/7anzxKP7LZaDwT5NoTqXaD5Fo/k3D/rnScm+BJtVeWvmvdAuFfundnvKRp1AgMBAAECgYEArHLY/PwOxg1LeW6vAjkqnmc5COTxRSBV+kq0TR9ZYojRjA3RXXMJf5viK9q+4/4aPxLRj0FCnFenXqVKPIBW7iTy5E8ZXRf6hG9g/a7Vp/I/3V5X0y17bBgWvfBTFLvZ4KpmrIdWf13ONghr8uJaJbvLUzxWvfSDRsPMxbKe3uECQQD1oKAm2+05sD5vlPY+6D3UwkXkfVsHQ+v0rKCEbeDjJhMFX2atdvhmp++ZDtpQzEHHz8jB3jfVl/fjibyZ0IrJAkEAw+9dffoJQJmvG3Yh+eZaGA7hWl0YU59IKhfzOfthMsAOvdlIT0OiOQdvgPfEBcvqlIuoQmxnaOgoymrUa4Z8TQJAIDLJK67ivxAybxAY45AwIsbAwL4KA77MnItQk2uP8lbYYXjrDcwtrc9P4kGHLtZHvTiouXk22oIMVcCFf6hNuQJAB/Ym/2J0k8pq4hvHv6bLjGS2ZXyY63TycrtsZ7O+PFivgKleldHyuoyBzGTE81NY4dZW7PQIs6uTVL5IiWH6lQJAbszX6vPEVD5Xak88ANf5Vk5HU1sy2+BSh7quYqbREOiGq0LLCBuC/o1/IT22ogwy1p2eiSA26SbuRYIPXEM60Q=="),
	PAY_ALIPAY_APP_PUBLIC_KEY("pay.alipay.wap.public.key","支付宝APP交易公钥","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB"),
	//银联参数
	PAY_CHINAPAY_MERID("pay.chinapay.merId","银联商户代码","777290058110048"),
	//财付通参数
	PAY_TENPAY_PARTNER("pay.tenpay.partner","财付通商户代码","1234811401"),
	PAY_TENPAY_KEY("pay.tenpay.key","财付通安全检验码","LQT20140110hncs07318583862799NYF"),
	PAY_TENPAY_CERT_PATH("pay.tenpay.cert.path","财付通证书路径","/tenpay/1234811401_20150320165958.pfx"),
	PAY_TENPAY_CERT_PWD("pay.tenpay.cert.pwd","财付通退款证书密码","785093"),
	//财付通WAP参数，手机支付接口要签约开通（接口权限统一，参数可以省略）
	PAY_TENPAY_WAP_PARTNER("pay.tenpay.partner","财付通商户代码","1246914401"),
	PAY_TENPAY_WAP_KEY("pay.tenpay.key","财付通安全检验码","218b5081e9133d167fee3ce4588bd900"),
	PAY_TENPAY_WAP_CERT_PATH("pay.tenpay.cert.path","财付通证书路径","/qq/1246914401_20150604164715.pfx"),
	PAY_TENPAY_WAP_CERT_PWD("pay.tenpay.cert.pwd","财付通退款证书密码","147966"),
	//微信参数
	PAY_WECHAT_PARTNER("pay.wechat.partner","微信商户号","1236035802"),
	PAY_WECHAT_PARTNER_KEY("pay.wechat.partner.key","微信商户号对应密钥","hncslqt1234567812345678123456789"),
	PAY_WECHAT_APP_KEY("pay.wechat.app.key","微信安全检验码","L8LrMqqeGRxST5reouB0K66CaYAWpqhAVsq7ggKkxHCOastWksvuX1uvmvQclxaHoYd3ElNBrNO2DHnnzgfVG9Qs473M3DTOZug5er46FhuGofumV8H2FVR9qkjSlC5K"),
	PAY_WECHAT_APP_ID("pay.wechat.app.id","微信应用ID","wxb6e62cf7e8b9c502"),
	PAY_WECHAT_APP_SECRET("pay.wechat.app.secret","微信应用凭证","960657676ae91f020f8bd4bd50e1a530"),
	PAY_WECHAT_CERT_PATH("pay.wechat.cert.path","微信应用证书路径","/wx/test/apiclient_cert.p12"),
	PAY_WECHAT_CERT_PWD("pay.wechat.cert.pwd","微信应用证书密码","1236035802"),
	//微信APP参数（接口权限统一，参数可以省略）
	PAY_WECHAT_PARTNER_APP("pay.wechat.partner.app","微信商户号","1219977501"),
	PAY_WECHAT_PARTNER_KEY_APP("pay.wechat.partner.key.app","微信商户号对应密钥","510253c6d156990fbfdf6feab922c1e6"),
	PAY_WECHAT_APP_KEY_APP("pay.wechat.app.key.app","微信安全检验码","jnbrWCHLWVjTa6kLBIUIfm2eZno6nTX8iDY1Pi4k0J4sOvtGzmYh9D3MICaGKLLvAiCrEIyweEZNTxuAwWZhSuVAxJWixXcmCuYrWcLNNof4ybyDFkFxoDVrnPXrLbAy"),
	PAY_WECHAT_APP_ID_APP("pay.wechat.app.id.app","微信应用ID","wxab37c2f3bd7837d6"),
	PAY_WECHAT_APP_SECRET_APP("pay.wechat.app.secret.app","微信应用凭证","75ccdaddeb2677f79ca0cb9b5c143b23"),
	PAY_WECHAT_CERT_PATH_APP("pay.wechat.cert.path.app","微信应用证书路径","/wxapp/online/apiclient_cert.p12"),
	PAY_WECHAT_CERT_PWD_APP("pay.wechat.cert.pwd.app","微信应用证书密码","1219977501"),
	
	NULL(null,null,null);
	
	private String code;
	private String name;
	private String value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	PayParam(String code, String name,String value) {
		this.code = code;
		this.name = name;
		this.value = value;
	}
	
	public static PayParam getPayParam(String code){
		if(StringUtil.isEmpty(code)){
			return PayParam.NULL;
		}
		PayParam[] _param = PayParam.values();
		for(PayParam param : _param){
			if(param.getCode().equals(code)){
				return param;
			}
		}
		return PayParam.NULL;
	}

}
