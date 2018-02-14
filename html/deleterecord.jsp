<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 録音情報削除</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 録音情報削除</h1>

		<div class=hatena-body>
		<div class=main>
			以下を削除しました。
			<br>
			<table cellpadding="5">
				<tr bgcolor="#eeeeff">
					<td>録音ID</td>
					<td><s:property value="recordId" /></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>ディスクID</td>
					<td><s:property value="diskId" /></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>作曲家</td>
					<td><s:property value="name" /></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>タイトル</td>
					<td><s:property value="title" /></td>
				</tr>
			</table>
		</div>
		</div>

	</body>
</html>
