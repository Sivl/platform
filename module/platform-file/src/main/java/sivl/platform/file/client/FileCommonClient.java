package sivl.platform.file.client;

import sivl.platform.file.utils.FileUtils;


/**
 * 文件上传公用客户端
 * 目的：
 * @author lixuefeng
 * @date 2016年1月6日
 * @version 1.0
 */
public abstract class FileCommonClient {

	protected String[] splitFileId(String fileId) {
        return FileUtils.splitFileId(fileId);
    }
}
