package sivl.platform.common.customview;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import sivl.platform.common.constant.CNotSpringMultipartResolver;

/**
 * 自定义上传表单spring封装处理器
 * <br/>目的：个性化处理部分表单上传是否经过spring请求分装
 * @author lixuefeng
 * @date 2016年1月18日
 * @version 1.0
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		String url = request.getRequestURI();
		if(url.indexOf(CNotSpringMultipartResolver.UE_NOT_RESOLVER.getValue()) > 0){
			return false;
		}
		return super.isMultipart(request);
	}
}
