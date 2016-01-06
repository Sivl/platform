//package com.core.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.util.List;
//
//import org.apache.commons.fileupload.FileItem;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//public class FileManageUtils  {
//  public static void main(String args[]) throws FileNotFoundException{
//	  
//	  File file=new File("D:/Chrysanthemum.jpg");
//	  try {
//		FileOutputStream os=new FileOutputStream("L:\\html\\4028804a5011b03b015011b553860002\\product\\xprod2040\\img\\Chrysanthemum.jpg");
//		FileInputStream in=new FileInputStream(file);
//		 byte [] b=new byte[1024];
//         int len;
//         while((len=in.read(b,0,b.length))!=-1){
//        	 os.write(b, 0, len);
//         } 
//        os.flush();
//        in.close();
//        os.close();
//     
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  }
// 
//
//public static  String uploadFiles(CommonsMultipartFile file,String filePath,com.main.entity.File  fileEntity,String id) throws IOException{
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
//		    		fileEntity.setFileSize(FormetFileSize(itemFile.getSize()));//计算文件大小
//			    	//fileEntity.setFilePath(filePath);//保存文件的路径
//			    	fileEntity.setFileName(saveId);//这里不保存文件实际名称，修改成按照我们的规则来生成文件名
//			    	//fileEntity.setFromId(id);//业务ID
//			    	fileEntity.setFileType(itemFile.getContentType());//附件类型
//			    	fileEntity.setFileRelayName(fileRelay);//保存附件真实名称
//		    	}
//		        //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
//		         os = new FileOutputStream(filePath+"/"+saveId);
//		         in = file.getInputStream();
//		         byte [] b=new byte[1024*256];
//		         int len;
//		         while((len=in.read(b,0,b.length))!=-1){
//		        	 os.write(b, 0, len);
//		         } 		       
//		      } catch (FileNotFoundException e) {
//		    	  e.printStackTrace();
//		          throw e;
//		      } catch (IOException e) {
//		    	  e.printStackTrace();
//		    	  throw e;
//		      }finally{
//		    	  if(null!=os){
//		    		  os.flush(); //关闭流 
//		    	  }
//		    	 if(null!=in){
//		    		 in.close();
//		    	 }
//			     if(os!=null){
//			    	 os.close();
//			     }
//		      }
//		    }
//		    long endTime=System.currentTimeMillis(); //获取结束时间
//		    System.out.println("上传文件共使用时间："+(endTime-startTime));
//		    return "success";
//	}
//public static  String upMultipFiles(MultipartFile [] files,String uploadRePath,String saveFilePath,List<com.main.entity.File> list,String id) throws IOException{
//	 long startTime=System.currentTimeMillis();   //获取开始时间
//	int count=0;
//	
//	for (MultipartFile multipartFile : files) {
//		 count++;
//		 if (multipartFile.isEmpty()) continue;
//		    FileOutputStream fileOS;
//		  
//		    com.main.entity.File fileEntity=new com.main.entity.File();
//			try {
//				String fileRelayName=multipartFile.getOriginalFilename();
//				String endFix=fileRelayName.substring(fileRelayName.lastIndexOf("."));//获取文件后缀
//				String saveName=id+"_"+count+endFix;
//				
//				File fileExistis=new File(uploadRePath+"/"+saveName);
//				if(!fileExistis.exists()){
//					fileEntity.setFileSize(FormetFileSize(multipartFile.getSize()));//计算文件大小
//					fileEntity.setFilePath(saveFilePath);//保存文件的路径
//					fileEntity.setFileName(saveName);//这里不保存文件实际名称，修改成按照我们的规则来生成文件名
//					fileEntity.setFileType(multipartFile.getContentType());//附件类型
//					fileEntity.setFileRelayName(fileRelayName);//保存附件真实名称
//					list.add(fileEntity);
//				}
//		        //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
//				//拿到输出流，同时重命名上传的文件  
//				fileOS = new FileOutputStream(uploadRePath+"/"+saveName);
//				//拿到上传文件的输入流  
//               InputStream in = multipartFile.getInputStream();  
//               //以写字节的方式写文件  
//               byte [] b=new byte[1024*256];
//               int len;
//               while((len=in.read(b,0,b.length))!=-1){
//               	fileOS.write(b, 0, len);
//               } 
//               fileOS.flush();  
//               fileOS.close();  
//               in.close();
//			} catch (FileNotFoundException e) {
//				 throw e;
//			} catch (IOException e) {
//				throw e;
//			}
//	}
//	long endTime=System.currentTimeMillis(); //获取结束时间
//    System.out.println("上传文件共使用时间："+(endTime-startTime));
//	return "success";
//}
//	public static boolean deleteFile(String filePath){
//		  File file=new File(filePath);
//		  if(file.exists()){
//			 return file.delete();
//		  }else{
//			  return true;//文件不存在，也返回true 表示删除成功
//		  }
//	}
//	//递归
//	public long getFileSize(File f)throws Exception//取得文件夹大小
//	{
//	   long size = 0;
//	   File flist[] = f.listFiles();
//	   for (int i = 0; i < flist.length; i++)
//	   {
//	       if (flist[i].isDirectory())
//	       {
//	           size = size + getFileSize(flist[i]);
//	       } else
//	       {
//	           size = size + flist[i].length();
//	       }
//	   }
//	   return size;
//	}
//	//递归求取目录文件个数
//	 public long getlist(File f){//递归求取目录文件个数
//	       long size = 0;
//	       File flist[] = f.listFiles();
//	       size=flist.length;
//	       for (int i = 0; i < flist.length; i++) {
//	           if (flist[i].isDirectory()) {
//	               size = size + getlist(flist[i]);
//	               size--;
//	           }
//	       }
//	       return size;
//	 
//	   }
//	//转换文件大小
//	 public static String FormetFileSize(long fileS) {
//	       DecimalFormat df = new DecimalFormat("#.00");
//	       String fileSizeString = "";
//	       if (fileS < 1024) {
//	           fileSizeString = df.format((double) fileS) + "B";
//	       } else if (fileS < 1048576) {
//	           fileSizeString = df.format((double) fileS / 1024) + "K";
//	       } else if (fileS < 1073741824) {
//	           fileSizeString = df.format((double) fileS / 1048576) + "M";
//	       } else {
//	           fileSizeString = df.format((double) fileS / 1073741824) +"G";
//	       }
//	       return fileSizeString;
//	    }
//
//
//	public static void upEstorFiles(MultipartFile[] files, String uploadRePath, String saveFilePath,
//			List<com.main.entity.File> list)  throws IOException{
//		int count=0;
//		for (MultipartFile multipartFile : files) {
//			count++;
//			 if (multipartFile.isEmpty()) continue;
//			    FileOutputStream fileOS;
//			  
//			    com.main.entity.File fileEntity=new com.main.entity.File();
//				try {
//					String fileRelayName=multipartFile.getOriginalFilename();
//					String endFix=fileRelayName.substring(fileRelayName.lastIndexOf("."));//获取文件后缀
//					String saveName="estor_"+count+endFix;
//					
//					File fileExistis=new File(uploadRePath+"/"+saveName);
//					if(!fileExistis.exists()){
//						fileEntity.setFileSize(FormetFileSize(multipartFile.getSize()));//计算文件大小
//						fileEntity.setFilePath(saveFilePath);//保存文件的路径
//						fileEntity.setFileName(saveName);//这里不保存文件实际名称，修改成按照我们的规则来生成文件名
//						fileEntity.setFileType(multipartFile.getContentType());//附件类型
//						fileEntity.setFileRelayName(fileRelayName);//保存附件真实名称
//						list.add(fileEntity);
//					}
//			        //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
//					//拿到输出流，同时重命名上传的文件  
//					fileOS = new FileOutputStream(uploadRePath+"/"+saveName);
//					//拿到上传文件的输入流  
//	               InputStream in = multipartFile.getInputStream();  
//	               //以写字节的方式写文件  
//	               byte [] b=new byte[1024*256];
//	               int len;
//	               while((len=in.read(b,0,b.length))!=-1){
//	               	fileOS.write(b, 0, len);
//	               } 
//	               fileOS.flush();  
//	               fileOS.close();  
//	               in.close();
//				} catch (FileNotFoundException e) {
//					 throw e;
//				} catch (IOException e) {
//					throw e;
//				}
//		}
//	}
//	 
//}
