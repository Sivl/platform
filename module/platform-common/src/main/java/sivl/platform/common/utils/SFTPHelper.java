//package com.core.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Properties;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelExec;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
//
//public class SFTPHelper {
//	private Session session= null;
//	private JSch jsch= null;
//	private  Channel channel = null;
//	private ChannelSftp channelSftp=null;
//	private static final String SFTP_REQ_HOST="192.168.0.85";
//    private static final String SFTP_REQ_USERNAME="root";
//    private static final String SFTP_REQ_PASSWORD="itbt2015";
//    private static final Integer SFTP_REQ_PORT=22;
//    private static final int TIMEOUT=6000;
//    public SFTPHelper(){
//    	 jsch = new JSch();   
//    }
//    public Session openSession(){
//    	 try { 
//			session = jsch.getSession(SFTP_REQ_USERNAME, SFTP_REQ_HOST, SFTP_REQ_PORT);
//			session.setPassword(SFTP_REQ_PASSWORD); 
//			Properties config = new Properties();
//		    config.put("StrictHostKeyChecking", "no");
//		    session.setConfig(config);                // 为Session对象设置properties
//		    session.setTimeout(TIMEOUT);              // 设置timeout时间
//		    int aliveInt=session.getServerAliveInterval();
//		     
//		    session.connect();                        // 通过Session建立链接
//		} catch (JSchException e) {
//			LoggerFactory.getLogger(getClass()).error("打开session失败");
//			e.printStackTrace();
//		} // 根据用户名，主机ip，端口获取一个Session对象
//    	return session;
//    }
//    public ChannelSftp getChannel(){
//    	 try {
//			channel = session.openChannel("sftp"); // 打开SFTP通道
//			channel.connect();                      // 建立SFTP通道的连接
//		} catch (JSchException e) {
//			System.out.print("打开session失败");
//			e.printStackTrace();
//		}    // 打开SFTP通道
//    	 channelSftp=(ChannelSftp) channel;
//    	return channelSftp;
//    }
//    
//    public void closeChannel(){
//        if (channel != null) {
//            channel.disconnect();
//        }
//    }
//    public void colseSession(){
//     if (session != null) {
//             session.disconnect();
//      }
//    }
//    public  int exec(String command){
//    	int exitStatus=0;
//    	 try {
//			ChannelExec openChannel = (ChannelExec) session.openChannel("exec");
//			openChannel.setCommand(command);
//		    exitStatus = openChannel.getExitStatus();
//		    System.out.println(exitStatus);
//		    openChannel.connect();  
//		} catch (JSchException e) {
//			e.printStackTrace();
//		}
//    	 return exitStatus;
//    }
//    /**
//     *     
//     * @param src  上传的文件路径地址
//     * @param dst  服务生成文件路径地址
//     * @return  
//     * @throws IOException  InputStream in = multipartFile.getInputStream();
//     */
//    public int putOneFile(MultipartFile multipartFile,String dst) throws IOException{
//    	int count=0;
//    	 try {
//			OutputStream out = getChannel().put(dst, ChannelSftp.OVERWRITE);// 使用OVERWRITE模式
//			 byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
//		        int read;
//		        if (out != null) {
//		        	InputStream in=multipartFile.getInputStream();
//		            do {
//		                read = in.read(buff, 0, buff.length);
//		                if (read > 0) {
//		                    out.write(buff, 0, read);
//		                }
//		            } while (read >= 0);
//		            out.flush();
//		            out.close();
//		            in.close();
//		            count=1;
//		        }
//		}catch(SftpException e) {
//			e.printStackTrace();
//		} 
//    	 channelSftp.quit();
//    	 return count;
//    }
//}
