package sivl.platform.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import sivl.platform.common.utils.JSONUtil;

public class BaseController {
	
	
	public void writerJson(HttpServletResponse response, Object obj){
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.print(JSONUtil.obj2json(obj));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
