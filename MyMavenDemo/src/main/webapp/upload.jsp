<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<title>ajax上传文件</title>
<script type="text/javascript">
	function upload() {
		$.ajaxFileUpload({
			url : '${pageContext.request.contextPath}/service/stu/upload',
			secureuri : false,
			fileElementId : [ 'file1', 'file2' ],
			// 			fileElementId : "file_name",
			dataType : 'text/html',
			success : function(result, status) {
				//alert(JSON.stringify(result));
				$("#txt").html(result);
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	}
</script>
</head>
<body>
	附件：
	<input type="file" id="file1" name="file_name"
		accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
	<br />
	<input type="file" id="file2" name="file2_name" />
	<br />
	<input type="button" value="上传" onclick="upload()">
	<br />
	<div id="txt"></div>
</body>
</html>