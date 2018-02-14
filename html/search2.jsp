<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>検索</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
	<h1>MDリスト - 検索</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

		<table>
			<tr>
				<th>ディスクID</th>
				<th>作曲家</th>
				<th>曲</th>
				<th>日付</th>
				<th>備考</th>
				<th>変更</th>
			</tr>

			<s:iterator value="diskAndRecords">
				<tr>
					<td><s:property value="diskId" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="title" /></td>
					<td><s:property value="date" /></td>
					<td><s:property value="memo" /></td>
					<td>
						<s:form action="updaterecord1" theme="simple">
							<input type="hidden" name="recordId" value="<s:property value="recordId" />" />
							<s:submit value="変更" />
						</s:form>
					</td>
				</tr>
			</s:iterator>
		</table>

		</div>
		</div>
		</div>

	</body>
</html>
