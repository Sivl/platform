package sivl.platform.file.utils;

/**
 * 比特处理工具类<br/>
 * 目的：对比特、字节进行处理
 * @author lixuefeng
 * @date 2016年1月7日
 * @version 1.0
 */
public class BityUtils {

	/**
	 * long转换为字节数组
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月7日
	 * @param n
	 * @return
	 */
	public static byte[] long2buff(long n) {
		byte[] bs;
		bs = new byte[8];
		bs[0] = (byte) ((n >> 56) & 0xFF);
		bs[1] = (byte) ((n >> 48) & 0xFF);
		bs[2] = (byte) ((n >> 40) & 0xFF);
		bs[3] = (byte) ((n >> 32) & 0xFF);
		bs[4] = (byte) ((n >> 24) & 0xFF);
		bs[5] = (byte) ((n >> 16) & 0xFF);
		bs[6] = (byte) ((n >> 8) & 0xFF);
		bs[7] = (byte) (n & 0xFF);

		return bs;
	}
	
	/**
	 * 字节数组转long
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月7日
	 * @param bs
	 * @param offset
	 * @return
	 */
	public static long buff2long(byte[] bs, int offset) {
		return (((long) (bs[offset] >= 0 ? bs[offset] : 256 + bs[offset])) << 56)
				| (((long) (bs[offset + 1] >= 0 ? bs[offset + 1]
						: 256 + bs[offset + 1])) << 48)
				| (((long) (bs[offset + 2] >= 0 ? bs[offset + 2]
						: 256 + bs[offset + 2])) << 40)
				| (((long) (bs[offset + 3] >= 0 ? bs[offset + 3]
						: 256 + bs[offset + 3])) << 32)
				| (((long) (bs[offset + 4] >= 0 ? bs[offset + 4]
						: 256 + bs[offset + 4])) << 24)
				| (((long) (bs[offset + 5] >= 0 ? bs[offset + 5]
						: 256 + bs[offset + 5])) << 16)
				| (((long) (bs[offset + 6] >= 0 ? bs[offset + 6]
						: 256 + bs[offset + 6])) << 8)
				| ((long) (bs[offset + 7] >= 0 ? bs[offset + 7]
						: 256 + bs[offset + 7]));
	}
	
	/**
	 * 字节转int
	 * 目的：
	 * @author lixuefeng
	 * @date 2016年1月7日
	 * @param bs
	 * @param offset
	 * @return
	 */
	public static int buff2int(byte[] bs, int offset)
	{
		return  (((int)(bs[offset] >= 0 ? bs[offset] : 256+bs[offset])) << 24) | 
		        (((int)(bs[offset+1] >= 0 ? bs[offset+1] : 256+bs[offset+1])) << 16) | 
		        (((int)(bs[offset+2] >= 0 ? bs[offset+2] : 256+bs[offset+2])) <<  8) |
		         ((int)(bs[offset+3] >= 0 ? bs[offset+3] : 256+bs[offset+3]));
	}
}
