package sivl.platform.pay.service.impl;

import java.util.Calendar;
import java.util.Map;

import org.springframework.stereotype.Service;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.DateUtil;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.chinapay.web.ChinaWebUtil;
import sivl.platform.pay.service.PaymentService;

@Service("chinapayWebService")
public class ChinapayWebServiceImpl implements PaymentService {

	public ResultModel<Object> payment(Map<String, Object> params) {
		ResultModel<Object> result = new ResultModel<Object>();
		// 参数不为空验证
		PaymentsModel payment = new PaymentsModel(params);
		result = validatePayment(payment);
		if (result.getCode().equals(ResultCons.SUCCESS)) {
			// 调起支付支付
			result = ChinaWebUtil.payment(payment);
		}
		return result;
	}

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
		//金额转成分
		Double tradeFee = payment.getTradeFee();
		payment.setTradeFee(tradeFee*100);
		//超时支付
		String overtime = payment.getOvertime();
		if(StringUtil.isNotEmpty(overtime)){
			Calendar currentTime = Calendar.getInstance();
			currentTime.add(Calendar.MINUTE, Integer.parseInt(overtime));
			String _overtime = DateUtil.fmtDateToStr(currentTime.getTime(), "yyyyMMddHHmmss");
			payment.setOvertime(_overtime);
		}else{
			Calendar currentTime = Calendar.getInstance();
			currentTime.add(Calendar.DATE, 30);
			String _overtime = DateUtil.fmtDateToStr(currentTime.getTime(), "yyyyMMddHHmmss");
			payment.setOvertime(_overtime);
		}
		if(StringUtil.isEmpty(payment.getBody())){
			payment.setBody("银联渠道");
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
