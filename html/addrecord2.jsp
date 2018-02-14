<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>録音登録</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 録音登録</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			以下を登録しました。
			<table>

				<tr>
					<td>ディスクID</td>
					<td><s:property value="diskId" /></td>
				</tr>
				<tr>
					<td>作曲家ID</td>
					<td><s:property value="composerId" /></td>
				</tr>
				<tr>
					<td>曲</td>
					<td><s:property value="title" /></td>
				</tr>
				<tr>
					<td>備考</td>
					<td><s:property value="memo" /></td>
				</tr>
				<tr>
					<td>録音日</td>
					<td><s:property value="date" /></td>
				</tr>

			</table>

		</div>
		</div>
		</div>

	</body>
</html>
