package sivl.platform.pay.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.JSONUtil;
import sivl.platform.common.utils.MapUtil;
import sivl.platform.pay.constant.ResultCons;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-pay.xml" })
public class PaymentServiceTest {

	@Autowired
	private OpenService openService;

	@Test
	public void payment() {
		String uuid = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		uuid += System.currentTimeMillis();
		String params = "{\"outTradeNo\":"+uuid+",\"totalFee\":\"0.01\",\"channel\":\"10\",\"subject\":\"挂号付费\",\"body\":\"挂号付费-湘雅医院-皮肤科，王毅\",\"overtime\":\"30m\"}";
		Map<String, Object> maps = JSONUtil.json2map(params);
		Integer code = MapUtil.getInt(maps, "channel");
		PaymentService paymentService = openService.getInstance(code);
		ResultModel<Object> result = new ResultModel<Object>();
		result = paymentService.payment(maps);
		System.out.println(JSONUtil.obj2json(result));
	}

	
	public static void main(String[] args){
		String ip = "127.0.0.1";
		ip = ip.replaceAll("\\.", "%2E");
		System.out.println(ip);
		String params = "{\"outTradeNo\":\"123456\",\"totalFee\":\"0.01\",\"channel\":\"10\",\"subject\":\"挂号付费\",\"body\":\"挂号付费-湘雅医院-皮肤科，王毅\",\"overtime\":\"30\"}";
		Map<String, Object> maps = JSONUtil.json2map(params);
		ResultModel<Object> result = new ResultModel<Object>();
		result.setMsg(ResultCons.SUCCESS_MSG);
		result.setCode(ResultCons.SUCCESS);
		result.setData(ip);
		result.setExt(maps);
		System.out.println(JSONUtil.obj2json(result));
	}
}
