package sivl.platform.pay.sdk.tenpay.common;

public class TenpayConfig {

	// 收款方
	private static String spname;
	// 商户号
	private static String partner;
	// 密钥
	private static String key;
	// 证书路径
	private static String cert_path;
	// 证书密码
	private static String cert_pwd;
	// 交易完成后跳转的URL
	private static String web_return_url;
	// 接收财付通通知的URL
	private static String web_notify_url;
	//手机支付参数（账户权限统一，参数可以省略）--------------------------
	// 商户号
	private static String wap_partner;
	// 密钥
	private static String wap_key;
	// 证书路径
	private static String wap_cert_path;
	// 证书密码
	private static String wap_cert_pwd;
	// 交易完成后跳转的URL
	private static String wap_return_url;
	// 接收财付通通知的URL
	private static String wap_notify_url;
	//手机支付参数（账户权限统一，参数可以省略）--------------------------

	public static String PAY_WEB_API = "https://gw.tenpay.com/gateway/pay.htm";
	public static String PAY_WAP_API = "https://wap.tenpay.com/cgi-bin/wappayv2.0/wappay_init.cgi";
	public static String CHARSET = "UTF-8";

	public static String getSpname() {
		return spname;
	}

	public static void setSpname(String spname) {
		TenpayConfig.spname = spname;
	}

	public static String getPartner() {
		return partner;
	}

	public static void setPartner(String partner) {
		TenpayConfig.partner = partner;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		TenpayConfig.key = key;
	}

	public static String getWeb_return_url() {
		return web_return_url;
	}

	public static void setWeb_return_url(String web_return_url) {
		TenpayConfig.web_return_url = web_return_url;
	}

	public static String getWeb_notify_url() {
		return web_notify_url;
	}

	public static void setWeb_notify_url(String web_notify_url) {
		TenpayConfig.web_notify_url = web_notify_url;
	}

	public static String getWap_return_url() {
		return wap_return_url;
	}

	public static void setWap_return_url(String wap_return_url) {
		TenpayConfig.wap_return_url = wap_return_url;
	}

	public static String getWap_notify_url() {
		return wap_notify_url;
	}

	public static void setWap_notify_url(String wap_notify_url) {
		TenpayConfig.wap_notify_url = wap_notify_url;
	}

	public static String getCert_path() {
		return cert_path;
	}

	public static void setCert_path(String cert_path) {
		TenpayConfig.cert_path = cert_path;
	}

	public static String getCert_pwd() {
		return cert_pwd;
	}

	public static void setCert_pwd(String cert_pwd) {
		TenpayConfig.cert_pwd = cert_pwd;
	}

	public static String getWap_partner() {
		return wap_partner;
	}

	public static void setWap_partner(String wap_partner) {
		TenpayConfig.wap_partner = wap_partner;
	}

	public static String getWap_key() {
		return wap_key;
	}

	public static void setWap_key(String wap_key) {
		TenpayConfig.wap_key = wap_key;
	}

	public static String getWap_cert_path() {
		return wap_cert_path;
	}

	public static void setWap_cert_path(String wap_cert_path) {
		TenpayConfig.wap_cert_path = wap_cert_path;
	}

	public static String getWap_cert_pwd() {
		return wap_cert_pwd;
	}

	public static void setWap_cert_pwd(String wap_cert_pwd) {
		TenpayConfig.wap_cert_pwd = wap_cert_pwd;
	}
	
}
