<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script language="javascript" type="text/javascript" src="js/rsa/Barrett.js"></script>
<script language="javascript" type="text/javascript" src="js/rsa/BigInt.js"></script>
<script language="javascript" type="text/javascript" src="js/rsa/RSA.js"></script>
<title>RSA加密</title>
<script type="text/javascript">
	function encrypt(content) {
		//Exponent：指数，后台publicKey生成
		var rsa_m = "971fca9f2955e5e0c5372bba583828a723b09384170aa0da699a0abcc4db06e872aa0e294c8251beccd1514a3d074e8d305706ecd27d039d07c1071fe5e138cebd0438f5aff03e86f2e00cd49c685e30b5944af4f70122474934c66a2d335c495d6c2b939af895eb1fe209a161763c8fcacfb5367caae0a89bc0d6ac1341aecf";
		//Modulus：模数，后台publicKey生成
		var rsa_e = "10001";
		setMaxDigits(131); //131 => n的十六进制位数/2+3
		var key = new RSAKeyPair(rsa_e, '', rsa_m);
		var code = encryptedString(key, content);
		return code;
	}

	function submit() {
		var name = $("#u_name").val();
		name = encodeURIComponent(name);
		var pwd = $("#u_pwd").val();
		pwd = encodeURIComponent(pwd);
		$.ajax({
			url : "${pageContext.request.contextPath}/service/stu/decryptLogin2",
			type : "POST",
			data : {
				user : encrypt(name),
				pwd : encrypt(pwd)
			},
			success : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	用户名：
	<input type="text" id="u_name" />
	<br /> 密码：
	<input type="text" id="u_pwd" />
	<br />
	<input type="button" id="submit" onclick="submit()" value="提交" />
</body>
</html>