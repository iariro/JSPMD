<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - <s:property value="composerName" />交響曲一覧</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - <s:property value="composerName" />交響曲一覧</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<s:property value="count" /> / <s:property value="maxNumber" />
			<table>
				<s:iterator value="array2">
					<tr bgcolor="<s:property value="color" />">
						<td><s:property value="number" /></td>
						<td><s:property value="recordMark" /></td>
					</tr>
				</s:iterator>
			</table>

		</div>
		</div>
		</div>

	</body>
</html>
