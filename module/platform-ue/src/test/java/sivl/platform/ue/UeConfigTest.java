package sivl.platform.ue;

import org.junit.Test;

import sivl.platform.common.utils.JSONUtil;
import sivl.platform.ue.model.UeConfig;

/**
 * ue配置测试
 * 目的：
 * @author lixuefeng
 * @date 2016年1月14日
 * @version 1.0
 */
public class UeConfigTest {

	@Test
	public void testUeCongif(){
		UeConfig ueConfig = new UeConfig();
		String json = JSONUtil.obj2json(ueConfig);
		System.out.println(json);
	}
}
