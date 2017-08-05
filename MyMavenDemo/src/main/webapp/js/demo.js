/**
 * json对象转换为请求字符串，如：key1=value1&key2=value2
 * 
 * @param jsonObj
 *            json对象
 * @param key
 *            前缀，可选
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