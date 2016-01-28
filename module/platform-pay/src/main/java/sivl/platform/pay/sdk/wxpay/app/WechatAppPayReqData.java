package sivl.platform.pay.sdk.wxpay.app;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sivl.platform.common.utils.DateUtil;
import sivl.platform.pay.enums.Channel;
import sivl.platform.pay.model.PaymentsModel;
import sivl.platform.pay.sdk.wxpay.common.RandomStringGenerator;
import sivl.platform.pay.sdk.wxpay.common.Configure;
import sivl.platform.pay.sdk.wxpay.common.Signature;

/**
 * 微信账户统一可以省略
 * @author Zhouxiang
 *
 */
public class WechatAppPayReqData {

	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String body;
	private String attach;
	private String out_trade_no;
	private int total_fee;
	private String spbill_create_ip;// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	private String time_start;// 交易开始时间
	private String time_expire;// 交易结束时间
	private String notify_url;
	private String trade_type;// 交易类型
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
	public WechatAppPayReqData(PaymentsModel payment){
		setAppid(Configure.getAppID_app());
		setMch_id(Configure.getMchID_app());
		setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
		setBody(payment.getBody());
		setAttach(payment.getBody());
		setOut_trade_no(payment.getOutTradeNo());
		setSpbill_create_ip(payment.getCreateIp());
		setTime_start(DateUtil.fmtDateToStr(new Date(), "yyyyMMddHHmmss"));
		setTotal_fee(payment.getTradeFee().intValue());
		String trade_type = "WAP";
		if(payment.getChannel().equals(Channel.WECHAT_NATIVE.getCode())){
			trade_type = "NATIVE";
		}else if(payment.getChannel().equals(Channel.WECHAT_PUBLIC.getCode())){
			trade_type = "JSAPI";
		}else if(payment.getChannel().equals(Channel.WECHAT_APP.getCode())){
			trade_type = "APP";
		}
		setTrade_type(trade_type);
		setTime_expire(payment.getOvertime());
		setNotify_url(Configure.getApp_back_url());
//		setOpenId(payment.getOpenId());
		//根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中

	}
	
	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
