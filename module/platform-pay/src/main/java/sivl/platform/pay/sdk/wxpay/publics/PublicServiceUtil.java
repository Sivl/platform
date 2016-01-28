package sivl.platform.pay.sdk.wxpay.publics;

import java.util.HashMap;
import java.util.Map;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.wxpay.common.Configure;
import sivl.platform.pay.sdk.wxpay.common.HttpsRequest;
import sivl.platform.pay.sdk.wxpay.common.Util;
import sivl.platform.pay.sdk.wxpay.model.WechatPayReqData;
import sivl.platform.pay.sdk.wxpay.model.WechatPayResData;

public class PublicServiceUtil {

	public static ResultModel<Object> payment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		WechatPayReqData wechat = new WechatPayReqData(payment);
		try {
			String xml = new HttpsRequest().sendPost(Configure.UNIFIED_ORDER,
					wechat);
			WechatPayResData res=(WechatPayResData) Util.getObjectFromXML(xml,WechatPayResData.class);
			if(res.getReturn_code().equals("SUCCESS")
				&& res.getResult_code().equals("SUCCESS")){
				Map<String,Object> maps = new HashMap<String,Object>();
				maps.put("appId", res.getAppid());
				maps.put("timeStamp", String.valueOf(System.currentTimeMillis()/1000));
				maps.put("nonceStr", res.getNonce_str());
				maps.put("package", "prepay_id=".concat(res.getPrepay_id()));
				maps.put("signType", "MD5");
				maps.put("paySign", res.getSign());
				result.setMsg(ResultCons.SUCCESS_MSG);
				result.setCode(ResultCons.SUCCESS);
				result.setData(maps);
			}else if(res.getReturn_code().equals("SUCCESS")){
				result.setMsg(res.getErr_code().concat(":").concat(res.getErr_code_des()));
				result.setCode(ResultCons.FAIL);
			}else{
				result.setMsg(res.getReturn_code().concat(":").concat(res.getReturn_msg()));
				result.setCode(ResultCons.FAIL);
			}
		} catch (Exception e) {
			result.setMsg("微信公众号支付异常:"+e.getMessage());
			result.setCode(ResultCons.FAIL);
			e.printStackTrace();
		}
		return result;
	}

}
