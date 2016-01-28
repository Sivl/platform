package sivl.platform.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sivl.platform.common.service.SysParamService;
import sivl.platform.common.utils.SpringContextUtil;
import sivl.platform.pay.enums.Channel;
import sivl.platform.pay.enums.PayParam;
import sivl.platform.pay.sdk.alipay.common.config.AlipayConfig;
import sivl.platform.pay.sdk.chinapay.common.config.ChinapayConfig;
import sivl.platform.pay.sdk.chinapay.common.sdk.SDKConfig;
import sivl.platform.pay.sdk.tenpay.common.TenpayConfig;
import sivl.platform.pay.sdk.wxpay.common.Configure;
import sivl.platform.pay.service.OpenService;
import sivl.platform.pay.service.PaymentService;

@Component
public class PaymentServiceImpl implements OpenService {
	
	@Autowired
	private SysParamService sysParamService;

	public PaymentService getInstance(Integer code) {
		Channel channel = Channel.getChannel(code);
		if (code == Channel.ALIPAY_WEB.getCode()
				|| code == Channel.ALIPAY_WAP.getCode()
				|| code == Channel.ALIPAY_APP.getCode()) {
			installAlipay();
		}else if(code == Channel.CHINAPAY_WEB.getCode()
				|| code == Channel.CHINAPAY_WAP.getCode()
				|| code == Channel.CHINAPAY_APP.getCode()){
			installChinapay();
		}else if(code == Channel.TENPAY_WEB.getCode()
				|| code == Channel.TENPAY_WAP.getCode()
				|| code == Channel.TENPAY_APP.getCode()){
			installTenpay();
		}else if(code == Channel.WECHAT_NATIVE.getCode()
				|| code == Channel.WECHAT_PUBLIC.getCode()
				|| code == Channel.WECHAT_APP.getCode()
				|| code == Channel.WECHAT_WAP.getCode()){
			installWechatPay();
		}
		PaymentService paymentService = (PaymentService) SpringContextUtil
				.getBean(channel.getBean());
		return paymentService;
	}

	//初始化微信支付信息
	private void installWechatPay() {
		String host = PayParam.PAY_HOST.getValue();
		String certs_path = PayParam.PAY_CERTS_PATH.getValue();
		Configure.setMchID(PayParam.PAY_WECHAT_PARTNER_APP.getValue());
		Configure.setKey(PayParam.PAY_WECHAT_PARTNER_KEY_APP.getValue());
		Configure.setAppID(PayParam.PAY_WECHAT_APP_ID_APP.getValue());
		Configure.setCertLocalPath(certs_path.concat(PayParam.PAY_WECHAT_CERT_PATH_APP.getValue()));
		Configure.setCertPassword(PayParam.PAY_WECHAT_CERT_PWD_APP.getValue());
		Configure.setSecret(PayParam.PAY_WECHAT_APP_SECRET_APP.getValue());
		Configure.setFront_url(host.concat("/wechat/wechat_front"));
		Configure.setBack_url(host.concat("/wechat/wechat_notify"));
		//App
		Configure.setMchID_app(PayParam.PAY_WECHAT_PARTNER_APP.getValue());
		Configure.setKey_app(PayParam.PAY_WECHAT_PARTNER_KEY_APP.getValue());
		Configure.setAppID_app(PayParam.PAY_WECHAT_APP_ID_APP.getValue());
		Configure.setCertLocalPath_app(certs_path.concat(PayParam.PAY_WECHAT_CERT_PATH_APP.getValue()));
		Configure.setCertPassword_app(PayParam.PAY_WECHAT_CERT_PWD_APP.getValue());
		Configure.setSecret_app(PayParam.PAY_WECHAT_APP_SECRET_APP.getValue());
		Configure.setApp_back_url(host.concat("/wechat/wechat_notify"));
	}

	//初始化财付通支付信息
	private void installTenpay() {
		String host = PayParam.PAY_HOST.getValue();
		String certs_path = PayParam.PAY_CERTS_PATH.getValue();
		TenpayConfig.setPartner(PayParam.PAY_TENPAY_PARTNER.getValue());
		TenpayConfig.setKey(PayParam.PAY_TENPAY_KEY.getValue());
		TenpayConfig.setCert_path(certs_path.concat(PayParam.PAY_TENPAY_CERT_PATH.getValue()));
		TenpayConfig.setCert_pwd(PayParam.PAY_TENPAY_CERT_PWD.getValue());
		TenpayConfig.setWeb_notify_url(host.concat("/payment/tenpay/web_payment_back"));
		TenpayConfig.setWeb_return_url(host.concat("/payment/tenpay/web_payment_front"));
		TenpayConfig.setWap_partner(PayParam.PAY_TENPAY_WAP_PARTNER.getValue());
		TenpayConfig.setWap_key(PayParam.PAY_TENPAY_WAP_KEY.getValue());
		TenpayConfig.setWap_cert_path(certs_path.concat(PayParam.PAY_TENPAY_WAP_CERT_PATH.getValue()));
		TenpayConfig.setWap_cert_pwd(PayParam.PAY_TENPAY_WAP_CERT_PWD.getValue());
		TenpayConfig.setWap_notify_url(host.concat("/payment/tenpay/wap_payment_back"));
		TenpayConfig.setWap_return_url(host.concat("/payment/tenpay/wap_payment_front"));
	}

	//初始化银联支付信息
	private void installChinapay() {
		//从应用的classpath下加载acp_sdk.properties属性文件并将该属性文件中的键值对赋值到SDKConfig类中
		SDKConfig.getConfig().loadPropertiesFromSrc();
		String host = PayParam.PAY_HOST.getValue();
		ChinapayConfig.merId = PayParam.PAY_CHINAPAY_MERID.getValue();
		ChinapayConfig.frontUrl = host.concat("/chinapay/web/front_payment");
		ChinapayConfig.backUrl = host.concat("/chinapay/web/back_payment");
	}

	//初始化支付宝支付信息
	private void installAlipay() {
		String host = PayParam.PAY_HOST.getValue();//sysParamService.getValue(PayParam.PAY_HOST.getCode());
		AlipayConfig.partner = PayParam.PAY_ALIPAY_PARTNER.getValue();//sysParamService.getValue(PayParam.PAY_ALIPAY_PARTNER.getCode());
		AlipayConfig.key = PayParam.PAY_ALIPAY_KEY.getValue();//sysParamService.getValue(PayParam.PAY_ALIPAY_KEY.getCode());
		AlipayConfig.seller = PayParam.PAY_ALIPAY_SELLER.getValue();//sysParamService.getValue(PayParam.PAY_ALIPAY_SELLER.getCode());
		AlipayConfig.web_payment_return = host.concat("/alipay/web/return_payment");
		AlipayConfig.web_payment_notify = host.concat("/alipay/web/notify_payment");
		AlipayConfig.refundment_notify = host.concat("/alipay/web/notify_refundment");
		//wap参数初始化
		AlipayConfig.private_key = PayParam.PAY_ALIPAY_PRIVATE_KEY.getValue();
		AlipayConfig.wap_public_key = PayParam.PAY_ALIPAY_WAP_PUBLIC_KEY.getValue();
		AlipayConfig.wap_payment_return = host.concat("/alipay/wap/return_payment");
		AlipayConfig.wap_payment_notify = host.concat("/alipay/wap/notify_payment");
		//app参数
		AlipayConfig.app_public_key = PayParam.PAY_ALIPAY_APP_PUBLIC_KEY.getValue();
		AlipayConfig.app_payment_notify = host.concat("/alipay/app/notify_payment");
	}
	
}
