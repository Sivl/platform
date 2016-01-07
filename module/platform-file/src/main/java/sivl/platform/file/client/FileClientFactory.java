package sivl.platform.file.client;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sivl.platform.file.client.custom.StorageClient;
import sivl.platform.file.client.custom.StorageClientFactory;
import sivl.platform.file.client.custom.TrackerClient;
import sivl.platform.file.client.custom.TrackerClientFactory;


/**
 * 文件客户端工厂<br/>
 * 目的：标准化生产文件操作客户端
 * @author lixuefeng
 * @date 2016年1月6日
 * @version 1.0
 */
@Component
public class FileClientFactory {

	@Autowired
	private FileClientConfig fileClientConfig;
	@Autowired
	private GenericKeyedObjectPoolConfig trackerPoolConfig;
	@Autowired
	private GenericKeyedObjectPoolConfig storagePoolConfig;
	private static volatile FileClient fileClient;
	
	//自身
	private static FileClientFactory current;
	
	@PostConstruct
	public void init() {
		current = this;
		current.fileClientConfig = this.fileClientConfig;
		current.trackerPoolConfig= this.trackerPoolConfig;
		current.storagePoolConfig= this.storagePoolConfig;
	}
	
	/**
	 * 创建文件客户端<br/>
	 * 目的：单例模式生产
	 * @author lixuefeng
	 * @date 2016年1月6日
	 * @return
	 */
	public static FileClient createFileClient(){
		if(null == fileClient){
			//添加类锁进行双重检查
			synchronized(FileClient.class){
				//1.创建跟踪服务器对象池
				TrackerClientFactory trackerClientFactory = new TrackerClientFactory(current.fileClientConfig.getConnectTimeout(), current.fileClientConfig.getNetworkTimeout());
				GenericKeyedObjectPool<String,TrackerClient> trackerClientPool = new GenericKeyedObjectPool<String,TrackerClient>(trackerClientFactory, current.trackerPoolConfig);
				//2.创建存储服务器对象池
				StorageClientFactory storageClientFactory = new StorageClientFactory(current.fileClientConfig.getConnectTimeout(), current.fileClientConfig.getNetworkTimeout());
				GenericKeyedObjectPool<String,StorageClient> storageClientPool = new GenericKeyedObjectPool<String,StorageClient>(storageClientFactory, current.storagePoolConfig);
				//3.创建文件客户端
				List<String> trackerAddrs = current.fileClientConfig.getTrackerList();
				//初始化客户端
				fileClient = new FileClient(trackerAddrs, trackerClientPool, storageClientPool);
				return fileClient;
			}
		}
		return fileClient;
	}
}
