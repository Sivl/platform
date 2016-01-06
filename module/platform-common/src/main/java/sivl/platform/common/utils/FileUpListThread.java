//package com.core.util;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileUpListThread implements Runnable
//{
//
//	private MultipartFile [] files;
//	private String uploadRePath;
//	private String saveFilePath;
//	private List<com.main.entity.File> list;
//	private String id;
//	
//	
//	public MultipartFile[] getFiles() {
//		return files;
//	}
//
//
//	public void setFiles(MultipartFile[] files) {
//		this.files = files;
//	}
//
//
//	public String getUploadRePath() {
//		return uploadRePath;
//	}
//
//
//	public void setUploadRePath(String uploadRePath) {
//		this.uploadRePath = uploadRePath;
//	}
//
//
//	public String getSaveFilePath() {
//		return saveFilePath;
//	}
//
//
//	public void setSaveFilePath(String saveFilePath) {
//		this.saveFilePath = saveFilePath;
//	}
//
//
//	public List<com.main.entity.File> getList() {
//		return list;
//	}
//
//
//	public void setList(List<com.main.entity.File> list) {
//		this.list = list;
//	}
//
//
//	public String getId() {
//		return id;
//	}
//
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//
//	public void run() {
//		 long startTime=System.currentTimeMillis();   //获取开始时间
//			int count=0;
//			
//			for (MultipartFile multipartFile : files) {
//				 count++;
//				 if (multipartFile.isEmpty()) continue;
//				    FileOutputStream fileOS;
//				  
//				    com.main.entity.File fileEntity=new com.main.entity.File();
//					try {
//						String fileRelayName=multipartFile.getOriginalFilename();
//						String endFix=fileRelayName.substring(fileRelayName.lastIndexOf("."));//获取文件后缀
//						String saveName=id+"_"+count+endFix;
//						
//						File fileExistis=new File(uploadRePath+"/"+saveName);
//						if(!fileExistis.exists()){
//							fileEntity.setFileSize(FileManageUtils.FormetFileSize(multipartFile.getSize()));//计算文件大小
//							fileEntity.setFilePath(saveFilePath);//保存文件的路径
//							fileEntity.setFileName(saveName);//这里不保存文件实际名称，修改成按照我们的规则来生成文件名
//							fileEntity.setFileType(multipartFile.getContentType());//附件类型
//							fileEntity.setFileRelayName(fileRelayName);//保存附件真实名称
//							list.add(fileEntity);
//						}
//				        //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
//						//拿到输出流，同时重命名上传的文件  
//						fileOS = new FileOutputStream(uploadRePath+"/"+saveName);
//						//拿到上传文件的输入流  
//		               InputStream in = multipartFile.getInputStream();  
//		               //以写字节的方式写文件  
//		               byte [] b=new byte[1024];
//		               int len;
//		               while((len=in.read(b,0,b.length))!=-1){
//		               	fileOS.write(b, 0, len);
//		               } 
//		               fileOS.flush();  
//		               fileOS.close();  
//		               in.close();
//					} catch (IOException e) {
//						  
//					}  
//			}
//			long endTime=System.currentTimeMillis(); //获取结束时间
//		    System.out.println("上传文件共使用时间："+(endTime-startTime));
//		
//	}
//
//}
