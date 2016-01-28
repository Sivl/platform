package sivl.platform.pay.sdk.alipay.common.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = null;
	// 商户的私钥
	public static String key = null;
	// 卖家账户
	public static String seller = null;
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = null;
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	// web支付前台通知
	public static String web_payment_return = null;
	// web支付异步通知
	public static String web_payment_notify = null;
	// 退款异步通知
	public static String refundment_notify = null;
	// 支付接口
	public static String service_payment = "create_direct_pay_by_user";
	// 支付类型
	public static String payment_type = "1";
	// 超时时间
	public static String it_b_pay = "15d";

	// -----------------wap参数---------------------------
	//WAP签名方式
	public static String wap_sign_type = "0001";
	// 支付宝网关地址
	public static String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
	// 授权接口
	public static String service_auth = "alipay.wap.trade.create.direct";
	// 交易接口
	public static String service_trade = "alipay.wap.auth.authAndExecute";
	// 无线支付私钥
	public static String private_key = null;
	// wap专用公钥
	public static String wap_public_key = null;
	// wap支付前台通知
	public static String wap_payment_return = null;
	// wap支付异步通知
	public static String wap_payment_notify = null;
	//默认超时时间
	public static String pay_expire = "21600";
	// ------------------app参数---------------------------
	//交易接口
	public static String app_service = "mobile.securitypay.pay";
	// 签名方式 不需修改
	public static String app_sign_type = "RSA";
	// app专用公钥与wap公用私钥
	public static String app_public_key = null;
	// app支付异步通知
	public static String app_payment_notify = null;

}
