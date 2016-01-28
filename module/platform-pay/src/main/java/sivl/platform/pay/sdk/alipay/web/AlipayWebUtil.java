package sivl.platform.pay.sdk.alipay.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.alipay.common.config.AlipayConfig;
import sivl.platform.pay.sdk.alipay.common.util.AlipayNotify;
import sivl.platform.pay.sdk.alipay.common.util.AlipaySubmit;

public class AlipayWebUtil {

	/**
	 * 调用支付宝支付接口
	 * @param payments
	 * @return
	 */
	public static ResultModel<Object> payment(PaymentsModel payments) {
		ResultModel<Object> result = new ResultModel<Object>();
		try {
			Map<String, String> param = new HashMap<String, String>();
			// 把请求参数打包成数组
			param.put("service", AlipayConfig.service_payment);
			param.put("partner", AlipayConfig.partner);
			param.put("_input_charset", AlipayConfig.input_charset);
			param.put("payment_type", AlipayConfig.payment_type);
			param.put("return_url", AlipayConfig.web_payment_return);
			param.put("notify_url", AlipayConfig.web_payment_notify);
			param.put("seller_email", AlipayConfig.seller);
			param.put("out_trade_no", payments.getOutTradeNo());
			param.put("subject", payments.getSubject());
			param.put("total_fee", Double.toString(payments.getTradeFee()));
			param.put("body", payments.getBody());
			param.put("it_b_pay", payments.getOvertime());
			String table = AlipaySubmit.buildRequest(param, "post", "确认");
			result.setMsg(ResultCons.SUCCESS_MSG);
			result.setCode(ResultCons.SUCCESS);
			result.setData(table);
		} catch (Exception e) {
			result.setCode(ResultCons.FAIL);
			result.setMsg("发起支付宝请求异常："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 支付宝回调校验
	 * @param payments
	 * @return
	 */
	public static ResultModel<Object> paymentVerify(PaymentsModel payments){
		HttpServletRequest request = payments.getRequest();
		ResultModel<Object> result = new ResultModel<Object>();
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		Map<String,Object> rsMap = new HashMap<String, Object>();
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no"));
		rsMap.put("out_trade_no", out_trade_no);
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no"));
		rsMap.put("trade_no", trade_no);
		//交易状态
		String trade_status = new String(request.getParameter("trade_status"));
		rsMap.put("trade_status", trade_status);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){
			result.setMsg(ResultCons.SUCCESS_MSG);
			result.setCode(ResultCons.SUCCESS);
		}else{
			result.setMsg(ResultCons.FAIL_MSG.concat("支付宝回调,参数验证失败。"));
			result.setCode(ResultCons.FAIL);
		}
		result.setExt(rsMap);
		result.setData(rsMap);
		return result;
	}

}
