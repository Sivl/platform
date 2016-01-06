//package com.core.util;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.apache.commons.fileupload.FileItem;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//public class FileUpThread implements  Runnable{
//	private CommonsMultipartFile file;
//	private String filePath;
//	private com.main.entity.File  fileEntity;
//	private String id;
//	
//	public CommonsMultipartFile getFile() {
//		return file;
//	}
//
//
//	public void setFile(CommonsMultipartFile file) {
//		this.file = file;
//	}
//
//
//	public String getFilePath() {
//		return filePath;
//	}
//
//
//	public void setFilePath(String filePath) {
//		this.filePath = filePath;
//	}
//
//
//	public com.main.entity.File getFileEntity() {
//		return fileEntity;
//	}
//
//
//	public void setFileEntity(com.main.entity.File fileEntity) {
//		this.fileEntity = fileEntity;
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
//	@Override
//	public void run() {
//		 long startTime=System.currentTimeMillis();   //获取开始时间
//		 FileOutputStream os=null;
//		 InputStream in =null;
//		    if(!file.isEmpty()){
//		      try {
//		    	FileItem itemFile=file.getFileItem();
//		    	String fileRelay=file.getOriginalFilename();
//		    	String fieldName=itemFile.getFieldName();
//		    	String saveId=id;
//		    	String endFix=fileRelay.substring(fileRelay.lastIndexOf("."));
//		    	if(fieldName.equals(Constants.UP_FILE_PARAMNAME_DT)){//如果是大图
//		    		saveId+="_DT"+endFix;
//		    	}else if(fieldName.equals(Constants.UP_FILE_PARAMNAME_XT)){//小图
//		    		saveId+="_XT"+endFix;
//		    	}else if(fieldName.equals(Constants.UP_FILE_PARAMNAME_SLT)){//缩略图
//		    		saveId+="_SLT"+endFix;
//		    	}else{
//		    		
//		    	}
//		    	File fileExistis=new File(filePath+"/"+saveId);
//		    	if(!fileExistis.exists()){//如果附件不存在则需要往数据库表中添加数据
//		    		fileEntity.setFileSize(FileManageUtils.FormetFileSize(itemFile.getSize()));//计算文件大小
//			    	//fileEntity.setFilePath(filePath);//保存文件的路径
//			    	fileEntity.setFileName(saveId);//这里不保存文件实际名称，修改成按照我们的规则来生成文件名
//			    	//fileEntity.setFromId(id);//业务ID
//			    	fileEntity.setFileType(itemFile.getContentType());//附件类型
//			    	fileEntity.setFileRelayName(fileRelay);//保存附件真实名称
//		    	}
//		        //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
//		         os = new FileOutputStream(filePath+"/"+saveId);
//		         in = file.getInputStream();
//		         byte [] b=new byte[1024];
//		         int len;
//		         while((len=in.read(b,0,b.length))!=-1){
//		        	 os.write(b, 0, len);
//		         } 
//		         if(null!=os){
//		    		  os.flush(); //关闭流 
//		    	  }
//		    	 if(null!=in){
//		    		 in.close();
//		    	 }
//			     if(os!=null){
//			    	 os.close();
//			     }
//		      } catch (IOException e) {
//				   e.printStackTrace();
//		      }  
//		    }
//		    long endTime=System.currentTimeMillis(); //获取结束时间
//		    System.out.println("上传文件共使用时间："+(endTime-startTime));
//		
//	}
// 
//}
