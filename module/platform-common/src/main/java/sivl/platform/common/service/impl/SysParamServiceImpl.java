package sivl.platform.common.service.impl;import java.io.IOException;import java.io.InputStream;import java.util.ArrayList;import java.util.List;import java.util.Properties;import org.springframework.stereotype.Service;import sivl.platform.common.constant.Constant;import sivl.platform.common.model.SysParamModel;import sivl.platform.common.service.SysParamService;import sivl.platform.common.utils.DateUtil;import sivl.platform.common.utils.StringUtil;/** * 系统参数接口实现<br/> * 目的：用于读取系统配置信息 * @author lixuefeng * @createTime 2015年12月10日 * @version 1.0 */@Service("sysParamService")public class SysParamServiceImpl implements SysParamService {		private static Properties sysParamPro = null;	//属性前缀	private static String proPrefix = "sys.param.";	//value后缀	private static String valueSuffix=".value";	//modifTime后缀	private static String modifTimeSuffix=".modifTime";	//modifTime后缀	private static String enableSuffix=".enabled";	@Override	public List<SysParamModel> getAllSysParamList() {		//TODO 先去缓存服务器中读取		return getSysParamListFromPro(null,null);	}	@Override	public List<SysParamModel> getAllEnabledSysParamList() {		//TODO 先去缓存服务器中读取		return getSysParamListFromPro(null,true);	}	@Override	public List<SysParamModel> getSysPatamListByGroupName(String groupName) {		//TODO 先去缓存服务器中读取		return getSysParamListFromPro(groupName,null);	}		@Override	public List<SysParamModel> getSysPatamListEnabledByGroupName(String groupName) {		//TODO 先去缓存服务器中读取		return getSysParamListFromPro(groupName,true);	}	@Override	public SysParamModel getSysParamByCode(String code) {		if(StringUtil.isEmpty(code)){			return null;		}		//TODO 先去缓存服务器中读取		return getSysParam(code,null);	}		@Override	public SysParamModel getSysParamEnableByCode(String code) {		if(StringUtil.isEmpty(code)){			return null;		}		//TODO 先去缓存服务器中读取		return getSysParam(code,true);	}		@Override	public String getValue(String code) {		SysParamModel sysp = getSysParamEnableByCode(code);		if(null == sysp){			return null;		}		return sysp.getValue();	}	/**	 * 根据编码取得单个系统参数	 * @param code	 * @return	 */	private SysParamModel getSysParam(String code,Boolean enable){		SysParamModel sysParam = null;		Constant sysParamEnum = Constant.getConstant(code);		//当前系统参数不存在		if(null == sysParamEnum.getKey()){			return sysParam;		}		//取得属性文件		Properties pro = loadSysParamProperty();		String isEnables = StringUtil.isEmpty(pro.getProperty(proPrefix+sysParamEnum.getKey()+enableSuffix)) ? sysParamEnum.isEnabled().toString() : pro.getProperty(proPrefix+sysParamEnum.getKey()+enableSuffix);		Boolean isEnable = Boolean.parseBoolean(isEnables);		if(null != enable && enable && !isEnable){			//当前参数是关闭的 返回空			return sysParam;		}		sysParam = new SysParamModel();		//参数值		String value = StringUtil.isEmpty(pro.getProperty(proPrefix+sysParamEnum.getKey()+valueSuffix)) ? sysParamEnum.getValue() : pro.getProperty(proPrefix+sysParamEnum.getKey()+valueSuffix);		//修改时间		String modifTime = StringUtil.isEmpty(pro.getProperty(proPrefix+sysParamEnum.getKey()+modifTimeSuffix)) ? null : pro.getProperty(proPrefix+sysParamEnum.getKey()+modifTimeSuffix);		sysParam.setCode(sysParamEnum.getKey());		sysParam.setName(sysParamEnum.getName());		sysParam.setValue(value);		sysParam.setEnabled(isEnable);		sysParam.setGroupName(sysParamEnum.getGroupName());		sysParam.setModifTime(DateUtil.fmtStrToDate(modifTime));		sysParam.setDescribe(sysParamEnum.getDescribe());		return sysParam;	}		/**	 * 根据类型从属性文件中取得系统参数集合<br/>	 * groupName is null 查询全部；is not null 根据分组查询</br>	 * enable true 开启 false 关闭 null 全部	 * @param groupName	 * @param enable	 * @return	 */	private List<SysParamModel> getSysParamListFromPro(String groupName,Boolean enable){		List<SysParamModel> list = new ArrayList<SysParamModel>();		//取得属性文件		Properties pro = loadSysParamProperty();		//遍历枚举		for(Constant sysParamEnum : Constant.values()){			String isEnables = StringUtil.isEmpty(pro.getProperty(proPrefix+sysParamEnum.getKey()+enableSuffix)) ? sysParamEnum.isEnabled().toString() : pro.getProperty(proPrefix+sysParamEnum.getKey()+enableSuffix);			Boolean isEnable = Boolean.parseBoolean(isEnables);			//开启的			if(null != enable && enable && !isEnable){				//非开启的直接跳出				continue;			}			//关闭的			if(null != enable && !enable && isEnable){				//开启的直接跳出				continue;			}			if(StringUtil.isNotEmpty(groupName) && !sysParamEnum.getGroupName().equals(groupName)){				//查询具体某一分组的参数，非当前分组直接跳出，执行下一循环				continue;			}			SysParamModel sysParam = new SysParamModel();			//参数值			String value = StringUtil.isEmpty(pro.getProperty(proPrefix+sysParamEnum.getKey()+valueSuffix)) ? sysParamEnum.getValue() : pro.getProperty(proPrefix+sysParamEnum.getKey()+valueSuffix);			//修改时间			String modifTime = StringUtil.isEmpty(pro.getProperty(proPrefix+sysParamEnum.getKey()+modifTimeSuffix)) ? null : pro.getProperty(proPrefix+sysParamEnum.getKey()+modifTimeSuffix);			sysParam.setCode(sysParamEnum.getKey());			sysParam.setName(sysParamEnum.getName());			sysParam.setValue(value);			sysParam.setEnabled(isEnable);			sysParam.setGroupName(sysParamEnum.getGroupName());			sysParam.setModifTime(DateUtil.fmtStrToDate(modifTime));			sysParam.setDescribe(sysParamEnum.getDescribe());			list.add(sysParam);		}		return list;	}		/**	 * 加载系统参数属性文件	 * @return	 */	private Properties loadSysParamProperty(){		if(sysParamPro == null){			sysParamPro = new Properties();		}		//如果存在不去重复加载		String version = sysParamPro.getProperty("sys.param.version");		if(StringUtil.isNotEmpty(version)){			return sysParamPro;		}		InputStream is=this.getClass().getResourceAsStream("/sysParam.properties");		try {			sysParamPro.load(is);			is.close();		} catch (IOException e) {					} finally{			try {				is.close();			} catch (IOException e) {			}		}		return sysParamPro;	}}