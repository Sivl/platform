package sivl.platform.ue.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import sivl.platform.common.model.CommonModel;

/**
 * ue服务端多结果结果载体<br/>
 * 目的：
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
@JsonInclude(Include.NON_NULL)
public class UeMultiResultModel implements CommonModel, Serializable {

	private static final long serialVersionUID = 1L;
	//执行状态
	private String state;
	//文件索引
	private Long start;
	//文件总数
	private Long total;
	//结果集
	private List<UeFileResultModel> list;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<UeFileResultModel> getList() {
		return list;
	}
	public void setList(List<UeFileResultModel> list) {
		this.list = list;
	}
	
	
}
