/**
 * js工具大全
 */

/**
 * json对象拼接成请求字符串
 * 
 * @param jsonObj json对象
 * @param key 属性前缀，可为空
 * @returns
 */
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

/**
 * 判断指定名称的复选框是否被选中
 * 
 * @param chname
 *            复选框名称
 * @returns {Boolean}
 */
function chkCheckCha(chname) {
	var obj = jQuery("[name='" + chname + "']");
	var isCheck = false;
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].checked == true) {
			isCheck = true;
			break;
		}
	}
	return isCheck;
}

/**
 * 得到指定名称的复选框被选中个数
 * 
 * @param chname
 * @returns {Number}
 */
function checkChangedOnly(chname) {
	var obj = jQuery("[name='" + chname + "']");
	var count = 0;
	for (var i = 0; i < obj.length; i++) {
		obj[i].checked && count++;
	}
	return count;
}

/**
 * 得到指定名称的单个被选中的复选择框的值
 * 
 * @param chname
 * @returns {String}
 */
function getValueCheckOnly(chname) {
	var str = "";
	jQuery("[name='" + chname + "'][checked]").each(function() {
		str += jQuery(this).val();
	});
	return str;
}

/**
 * 得到多个复选框的值
 * 
 * @param chname
 * @returns
 */
function getCheckVals(chname) {
	var str = "";
	jQuery("[name='" + chname + "'][checked]").each(function() {
		str += jQuery(this).val() + "@";
	});
	return str.substring(0, str.length - 1);
}

/**
 * @param chname
 * @param tagId
 * @returns {String}
 */
function getCheckHidden(chname, tagId) {
	var strval = getCheckVals(chname);
	var arr = "";
	var temp = strval.split("@");
	for (var i = 0; i < temp.length; i++) {
		var val = temp[i];
		var id = tagId + val;
		var tag = $("#" + id).val();
		arr += tag + "@";
	}
	return arr;
}

/**
 * 得到一些复选框的值 复选框的值中是否包含有","若无则用","将多个值组合 若有则先将值用","隔开再用","组合
 * 
 * @param chname
 * @returns
 */
function getSomeChVals(chname) {
	var str = "";
	jQuery("[name='" + chname + "'][checked]").each(function() {
		var temp = jQuery(this).val();
		if (temp.indexOf(",") == -1) {
			str += temp + ",";
		} else {
			var tempValue = temp.split(",");
			str += tempValue[0] + ",";
		}
	});
	return str.substring(0, str.length - 1);
}

/**
 * @param chname
 * @returns
 */
function getAnyCheckValue(chname) {
	var str = getCheckVals(chname);
	var tstr = str.split("@");
	var result = "";
	for (var i = 0; i < tstr.length; i++) {
		var temp = tstr[i].split(",");
		result += temp[0] + ",";
	}
	return result.substring(0, result.length - 1);
}

/**
 * 得到指定名称的有多个值的多个复选框的值
 * 
 * @param chname
 * @returns {String}
 */
function getCheckValues(chname) {
	var str = "";
	var sids = "";
	var snames = "";
	jQuery("[name='checkbox'][checked]").each(function() {
		var strval = jQuery(this).val();
		var temp = strval.split(",");
		var sid = temp[0];
		var sname = temp[1];
		sids += sid + ",";
		snames += sname + ", ";
	});
	str = sids.substring(0, sids.length - 1) + "|" + snames.substring(0, snames.length - 2);
	return str;
}

/**
 * 判断复选框的状态
 * 
 * @param chname
 * @returns {String}
 */
function decideCheckState(chname) {
	var str = getCheckVals(chname);
	var tstr = str.split("@");
	var temp = "";
	for (var i = 0; i < tstr.length; i++) {
		var tval = tstr[i];
		var tem = tval.substring(tval.length - 1, tval.length);
		temp += tem;
	}
	return temp;
}

/**
 * 复选框的值是否全为0
 * 
 * @param chname
 * @returns
 */
function checkStringz(chname) {
	var regex = /^[0]*$/g;
	var source = decideCheckState(chname);
	return regex.test(source);
}

/**
 * 复选框的值是否全为1
 * 
 * @param chname
 * @returns
 */
function checkStringO(chname) {
	var regex = /^[1]*$/g;
	var source = decideCheckState(chname);
	return regex.test(source);
}

/**
 * @param source
 * @returns
 */
function decideStrallz(source) {
	var regex = /^[0]*$/g;
	return regex.test(source);
}

/**
 * @param source
 * @returns
 */
function decideStrallO(source) {
	var regex = /^[1]*$/g;
	return regex.test(source);
}

/**
 * 复选框全选
 * 
 * @param chname
 */
function checkboxAll(chname) {
	jQuery("[name='" + chname + "']").each(function() {
		jQuery(this).attr("checked", true);
	});
}

/**
 * 复选框反选
 * 
 * @param chname
 */
function inverSelect(chname) {
	jQuery("[name='" + chname + "']").each(function() {
		if (jQuery(this).attr("checked")) {
			jQuery(this).attr("checked", false);
		} else {
			jQuery(this).attr("checked", true);
		}
	});
}

/**
 * 复选框全选或反选
 * 
 * @param all_id
 *            触发全选/反选事件的元素id
 * @param chname
 */
function selects(all_id, chname) {
	$("#" + all_id).click(function() {
		if ($(this).attr("checked")) {
			checkboxAll(chname);
		} else {
			clearSelect(chname);
		}
	});
}

function mouseHover(obj) {
	alert($(obj).attr("style"));
}

/**
 * 取消全选或反选
 * 
 * @param chname
 */
function clearSelect(chname) {
	jQuery("[name='" + chname + "']").each(function() {
		jQuery(this).attr("checked", false);
	});
}

/**
 * 点击删除按钮时使用
 * 
 * @param chname
 * @returns {Boolean}
 */
function dele(chname) {
	var b = chkCheckCha(chname);
	if (!b) {
		alert("请选择要删除的记录");
		return false;
	} else if (confirm("删除后无法恢复，确定删除吗?")) {
		jQuery("[name='form1']").submit();
	} else {
		return false;
	}
}

/**
 * 校验时间格式
 * 
 * @param timevale
 * @returns
 */
function checkTime(timevale) {
	var regex = /^(([0-1][0-9])|([2][0-4]))(\:)[0-5][0-9](\:)[0-5][0-9]$/g;
	var b = regex.test(timevale);
	return b;
}

/**
 * 校验Ip地址格式
 * 
 * @param ipvale
 * @returns
 */
