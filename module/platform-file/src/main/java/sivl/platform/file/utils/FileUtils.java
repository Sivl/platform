package sivl.platform.file.utils;

import sivl.platform.file.cmd.Command;

/**
 * 文件工具类<br/>
 * 目的：对文件进行操作
 * @author lixuefeng
 * @date 2016年1月7日
 * @version 1.0
 */
public class FileUtils {

	/**
	 * 分离文件id<br/>
	 * 目的：传入带group的fileId,返回group和filename的二元数组
	 * @author lixuefeng
	 * @date 2016年1月7日
	 * @param file_id fileid like g1/M00/00/00/abc.jpg
	 * @return string[2] string[0] = g1, string[1]=M00/00/00/abc.jpg
	 */
    public static String[] splitFileId(String fileId) {
        int pos = fileId.indexOf("/");
        if ((pos <= 0) || (pos == fileId.length() - 1)) {
            return null;
        }
        String[] results = new String[2];
        results[0] = fileId.substring(0, pos); //group name
        results[1] = fileId.substring(pos + 1); //file name
        return results;
    }
    
    /**
     * 取得文件扩展名
     * 目的：
     * @author lixuefeng
     * @date 2016年1月7日
     * @param abstractCmd
     * @param fileName
     * @return
     */
	@SuppressWarnings("static-access")
	public static byte[] getFileExtNameByte(Command<?> command,String fileName) {
		String fileExtName = null;
		int nPos = fileName.lastIndexOf('.');
		if (nPos > 0 && fileName.length() - nPos <= command.FDFS_FILE_EXT_NAME_MAX_LEN + 1) {
			fileExtName = fileName.substring(nPos + 1);
            if (fileExtName != null && fileExtName.length() > 0) {
                return fileExtName.getBytes(command.charset);
            }else{
                return new byte[0];
            }
		} else {
            //传入的即为扩展名
            return fileName.getBytes(command.charset);
        }

	}
}
