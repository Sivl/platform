package sivl.platform.pay.sdk.wxpay.common;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:40
 * 这里放置各种配置数据
 */
public class Configure {

	//sdk的版本号
	private static final String sdkVersion = "java sdk 1.0.1";

	private static String key;

	//微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID;

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID;

	//受理模式下给子商户分配的子商户号
	private static String subMchID;

	//HTTPS证书的本地路径
	private static String certLocalPath;

	//HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword;

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;

	//机器IP
	private static String ip;
	
	//支付凭证
	private static String secret;
	
	//前台通知
	private static String front_url;
	
	//后台通知
	private static String back_url;
	
	//APP参数（账户权限统一，参数可以省略）--------------------------------------
	private static String key_app;
	//微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID_app;
	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID_app;
	//HTTPS证书的本地路径
	private static String certLocalPath_app;
	//HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword_app;
	//支付凭证
	private static String secret_app;
	//后台通知
	private static String app_back_url;
	//APP----------------------------------------------
	
	//以下是几个API的路径：
	//1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	//2）被扫支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	//统一下单API
	public static String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	//获取网页授权作用域
	public static String CODE_URL = "https://open.weixin.qq.com/connect/qrconnect";
	
	//授权
	public static String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}

	public static void setSubMchID(String subMchID) {
		Configure.subMchID = subMchID;
	}

	public static void setCertLocalPath(String certLocalPath) {
		Configure.certLocalPath = certLocalPath;
	}

	public static void setCertPassword(String certPassword) {
		Configure.certPassword = certPassword;
	}

	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}
	
	public static String getMchid(){
		return mchID;
	}

	public static String getSubMchid(){
		return subMchID;
	}
	
	public static String getCertLocalPath(){
		return certLocalPath;
	}
	
	public static String getCertPassword(){
		return certPassword;
	}

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}

	public static String getSdkVersion(){
		return sdkVersion;
	}

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		Configure.secret = secret;
	}

	public static String getFront_url() {
		return front_url;
	}

	public static void setFront_url(String front_url) {
		Configure.front_url = front_url;
	}

	public static String getBack_url() {
		return back_url;
	}

	public static void setBack_url(String back_url) {
		Configure.back_url = back_url;
	}

	public static String getKey_app() {
		return key_app;
	}

	public static void setKey_app(String key_app) {
		Configure.key_app = key_app;
	}

	public static String getAppID_app() {
		return appID_app;
	}

	public static void setAppID_app(String appID_app) {
		Configure.appID_app = appID_app;
	}

	public static String getMchID_app() {
		return mchID_app;
	}

	public static void setMchID_app(String mchID_app) {
		Configure.mchID_app = mchID_app;
	}

	public static String getCertLocalPath_app() {
		return certLocalPath_app;
	}

	public static void setCertLocalPath_app(String certLocalPath_app) {
		Configure.certLocalPath_app = certLocalPath_app;
	}

	public static String getCertPassword_app() {
		return certPassword_app;
	}

	public static void setCertPassword_app(String certPassword_app) {
		Configure.certPassword_app = certPassword_app;
	}

	public static String getSecret_app() {
		return secret_app;
	}

	public static void setSecret_app(String secret_app) {
		Configure.secret_app = secret_app;
	}

	public static String getApp_back_url() {
		return app_back_url;
	}

	public static void setApp_back_url(String app_back_url) {
		Configure.app_back_url = app_back_url;
	}
	
	
}
