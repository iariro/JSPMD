<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 作曲家一覧</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 作曲家一覧</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<table>
				<tr><th>ID</th><th>作曲家</th><th>交響曲数</th></tr>

				<s:iterator value="composers">
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="maxNumber" /></td>
					</tr>
				</s:iterator>
			</table>

		</div>
		</div>
		</div>
	</body>
</html>
