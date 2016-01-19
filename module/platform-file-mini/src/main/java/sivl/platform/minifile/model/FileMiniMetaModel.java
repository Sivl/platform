package sivl.platform.minifile.model;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 文件mini载体
 * 目的：
 * @author lixuefeng
 * @date 2016年1月8日
 * @version 1.0
 */
@JsonIgnoreProperties({"bytes"})
public class FileMiniMetaModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileName;
    private String fileSize;
    private String fileType;
    private byte[] bytes;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	@Override
	public String toString() {
		return "FileMiniMetaModel [fileName=" + fileName + ", fileSize="
				+ fileSize + ", fileType=" + fileType + ", bytes="
				+ Arrays.toString(bytes) + "]";
	}
    
}
