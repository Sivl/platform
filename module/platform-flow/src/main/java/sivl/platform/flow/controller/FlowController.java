package sivl.platform.flow.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sivl.platform.common.constant.CFlowEnum;
import sivl.platform.common.model.BuzLogModel;
import sivl.platform.common.utils.JSONUtil;
import sivl.platform.common.utils.LogUtil;
import sivl.platform.flow.model.FlowModel;
import sivl.platform.flow.service.FlowService;

/**
 * 流量监控控制层
 * 目的：
 * @author lixuefeng
 * @date 2016年1月8日
 * @version 1.0
 */
@Controller
@RequestMapping("/flow")
public class FlowController {
	
	private static String className = "sivl.platform.flow.controller.FlowController";
	
	@Autowired
	private FlowService flowService;
	
    /**
     * 统计页面点击量
     * 目的：
     * @author lixuefeng
     * @date 2016年1月8日
     * @return
     */
    @RequestMapping(value="/pv",method = {RequestMethod.POST,RequestMethod.GET})  
    public void pageView(HttpServletRequest request, FlowModel flowParams){
    	flowParams.setType(CFlowEnum.FLOW_PV.getValue());
    	flowParams.setAddTime(new Date());
    	flowService.saveFlowData(flowParams);
    }
    
    /**
     * 统计页面停留时间
     * <br/>目的：
     * @author lixuefeng
     * @date 2016年1月28日
     * @param request
     * @param flowParams
     */
    @RequestMapping(value="/pageRemainTime",method = {RequestMethod.POST,RequestMethod.GET})  
    public void pageRemainTime(HttpServletRequest request,FlowModel flowParams){
    	if(flowParams.getStartTime() == null || flowParams.getEndTime() == null){
    		LogUtil.warn(JSONUtil.obj2json(new BuzLogModel(className,"pageRemainTime",flowParams.toString(),"参数异常：开始或者结束时间为空！")), FlowController.class);
    		return;
    	}
    	flowParams.setResidenceTime(flowParams.getEndTime().getTime()-flowParams.getStartTime().getTime());
    	flowParams.setType(CFlowEnum.FLOW_RESIDENCE_TIME.getValue());
    	flowParams.setAddTime(new Date());
    	flowService.saveFlowData(flowParams);
    }
    
}
