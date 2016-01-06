package sivl.platform.file.demo.client;import java.io.IOException;import java.util.List;import sivl.platform.file.demo.data.GroupInfo;import sivl.platform.file.demo.data.Result;import sivl.platform.file.demo.data.StorageInfo;import sivl.platform.file.demo.data.UploadStorage;/** *  * 主要功能：TrackerClient * @author liuli * @creationDate 2015年5月22日 下午2:47:58 */public interface TrackerClient {		/**	 * 	 * 功能说明：获取上传文件信息	 * @author liuli	 * @creationDate 2015年5月22日 下午2:45:33	 * @return Result<UploadStorage>	 * @throws IOException 	 */	public Result<UploadStorage> getUploadStorage() throws IOException;	/**	 * 	 * 功能说明：获取上传文件地址	 * @author liuli	 * @creationDate 2015年5月22日 下午2:45:52	 * @param group	 * @param fileName	 * @return Result<String>	 * @throws IOException 	 */	public Result<String> getUpdateStorageAddr(String group,String fileName) throws IOException;		/**	 * 	 * 功能说明：获取下载文件地址	 * @author liuli	 * @creationDate 2015年5月22日 下午2:46:12	 * @param group 分组	 * @param fileName 文件	 * @return Result<String>	 * @throws IOException 	 */	public Result<String> getDownloadStorageAddr(String group,String fileName) throws IOException;	/**	 * 	 * 功能说明：获取分组信息	 * @author liuli	 * @creationDate 2015年5月22日 下午2:46:52	 * @return	 * @throws IOException Result<List<GroupInfo>>	 */	public Result<List<GroupInfo>> getGroupInfos() throws IOException;		/**	 * 	 * 功能说明：获取StorageInfo信息	 * @author liuli	 * @creationDate 2015年5月22日 下午2:47:08	 * @param group 分组	 * @return Result<List<StorageInfo>>	 * @throws IOException 	 */	public Result<List<StorageInfo>> getStorageInfos(String group) throws IOException;		/**	 * 	 * 功能说明：关闭连接	 * @author liuli	 * @creationDate 2015年5月22日 下午2:47:28	 * @throws IOException void	 */	public void close() throws IOException;	}