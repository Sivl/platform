package sivl.platform.ue;

import org.junit.Test;

import sivl.platform.common.utils.ComFileUtils;
import sivl.platform.ue.utils.UeFileUtils;

public class UeFileUtilsTest {

	
	@Test
	public void pathTest(){
		String path = "c:/sf\\sgjs\\wer\\ghjf/sdfs/scvb/{yyyy}{mm}{dd}/{time}-{rand:6}";
		path = ComFileUtils.cleanFilePath(path);
		System.out.println(path);
		System.out.println(ComFileUtils.filePathParse(path, "filename$123/567.jpg"));
	}
}
