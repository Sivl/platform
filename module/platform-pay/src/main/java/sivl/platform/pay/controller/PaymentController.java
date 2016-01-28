package sivl.platform.pay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sivl.platform.common.model.NetLogModel;
import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.JSONUtil;
import sivl.platform.common.utils.LogUtil;
import sivl.platform.common.utils.MapUtil;
import sivl.platform.pay.constant.ResultCons;
import sivl.platform.pay.service.OpenService;
import sivl.platform.pay.service.PaymentService;

@Controller
@RequestMapping("/pay")
public class PaymentController extends BaseController {

	private static String className = "sivl.platform.pay.controller.PaymentController";

	@Autowired
	private OpenService openService;

	/**
	 * 支付发起接口
	 * 
	 * @param request
	 * @param params
	 *            json格式字符串（商户订单号，交易金额，交易渠道，商品名称，商品说明,支付失效时间,微信openId） 注：可空字段上送
	 *            key = "" (如：openId="")
	 * @return
	 */
	@RequestMapping(value = "/payment", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void payment(HttpServletResponse response,
			HttpServletRequest request, String params) {
		// 记录日志
		LogUtil.netLegalLog(
				JSONUtil.obj2json(new NetLogModel(request)
						.buildClassName(className)
						.buildInterfaceName("/pay/payment").buildMsg("发起支付")
						.buildParame(params)), PaymentController.class);
		ResultModel<Object> result = new ResultModel<Object>();
		Map<String, Object> maps = JSONUtil.json2map(params);
		if (maps == null) {
			result.setMsg("参数格式异常,Json无法转换Map");
			result.setCode(ResultCons.FAIL);
		} else {
			Integer code = MapUtil.getInt(maps, "channel");
			if (code == null) {
				result.setMsg("参数异常:支付渠道不能为空");
				result.setCode(ResultCons.FAIL);
			} else {
				PaymentService paymentService = openService.getInstance(code);
				String createIp = request.getRemoteAddr();
				maps.put("createIp", createIp);
				maps.put("request", request);
				maps.put("response", response);
				result = paymentService.payment(maps);
				if (result.getCode().equals(ResultCons.SUCCESS)) {
					Map<String, Object> ext = new HashMap<String, Object>();
					ext.put("channel", code);
					ext.put("out_trade_no",
							MapUtil.getString(maps, "outTradeNo"));
					ext.put("trade_fee", MapUtil.getString(maps, "tradeFee"));
					result.setExt(ext);
				}
			}
		}
		writerJson(response, result);
	}

	/**
	 * 退款接口
	 * 
	 * @param request
	 * @param params
	 *            json格式字符串（交易流水号，退款金额，总金额，商户退款订单号，订单支付时间）
	 * @return
	 */
	@RequestMapping(value = "/refundment", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void refundment(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required = false) String params) {
		// 记录日志
		LogUtil.netLegalLog(
				JSONUtil.obj2json(new NetLogModel(request)
						.buildClassName(className)
						.buildInterfaceName("/token_ck/refundment")
						.buildMsg("退款")), PaymentController.class);
		Map<String, Object> maps = JSONUtil.json2map(params);
		Integer code = MapUtil.getInt(maps, "channel");
		PaymentService paymentService = openService.getInstance(code);
		ResultModel<Object> result = new ResultModel<Object>();
		result = paymentService.refundment(maps);
		writerJson(response, result);
	}

	/**
	 * 获取支付结果接口
	 * 
	 * @param request
	 * @param params
	 *            json格式字符串（商户订单号，交易流水号）
	 * @return
	 */
	@RequestMapping(value = "/token_ck/getresult", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void getResult(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required = false) String params) {
		// 记录日志
		LogUtil.netLegalLog(
				JSONUtil.obj2json(new NetLogModel(request)
						.buildClassName(className)
						.buildInterfaceName("/token_ck/getresult")
						.buildMsg("获取支付结果")), PaymentController.class);
		Map<String, Object> maps = JSONUtil.json2map(params);
		Integer code = MapUtil.getInt(maps, "channel");
		PaymentService paymentService = openService.getInstance(code);
		ResultModel<Object> result = new ResultModel<Object>();
		result = paymentService.getResult(maps);
		writerJson(response, result);
	}

	/**
	 * 提现接口：暂时支持银联、支付宝、财付通
	 * 
	 * @param response
	 * @param request
	 * @param params
	 */
	@RequestMapping(value = "/token_ck/cash", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void withdrawal(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(required = false) String params) {
		// 记录日志
		LogUtil.netLegalLog(
				JSONUtil.obj2json(new NetLogModel(request)
						.buildClassName(className)
						.buildInterfaceName("/token_ck/withdrawal")
						.buildMsg("提现接口")), PaymentController.class);
		Map<String, Object> maps = JSONUtil.json2map(params);
		Integer code = MapUtil.getInt(maps, "channel");
		PaymentService paymentService = openService.getInstance(code);
		ResultModel<Object> result = new ResultModel<Object>();
		result = paymentService.withdrawal(maps);
		writerJson(response, result);
	}

}
