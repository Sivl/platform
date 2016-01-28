package sivl.platform.pay.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.alipay.common.config.AlipayConfig;
import sivl.platform.pay.sdk.alipay.web.AlipayWebUtil;
import sivl.platform.pay.service.PaymentService;

@Service("alipayWebService")
public class AlipayWebServiceImpl implements PaymentService {

	/**
	 * 发起支付接口
	 */
	public ResultModel<Object> payment(Map<String, Object> params) {
		ResultModel<Object> result = new ResultModel<Object>();
		// 参数不为空验证
		PaymentsModel payment = new PaymentsModel(params);
		result = validatePayment(payment);
		if(result.getCode().equals(ResultCons.SUCCESS)){
			//调起支付支付
			result = AlipayWebUtil.payment(payment);
		}
		return result;
	}

	/**
	 * 支付参数校验
	 * 
	 * @param payment
	 *            支付对象
	 * @return result
	 */
	private ResultModel<Object> validatePayment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		if (StringUtil.isEmpty(payment.getOutTradeNo())) {
			result.setMsg("商户订单号不能为空");
			result.setCode(ResultCons.FAIL);
		}else if (payment.getTradeFee()== null) {
			result.setMsg("订单金额不能为空");
			result.setCode(ResultCons.FAIL);
		}else if(payment.getTradeFee() <= 0){
			result.setMsg("订单金额不能小于0");
			result.setCode(ResultCons.FAIL);
		}else{
			result.setMsg(ResultCons.SUCCESS_MSG);
			result.setCode(ResultCons.SUCCESS);
		}
		String body = payment.getBody();
		if(StringUtil.isNotEmpty(body)
		   && body.length()>20){
			body = body.substring(0, 20).concat("...");
			payment.setBody(body);
		}
		String overTime = payment.getOvertime();
		if(StringUtil.isEmpty(overTime)){
			payment.setOvertime(AlipayConfig.it_b_pay);
		}else{
			payment.setOvertime(overTime+"m");
		}
		return result;
	}

	public ResultModel<Object> refundment(Map<String, Object> params) {
		return null;
	}

	public ResultModel<Object> withdrawal(Map<String, Object> params) {
		return null;
	}

	public ResultModel<Object> getResult(Map<String, Object> params) {
		return null;
	}

	public ResultModel<Object> checking(Map<String, Object> params) {
		return null;
	}

}
