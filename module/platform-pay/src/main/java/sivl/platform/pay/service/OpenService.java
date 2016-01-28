package sivl.platform.pay.service;

import org.springframework.stereotype.Component;

@Component
public interface OpenService {
	
	public abstract PaymentService getInstance(Integer code);

}
