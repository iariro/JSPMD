<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 録音登録</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 録音登録</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

		<div class=hatena-body>
		<div class=main>

			<s:form action="addrecord2" theme="simple">
				<table cellpadding="5">
					<tr bgcolor="#eeeeff"><td>ディスクID</td><td><input type="text" name="diskId" value="<s:property value="newid" />"></td></tr>

					<tr bgcolor="#eeeeff"><td>作曲家</td><td>
					<s:select name="composerId" list="idAndNames" listKey="id" listValue="name" />
					</td></tr>

					<tr bgcolor="#eeeeff"><td>曲ナンバー</td><td><input type="text" name="title"></td></tr>
					<tr bgcolor="#eeeeff"><td>備考</td><td><input type="text" name="memo"></td></tr>
					<tr bgcolor="#eeeeff">
						<td>交響曲</td>
						<td>
							<input type="radio" name="symphony" value="true" checked>yes
							<input type="radio" name="symphony" value="false">no
						</td>
					</tr>
					<tr bgcolor="#eeeeff"><td>録音日</td><td><input type="text" name="date" value="<s:property value="today" />"></td></tr>
					<tr><td colspan="2" align="center">
						<s:submit value="追加" />
					</td></tr>
				</table>
			</s:form>

		</div>
		</div>

		</div>
		</div>
		</div>

	</body>
</html>
