package sivl.platform.file.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

import sivl.platform.file.client.custom.StorageClient;
import sivl.platform.file.client.custom.TrackerClient;


/**
 * 文件客户端<br/>
 * 目的：提供文件上传的各种操作
 * @author lixuefeng
 * @date 2016年1月6日
 * @version 1.0
 */
public class FileClient extends FileCommonClient {

	//跟踪服务器池
	private GenericKeyedObjectPool<String, TrackerClient> trackerClientPool;
	//存储服务器池
	private GenericKeyedObjectPool<String, StorageClient> storageClientPool;
	//跟踪服务器地址
	private List<String> trackerAddrs = new ArrayList<String>();
	
	public FileClient(List<String> trackerAddrs,
			GenericKeyedObjectPool<String, TrackerClient> trackerClientPool,
			GenericKeyedObjectPool<String, StorageClient> storageClientPool){
		super();
		this.trackerAddrs = trackerAddrs;
		this.trackerClientPool = trackerClientPool;
		this.storageClientPool = storageClientPool;
	}
	
	/**
	 * 销毁客户端
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月6日
	 */
	public void destroy(){
		this.trackerClientPool.close();
		this.storageClientPool.close();
	}
	
	/**
	 * 上传文件（文件带有附加信息）
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月6日
	 * @param file 要上传的文件
	 * @param fileType 文件类型
	 * @param meta key/value的meta data，可为null
	 * @return 上传文件的ID
	 */
	public String upload(File file,String fileType,Map<String,String> meta){
		
		return null;
	}
	
	/**
	 * 上传文件（文件带有附加信息）
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月6日
	 * @param byteArray 文件字节数组
	 * @param fileType 文件类型
	 * @param meta key/value的meta data，可为null
	 * @return 上传文件的ID
	 */
	public String upload(byte[] byteArray,String fileType,Map<String,String> meta){
		
		return null;
	}
}
