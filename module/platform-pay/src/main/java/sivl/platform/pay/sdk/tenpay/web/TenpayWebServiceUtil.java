package sivl.platform.pay.sdk.tenpay.web;

import java.io.UnsupportedEncodingException;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.JSONUtil;
import sivl.platform.common.utils.MapUtil;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.tenpay.common.RequestHandler;
import sivl.platform.pay.sdk.tenpay.common.TenpayConfig;
import sivl.platform.pay.sdk.tenpay.common.util.TenpayUtil;

public class TenpayWebServiceUtil {

	public static ResultModel<Object> payment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(payment.getRequest(),
				payment.getResponse());
		reqHandler.init();
		// 设置密钥
		reqHandler.setKey(TenpayConfig.getKey());
		// 设置支付网关
		reqHandler.setGateUrl(TenpayConfig.PAY_WEB_API);

		// -----------------------------
		// 设置支付参数
		// -----------------------------
		reqHandler.setParameter("partner", TenpayConfig.getPartner()); // 商户号
		reqHandler.setParameter("out_trade_no", payment.getOutTradeNo()); // 商家订单号
		reqHandler.setParameter("total_fee",
				String.valueOf(payment.getTradeFee().intValue())); // 商品金额,以分为单位
		reqHandler.setParameter("return_url", TenpayConfig.getWeb_return_url()); // 交易完成后跳转的URL
		reqHandler.setParameter("notify_url", TenpayConfig.getWeb_notify_url()); // 接收财付通通知的URL
		reqHandler.setParameter("body", payment.getBody()); // 商品描述
		reqHandler.setParameter("bank_type", "DEFAULT"); // 银行类型(中介担保时此参数无效)
		reqHandler.setParameter("spbill_create_ip", payment.getCreateIp()); // 用户的公网ip，不是商户服务器IP
		reqHandler.setParameter("fee_type", "1"); // 币种，1人民币
		reqHandler.setParameter("subject", payment.getSubject()); // 商品名称(中介交易时必填)

		// 系统可选参数
		reqHandler.setParameter("sign_type", "MD5"); // 签名类型,默认：MD5
		reqHandler.setParameter("service_version", "1.0"); // 版本号，默认为1.0
		reqHandler.setParameter("input_charset", TenpayConfig.CHARSET); // 字符编码
		reqHandler.setParameter("sign_key_index", "1"); // 密钥序号

		// 业务可选参数
		reqHandler.setParameter("time_start", TenpayUtil.getCurrTime()); // 订单生成时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("time_expire", payment.getOvertime()); // 订单失效时间，格式为yyyymmddhhmmss
		
		// 请求的url
		try {
			String url = reqHandler.getRequestURL();
			if (StringUtil.isEmpty(url)) {
				result.setMsg("财富支付链接获取异常");
				result.setCode(ResultCons.FAIL);
			} else {
				result.setMsg(ResultCons.SUCCESS_MSG);
				result.setCode(ResultCons.SUCCESS);
				result.setData(url);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result.setMsg("财富支付异常：" + e.getMessage());
			result.setCode(ResultCons.FAIL);
		}
		// 获取debug信息,建议把请求和debug信息写入日志，方便定位问题
		String debuginfo = reqHandler.getDebugInfo();
		System.out.println("debug:" + debuginfo);
		return result;
	}

}
