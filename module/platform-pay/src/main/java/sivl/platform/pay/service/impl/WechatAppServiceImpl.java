package sivl.platform.pay.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.wxpay.app.WechatAppServiceUtil;
import sivl.platform.pay.sdk.wxpay.common.util.ValidataPayment;
import sivl.platform.pay.sdk.wxpay.publics.PublicServiceUtil;
import sivl.platform.pay.service.PaymentService;

@Service("wechatAppService")
public class WechatAppServiceImpl implements PaymentService{

	public ResultModel<Object> payment(Map<String, Object> params) {
		ResultModel<Object> result = new ResultModel<Object>();
		// 参数不为空验证
		PaymentsModel payment = new PaymentsModel(params);
		result = ValidataPayment.validatePayment(payment);
		if (result.getCode().equals(ResultCons.SUCCESS)) {
			// 调起支付支付
			result = WechatAppServiceUtil.payment(payment);
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
