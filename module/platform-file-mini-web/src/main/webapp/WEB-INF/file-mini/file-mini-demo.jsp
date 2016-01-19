<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/platform-admin/res/js/webuploader/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/platform-admin/res/js/webuploader/css/demo.css">
<link rel="stylesheet" type="text/css" href="/platform-admin/res/js/webuploader/css/style.css">
<script type="text/javascript" src="/platform-admin/res/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript">
BASE_URL = '/platform-admin';
</script>
<title>file-mini-demo</title>
</head>
<body>
	<div id="uploader" class="wu-example">
	    <div class="queueList">
	        <div id="dndArea" class="placeholder">
	            <div id="filePicker" class="webuploader-container">
	            	<div class="webuploader-pick">点击选择图片</div>
	            	<div id="rt_rt_1a8nm5lb6luvadc20lgjs1gfe1" style="position: absolute; top: 0px; left: 448px; width: 168px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
	            		<input type="file" name="file" class="webuploader-element-invisible" multiple="multiple" accept="image/*">
	            		<label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
	            	</div>
	            </div>
	            <p>或将照片拖到这里，单次最多可选300张</p>
	        </div>
	    <ul class="filelist"></ul></div>
	    <div class="statusBar" style="display:none;">
	        <div class="progress" style="display: none;">
	            <span class="text">0%</span>
	            <span class="percentage" style="width: 0%;"></span>
	        </div><div class="info">共0张（0B），已上传0张</div>
	        <div class="btns">
	            <div id="filePicker2" class="webuploader-container">
	            	<div class="webuploader-pick">继续添加</div>
	            	<div id="rt_rt_1a8nm5lbb1irnmmjdoi1blo1e536" style="position: absolute; top: 0px; left: 0px; width: 1px; height: 1px; overflow: hidden;">
	            		<input type="file" name="file" class="webuploader-element-invisible" multiple="multiple" accept="image/*">
	            			<label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
	            	</div>
	            </div>
	            <div class="uploadBtn state-pedding">开始上传</div>
	        </div>
	    </div>
	</div>
	<script type="text/javascript" src="/platform-admin/res/js/webuploader/webuploader.js"></script>
 	<script type="text/javascript" src="/platform-admin/res/js/webuploader/file-mini-demo.js"></script>
</body>
</html>