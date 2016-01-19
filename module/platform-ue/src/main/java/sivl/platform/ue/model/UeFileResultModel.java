package sivl.platform.ue.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import sivl.platform.common.model.CommonModel;

/**
 * ue服务端结果载体<br/>
 * 目的：
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
@JsonInclude(Include.NON_NULL)
public class UeFileResultModel implements CommonModel, Serializable {

	private static final long serialVersionUID = 1L;
	//执行状态
	private String state;
	//文件大小
	private String size;
	//文件名
	private String title;
	//保存路径
	private String url;
	//文件类型
	private String type;
	//原文件名
	private String original;
	//url源
	private String source;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
