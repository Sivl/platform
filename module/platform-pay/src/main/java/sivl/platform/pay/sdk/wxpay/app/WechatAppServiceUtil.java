package sivl.platform.pay.sdk.wxpay.app;

import java.util.HashMap;
import java.util.Map;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.wxpay.common.Configure;
import sivl.platform.pay.sdk.wxpay.common.HttpsRequest;
import sivl.platform.pay.sdk.wxpay.common.Signature;
import sivl.platform.pay.sdk.wxpay.common.Util;
import sivl.platform.pay.sdk.wxpay.model.WechatPayResData;

/**
 * 微信App支付
 * @author Zhouxiang
 *
 */
public class WechatAppServiceUtil {

	public static ResultModel<Object> payment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		WechatAppPayReqData wechat = new WechatAppPayReqData(payment);
		try {
			String xml = new HttpsRequest().sendPost(Configure.UNIFIED_ORDER,
					wechat);
			WechatPayResData res = (WechatPayResData) Util.getObjectFromXML(
					xml, WechatPayResData.class);
			if (res.getReturn_code().equals("SUCCESS")
					&& res.getResult_code().equals("SUCCESS")) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("appid", res.getAppid());
				maps.put("partnerid", Configure.getMchid());
				maps.put("timeStamp",
						String.valueOf(System.currentTimeMillis() / 1000));
				maps.put("nonceStr", res.getNonce_str());
				maps.put("prepayid", res.getPrepay_id());
				maps.put("package", "Sign=WXPay");
				//获取预付单，将参数再次签名传输给APP发起支付
				//根据API给的签名规则进行签名
		        String sign = Signature.getSign(maps);
		        maps.put("sign", sign);
				result.setMsg(ResultCons.SUCCESS_MSG);
				result.setCode(ResultCons.SUCCESS);
				result.setData(maps);
			} else if (res.getReturn_code().equals("SUCCESS")) {
				result.setMsg(res.getErr_code().concat(":")
						.concat(res.getErr_code_des()));
				result.setCode(ResultCons.FAIL);
			} else {
				result.setMsg(res.getReturn_code().concat(":")
						.concat(res.getReturn_msg()));
				result.setCode(ResultCons.FAIL);
			}
		} catch (Exception e) {
			result.setMsg("微信APP支付异常:" + e.getMessage());
			result.setCode(ResultCons.FAIL);
			e.printStackTrace();
		}
		return result;
	}
}