function checkIp(ipvale) {
	var regex = /^([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|22[0-3])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}$/;
	var b = regex.test(ipvale);
	return b;
}

/**
 * 是否是由字母或数字组成的字符串
 * 
 * @param letVale
 * @returns
 */
function checkLetOrNum(letVale) {
	var regex = /^([a-zA-Z_]{1})([\w]*)$/g;
	var b = regex.test(letVale);
	return b;
}

/**
 * 取字符串的第index的字符
 * 
 * @param source
 * @param index
 * @returns
 */
function interceptStr(source, index) {
	var temp = source.charAt(index);
	return parseInt(temp);
}

/**
 * 检查字符串中beindex位置到endindex位置之间是否全由targer组成
 * 
 * @param source
 * @param targer
 * @param beindex
 * @param endindex
 * @returns {Boolean}
 */
function checkStr(source, targer, beindex, endindex) {
	var flag = false;
	for (var i = beindex; i <= endindex; i++) {
		var temp = source.charAt(i);
		if (targer == temp) {
			flag = true;
		}
	}
	return flag;
}

/**
 * 验证字符串中的某一段是否全为0
 * 
 * @param source
 * @param begin
 * @param end
 * @returns
 */
function checkString(source, begin, end) {
	var regex = /^[0]*$/g;
	var temp = source.substring(begin, end + 1);
	// alert("###temp=="+temp);
	return regex.test(temp);
}

/**
 * 判断两个字符串是否想等 相等返回true否则返回false
 * 
 * @param source
 * @param target
 * @returns
 */
function decideString(source, target) {
	return (source == target) ? true : false;
}

/**
 * 将字符串转换成数字
 * 
 * @param val
 * @return
 */
function stringToNumber(val) {
	return Number(val);
}

/**
 * 验证是否是整数或小数
 * 
 * @param source
 * @return
 */
function checkIntAndFloat(source) {
	var regex = /^[0-9]+(\.[0-9]+)?$/g;
	return regex.test(source);
}

/**
 * 验证是否是整数或只有一位小数点的小数
 * 
 * @param source
 * @returns
 */
function checkFloat(source) {
	// var regex=/^[1-9]d*.d{1}|0.d{1}[1-9]d{1}$/g;
	var regex = /^[0-9]+\d*[\.\d]?\d{0,1}$/g;
	return regex.test(source);
}

/**
 * 验证是否两位数以内的正整数
 * 
 * @param source
 * @returns
 */
function checkTwoInt(source) {
	var regex = /^[1-9][0-9]?$/g;
	return regex.test(source);
}

/**
 * 验证数字
 * 
 * @param source
 * @returns
 */
function checkNumber(source) {
	var regex = /^(\-|\+)?\d+(\.\d+)?$/;
	return regex.test(source);
}

/**
 * 验证是否是两位小数的正实数
 * 
 * @param source
 * @returns
 */
function checkTowLenFloat(source) {
	var regex = /^[0-9]+(.[0-9]{2})?$/g;
	return regex.test(source);
}

/**
 * 验证是否是两位或一位小数的正实数
 * 
 * @param source
 * @returns
 */
function checkTowLenFloatt(source) {
	var regex = /^[0-9]+(.[0-9]{1,2})?$/g;
	return regex.test(source);
}

/**
 * 验证是否是整数或只有2位小数的数
 * 
 * @param source
 * @returns
 */
function checkTowFloat(source) {
	var regex = /^[1-9]+\d*[\.\d]?\d{0,2}$/g;
	return regex.test(source);
}

/**
 * 验证是否有空格
 * 
 * @param source
 * @returns
 */
function checkSpace(source) {
	var regex = /\s/g;
	return regex.test(source);
}

/**
 * 检查一个数是否是整数则位数在8以内
 * 
 * @param source
 * @returns
 */
function checkIntLeng(source) {
	var regex = /^[1-9]{1}[0-9]{1,7}$/g
	return regex.test(source);
}

/**
 * 检查一个数是否是整数则位数在2以内
 * 
 * @param source
 * @returns
 */
function checkIntTwoLeng(source) {
	var regex = /^[1-9]{1}[0-9]{1,2}$/g
	return regex.test(source);
}

/**
 * 验证正整数
 * 
 * @param source
 * @returns
 */
function checkInt(source) {
	// var regex=/^[1-9]d*$/g
	var regex = /^[0-9]*[1-9][0-9]*$/g
	return regex.test(source);
}

/**
 * 验证非负数
 * 
 * @param source
 * @returns
 */
function checkNegative(source) {
	var regex = /^[1-9]\d*|0$/g
	return regex.test(source);
}

/**
 * 分割IP地址
 * 
 * @param ipAddress
 * @returns
 */
function getIpNum(ipAddress) {
	var ip = ipAddress.split(".");
	var a = parseInt(ip[0]);
	var b = parseInt(ip[1]);
	var c = parseInt(ip[2]);
	var d = parseInt(ip[3]);
	var ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
	return ipNum;
}

/**
 * 判断IP大小
 * 
 * @param startIp
 * @param endIp
 * @returns
 */
function decideIp(startIp, endIp) {
	var ip1 = getIpNum(startIp);
	var ip2 = getIpNum(endIp);
	return (ip2 > ip1) ? true : false;
}

/**
 * 验证是否全是空格
 * 
 * @param source
 * @returns
 */
function checkAllSpace(source) {
	var regex = /^\s+$/g
	return regex.test(source);
}

/*******************************************************************************
 * openWindow(url)函数：弹出窗口 * url：路径 * left:左边的距离 * top：上边的距离 * width：窗口宽度 *
 * height：窗口高度 * resize：yes时可调整窗口大小，no则不可调 *
 ******************************************************************************/
/**
 * @param url
 *            路径
 * @param width
 *            窗口宽度
 * @param height
 *            窗口高度
 * @param resize
 *            是否可调整窗口大小
 */
function openWindow(url, width, height, resize) {
	var mleft = (screen.width - width) / 2;
	var mtop = (screen.height - height) / 2;
	window.open(url, "", "height=" + height + ",width=" + width + ",location=no,menubar=no,resizable=" + resize
			+ ",scrollbars=yes,status=no,toolbar=no,left=" + mleft + ",top=" + mtop + "");
}

function openWindow(url, width, height, resize, scrollbars) {
	var mleft = (screen.width - width) / 2;
	var mtop = (screen.height - height) / 2;
	window.open(url, "", "height=" + height + ",width=" + width + ",location=no,menubar=no,resizable=" + resize + ",scrollbars="
			+ scrollbars + ",status=no,toolbar=no,left=" + mleft + ",top=" + mtop + "");
}

