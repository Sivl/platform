package sivl.platform.pay.sdk.tenpay.wap;

import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.tenpay.common.TenpayConfig;
import sivl.platform.pay.sdk.tenpay.common.client.TenpayHttpClient;
import sivl.platform.pay.sdk.tenpay.common.client.XMLClientResponseHandler;
import sivl.platform.pay.sdk.tenpay.common.util.TenpayUtil;
import sivl.platform.pay.sdk.tenpay.common.wap.WapPayInitRequestHandler;
import sivl.platform.pay.sdk.tenpay.common.wap.WapPayRequestHandler;

public class TenpayWapServiceUtil {

	public static ResultModel<Object> payment(PaymentsModel payment) {
		ResultModel<Object> result = new ResultModel<Object>();
		try {
			// 创建支付初始化请求示例
			WapPayInitRequestHandler reqHandler = new WapPayInitRequestHandler(
					payment.getRequest(), payment.getResponse());
			// 初始化
			reqHandler.init();
			// 设置密钥
			reqHandler.setKey(TenpayConfig.getWap_key());
			// -----------------------------
			// 设置请求参数
			// -----------------------------
			// 当前时间 yyyyMMddHHmmss
			// 订单号，必须保持唯一。此处 用 时间+4个随机数 模拟 ，商户可自行替换
			reqHandler.setParameter("sp_billno", payment.getOutTradeNo());
			reqHandler.setParameter("desc", payment.getBody());
			reqHandler.setParameter("bargainor_id", TenpayConfig.getWap_partner());
			reqHandler.setParameter("total_fee",String.valueOf(payment.getTradeFee().intValue()));
			reqHandler.setParameter("notify_url",TenpayConfig.getWap_notify_url());
			reqHandler.setParameter("callback_url",TenpayConfig.getWap_return_url());
			reqHandler.setParameter("time_start", TenpayUtil.getCurrTime());
			reqHandler.setParameter("time_expire", payment.getOvertime());
			// 获取请求带参数的url
			String requestUrl = reqHandler.getRequestURL();

			// 获取debug信息
			String debuginfo = reqHandler.getDebugInfo();
			System.out.println("debuginfo:" + debuginfo);
			System.out.println("requestUrl:" + requestUrl);

			// 创建TenpayHttpClient，后台通信
			TenpayHttpClient httpClient = new TenpayHttpClient();

			// 设置请求内容
			httpClient.setReqContent(requestUrl);
			// 远程调用
			if (httpClient.call()) {
				String resContent = httpClient.getResContent();
				System.out.println("responseContent:" + resContent);
				// ----------------------
				// 应答处理,获取token_id
				// ----------------------
				XMLClientResponseHandler resHandler = new XMLClientResponseHandler();
				resHandler.setContent(resContent);
				String token_id = resHandler.getParameter("token_id");
				if (StringUtil.isNotEmpty(token_id)) {
					// 生成支付请求
					WapPayRequestHandler wapPayRequestHandler = new WapPayRequestHandler(
							payment.getRequest(), payment.getResponse());
					wapPayRequestHandler.init();
					wapPayRequestHandler.setParameter("token_id", token_id);
					String url = wapPayRequestHandler.getRequestURL();
					if(StringUtil.isNotEmpty(url)){
						result.setMsg(ResultCons.SUCCESS_MSG);
						result.setCode(ResultCons.SUCCESS);
						result.setData(url);
					}else{
						// 支付url获取失败
						result.setMsg("支付url获取失败支付url获取失败:"+resHandler.getParameter("err_info"));
						result.setCode(ResultCons.FAIL);
					}
				} else {
					// 获取token_id调用失败 ，显示错误 页面
					result.setMsg("财付通wap获取token_id调用失败:"+resHandler.getParameter("err_info"));
					result.setCode(ResultCons.FAIL);
				}
			} else {
				// 后台调用失败 ，显示错误 页面
				result.setMsg("财付通wap后台调用失败:"+httpClient.getResponseCode()
						+ httpClient.getErrInfo());
				result.setCode(ResultCons.FAIL);
			}
		}  catch (Exception e) {
			result.setMsg("财付通wap:"+e.getMessage());
			result.setCode(ResultCons.FAIL);
		}
		
		return result;
	}

}
