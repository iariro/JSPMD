<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv=Content-Style-Type content=text/css>
		<link rel="stylesheet" type="text/css" href="hatena.css">
		<title>MDリスト - 作曲家登録</title>
	</head>

	<body>
		<h1>MDリスト - 作曲家登録</h1>

		<div class=hatena-body>
		<div class=main>

		<s:form action="addcomposer2" theme="simple">
			<table cellpadding="5">
				<tr bgcolor="#eeeeff">
					<td>作曲家</td><td><input type="text" name="composer"></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>交響曲数</td><td><input type="text" name="maxNumber"></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>知名度</td><td><s:select name="namevalue" list="#{ '1':'有名','2':'中間','3':'無名' }"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><s:submit value="登録" /></td>
				</tr>
			</table>
		</s:form>

		</div>
		</div>

	</body>
</html>