/**
 * @param url
 * @param width
 * @param height
 * @returns
 */
function showNewWind(url, width, height) {
	// alert(url);
	var showresult = window.showModalDialog(url, window, "dialogWidth=" + width + "px;dialogHeight=" + height
			+ "px;location=no;status=no;scroll=yes");
	return showresult;
}

/**
 * @param url
 * @param width
 * @param height
 * @returns
 */
function showNewLessWind(url, width, height) {
	// alert(url);
	var showresult = window.showModelessDialog(url, window, "dialogWidth:" + width + "px;location=no;status:no;dialogHeight:" + height
			+ "px");
	return showresult;
}

/**
 * @param source
 * @returns
 */
function decideLeve(source) {
	var regex = /^[a-zA-Z]{1}$/g;
	return regex.test(source);
}

/**
 * @param url
 */
function openBlankWindow(url) {
	openWindow(url, "650", "400", "yes");
}

/**
 * 时间变化
 * 
 * @param source
 * @param addval
 * @returns
 */
function dateToString(source, addval) {
	var paddval = parseInt(addval);// 增量(秒)
	var temp = source.split(":");
	var thrs = parseInt(temp[0]) * 3600;// 小时化成秒
	var tmis = parseInt(temp[1]) * 60;// 分钟化成秒;
	var tss = parseInt(temp[2]);// 秒
	var totals = parseInt(thrs) + parseInt(tmis) + parseInt(tss) + parseInt(paddval);
	var result = timeTohhmmss(totals);
	return result;
}

/**
 * 由秒数转化成hh:mm:ss格式
 * 
 * @param seconds
 * @returns {String}
 */
function timeTohhmmss(seconds) {
	var hh;
	var mm;
	var ss;
	if (seconds == null || seconds < 0) {
		return;
	}
	var pseconds = parseInt(seconds);
	// 得到小时
	/**
	 * @param num
	 * @returns
	 */
	/**
	 * @param num
	 * @returns
	 */
	/**
	 * @param num
	 * @returns
	 */
	hh = pseconds / 3600 | 0;
	pseconds = parseInt(pseconds) - parseInt(hh) * 3600;
	if (parseInt(hh) < 10) {
		hh = "0" + hh;
	}
	if (parseInt(hh) >= 24) {
		hh = "00";
	}
	// 得到分钟
	mm = parseInt(pseconds) / 60 | 0;
	// 得到秒
	ss = parseInt(pseconds) - parseInt(mm) * 60;
	if (parseInt(mm) < 10) {
		mm = "0" + mm;
	}
	if (parseInt(ss) < 10) {
		ss = "0" + ss;
	}
	return hh + ":" + mm + ":" + ss;
}

/**
 * 验证身份证号是否正确
 */
function isCardNo(num) {
	if (isNaN(num)) {
		alert("输入的身份证号不是数字！");
		return false;
	}
	var len = num.length;
	if (len < 15 || len > 18) {
		alert("输入的身份证号码长度不正确定！应为15位或18位");
		return false;
	}
	var re15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	var re18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	var res = (re15.test(num) || re18.test(num));
	if (res == false) {
		alert("输入的身份证号格式不正确！");
		return false;
	}
	return res;
}

/**
 * 判断字符串是否为空，若为空则返回true否则返回false
 * 
 * @param source
 * @returns {true或者false}
 */
function isEmpty(source) {
	var str = source.replace(/(^\s*)|(\s*$)/g, "");
	if (str == "" || str.toLowerCase() == "null" || str.length <= 0) {
		return true;
	} else {
		return false;
	}
}

/**
 * 验证是否为电话号码（座机）
 * 
 * @param source
 * @returns
 */
function isTelephone(source) {
	var regex = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/
	return regex.test(source); // search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)
	// != -1
}

/**
 * 验证是否为手机号码（移动手机）
 * 
 * @param source
 * @returns
 */
function isMobilePhone(source) {
	var regex = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}/;
	return regex.test(source);
}

/**
 * 验证是否为电子邮箱
 * 
 * @param source
 * @returns {Boolean}
 */
function isEmail(source) {
	var regex = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if (source.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	} else {
		alert("电子邮箱格式不正确");
		return false;
	}
}

/**
 * 
 * 验证是否为邮编
 * 
 * @param source
 * @returns
 */
function isZip(source) {
	var regex = /^[1-9]\d{5}$/;
	return regex.test(source);
}

/**
 * 
 * 验证字符串是否是中文
 * 
 * @param source
 * @returns
 */
function isChines(source) {
	var regex = /^[\u4E00-\u9FA5]+$/;
	return regex.test(source);
}

/**
 * 生成指定位数的随机整数
 * 
 * @param count
 * @returns {String}
 */
function getRandomNum(count) {
	var arr = new Array;
	var reNum = "";
	for (var i = 0; i < count; i++) {
		arr[i] = parseInt(Math.random() * 10);
		reNum += String(arr[i]);
	}
	return reNum;
}

/**
 * @param min
 * @param max
 * @returns
 */
function random(min, max) {
	return Math.floor(min + Math.random() * (max - min));
}

/**
 * 判断字符串包含关系
 * 
 * @param string
 *            原始字符串
 * @param substr
 *            子字符串
 * @param isIgnoreCase
 *            是否忽略大小写
 * @returns {Boolean}
 */
function jsContains(string, substr, isIgnoreCase) {
	if (isIgnoreCase) {
		string = string.toLowerCase();
		substr = substr.toLowerCase();
	}
	var startChar = substr.substring(0, 1);
	var strLen = substr.length;
	for (var j = 0; j < string.length - strLen + 1; j++) {
		if (string.charAt(j) == startChar)// 如果匹配起始字符,开始查找
		{
			if (string.substring(j, j + strLen) == substr)// 如果从j开始的字符与str匹配，那ok
			{
				return true;
			}
		}
	}
	return false;
}

/**
 * 随机数UUID
 * 
 * @returns
 */
function makeUUID() {
	var S4 = function() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	};
	// return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() +
	// S4() + S4());
	return (S4() + S4() + S4() + S4() + S4() + S4() + S4() + S4());
}

/**
 * 得到项目的基地址
 * 
 * @returns
 */
function getContextPath() {
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return path;
}

/**
 * 显示提示信息
 * 
 * @param msg
 */
function showInfoMessage(msg) {
	Ext.MessageBox.show({
		width : 320,
		buttons : Ext.Msg.OK,
		msg : msg,
		icon : Ext.MessageBox.INFO,
		title : "系统提示"
	});
}

