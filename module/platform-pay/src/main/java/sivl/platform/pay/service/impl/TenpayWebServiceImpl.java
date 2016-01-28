package sivl.platform.pay.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.tenpay.common.validate.ValidateTenpayPayment;
import sivl.platform.pay.sdk.tenpay.web.TenpayWebServiceUtil;
import sivl.platform.pay.service.PaymentService;

@Service("tenpayWebService")
public class TenpayWebServiceImpl implements PaymentService{

	public ResultModel<Object> payment(Map<String, Object> params) {
		ResultModel<Object> result = new ResultModel<Object>();
		// 参数验证
		PaymentsModel payment = new PaymentsModel(params);
		result = ValidateTenpayPayment.validatePayment(payment);
		if (result.getCode().equals(ResultCons.SUCCESS)) {
			// 调起支付支付
			result = TenpayWebServiceUtil.payment(payment);
		}
		return result;
	}

	public ResultModel<Object> refundment(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultModel<Object> withdrawal(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultModel<Object> getResult(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultModel<Object> checking(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
