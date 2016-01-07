package sivl.platform.file.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sivl.platform.common.utils.StringUtil;

/**
 * 文件客户端配置载体
 * 目的：
 * @author lixuefeng
 * @date 2016年1月6日
 * @version 1.0
 */
public class FileClientConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_CONNECT_TIMEOUT = 5;  //second
	public static final int DEFAULT_NETWORK_TIMEOUT = 30; //second
	public static final String DEFAULT_CHARSET = "UTF-8";

	private int connectTimeout=DEFAULT_CONNECT_TIMEOUT;
	private int networkTimeout=DEFAULT_NETWORK_TIMEOUT;
	private String trackerService;
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getNetworkTimeout() {
		return networkTimeout;
	}
	public void setNetworkTimeout(int networkTimeout) {
		this.networkTimeout = networkTimeout;
	}
	public String getTrackerService() {
		return trackerService;
	}
	public void setTrackerService(String trackerService) {
		this.trackerService = trackerService;
	}
	public List<String> getTrackerList(){
		if(StringUtil.isEmpty(trackerService)){
			return null;
		}
		String[] trackerss = trackerService.split(",");
		List<String> trackerList = new ArrayList<String>(trackerss.length);
		for(String tracker : trackerss){
			trackerList.add(tracker);
		}
		return trackerList;
	}
	
}
