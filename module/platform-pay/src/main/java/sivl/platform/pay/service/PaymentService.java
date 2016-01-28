package sivl.platform.pay.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import sivl.platform.common.model.ResultModel;

@Component
public interface PaymentService {
	
	//发起支付接口
	public abstract ResultModel<Object> payment(Map<String, Object> params);
	
	//退款接口
	public abstract ResultModel<Object> refundment(Map<String, Object> params);
	
	//提现接口:类似代发工资
	public abstract ResultModel<Object> withdrawal(Map<String, Object> params);
	
	//结果接口查询接口
	public abstract ResultModel<Object> getResult(Map<String, Object> params);
	
	//对账接口
	public abstract ResultModel<Object> checking(Map<String, Object> params);

}
