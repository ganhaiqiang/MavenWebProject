<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/table.css" type="text/css" rel="stylesheet" />
<link href="css/kkpager_blue.css" type="text/css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script language="javascript" type="text/javascript" src="js/kkpager.min.js"></script>
<title>表格</title>
<script type="text/javascript">
	$(function() {
		kkpager.generPageHtml({
			pno : 25,
			mode : 'click', //设置为click模式
			total : 100, //总页码  
			totalRecords : 500, //总数据条数 
			//点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
			//适用于不刷新页面，比如ajax
			click : function(n) {
				//这里可以做自已的处理
				//...
				//处理完后可以手动条用selectPage进行页码选中切换
				this.selectPage(n);
			},
			//getHref是在click模式下链接算法，一般不需要配置，默认代码如下
			getHref : function(n) {
				return '#';
			}

		}, true);
	});
</script>
</head>
<body>
<div class="mian_top_01">
		<form action="http://svn-sqlreview.sf-express.com/sqlreview/remark"
			method="post">
			<div class="mian_b">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="mian_b_bg">
					<caption
						style="font-weight: bold; font-size: 16pt; color: #27A9E3;">检查表数据的备份</caption>
					<tbody>
						<tr>
							<td width="18%" class="mian_b_bg_lm">文件名</td>
							<td width="23%" class="mian_b_bg_lm">语句内容</td>
							<td width="11%" class="mian_b_bg_lm">涉及操作</td>
							<td width="8%" class="mian_b_bg_lm">所在行数</td>
							<td width="4%" class="mian_b_bg_lm">备份</td>
							<td width="6%" class="mian_b_bg_lm">通过</td>
							<td width="30%" class="mian_b_bg_lm">不合规理由说明</td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'B0703%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">56</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[0].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'B0702%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">57</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/yes.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[1].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'C0802%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">58</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[2].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'C0801%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">59</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[3].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where role_id in('1', 'B', 'C')</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">113</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[4].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">update mem_functions set
									func_id_web = 'B10201' where func_id = 'B070201'</div></td>
							<td><div class="tablecontent">部分数据更新</div></td>
							<td><div class="tablecontent">5</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[5].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'B0703%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">56</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[0].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'B0702%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">57</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/yes.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[1].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'C0802%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">58</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[2].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where func_id like 'C0801%'</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">59</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[3].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">delete from
									mem_role_function where role_id in('1', 'B', 'C')</div></td>
							<td><div class="tablecontent">部分数据删除</div></td>
							<td><div class="tablecontent">113</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[4].remark" type="text">
							</span></td>
						</tr>
						<tr>
							<td><div class="tablecontent">14-DML-80001242.sql</div></td>
							<td><div class="tablecontent">update mem_functions set
									func_id_web = 'B10201' where func_id = 'B070201'</div></td>
							<td><div class="tablecontent">部分数据更新</div></td>
							<td><div class="tablecontent">5</div></td>
							<td style="color: #D94600;"><div class="tablecontent">否</div></td>
							<td><img src="img/no.png"></td>
							<td class="filename" style="padding: .1em .1em"><span>
									<input placeholder="若不需要修改，请备注理由" required=""
									class="remarkinput" name="remarks[5].remark" type="text">
							</span></td>
						</tr>
					</tbody>
				</table>
				<br />
				<div id="kkpager"></div>
			</div>
<!-- 			<div class="mian_b"> -->
<!-- 				<table width="100%" border="0" cellpadding="0" cellspacing="0" -->
<!-- 					class="mian_b_bg"> -->
<!-- 					<tbody> -->
<!-- 						<tr> -->
<!-- 							<th width="50%"><div style="text-align: left;"> -->
<!-- 									<span><input type="button" value="返回主页" -->
<!-- 										onclick="location.href=&#39;./&#39;" /></span> -->
<!-- 								</div></th> -->
<!-- 							<th width="50%"><div style="text-align: right;"> -->
<!-- 									<span><input type="submit" value="提 交"></span> -->
<!-- 								</div></th> -->
<!-- 						</tr> -->
<!-- 					</tbody> -->
<!-- 				</table> -->
<!-- 			</div> -->
		</form>
	</div>
</body>
</html>