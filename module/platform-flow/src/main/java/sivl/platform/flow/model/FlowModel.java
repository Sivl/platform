package sivl.platform.flow.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import sivl.platform.common.model.CommonModel;

/**
 * 流量统计数据载体
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月27日
 * @version 1.0
 */
@Document(collection="flowLog")
public class FlowModel implements Serializable,CommonModel {

	private static final long serialVersionUID = 1L;
	@Id
	private ObjectId id;
	//统计类型
	@TextIndexed
	private String type;
	//页面名称
	private String pageName;
	//页面路径
	private String pageUrl;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//停留时间
	private Long residenceTime;
	//浏览器名称
	private String browerName;
	//浏览器版本
	private String browerVersion;
	//浏览器内核
	private String browerCore;
	//浏览器运行平台
	private String browerPlatform;
	//添加时间
	@Indexed
	@CreatedDate
	private Date addTime;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getResidenceTime() {
		if(residenceTime != null){
			return residenceTime;
		}
		if(startTime != null && endTime != null){
			residenceTime = endTime.getTime() - startTime.getTime();
		}
		return residenceTime;
	}
	public void setResidenceTime(Long residenceTime) {
		this.residenceTime = residenceTime;
	}
	public String getBrowerName() {
		return browerName;
	}
	public void setBrowerName(String browerName) {
		this.browerName = browerName;
	}
	public String getBrowerVersion() {
		return browerVersion;
	}
	public void setBrowerVersion(String browerVersion) {
		this.browerVersion = browerVersion;
	}
	public String getBrowerCore() {
		return browerCore;
	}
	public void setBrowerCore(String browerCore) {
		this.browerCore = browerCore;
	}
	public String getBrowerPlatform() {
		return browerPlatform;
	}
	public void setBrowerPlatform(String browerPlatform) {
		this.browerPlatform = browerPlatform;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Override
	public String toString() {
		return "FlowModel [id=" + id + ", type=" + type + ", pageName="
				+ pageName + ", pageUrl=" + pageUrl + ", startTime="
				+ startTime + ", endTime=" + endTime + ", residenceTime="
				+ residenceTime + ", browerName=" + browerName
				+ ", browerVersion=" + browerVersion + ", browerCore="
				+ browerCore + ", browerPlatform=" + browerPlatform
				+ ", addTime=" + addTime + "]";
	}
}
