package sivl.platform.pay.sdk.chinapay.web;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.chinapay.common.config.ChinapayConfig;
import sivl.platform.pay.sdk.chinapay.common.sdk.SDKConfig;
import sivl.platform.pay.sdk.chinapay.common.sdk.SDKUtil;

public class ChinaWebUtil {

	public static ResultModel<Object> payment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		try {
			Map<String, String> requestData = new HashMap<String, String>();

			/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
			requestData.put("version", SDKUtil.version); // 版本号，全渠道默认值
			requestData.put("encoding", SDKUtil.encoding_UTF8); // 字符集编码，可以使用UTF-8,GBK两种方式
			requestData.put("signMethod", "01"); // 签名方法，只支持 01：RSA方式证书加密
			requestData.put("txnType", "01"); // 交易类型 ，01：消费
			requestData.put("txnSubType", "01"); // 交易子类型， 01：自助消费
			requestData.put("bizType", "000201"); // 业务类型，B2C网关支付，手机wap支付
			requestData.put("channelType", "07"); // 渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板
													// 08：手机
			/*** 商户接入参数 ***/
			requestData.put("merId", ChinapayConfig.merId); // 商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
			requestData.put("accessType", "0"); // 接入类型，0：直连商户
			requestData.put("orderId", payment.getOutTradeNo()); // 商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
			requestData.put("txnTime", ChinapayConfig.getCurrentTime()); // 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
			requestData.put("currencyCode", "156"); // 交易币种（境内商户一般是156 人民币）
			requestData.put("txnAmt", new DecimalFormat("#").format(payment.getTradeFee())); // 交易金额，单位分，不要带小数点
			requestData.put("reqReserved", payment.getBody()); // 请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节
			requestData.put("payTimeout", payment.getOvertime());
			requestData.put("frontUrl", ChinapayConfig.frontUrl);

			// 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d
			// 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
			 requestData.put("backUrl", ChinapayConfig.backUrl);

			/** 请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面 **/
			Map<String, String> submitFromData = SDKUtil.signData(requestData,
					SDKUtil.encoding_UTF8); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

			String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl(); // 获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
			String html = SDKUtil.createAutoFormHtml(requestFrontUrl,
					submitFromData, SDKUtil.encoding_UTF8); // 生成自动跳转的Html表单
			result.setMsg(ResultCons.SUCCESS_MSG);
			result.setCode(ResultCons.SUCCESS);
			result.setData(html);
		} catch (Exception e) {
			result.setMsg("银联支付接口异常："+e.getMessage());
			result.setCode(ResultCons.FAIL);
		}

		return result;
	}

}