/**
 * 给URL追加参数
 * 
 * @param url
 * @param parameter
 *            参数名
 * @param value
 *            参数值
 * @returns
 */
function urlAddParmert(url, parameter, value) {
	var buf = new StringBuffer();
	if (!isEmpty(url)) {
		buf.append(url);
		if (url.indexOf("?") > -1) { // 已经有参数
			buf.append("&");
		} else {
			buf.append("?");
		}
		buf.append(parameter);
		buf.append("=");
		buf.append(value);
	}
	return buf.toString();
}

/**
 * 得到文件的扩展名
 * 
 * @param filename
 * @returns
 */
function getFileExt(filename) {
	var d = /\.[^\.]+$/.exec(filename);
	var ext = new String(d);
	var s = ext.toLowerCase();
	return s;
}

/**
 * 字符串编码
 * 
 * @param source
 * @returns
 */
function strEncode(source) {
	return encodeURIComponent(source);
}

/**
 * 字符串解码
 * 
 * @param source
 * @returns
 */
function strDencode(source) {
	return decodeURIComponent(source);
}

/**
 * 字符串转整型
 * 
 * @param source
 * @returns
 */
function strParseInt(source) {
	if (isEmpty(source) || isNaN(source)) {
		return 0;
	}
	return parseInt(source);
}

/**
 * 字符串转Float
 * 
 * @param source
 * @returns
 */
function strParseFloat(source) {
	if (isEmpty(source) || isNaN(source)) {
		return 0;
	}
	return parseFloat(source);
}

/**
 * 获取今天日期，星期几
 * 
 * @returns
 */
function getTodayDate() {
	var now = new Date();
	var yy = now.getFullYear();
	var mm = now.getMonth() + 1;
	var dd = now.getDate();
	var day = new Array();
	day[0] = "星期日";
	day[1] = "星期一";
	day[2] = "星期二";
	day[3] = "星期三";
	day[4] = "星期四";
	day[5] = "星期五";
	day[6] = "星期六";
	return (yy + '年' + mm + '月' + dd + '日 ' + day[now.getDay()]);
}

/**
 * 获取一段时间中含有的周末数量
 * 
 * @param beginDate
 * @param endDate
 * @returns {number}
 */
function getIntervalWeekends(beginDate, endDate) {
	var weekends = 0;
	var dateDiffDays = dateDiff("d", beginDate, endDate) + 1;
	if (dateDiffDays > 0) {
		for (var i = 0; i < dateDiffDays; i++) {
			var newDate = dateAdd("d", i, beginDate);
			if (newDate.getDay() == 0 || newDate.getDay() == 6) {
				weekends++;
			}
		}
	}
	return weekends;
}

/**
 * 时间戳转成时间
 * 
 * @param time
 * @returns
 */
