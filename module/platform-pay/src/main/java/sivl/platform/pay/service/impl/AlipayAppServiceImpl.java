package sivl.platform.pay.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.alipay.app.AlipayAppUtil;
import sivl.platform.pay.sdk.alipay.common.validate.ValidateAlipayPayment;
import sivl.platform.pay.service.PaymentService;

@Service("alipayAppService")
public class AlipayAppServiceImpl implements PaymentService {

	/**
	 * 发起支付接口
	 */
	public ResultModel<Object> payment(Map<String, Object> params) {
		ResultModel<Object> result = new ResultModel<Object>();
		// 参数不为空验证
		PaymentsModel payment = new PaymentsModel(params);
		result = ValidateAlipayPayment.validatePayment(payment);
		if(result.getCode().equals(ResultCons.SUCCESS)){
			//调起支付支付
			result = AlipayAppUtil.payment(payment);
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
