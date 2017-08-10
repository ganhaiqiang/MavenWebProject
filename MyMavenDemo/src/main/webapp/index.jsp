<%@page import="com.demo.pojo.Records"%>
<%@page import="com.demo.pojo.Student"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-ui-themes-1.8.24/themes/cupertino/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="css/ui.jqgrid.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.bstablecrosshair.js"></script>
<script language="javascript" type="text/javascript" src="js/grid.locale-cn.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jsencrypt.js"></script>

<title>首页</title>
<script type="text/javascript">
	function getHtml() {
		$.ajax({
			url : "${pageContext.request.contextPath}/service/stu/getHtml",
			type : "GET",
			dataType : "script",
			success : function(data) {

			}
		});
	}

	function getCas() {
		$.ajax({
			url : "${pageContext.request.contextPath}/service/stu/cas/url",
			type : "POST",
			data : "{name:甘海强}",
			success : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<a href="${pageContext.request.contextPath}/service/stu/getAllUrl">getAllUrl</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getSource">获取messageSource</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/async/test">spring异步任务测试</a>
	<br />
	<input type="button" value="获取getCas" onclick="getCas()" />
	<br />
	<a href="${pageContext.request.contextPath}/service/redis/clearKeys">clearKeys</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/redis/getKeys">getRedisKeys</a>
	<br />
	<a href="javascript:void(0);" onclick="getHtml()">getHtml</a>动态加载js函数：
	<a href="javascript:void(0);" onclick="hehe()">hehe()</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getProp">获取Properties值</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/execute/ipconfig">执行命令</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/insertStudentByMap">insertStudentByMap</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/cookie/showCookies">获取所有cookie</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/delMemUsers">delMemUsers</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getByTable?tableName=student&column=count(1) ">getByTable</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getStuPicList">getStuPicList</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getStuPic">getStuPic</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/excel">下载excel</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/add">addStudent</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getSession">getSession</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getFtl">FTL==>HTML</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/downFile">下载DCN-V6.8.pdf</a>
	<br />
	<input type="button" value="请求" onclick="getDown()" />
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getAll">getAll</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getStu">getStu</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/excel">excel</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/error">error.jsp</a>
	<br />
	<a href="${pageContext.request.contextPath}/service/stu/getById">ID为5</a>
	<br />
	<table id="tab" style="border: 2px solid #444; border-collapse: collapse;">
		<tr>
			<td>ID</td>
			<td>图书名称</td>
			<td>图书编号</td>
			<td>借出日期</td>
		</tr>
	</table>
	<hr>
	<input type="button" id="query" value="查询" />
	<select id="sex">
		<option value="" selected="selected">全部</option>
		<option value="男">男</option>
		<option value="女">女</option>
	</select>
	<input type="button" id="edit" value="修改" />
	<input type="button" id="add" value="添加" />
	<input type="button" id="del" value="删除" />

	<table id="jqgrid"></table>
	<div id="gridPager"></div>
	<hr>

	验证码1：
	<img id="imgObj" style="cursor: pointer;" onclick="changeImg($(this))" alt="验证码"
		src="${pageContext.request.contextPath}/service/stu/getCode" />
	<br /> 验证码2：
	<img id="imgObj" width="96" height="20" style="cursor: pointer;" onclick="changeImg($(this))" alt="验证码"
		src="${pageContext.request.contextPath}/service/stu/getVerify" />
	<br />

	<form id="fm" action="${pageContext.request.contextPath}/service/stu/getForm" method="post"
		enctype="multipart/form-data">
		姓名1：<input type="text" name="name" /><br /> 密码1：<input type="password" name="pwd" /><br /> 附件1：<input type="file"
			name="file1" /><br /> 附件2：<input type="file" name="ffff" /><br />
		<hr />
		姓名2：<input type="text" name="name" /><br /> 密码2：<input type="password" name="pwd" /><br /> 附件3：<input type="file"
			name="file2" /><br /> 附件4：<input type="file" name="ffff2" /><br />
		<hr />
		<input type="submit" value="提交" /> <input type="button" id="btn_sub" value="ajax提交" />
	</form>
	<br /> RSA公钥加密、私钥解密
	<form id="fm" action="${pageContext.request.contextPath}/service/stu/decryptLogin" method="post"
		enctype="multipart/form-data">
		用户名：<input type="text" name="user_name" /><br /> 密 码：<input type="password" name="user_pwd" /><br /> <input
			type="button" onclick="login()" value="提交表单" />
	</form>
	<br />
	<img id="img" alt="pic" src="${pageContext.request.contextPath}/service/stu/getImg?size=0" />
	<br />
	<input type="button" value="增加进度" onclick="setProcess()" />
	<br />
	<img alt="pic11" src="${pageContext.request.contextPath}/service/stu/getPic">
	<br />
	<hr>
	<img alt="pic12" src="${pageContext.request.contextPath}/service/stu/getBarcode?keycode=222222222">
	<br />
	<hr>
	<img alt="pic13"
		src="${pageContext.request.contextPath}/service/stu/getBarcode2D?keycode=http://42.121.120.150/download?val_code=VwZIt6UoBeqDdYuvZ7fP1462955865981&type=zip">
	<br />
	<hr>
	<script type="text/javascript">
		$(function() {
			//var formData = new FormData($( "#fm" )[0]); 
			var formobj = document.getElementById("fm");
			var formData = new FormData(formobj);
			$("#btn_sub").click(function() {
				$.ajax({
					url : "stu/getForm",
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(data) {

					},
					error : function() {

					}
				});
			});

		});
		var num = 0;
		var timer = setInterval('setProcess()', 2000);

		function setProcess() {
			if (num < 80) {
				num += 20;
			} else {
				num += 5;
			}
			if (num >= 100) {
				num = 100;
				clearInterval(timer);
			}
			$("#img").attr(
					"src",
					"${pageContext.request.contextPath}/service/stu/getImg?size="
							+ num + "&time" + new Date());
		}

		$.bstablecrosshair('tab', {
			color : '#444',
			background : '#aaa',
			'foreground' : '#fff'
		});

		var lastSel;
		$("#jqgrid").jqGrid({
			url : '${pageContext.request.contextPath}/service/stu/getAll',
			editurl : "${pageContext.request.contextPath}/service/stu/getAll",
			cellurl : "${pageContext.request.contextPath}/service/stu/getAll",
			datatype : "json",
			//colNames : [ '编号', '姓名', '性别', '年龄', '住址', ],
			colModel : [ {
				label : '编号',
				name : 'id',
				width : 55,
				align : "center"
			}, {
				label : '姓名',
				name : 'name',
				width : 55,
				align : "center"
			}, {
				label : '性别',
				name : 'sex',
				width : 50,
				align : "center"
			}, {
				label : '年龄',
				name : 'age',
				width : 50,
				align : "center"
			}, {
				label : '住址',
				name : 'address',
				align : "center"
			}, ],
			jsonReader : {
				root : "rows",
				page : "page",//当前页数
				total : "total", //总页数 
				records : "records",//总行数  
				repeatitems : false
			},
			cellEdit : true,
			width : 700,
			height : 240,
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : '#gridPager',
			sortname : 'id',
			mtype : "post",
			viewrecords : true,
			sortorder : "desc",
			caption : "人员信息表",
			onSelectRow : function(id, status, e) {
				//var rowData = $("#jqgrid").jqGrid("getRowData", id);
				//alert(JSON.stringify(rowData));
				/* if(id && id!==lastSel){ 
				   jQuery(this).restoreRow(lastSel); 
				   lastSel=id; 
				} 
				jQuery(this).editRow(id, true); */
			}
		});
		$("#jqgrid").jqGrid('navGrid', '#gridPager', {
			edit : true,
			add : true,
			del : true
		});

		//查询按钮
		$("#query").click(function() {
			$("#jqgrid").jqGrid('setGridParam', {
				url : "${pageContext.request.contextPath}/service/stu/getAll",
				//发送数据
				postData : {
					sex : $("#sex").val()
				},
				page : 1,
				//该方法是加载完
				loadComplete : function(data) {
					//alert("查询完成" + JSON.stringify(data));
				}
			}).trigger("reloadGrid");//重新载入
		});

		//修改数据
		$("#edit")
				.click(
						function() {
							//gr是获取 编辑行的id
							var gr = jQuery("#jqgrid").jqGrid('getGridParam',
									'selrow');
							if (gr != null) {
								jQuery("#jqgrid").jqGrid('editGridRow', gr, {
									height : 300,
									reloadAfterSubmit : false

								});
								var tr = "<tr><td>姓名：</td><td><input type='text' name='user_name' /></td></tr><tr><td>地址：</td><td><input type='text' name='address' /></td></tr>";
								$("#TblGrid_jqgrid tbody").append(tr);
							} else {
								alert("Please Select Row");
							}
						});

		//增加数据
		$("#add").click(function() {
			jQuery("#jqgrid").jqGrid('editGridRow', "new", {
				height : 300,
				reloadAfterSubmit : false
			});
			//$("#FrmGrid_jqgrid").append("<tr><td>姓名：</td><td><input type='text' name='na' /></td></tr>");
			//$("#Act_Buttons").before("<tr><td>姓名：</td><td><input type='text' name='na' /></td></tr>");
		});

		//删除数据
		$("#del").click(function() {
			var gr = jQuery("#jqgrid").jqGrid('getGridParam', 'selrow');
			if (gr != null) {
				var result = jQuery("#jqgrid").jqGrid('delGridRow', gr, {
					reloadAfterSubmit : false
				});
				//alert(result);
			} else
				alert("Please Select Row to delete!");

		});

		function changeImg(obj) {
			var imgSrc = obj;
			var src = imgSrc.attr("src");
			imgSrc.attr("src", chgUrl(src));
		}
		//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
		function chgUrl(url) {
			var timestamp = (new Date()).valueOf();
			url = url + "?timestamp=" + timestamp;
			return url;
		}

		function getDown() {
			var form = $("<form>");//定义一个form表单
			form.attr("style", "display:none");
			form.attr("target", "");
			form.attr("method", "post");
			form.attr("action",
					"${pageContext.request.contextPath}/service/stu/downFile");
			var input1 = $("<input>");
			input1.attr("type", "hidden");
			input1.attr("name", "exportData");
			input1.attr("value", (new Date()).getMilliseconds());
			$("body").append(form);//将表单放置在web中
			form.append(input1);

			form.submit();//表单提交

			/* $.ajax({
				url:"${pageContext.request.contextPath}/service/stu/downFile",
				type:"get",
				dataType:"json",
				data:{},
				success:function(data){
					
				},
				error:function(data){
					alert(data.responseText);
				}
			}); */
		}

		function login() {
			var userName = $("input[name='user_name']").val();
			var userPwd = $("input[name='user_pwd']").val();
			$
					.ajax({
						url : "${pageContext.request.contextPath}/service/stu/decryptLogin",
						type : "POST",
						dataType : "JSON",
						headers : {
							Accept : "text/plain; charset=utf-8",
						},
						data : {
							user_name : decode(userName),
							user_pwd : decode(userPwd)
						},
						success : function(data) {
							alert(data);
						},
						error : function(e) {
							alert(e.responseText);
						}
					});
		}

		//返回密文
		function decode(obj) {
			var publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCh0UAzMGJKYmKR5wTVbkTfG+UsdE7WjpVo18tQOkXwuMzlnQ/NPkoTcRr398wvDst/c0pqRax+zXiO/ZcCPVAfvQVPMu8CqOi/QsrcNj5LGKqLYsxBpWy/Qb/Xhxov9lXxsGNFGOlh2dojUJ8IVg0/Y45Q+2XWX8HGJKTlHG4AUQIDAQAB";
			var encrypt = new JSEncrypt();
			encrypt.setPublicKey(publicKey);
			var code = encrypt.encrypt(obj);
			console.log(code);
			return code;
		}
	</script>
</body>
</html>