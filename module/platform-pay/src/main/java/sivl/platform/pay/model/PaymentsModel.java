package sivl.platform.pay.model;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sivl.platform.common.utils.MapUtil;
import sivl.platform.pay.enums.Channel;
import sivl.platform.pay.enums.PayParam;

public class PaymentsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String outTradeNo;// 商户订单号
	private Double tradeFee;// 交易金额
	private String subject;// 商品名称
	private String overtime;// 超时时间
	private String body;// 商品描述
	private Integer channel;// 支付渠道
	// 扩展字段
	private Map<String, Object> params;// 请求参数
	private String createIp;// 微信专用
	private String openId;
	// 财付通参数
	private HttpServletResponse response;
	private HttpServletRequest request;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Double getTradeFee() {
		return tradeFee;
	}

	public void setTradeFee(Double tradeFee) {
		this.tradeFee = tradeFee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public PaymentsModel(){
		
	}

	public PaymentsModel(Map<String, Object> params) {
		super();
		this.params = params;
		this.outTradeNo = MapUtil.getString(params, "outTradeNo");
		this.tradeFee = MapUtil.getDouble(params, "tradeFee");
		this.subject = MapUtil.getString(params, "subject");
		this.overtime = MapUtil.getString(params, "overtime");
		this.body = MapUtil.getString(params, "body");
		this.channel = MapUtil.getInt(params, "channel");
		this.createIp = MapUtil.getString(params, "createIp");
		this.openId = MapUtil.getString(params, "openId");
		this.request = (HttpServletRequest) params.get("request");
		this.response = (HttpServletResponse) params.get("response");
	}

}