function timeStamp2String(time) {
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

/**
 * 判断是否为空
 * 
 * @param val
 * @returns
 */
function isNull(val) {
	if (val == undefined || val == null || val == "" || val == '' || val == "undefined" || val == "null" || val == "NULL") {
		return true;
	}
	return false;
}

/**
 * 判断闰年
 * 
 * @param date
 *            Date日期对象
 * @return boolean true 或false
 */
this.isLeapYear = function(date) {
	return (0 == date.getYear() % 4 && ((date.getYear() % 100 != 0) || (date.getYear() % 400 == 0)));
}

/**
 * 日期对象转换为指定格式的字符串
 * 
 * @param f
 *            日期格式,格式定义如下 yyyy-MM-dd HH:mm:ss
 * @param date
 *            Date日期对象, 如果缺省，则为当前时间
 * 
 * YYYY/yyyy/YY/yy 表示年份 MM/M 月份 W/w 星期 dd/DD/d/D 日期 hh/HH/h/H 时间 mm/m 分钟
 * ss/SS/s/S 秒
 * @return string 指定格式的时间字符串
 */
this.dateToStr = function(formatStr, date) {
	formatStr = arguments[0] || "yyyy-MM-dd HH:mm:ss";
	date = arguments[1] || new Date();
	var str = formatStr;
	var Week = [ '日', '一', '二', '三', '四', '五', '六' ];
	str = str.replace(/yyyy|YYYY/, date.getFullYear());
	str = str.replace(/yy|YY/, (date.getYear() % 100) > 9 ? (date.getYear() % 100).toString() : '0' + (date.getYear() % 100));
	str = str.replace(/MM/, date.getMonth() > 9 ? (date.getMonth() + 1) : '0' + (date.getMonth() + 1));
	str = str.replace(/M/g, date.getMonth());
	str = str.replace(/w|W/g, Week[date.getDay()]);

	str = str.replace(/dd|DD/, date.getDate() > 9 ? date.getDate().toString() : '0' + date.getDate());
	str = str.replace(/d|D/g, date.getDate());

	str = str.replace(/hh|HH/, date.getHours() > 9 ? date.getHours().toString() : '0' + date.getHours());
	str = str.replace(/h|H/g, date.getHours());
	str = str.replace(/mm/, date.getMinutes() > 9 ? date.getMinutes().toString() : '0' + date.getMinutes());
	str = str.replace(/m/g, date.getMinutes());

	str = str.replace(/ss|SS/, date.getSeconds() > 9 ? date.getSeconds().toString() : '0' + date.getSeconds());
	str = str.replace(/s|S/g, date.getSeconds());

	return str;
}

/**
 * 日期计算
 * 
 * @param strInterval
 *            string 可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param num
 *            int
 * @param date
 *            Date 日期对象
 * @return Date 返回日期对象
 */
this.dateAdd = function(strInterval, num, date) {
	date = arguments[2] || new Date();
	switch (strInterval) {
	case 's':
		return new Date(date.getTime() + (1000 * num));
	case 'n':
		return new Date(date.getTime() + (60000 * num));
	case 'h':
		return new Date(date.getTime() + (3600000 * num));
	case 'd':
		return new Date(date.getTime() + (86400000 * num));
	case 'w':
		return new Date(date.getTime() + ((86400000 * 7) * num));
	case 'm':
		return new Date(date.getFullYear(), (date.getMonth()) + num, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
	case 'y':
		return new Date((date.getFullYear() + num), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
	}
}

/**
 * 比较日期差 dtEnd 格式为日期型或者有效日期格式字符串
 * 
 * @param strInterval
 *            string 可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param dtStart
 *            Date 可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param dtEnd
 *            Date 可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 */
this.dateDiff = function(strInterval, dtStart, dtEnd) { // 如 'd',new
	// Date("2016-8-22"),new
	// Date("2016-8-25")
	switch (strInterval) {
	case 's':
		return parseInt((dtEnd - dtStart) / 1000);
	case 'n':
		return parseInt((dtEnd - dtStart) / 60000);
	case 'h':
		return parseInt((dtEnd - dtStart) / 3600000);
	case 'd':
		return parseInt((dtEnd - dtStart) / 86400000);
	case 'w':
		return parseInt((dtEnd - dtStart) / (86400000 * 7));
	case 'm':
		return (dtEnd.getMonth() + 1) + ((dtEnd.getFullYear() - dtStart.getFullYear()) * 12) - (dtStart.getMonth() + 1);
	case 'y':
		return dtEnd.getFullYear() - dtStart.getFullYear();
	}
}

/**
 * 字符串转换为日期对象
 * 
 * @param date
 *            Date 格式为yyyy-MM-dd HH:mm:ss，必须按年月日时分秒的顺序，中间分隔符不限制
 */
this.strToDate = function(dateStr) {
	var data = dateStr;
	var reCat = /(\d{1,4})/gm;
	var t = data.match(reCat);
	t[1] = t[1] - 1;
	eval('var d = new Date(' + t.join(',') + ');');
	return d;
}

/**
 * 把指定格式的字符串转换为日期对象yyyy-MM-dd HH:mm:ss
 * 
 */
this.strFormatToDate = function(formatStr, dateStr) {
	var year = 0;
	var start = -1;
	var len = dateStr.length;
	if ((start = formatStr.indexOf('yyyy')) > -1 && start < len) {
		year = dateStr.substr(start, 4);
	}
	var month = 0;
	if ((start = formatStr.indexOf('MM')) > -1 && start < len) {
		month = parseInt(dateStr.substr(start, 2)) - 1;
	}
	var day = 0;
	if ((start = formatStr.indexOf('dd')) > -1 && start < len) {
		day = parseInt(dateStr.substr(start, 2));
	}
	var hour = 0;
	if (((start = formatStr.indexOf('HH')) > -1 || (start = formatStr.indexOf('hh')) > 1) && start < len) {
		hour = parseInt(dateStr.substr(start, 2));
	}
	var minute = 0;
	if ((start = formatStr.indexOf('mm')) > -1 && start < len) {
		minute = dateStr.substr(start, 2);
	}
	var second = 0;
	if ((start = formatStr.indexOf('ss')) > -1 && start < len) {
		second = dateStr.substr(start, 2);
	}
	return new Date(year, month, day, hour, minute, second);
}

/**
 * 日期对象转换为毫秒数
 */
this.dateToLong = function(date) {
	return date.getTime();
}

/**
 * 毫秒转换为日期对象
 * 
 * @param dateVal
 *            number 日期的毫秒数
 */
this.longToDate = function(dateVal) {
	return new Date(dateVal);
}

/**
 * 判断字符串是否为日期格式
 * 
 * @param str
 *            string 字符串
 * @param formatStr
 *            string 日期格式， 如下 yyyy-MM-dd
 */
this.isDate = function(str, formatStr) {
	if (formatStr == null) {
		formatStr = "yyyyMMdd";
	}
	var yIndex = formatStr.indexOf("yyyy");
	if (yIndex == -1) {
		return false;
	}
	var year = str.substring(yIndex, yIndex + 4);
	var mIndex = formatStr.indexOf("MM");
	if (mIndex == -1) {
		return false;
	}
	var month = str.substring(mIndex, mIndex + 2);
	var dIndex = formatStr.indexOf("dd");
	if (dIndex == -1) {
		return false;
	}
	var day = str.substring(dIndex, dIndex + 2);
	if (!isNumber(year) || year > "2100" || year < "1900") {
		return false;
	}
	if (!isNumber(month) || month > "12" || month < "01") {
		return false;
	}
	if (day > getMaxDay(year, month) || day < "01") {
		return false;
	}
	return true;
}

this.getMaxDay = function(year, month) {
	if (month == 4 || month == 6 || month == 9 || month == 11)
		return "30";
	if (month == 2)
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return "29";
		else
			return "28";
	return "31";
}

/**
 * 变量是否为数字
 */
this.isNumber = function(str) {
	var regExp = /^\d+$/g;
	return regExp.test(str);
}

/**
 * 把日期分割成数组 [年、月、日、时、分、秒]
 */
this.toArray = function(myDate) {
	myDate = arguments[0] || new Date();
	var myArray = Array();
	myArray[0] = myDate.getFullYear();
	myArray[1] = myDate.getMonth();
	myArray[2] = myDate.getDate();
	myArray[3] = myDate.getHours();
	myArray[4] = myDate.getMinutes();
	myArray[5] = myDate.getSeconds();
	return myArray;
}

/**
 * 取得日期数据信息 参数 interval 表示数据类型 y 年 M月 d日 w星期 ww周 h时 n分 s秒
 */
this.datePart = function(interval, myDate) {
	myDate = arguments[1] || new Date();
	var partStr = '';
	var Week = [ '日', '一', '二', '三', '四', '五', '六' ];
	switch (interval) {
	case 'y':
		partStr = myDate.getFullYear();
		break;
	case 'M':
		partStr = myDate.getMonth() + 1;
		break;
	case 'd':
		partStr = myDate.getDate();
		break;
	case 'w':
		partStr = Week[myDate.getDay()];
		break;
	case 'ww':
		partStr = myDate.WeekNumOfYear();
		break;
	case 'h':
		partStr = myDate.getHours();
		break;
	case 'm':
		partStr = myDate.getMinutes();
		break;
	case 's':
		partStr = myDate.getSeconds();
		break;
	}
	return partStr;
}

/**
 * 取得当前日期所在月的最大天数
 */
this.maxDayOfDate = function(date) {
	date = arguments[0] || new Date();
	date.setDate(1);
	date.setMonth(date.getMonth() + 1);
	var time = date.getTime() - 24 * 60 * 60 * 1000;
	var newDate = new Date(time);
	return newDate.getDate();
}

/**
 * 方法作用：【格式化时间】 使用方法 示例： 使用方式一： var now = new Date(); var nowStr =
 * now.dateFormat("yyyy-MM-dd hh:mm:ss"); 使用方式二： new
 * Date().dateFormat("yyyy年MM月dd日"); new Date().dateFormat("MM/dd/yyyy"); new
 * Date().dateFormat("yyyyMMdd"); new Date().dateFormat("yyyy-MM-dd hh:mm:ss");
 * 
 * @param format
 *            {date} 传入要格式化的日期类型
 * @returns {2015-01-31 16:30:00}
 */
Date.prototype.dateFormat = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

/*******************************************************************************
 * 日期时间工具类 * 注：调用方式，deteUtil.方法名 *
 ******************************************************************************/
var dateUtil = {
	/*
	 * 方法作用：【取传入日期是星期几】 使用方法：dateUtil.nowFewWeeks(new Date()); @param date{date}
	 * 传入日期类型 @returns {星期四，...}
	 */
	nowFewWeeks : function(date) {
		if (date instanceof Date) {
			var dayNames = new Array("星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
			return dayNames[date.getDay()];
		} else {
			return "Param error,date type!";
		}
	},
	/*
	 * 方法作用：【字符串转换成日期】 使用方法：dateUtil.strTurnDate("2010-01-01"); @param str
	 * {String}字符串格式的日期，传入格式：yyyy-mm-dd(2015-01-31) @return {Date}由字符串转换成的日期
	 */
	strTurnDate : function(str) {
		var re = /^(\d{4})\S(\d{1,2})\S(\d{1,2})$/;
		var dt;
		if (re.test(str)) {
			dt = new Date(RegExp.$1, RegExp.$2 - 1, RegExp.$3);
		}
		return dt;
	},
	/*
	 * 方法作用：【计算2个日期之间的天数】 传入格式：yyyy-mm-dd(2015-01-31)
	 * 使用方法：dateUtil.dayMinus(startDate,endDate); @startDate {Date}起始日期 @endDate
	 * {Date}结束日期 @return endDate - startDate的天数差
	 */
	dayMinus : function(startDate, endDate) {
		if (startDate instanceof Date && endDate instanceof Date) {
			var days = Math.floor((endDate - startDate) / (1000 * 60 * 60 * 24));
			return days;
		} else {
			return "Param error,date type!";
		}
	}
};

/*******************************************************************************
 * 加载工具类 * 注：调用方式，loadUtil.方法名 *
 ******************************************************************************/
var loadUtil = {
	/*
	 * 方法说明：【动态加载js文件css文件】
	 * 使用方法：loadUtil.loadjscssfile("http://libs.baidu.com/jquery/1.9.1/jquery.js","js")
	 * @param fileurl 文件路径， @param filetype 文件类型，支持传入类型，js、css
	 */
	loadjscssfile : function(fileurl, filetype) {
		if (filetype == "js") {
			var fileref = document.createElement('script');
			fileref.setAttribute("type", "text/javascript");
			fileref.setAttribute("src", fileurl);
		} else if (filetype == "css") {

			var fileref = document.createElement('link');
			fileref.setAttribute("rel", "stylesheet");
			fileref.setAttribute("type", "text/css");
			fileref.setAttribute("href", fileurl);
		}
		if (typeof fileref != "undefined") {
			document.getElementsByTagName("head")[0].appendChild(fileref);
		} else {
			alert("loadjscssfile method error!");
		}
	}
};

/*******************************************************************************
 * 字符串操作工具类 * 注：调用方式，strUtil.方法名 *
 ******************************************************************************/
var strUtil = {
	/*
	 * 判断字符串是否为空 @param str 传入的字符串 @returns {}
	 */
	isEmpty : function(str) {
		if (str != null && str.length > 0) {
			return true;
		} else {
			return false;
		}
	},
	/*
	 * 判断两个字符串子否相同 @param str1 @param str2 @returns {Boolean}
	 */
	isEquals : function(str1, str2) {
		if (str1 == str2) {
			return true;
		} else {
			return false;
		}
	},
	/*
	 * 忽略大小写判断字符串是否相同 @param str1 @param str2 @returns {Boolean}
	 */
	isEqualsIgnorecase : function(str1, str2) {
		if (str1.toUpperCase() == str2.toUpperCase()) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 判断是否是数字
	 * 
	 * @param value
	 * @returns {Boolean}
	 */
	isNum : function(value) {
		if (value != null && value.length > 0 && isNaN(value) == false) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 判断是否是中文
	 * 
	 * @param str
	 * @returns {Boolean}
	 */
	isChine : function(str) {
		var reg = /^([u4E00-u9FA5]|[uFE30-uFFA0])*$/;
		if (reg.test(str)) {
			return false;
		}
		return true;
	}
};

/*
 * 说明：去除字符串两边空格函数 参数obj：要去除空格的文本框 返回值：去除空格之后的字符串
 */
function trim(obj) {
	return String(obj.value).replace(/(^\s*)|(\s*$)/g, "");
}

/*
 * 说明：显示错误信息函数 参数obj：出现错误信息的文本框 参数errmsg：错误信息
 */
function showError(obj, errmsg) {
	alert(errmsg);
	try {
		obj.focus();
	} catch (e) {
	}
}

/*
 * 说明：检查是否为空函数 参数obj：要检查的文本框 返回值：判断结果 true不为空 false为空
 */
function checkEmpty(obj) {
	if (obj == "") {
		return false;
	} else {
		return true;
	}
}

/*
 * 说明：检查长度函数 参数obj：要检查长度的文本框 参数min：最小长度 参数max：最大长度 返回值：判断结果 true在要求长度中
 * false超出要求长度
 */
function checkLength(obj, min, max) {
	if (obj.length < min || obj.length > max) {
		return false;
	} else {
		return true;
	}
}

/*
 * 说明：下拉列表选中函数 参数obj：要选中的下拉列表 参数selectvalue：标识选中的参数
 */
function selectitem(obj, selectvalue) {
	var options = obj.options;
	for (var i = 0; i < options.length; i++) {
		if (selectvalue == options[i].value) {
			options[i].selected = true;
		}
	}
}

/*
 * 说明：判断value变量值是否是数字 参数value:输入值 返回值：是数字返回true，否则false
 */
function isNumeric(value) {
	if (value != null && value.length > 0 && isNaN(value) == false) {
		return true;
	} else {
		return false;
	}
}

/*
 * 说明：判断value变量值是否是中文 参数value:输入值 返回值：是中文返回false，否则true
 */
function isChn(str) {
	var reg = /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/;
	if (reg.test(str)) {
		return false;
	}
	return true;
}

/*
 * 说明：对复选框的全选或不选 参数state:输入值 1 全选 2 全部选 返回值：是中文返回false，否则true
 */
function change(state) {
	try {
		var checks = document.getElementsByTagName("input");
		var i = 0;
		var length = checks.length;
		var flag = true;
		if (state == 1) {
			flag = true;
		}
		if (state == 0) {
			flag = false;
		}
		for (i; i < length; i++) {
			if (checks[i].type == "checkbox") {
				checks[i].checked = flag;
			}
		}
	} catch (e) {
		window.alert(e.message);
	}
}

/**
 * <code>DateUtil</code>类用于封装常用的日期处理操作
 */
var DateUtil = function(year, month, day, hour, minute, second) {
	/** curDateTime 当前客户端日期时间 */
	this.curDateTime = new Date();
	/**
	 * <code>getDateTime</code>方法返回Date类型对象
	 * 
	 */
	this.getDateTime = function() {
		var date = null;
		if ((year == null && month == null && day == null && hour == null && minute == null && second == null)) {
			date = this.curDateTime;
		} else if (year != null && month != null && day != null && hour == null && minute == null && second == null) {
			date = new Date(year, month - 1, day);
		} else if (year != null && month != null && day != null && hour != null && minute != null && second != null) {
			date = new Date(year, month - 1, day, hour, minute, second);
		}
		return date;
	};

	/**
	 * <code>getYear</code>方法取得年值
	 * 
	 */
	this.getYear = function() {
		var year = null;
		var dateTime = this.getDateTime();

		if (dateTime != null) {
			year = dateTime.getFullYear();
		} else {
			year = this.curDateTime.getFullYear();
		}
		return year;
	};

	/**
	 * <code>getMonth</code>方法取得月值
	 * 
	 */
	this.getMonth = function() {
		var month = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			month = dateTime.getMonth() + 1;
		} else {
			month = this.curDateTime.getMonth() + 1;
		}
		return month;
	};

	/**
	 * <code>getDay</code>方法取得日值
	 * 
	 */
	this.getDay = function() {
		var day = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			day = dateTime.getDate();
		} else {
			day = this.curDateTime.getDate();
		}
		return day;
	};

	/**
	 * <code>getHour</code>方法取得24进制小时
	 * 
	 */
	this.getHour = function() {
		var hour = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			hour = dateTime.getHours();
		} else {
			hour = this.curDateTime.getHours();
		}
		return hour;
	};

	/**
	 * <code>getMinute</code>方法取得分值
	 * 
	 */
	this.getMinute = function() {
		var minute = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			minute = dateTime.getMinutes();
		} else {
			minute = this.curDateTime.getMinutes();
		}
		return minute;
	};

	/**
	 * <code>getSecond</code>方法取得秒值
	 * 
	 */
	this.getSecond = function() {
		var second = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			second = dateTime.getSeconds();
		} else {
			second = this.curDateTime.getSeconds();
		}
		return second;
	};

	/**
	 * <code>getDateRange</code>方法用于得到一天之内的时刻范围
	 * 
	 * @return range ["凌晨"|"上午"|"中午"|"下午"|"晚上"]
	 */
	this.getDateRange = function() {
		var hour = window.parseInt(this.getHour());
		var range = "凌晨"
		if (hour >= 6 && hour < 11) {
			range = "早晨";
		} else if (hour >= 11 && hour < 14) {
			range = "中午";
		} else if (hour >= 14 && hour <= 18) {
			range = "下午";
		} else if (hour > 18 && hour < 24) {
			range = "晚上";
		}
		return range;
	};
	/**
	 * <code>get12PatternHour</code>方法用于得到12进制小时值
	 * 
	 */
	this.get12PatternHour = function() {
		return hour > 12 ? (hour + 12 - 24) : hour;
	};
	/**
	 * <code>isLeapYear</code>方法用于判断是否为闰年
	 * <p>
	 * 闰年算法说明： 能被4整除并且不能被100整除或者能被400整除的年份是闰年
	 */
	this.isLeapYear = function() {
		var flag = false;
		if ((this.getYear() % 4 == 0 && this.getYear() % 100 != 0) || (this.getYear() % 400 == 0)) {
			flag = true;
		}
		return flag;
	};

	/**
	 * <code>getMaxDaysByMonth</code>方法根据月份获取该月的最大天数
	 * 
	 */
	this.getMaxDaysByMonth = function() {
		var days = 31;
		var month = this.getMonth();
		switch (month) {
		case 2:
			if (this.isLeapYear()) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			break;
		}
		return days;
	}
}

/**
 * <code>isEmptyString</code>方法用于检查字符串是否为空字符串
 * <p>
 * 
 * @return boolean false → 不是空串 true → 是空串
 */

function isEmptyStr(str) {
	if (trim(str).length == 0 || str == null) {
		return true;
	} else {
		return false;
	}
}

/**
 * <code>isEqualString</code>方法用于检查两个字符串是否相等
 * <p>
 * 
 * @return boolean false → 不相等 true → 相等
 */
function isEqualStr(str1, str2) {
	if (str1 == str2) {
		return true;
	} else {
		return false;
	}
}

/**
 * <code>isValidateCols</code>方法用于检查字符串是否是有效位数
 * <p>
 * 
 * @return boolean false → 不是制定位数 true → 是指定位数
 */
function isValidateMinCols(str, cols) {
	if (str.length >= cols) {
		return true;
	} else {
		return false;
	}
}

function isValidateMaxCols(str, cols) {
	if (str.length <= cols) {
		return true;
	} else {
		return false;
	}
}

function isValidateRangeCols(str, min, max) {
	if (str.length >= min && str.length <= max) {
		return true;
	} else {
		return false;
	}
}

/**
 * <code>isValidateEmail<code>方法用于检查email格式是否正确 
 * <p> 
 * @return boolean false → 无效Email true → 有效Email
 */
function isValidateEmail(email) {
	var emailPattern = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))" +
	// "@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil" +
	"@([a-zA-Z0-9-]+[.])+(cn|net|NET|com|COM|gov|GOV|mil" + "|MIL|org|ORG|edu|EDU|int|INT)$"
	var re = new RegExp(emailPattern);
	if (re.test(email)) {
		return true;
	} else {
		return false;
	}
}
/**
 * <code>trim</code>方法用于去掉字符串两边的空格
 * <p>
 */
