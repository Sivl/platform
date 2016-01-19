package sivl.platform.minifile.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sivl.platform.common.constant.Constant;
import sivl.platform.common.constant.CFileOptionEnum;
import sivl.platform.common.model.ResultModel;
import sivl.platform.common.utils.ComFileUtils;
import sivl.platform.minifile.model.FileMiniMetaModel;

/**
 * 文件mini控制层
 * 目的：
 * @author lixuefeng
 * @date 2016年1月8日
 * @version 1.0
 */
@Controller
@RequestMapping("/fileMini")
public class FileMiniController {
	
	private static String className = "sivl.platform.minifile.controller.FileMiniController";
	
	LinkedList<FileMiniMetaModel> files = new LinkedList<FileMiniMetaModel>();
    FileMiniMetaModel fileMeta = null;
    
    /**
     * 上传文件页面
     * 目的：
     * @author lixuefeng
     * @date 2016年1月8日
     * @return
     */
    @RequestMapping(value="/uploadIndex",method = {RequestMethod.POST,RequestMethod.GET})  
    public String uploadIndex(){
    	return "file-mini/file-mini-demo";
    }
    
    /**
     * 百度文件上传控件上传处理
     * 目的：
     * @author lixuefeng
     * @date 2016年1月12日
     * @param model
     * @param request
     * @param response
     * @param file
     */
	@RequestMapping(value="/uploadFileMini",method = {RequestMethod.POST})  
	public void uploadFileMini(Model model,HttpServletRequest request,HttpServletResponse response ,@RequestParam MultipartFile file ){
		ResultModel<String> result = null;
		if(null == file){
			result = new ResultModel<String>(Constant.F_U_ER_PARAM.getValue(), Constant.F_U_ER_PARAM.getDescribe()+"【上传文件为空】");
			model.addAttribute("result",result);
			response.setStatus(560);  
			return;
		}
		CFileOptionEnum fiEnum = ComFileUtils.saveFile(file, "C:/Sivl/file-up/test/"+file.getOriginalFilename());
		if(!CFileOptionEnum.FO_SUCCESS.equals(fiEnum)){
			result = new ResultModel<String>(Constant.F_U_ER_EXP.getValue(), Constant.F_U_ER_EXP.getDescribe()+"【"+fiEnum.getRemark()+"】");
			model.addAttribute("result",result);
			response.setStatus(Integer.parseInt(Constant.F_U_ER_EXP.getValue())); 
			return;
		}
		result = new ResultModel<String>(Constant.F_U_SUCCESS.getValue(), Constant.F_U_SUCCESS.getDescribe());
		model.addAttribute("result",result);
	}
	
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response,@PathVariable String value){
		FileMiniMetaModel getFile = files.get(Integer.parseInt(value));
        try {      
               response.setContentType(getFile.getFileType());
               response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
               FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
        }catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
        }
    }
	
}
