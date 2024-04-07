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
				<s:iterator value="array1">
					<tr>
						<s:iterator>
							<td bgcolor="<s:property value="color" />" height="30" width="30" align="right">
								<s:if test="%{record}"><s:property value="number" /></s:if>
							</td>
						</s:iterator>
					</tr>
				</s:iterator>
			</table>

		</div>
		</div>
		</div>

	</body>
</html>
