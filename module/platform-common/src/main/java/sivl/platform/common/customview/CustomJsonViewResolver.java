package sivl.platform.common.customview;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import sivl.platform.common.utils.StringUtil;

/**
 * 自定义json视图解析器<br/>
 * 目的：处理自定义json格式返回
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
public class CustomJsonViewResolver extends AbstractCachingViewResolver
		implements Ordered {

	//后缀
	private String suffix;
	private int order;
	private View view = null; 
	
	@Override
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		View view = null;
		if(StringUtil.isEmpty(this.getSuffix())){
			return view;
		}
		//判断后缀名是否匹配
		if(viewName.endsWith(this.getSuffix())){
			view = this.getView();
		}
		return view;
	}

}
