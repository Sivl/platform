package sivl.platform.pay.sdk.wxpay.common.util;

import java.util.Calendar;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.DateUtil;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.enums.Channel;
import sivl.platform.pay.model.PaymentsModel;

public class ValidataPayment {
	
	public static ResultModel<Object> validatePayment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		if(payment.getChannel().equals(Channel.WECHAT_PUBLIC.getCode())){
			if(StringUtil.isEmpty(payment.getOpenId())){
				result.setMsg("openId不能为空");
				result.setCode(ResultCons.FAIL);
			}
		}
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
			//默认30天
			Calendar currentTime = Calendar.getInstance();
			currentTime.add(Calendar.DATE, 30);
			String _overtime = DateUtil.fmtDateToStr(currentTime.getTime(), "yyyyMMddHHmmss");
			payment.setOvertime(_overtime);
		}
		String body = payment.getBody();
		if(StringUtil.isEmpty(body)){
			payment.setBody("微信渠道");
		}else{
			if(body.length()>20){
				payment.setBody(body.substring(0,20).concat("..."));
			}
		}
		return result;
	}


}
