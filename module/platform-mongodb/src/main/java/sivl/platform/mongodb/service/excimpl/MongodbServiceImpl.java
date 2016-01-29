package sivl.platform.mongodb.service.excimpl;import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.mongodb.core.MongoTemplate;import org.springframework.data.mongodb.core.query.Query;import org.springframework.stereotype.Service;import sivl.platform.mongodb.model.BuzLogModel;import sivl.platform.mongodb.model.NetLogModel;import sivl.platform.mongodb.service.MongodbService;/** * mongodb服务实现<br/> * 目的： * @author lixuefeng * @createTime 2015年12月9日 * @version 1.0 */@Service("mongodbService")public class MongodbServiceImpl implements MongodbService {	@Autowired	private MongoTemplate mongoTemplate; 		@Override	public void saveNetLog(NetLogModel netLogModel) {		mongoTemplate.save(netLogModel);	}	@Override	public void saveBuzLog(BuzLogModel buzLogModel) {		mongoTemplate.save(buzLogModel);	}		@Override	public void saveObject(Object obj) {		mongoTemplate.save(obj);	}	@Override	public <T> List<T> getListObject(Query query, Class<T> classes) {		return mongoTemplate.find(query, classes);	}	}