function trim(str) {
	str = trimLeft(trimRight(str));
	return str;
}

/**
 * <code>trimLeft</code>方法用于去除字符串左侧的空格
 * <p>
 * 
 * @param str
 *            预处理的字符串
 * @return 去掉左侧空格的字符串
 */
function trimLeft(str) {
	var pattern = /^\s/;
	while (pattern.test(str)) {
		str = str.substring(1);
	}
	return str;
}

/**
 * <code>trimRight</code>方法用于去除字符串右侧的空格
 * <p>
 * 
 * @param str
 *            预处理字符串
 * @return 去掉右侧空格的字符串
 */
function trimRight(str) {
	var pattern = /\s$/;
	while (pattern.test(str)) {
		str = str.substring(0, str.length - 1);
	}
	return str;
}

/** ******************** date工具类 ************** */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

/** ******************** 公共工具类 ************** */
var PublicUtil = {
	isNotEmpty : function(val) {
		return !this.isEmpty(val);
	},
	isEmpty : function(val) {
		if ((val == null || typeof (val) == "undefined") || (typeof (val) == "string" && val == "" && val != "undefined")) {
			return true;
		} else {
			return false;
		}
	},
	isDebug : function() {
		if (this.isNotEmpty(configDebug) && configDebug == "true") {
			return true;
		} else {
			return false;
		}
	},
	// 去除元素内所有内容 strIds："#id1,#id2,#id3"
	emptyHtml : function(strIds) {
		try {
			var ids = strIds.trim(",").split(",");
			$(ids).each(function() {
				var obj = $(this.toString());
				if (obj.length > 0) {
					$(obj).each(function() {
						$(this).html("");
					});
				} else {
					obj.html("");
				}
			});
		} catch (ex) {
			if (PublicUtil.isDebug()) {
				throw new Error("js方法：【PublicUtil.emptyHtml(strIds)】，error！");
			}
		}
	},
	// 去除元素的值 strIds："#id1,#id2,#id3"
	emptyValue : function(strIds) {
		try {
			var ids = strIds.trim(",").split(",");
			$(ids).each(function() {
				var obj = $(this.toString());
				if (obj.length > 0) {
					$(obj).each(function() {
						$(this).val("");
					});
				} else {
					obj.val("");
				}
			});
		} catch (ex) {
			if (PublicUtil.isDebug()) {
				throw new Error("js方法：【PublicUtil.emptyValue(strIds)】，error！");
			}
		}
	},
	// 去除Textarea内所有内容 strIds："#id1,#id2,#id3"
	emptyTextarea : function(strIds) {
		try {
			var ids = strIds.trim(",").split(",");
			$(ids).each(function() {
				var obj = $(this.toString());
				if (obj.length > 0) {
					$(obj).each(function() {
						$(this).empty();
						$(this).val("");
					});
				} else {
					obj.empty();
					obj.val("");
				}
			});
		} catch (ex) {
			if (PublicUtil.isDebug()) {
				throw new Error("js方法：【PublicUtil.emptyTextarea(strIds)】，error！");
			}
		}
	}
}

