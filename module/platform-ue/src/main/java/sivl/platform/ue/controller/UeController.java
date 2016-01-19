package sivl.platform.ue.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sivl.platform.common.constant.CBoolean;
import sivl.platform.common.constant.Constant;
import sivl.platform.common.encrypt.Encrypt;
import sivl.platform.common.model.CommonModel;
import sivl.platform.common.utils.JSONUtil;
import sivl.platform.common.utils.StringUtil;
import sivl.platform.ue.constant.UeActionTypeCMDEnum;
import sivl.platform.ue.constant.UeAppInfoEnum;
import sivl.platform.ue.model.UeConfig;
import sivl.platform.ue.model.UeFileResultModel;
import sivl.platform.ue.service.UeService;

/**
 * ue控制层
 * 目的：
 * @author lixuefeng
 * @date 2016年1月8日
 * @version 1.0
 */
@Controller
@RequestMapping("/ue")
public class UeController {
	
	private static String className = "sivl.platform.ue.controller.UeController";
	
	@Autowired
	private UeConfig ueConfig;
	@Autowired
	private UeService ueService;
	
	@RequestMapping(value="/ueDemo",method = {RequestMethod.POST,RequestMethod.GET})  
    public String ueDemoIndex(){
    	return "ue/ue-demo";
    }
	
	/**
	 * 百度富文本编辑器ueditor请求处理
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月12日
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/ueditorHandle",method = {RequestMethod.POST,RequestMethod.GET})  
	public String ueditorHandle(Model model,HttpServletRequest request,HttpServletResponse response){
		String result = null;
		//回调方法
		String callbackName = request.getParameter("callback");
		if (StringUtil.isEmpty(callbackName)){
			result = invoke(request);
			System.out.println("-------result------"+result);
			model.addAttribute(Constant.CUSTOM_JSON.getValue(), result);
			return "ueditorHandle.cjson";
	    }
		if (!validCallbackName(callbackName)) {
			UeFileResultModel ueResult = new UeFileResultModel();
			ueResult.setState(UeAppInfoEnum.UE_APPINFO_ILLEGAL.getRemark());
			result = JSONUtil.obj2json(ueResult);
			System.out.println("-------result------"+result);
			model.addAttribute(Constant.CUSTOM_JSON.getValue(), result) ;
			return "ueditorHandle.cjson";
	    }
		model.addAttribute(Constant.CUSTOM_JSON.getValue(), callbackName + "(" + result + ");") ;
		System.out.println("-------result------"+callbackName + "(" + result + ");");
		return "ueditorHandle.cjson";
	}
	
	/**
	 * 执行请求命令<br/>
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月14日
	 * @param request
	 * @return
	 */
	private String invoke(HttpServletRequest request){
		//请求类型
		String actionType = request.getParameter("action");
		//非法命令
		if(StringUtil.isEmpty(actionType) || null == UeActionTypeCMDEnum.getUeActionTypeCmd(actionType).getCode()){
			UeFileResultModel ueResult = new UeFileResultModel();
			ueResult.setState(UeAppInfoEnum.UE_APPINFO_INVALID_ACTION.getRemark());
			return JSONUtil.obj2json(ueResult);
		}
		//
		int actionCmd = UeActionTypeCMDEnum.getUeActionTypeCmd(actionType).getValue();
		//取得部分配置
		Map<String,Object> configMap = null;
		CommonModel result = null;
		switch(actionCmd){
			case 0://取得所有配置信息
				String configJson = JSONUtil.obj2json(ueConfig);
				return configJson;
			case 1://上传图片
			case 2://上传涂鸦
			case 3://上传视频
			case 4://上传文件
				//上传配置信息
				configMap = ueConfig.getUeConfig(actionCmd);
				result = ueService.ueUpload(request, configMap);
				break;
			case 5://抓取图片
				configMap = ueConfig.getUeConfig(actionCmd);
				//抓去图片集合
		        String[] list = request.getParameterValues((String)configMap.get("fieldName"));
		        result = ueService.ueUpload(list, configMap);
		        break;
			case 6://文件集合
			case 7://图片集合
				configMap = ueConfig.getUeConfig(actionCmd);
				int start = this.getStartIndex(request);
				result = ueService.ueFileManage(start, configMap);
				break;
	    }
		return JSONUtil.obj2json(result);
	}
	
	
	
	/**
	 * 取得开始索引
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月12日
	 * @param request
	 * @return
	 */
	private int getStartIndex(HttpServletRequest request){
		String start = request.getParameter("start");
		try {
			return Integer.parseInt(start);
		} catch (NumberFormatException e) {
			
		}
		return 0;
	}
	
	/**
	 * 校验回调方法名
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月12日
	 * @param name
	 * @return
	 */
	private boolean validCallbackName(String name)
	{
		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}
		return false;
	}
	
}
