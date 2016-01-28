package sivl.platform.flow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sivl.platform.flow.model.FlowModel;
import sivl.platform.flow.service.FlowService;
import sivl.platform.mongodb.service.MongodbService;

@Service("flowService")
public class FlowServiceImpl implements FlowService {

	@Autowired
	private MongodbService mongodbService;
	
	@Override
	public void saveFlowData(FlowModel flowModel) {
		mongodbService.saveObject(flowModel);
	}

}