/** ******************** String工具类************** */
// trim去掉字符串两边的指定字符,默去空格
String.prototype.trim = function(tag) {
	if (!tag) {
		tag = '\\s';
	} else {
		if (tag == '\\') {
			tag = '\\\\';
		} else if (tag == ',' || tag == '|' || tag == ';') {
			tag = '\\' + tag;
		} else {
			tag = '\\s';
		}
	}
	eval('var reg=/(^' + tag + '+)|(' + tag + '+$)/g;');
	return this.replace(reg, '');
};
// 字符串截取后面加入...
String.prototype.interceptString = function(len) {
	if (this.length > len) {
		return this.substring(0, len) + "...";
	} else {
		return this;
	}
}
// 将一个字符串用给定的字符变成数组
String.prototype.toArray = function(tag) {
	if (this.indexOf(tag) != -1) {
		return this.split(tag);
	} else {
		if (this != '') {
			return [ this.toString() ];
		} else {
			return [];
		}
	}
}
// 只留下数字(0123456789)
String.prototype.toNumber = function() {
	return this.replace(/\D/g, "");
}
// 保留中文
String.prototype.toCN = function() {
	var regEx = /[^\u4e00-\u9fa5\uf900-\ufa2d]/g;
	return this.replace(regEx, '');
}
// 转成int
String.prototype.toInt = function() {
	var temp = this.replace(/\D/g, "");
	return isNaN(parseInt(temp)) ? this.toString() : parseInt(temp);
}
// 是否是以XX开头
String.prototype.startsWith = function(tag) {
	return this.substring(0, tag.length) == tag;
}
// 是否已XX结尾
String.prototype.endWith = function(tag) {
	return this.substring(this.length - tag.length) == tag;
}
// StringBuffer
var StringBuffer = function() {
	this._strs = new Array;
};
StringBuffer.prototype.append = function(str) {
	this._strs.push(str);
};
StringBuffer.prototype.toString = function() {
	return this._strs.join("");
};
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}

