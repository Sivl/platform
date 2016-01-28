package sivl.platform.pay.sdk.alipay.app;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.alipay.common.config.AlipayConfig;
import sivl.platform.pay.sdk.alipay.common.sign.RSA;
import sivl.platform.pay.sdk.alipay.common.util.UtilDate;
import sivl.platform.pay.sdk.alipay.wap.AlipayWapSubmit;

/**
 * 支付宝无线快捷支付 区别web支付：采用RSA签名（安全性更好）
 * 
 * @author Zhouxiang
 * 
 */
public class AlipayAppUtil {

	/**
	 * 调用支付宝支付接口
	 * 
	 * @param payments
	 * @return
	 */
	public static ResultModel<Object> payment(PaymentsModel payments) {
		ResultModel<Object> result = new ResultModel<Object>();
		Map<String, Object> requestParams = new HashMap<String, Object>();
		requestParams.put("partner", AlipayConfig.partner);
		requestParams.put("seller_id", AlipayConfig.seller);
		requestParams.put("out_trade_no", payments.getOutTradeNo());
		requestParams.put("subject", payments.getSubject());
		requestParams.put("body", payments.getBody());
		requestParams.put("total_fee", Double.toString(payments.getTradeFee()));
		requestParams.put("notify_url", AlipayConfig.app_payment_notify);
		requestParams.put("service", AlipayConfig.app_service);
		requestParams.put("payment_type", AlipayConfig.payment_type);
		requestParams.put("_input_charset", AlipayConfig.input_charset);
		requestParams.put("it_b_pay", payments.getOvertime());
		StringBuffer contentValues = new StringBuffer();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String value = (String) requestParams.get(name);
			contentValues.append(name + "=\"").append(value + "\"&");
		}
		
		String content = contentValues.toString().substring(0,
				contentValues.toString().length() - 1);
		
		String sign = RSA.sign(content, AlipayConfig.private_key,
				AlipayConfig.input_charset);
		try {
			sign = URLEncoder.encode(sign, AlipayConfig.input_charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		contentValues.append("sign=\"").append(sign)
				.append("\"&sign_type=\"" + AlipayConfig.app_sign_type + "\"");
		
		String contents = contentValues.toString();
		if(StringUtil.isNotEmpty(contents)){
			result.setMsg(ResultCons.SUCCESS_MSG);
			result.setCode(ResultCons.SUCCESS);
			result.setData(contents);
		}else{
			result.setMsg(ResultCons.FAIL_MSG);
			result.setCode(ResultCons.FAIL);
			result.setData(contents);
		}

		return result;
	}

}
