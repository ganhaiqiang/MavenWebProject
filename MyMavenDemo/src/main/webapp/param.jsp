<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		var param = {
			"dts" : [ {
				id:"111111111111",
				studentId:"666",
				subject:"语文",
				score:98
			}, {
				id:"22222222222",
				studentId:"777",
				subject:"数学",
				score:102
			} ],
			"info" : {
				"custCode" : "7550335485",
				"deptCode" : "755S"
			},
			"name" : "甘海强",
			"car" : "HONDA"
		};
		$.ajax({
			url : "${pageContext.request.contextPath}/service/param/demo4",
			// 			url : "${pageContext.request.contextPath}/service/param/demo4?other=" + encodeURI(encodeURI('甘海强')),
			type : "post",
			data : parseParam(param),
			success : function(result) {
				alert(JSON.stringify(result));
			}
		});
	});

	function parseParam(jsonObj, key) {
		var paramStr = "";
		if (jsonObj instanceof String || jsonObj instanceof Number || jsonObj instanceof Boolean) {
			paramStr += "&" + key + "=" + encodeURIComponent(jsonObj);
		} else {
			$.each(jsonObj, function(i) {
				var k = key == null ? i : key + (jsonObj instanceof Array ? "[" + i + "]" : "." + i);
				paramStr += '&' + parseParam(this, k);
			});
		}
		return paramStr.substr(1);
	};

	function jsonParam() {
		$.ajax({
			url : "${pageContext.request.contextPath}/service/param/demo1",
			type : "post",
			dataType : "json",
			data : $("#fm1").serialize(),
			success : function(data) {
				alert(JSON.stringify(data));
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}

	function getMapJson() {
		$.ajax({
			url : "${pageContext.request.contextPath}/service/param/demo2",
			type : "post",
			dataType : "json",
			data : $("#fm2").serialize(),
			success : function(data) {
				alert(JSON.stringify(data));
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}

	function demo6() {
		$.ajax({
			url : "${pageContext.request.contextPath}/service/param/demo6",
			type : "post",
			dataType : "json",
			data : {
				"name" : $("#json").val(),
				"url" : $("#url").val()
			},
			success : function(data) {
				alert(data);
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}

	function demo7() {
		$.ajax({
			url : "${pageContext.request.contextPath}/service/param/demo7",
			type : "post",
			dataType : "json",
			data : {
				"name" : $("#str").val(),
				"url" : $("#result_url").val()
			},
			success : function(data) {
				console.log(data);
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
	}
</script>
</head>
<body style="width: 700px; margin: 0 auto;">
	<!-- 二维码 -->
	<textarea rows="20" cols="80" id="json"></textarea>
	<br /> URL:
	<input type="text" id="url" style="width: 1050px;" />
	<br />
	<input type="button" value="请求二维码" onclick="demo6()" />
	<br />
	<!-- 结果 -->
	<textarea rows="10" cols="50" id="str"></textarea>
	<br /> URL：
	<input type="text" id="result_url" style="width: 1050px;" />
	<br />
	<input type="button" value="请求结果" onclick="demo7()" />

	<a href="${pageContext.request.contextPath}/service/param/demo5">demo5</a>
	<div style="width: 700px; margin: 0 auto;">
		<form id="fm3" action="${pageContext.request.contextPath}/service/param/demo3" method="post">
			<p>==============对象的数组属性或list属性的参数绑定===============</p>
			<table>
				<tr>
					<td>学生ID：</td>
					<td><input type="text" name="id" value="111111" /></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>男<input type="radio" name="sex" value="M" />女<input type="radio" name="sex" value="F" /></td>
				</tr>
				<tr>
					<td>颜色：</td>
					<td>黄<input type="checkbox" name="color" value="yellow" />绿<input type="checkbox" name="color" value="green" />红<input
						type="checkbox" name="color" value="red" /></td>
				</tr>
			</table>
			<input type="submit" value="提交" />
		</form>
		<form id="fm1" action="${pageContext.request.contextPath}/service/param/demo1" method="post">
			<p>==============List参数绑定===============</p>
			<table>
				<tr>
					<td>学生ID：</td>
					<td><input type="text" name="stuList[0].id" value="111111" /></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="stuList[0].name" value="张三" /></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="text" name="stuList[0].sex" value="男" /></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="stuList[0].age" value="23" /></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="stuList[0].phone" value="13428713005" /></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input type="text" name="stuList[0].address" value="深圳市福田区" /></td>
				</tr>
				<tr>
					<td>图片：</td>
					<td><input type="text" name="stuList[0].picture" value="D:/demo1.jpg" /></td>
				</tr>
				<tr>
					<td>日期：</td>
					<td><input type="text" name="create" value="2016-06-23" /></td>
				</tr>
			</table>
			<hr />
			<table>
				<tr>
					<td>学生ID：</td>
					<td><input type="text" name="stuList[1].id" value="111111" /></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="stuList[1].name" value="张三" /></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="text" name="stuList[1].sex" value="男" /></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="stuList[1].age" value="23" /></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="stuList[1].phone" value="13428713005" /></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input type="text" name="stuList[1].address" value="深圳市福田区" /></td>
				</tr>
				<tr>
					<td>图片：</td>
					<td><input type="text" name="stuList[1].picture" value="D:/demo1.jpg" /></td>
				</tr>
			</table>
			<input type="submit" value="提交" />
		</form>
		<input type="button" value="JSON提交" onclick="jsonParam()" />
		<form id="fm2" action="${pageContext.request.contextPath}/service/param/demo2" method="post">
			<p>==============map参数绑定===============</p>
			<table>
				<tr>
					<td>学生ID：</td>
					<td><input type="text" name="stuMap['a'].id" value="111111" /></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="stuMap['a'].name" value="张三" /></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="text" name="stuMap['a'].sex" value="男" /></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="stuMap['a'].age" value="23" /></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="stuMap['a'].phone" value="13428713005" /></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input type="text" name="stuMap['a'].address" value="深圳市福田区" /></td>
				</tr>
				<tr>
					<td>图片：</td>
					<td><input type="text" name="stuMap['a'].picture" value="D:/demo1.jpg" /></td>
				</tr>
			</table>
			<hr />
			<table>
				<tr>
					<td>学生ID：</td>
					<td><input type="text" name="stuMap['b'].id" value="8888888" /></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="stuMap['b'].name" value="潘佳丽" /></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="text" name="stuMap['b'].sex" value="女" /></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="stuMap['b'].age" value="26" /></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="stuMap['b'].phone" value="18745698533" /></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input type="text" name="stuMap['b'].address" value="广州市天河区" /></td>
				</tr>
				<tr>
					<td>图片：</td>
					<td><input type="text" name="stuMap['b'].picture" value="D:/xxxxx.jpg" /></td>
				</tr>
			</table>
			<input type="submit" value="提交" /> <input type="button" onclick="getMapJson()" value="JSON提交" />
		</form>

	</div>
</body>
</html>