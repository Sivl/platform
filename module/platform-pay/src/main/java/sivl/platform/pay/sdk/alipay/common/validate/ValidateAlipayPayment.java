package sivl.platform.pay.sdk.alipay.common.validate;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.enums.Channel;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.alipay.common.config.AlipayConfig;

public class ValidateAlipayPayment {
	
	/**
	 * 支付参数校验
	 * 
	 * @param payment
	 *            支付对象
	 * @return result
	 */
	public static ResultModel<Object> validatePayment(PaymentsModel payment) {
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
			if(payment.getChannel()==Channel.ALIPAY_WAP.getCode()){
				payment.setOvertime(AlipayConfig.pay_expire);
			}else{
				payment.setOvertime(AlipayConfig.it_b_pay);
			}
		}else{
			if(payment.getChannel()==Channel.ALIPAY_WAP.getCode()){
				payment.setOvertime(overTime);
			}else{
				payment.setOvertime(overTime.concat("m"));
			}
		}
		return result;
	}

}
