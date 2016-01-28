package sivl.platform.flow.service;

import sivl.platform.flow.model.FlowModel;

/**
 * 流量接口
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月28日
 * @version 1.0
 */
public interface FlowService {

	/**
	 * 保存流量数据
	 * <br/>目的：
	 * @author lixuefeng
	 * @date 2016年1月28日
	 * @param flowModel
	 */
	void saveFlowData(FlowModel flowModel);
}
