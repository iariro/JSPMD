<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 検索</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 検索</h1>

		<div class=hatena-body>
		<div class=main>

		<s:form action="search2" theme="simple">
			<table cellpadding="5">

			<tr bgcolor="#eeeeff">
				<td>作曲家</td>
				<td>
					<s:select name="composerId" list="idAndNames" listKey="id" listValue="name" />
				</td>
			</tr>

			<tr bgcolor="#eeeeff"><td>曲ナンバー</td><td><input type="text" name="title"></td></tr>
			<tr bgcolor="#eeeeff">
				<td>交響曲</td>
				<td>
					<input type="radio" name="symphony" value="true" checked>yes
					<input type="radio" name="symphony" value="false">no
				</td>
			</tr>
			<tr><td colspan="2" align="center">
				<s:submit value="検索" />
			</td></tr>
			</table>
		</s:form>

		</div>
		</div>

	</body>
</html>
