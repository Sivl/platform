package sivl.platform.common.customview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import sivl.platform.common.constant.Constant;
import sivl.platform.common.utils.StringUtil;

/**
 * 自定义json视图<br/>
 * 目的：返回自定义json格式视图
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
public class CustomJsonView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
	    String resutl = (String) map.get(Constant.CUSTOM_JSON.getValue());
	    if(StringUtil.isEmpty(resutl)){
	    	resutl = "{\""+Constant.CUSTOM_VIEW_CODE.getValue()+"\": \"101\",\"msg\":\"结果为空\"}";
	    }
	    // 写入 response 内容
        response.getWriter().write(resutl); 
        response.getWriter().close();
	}

}
