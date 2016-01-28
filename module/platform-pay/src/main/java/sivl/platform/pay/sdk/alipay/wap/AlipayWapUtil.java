package sivl.platform.pay.sdk.alipay.wap;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.alipay.common.config.AlipayConfig;
import sivl.platform.pay.sdk.alipay.common.util.UtilDate;

/**
 * 支付宝无线快捷支付
 * 区别web支付：采用RSA签名（安全性更好）
 * @author Zhouxiang
 *
 */
public class AlipayWapUtil {

	/**
	 * 调用支付宝支付接口
	 * 
	 * @param payments
	 * @return
	 */
	public static ResultModel<Object> payment(PaymentsModel payments) {
		ResultModel<Object> result = new ResultModel<Object>();
		result = getToken(payments);
		if(result.getCode().equals(ResultCons.FAIL)){
			return result;
		}
		String req_data = "<auth_and_execute_req><request_token>"
				+ result.getData() + "</request_token></auth_and_execute_req>";

		Map<String, String> tradeMap = new HashMap<String, String>();
		
		try {
			tradeMap.put("service", AlipayConfig.service_trade);
			tradeMap.put("partner", AlipayConfig.partner);
			tradeMap.put("_input_charset", AlipayConfig.input_charset);
			tradeMap.put("sec_id", AlipayConfig.wap_sign_type);
			tradeMap.put("format", "xml");
			tradeMap.put("v", "2.0");
			tradeMap.put("req_data", req_data);
			String html = AlipayWapSubmit.buildRequest(
					AlipayConfig.ALIPAY_GATEWAY_NEW, tradeMap, "get", "确认");
			if (StringUtil.isNotEmpty(html)) {
				result.setMsg(ResultCons.SUCCESS_MSG);
				result.setCode(ResultCons.SUCCESS);
				result.setData(html);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("支付宝WAP支付授权token请求异常：" + e.getMessage());
			result.setCode(ResultCons.FAIL);
		}
		
		return result;
	}

	/**
	 * wap支付授权token
	 * 
	 * @return
	 */
	private static ResultModel<Object> getToken(PaymentsModel payments) {
		ResultModel<Object> result = new ResultModel<Object>();
		String req_dataToken = "<direct_trade_create_req>" + "<notify_url>"
				+ AlipayConfig.wap_payment_notify + "</notify_url>"
				+ "<call_back_url>" + AlipayConfig.wap_payment_return
				+ "</call_back_url>" + "<seller_account_name>"
				+ AlipayConfig.seller + "</seller_account_name>"
				+ "<out_trade_no>" + payments.getOutTradeNo()
				+ "</out_trade_no>" + "<subject>" + payments.getSubject()
				+ "</subject>" + "<total_fee>" + payments.getTradeFee()
				+ "</total_fee>" + "<merchant_url></merchant_url>"
				+ "<pay_expire>" + payments.getOvertime() +"</pay_expire>"
				+ "</direct_trade_create_req>";
		System.out.println(req_dataToken);
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("service", AlipayConfig.service_auth);
		tokenMap.put("partner", AlipayConfig.partner);
		tokenMap.put("_input_charset", AlipayConfig.input_charset);
		tokenMap.put("sec_id", AlipayConfig.wap_sign_type);
		tokenMap.put("format", "xml");
		tokenMap.put("v", "2.0");
		tokenMap.put("req_id", UtilDate.getOrderNum());
		tokenMap.put("req_data", req_dataToken);
		System.out.println(tokenMap.toString());
		try {
			// 建立请求
			String token = AlipayWapSubmit.buildRequest(
					AlipayConfig.ALIPAY_GATEWAY_NEW, "", "", tokenMap);
			// URLDECODE返回的信息
			token = URLDecoder.decode(token, AlipayConfig.input_charset);
			System.out.println(token);
			// 获取token
			String request_token = AlipayWapSubmit.getRequestToken(token);
			if (StringUtil.isNotEmpty(request_token)) {
				result.setMsg(ResultCons.SUCCESS_MSG);
				result.setCode(ResultCons.SUCCESS);
				result.setData(request_token);
			}else{
				result.setMsg("支付宝WAP支付授权token获取失败");
				result.setCode(ResultCons.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("支付宝WAP支付授权token请求异常：" + e.getMessage());
			result.setCode(ResultCons.FAIL);
		}
		return result;
	}

}