/** ******************** Array工具 ************** */
// 根据数据取得再数组中的索引
Array.prototype.getIndex = function(obj) {
	for (var i = 0; i < this.length; i++) {
		if (obj == this[i]) {
			return i;
		}
	}
	return -1;
}
// 移除数组中的某元素
Array.prototype.remove = function(obj) {
	for (var i = 0; i < this.length; i++) {
		if (obj == this[i]) {
			this.splice(i, 1);
			break;
		}
	}
	return this;
}
// 判断元素是否在数组中
Array.prototype.contains = function(obj) {
	for (var i = 0; i < this.length; i++) {
		if (obj == this[i]) {
			return true;
		}
	}
	return false;
}

/** ******************** 浏览器相关操作 ************** */
// 进入全屏模式, 判断各种浏览器，找到正确的方法
var launchFullScreen = function(element) {
	if (element.requestFullscreen) {
		element.requestFullscreen();
	} else if (element.mozRequestFullScreen) {
		element.mozRequestFullScreen();
	} else if (element.webkitRequestFullscreen) {
		element.webkitRequestFullscreen();
	} else if (element.msRequestFullscreen) {
		element.msRequestFullscreen();
	}
	return true;
}
// 退出全屏模式
var exitFullScreen = function() {
	if (document.exitFullscreen) {
		document.exitFullscreen();
	} else if (document.mozCancelFullScreen) {
		document.mozCancelFullScreen();
	} else if (document.webkitExitFullscreen) {
		document.webkitExitFullscreen();
	}
	return false;
}
// cookie操作
var CookieUtil = {
	path : "/",
	domain : 'demo.j2ee.com',
	add : function(name, val) {
		$.cookie(name, val, {
			expires : 7,
			path : this.path,
			domain : this.domain,
			secure : true
		});
	},
	remove : function(name) {
		$.cookie(name, null, {
			path : this.path,
			domain : this.domain
		});
	},
	get : function(name) {
		$.cookie(name, {
			path : this.path,
			domain : this.domain
		});
	}
}
// error
var error = {
	e_404 : function() {
		alertMessage("404", "未找到改页面！", "warning");
	},
	e_500 : function() {
		alertMessage("500", "服务器内部错误！", "error");
	},
	e_403 : function() {
		alertMessage("403", "权限不足！", "warning");
	}
}