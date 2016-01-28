package sivl.platform.pay.sdk.wxpay.natives;

import sivl.platform.common.model.ResultModel;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.wxpay.common.Configure;
import sivl.platform.pay.sdk.wxpay.common.HttpsRequest;
import sivl.platform.pay.sdk.wxpay.common.Util;
import sivl.platform.pay.sdk.wxpay.model.WechatPayReqData;
import sivl.platform.pay.sdk.wxpay.model.WechatPayResData;

public class NativeServiceUtil {

	public static ResultModel<Object> payment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		WechatPayReqData wechat = new WechatPayReqData(payment);
		try {
			String xml = new HttpsRequest().sendPost(Configure.UNIFIED_ORDER,
					wechat);
			WechatPayResData res=(WechatPayResData) Util.getObjectFromXML(xml,WechatPayResData.class);
			if(res.getReturn_code().equals("SUCCESS")
				&& res.getResult_code().equals("SUCCESS")){
				result.setMsg(ResultCons.SUCCESS_MSG);
				result.setCode(ResultCons.SUCCESS);
				result.setData(res.getCode_url());
			}else if(res.getReturn_code().equals("SUCCESS")){
				result.setMsg(res.getErr_code().concat(":").concat(res.getErr_code_des()));
				result.setCode(ResultCons.FAIL);
			}else{
				result.setMsg(res.getReturn_code().concat(":").concat(res.getReturn_msg()));
				result.setCode(ResultCons.FAIL);
			}
		} catch (Exception e) {
			result.setMsg("微信扫描支付异常:"+e.getMessage());
			result.setCode(ResultCons.FAIL);
			e.printStackTrace();
		}
		return result;
	}

}
