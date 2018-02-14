<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 録音情報変更</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 録音情報変更</h1>

		<div class=hatena-body>
		<div class=main>
		<s:form action="updaterecord2" theme="simple">
			<table cellpadding="5">
				<tr bgcolor="#eeeeff">
					<td>録音ID</td>
					<td><input type="text" name="recordId" value="<s:property value="recordId" />" readonly=readonly"></td>
				</tr>
				<tr bgcolor="#eeeeff">
					<td>ディスクID</td>
					<td><input type="text" name="diskId" value="<s:property value="diskId" />"></td>
				</tr>

				<tr bgcolor="#eeeeff">
					<td>作曲家</td>
					<td>
					<s:select name="composerId" list="composers" listKey="id" listValue="name" />
					</td>
				</tr>

			<tr bgcolor="#eeeeff">
				<td>曲ナンバー</td>
				<td><input type="text" name="title" value="<s:property value="title" />"></td>
			</tr>
			<tr bgcolor="#eeeeff">
				<td>備考</td>
				<td><input type="text" name="memo" value="<s:property value="memo" />"></td>
			</tr>
			<tr bgcolor="#eeeeff">
				<td>交響曲</td>
				<td>
					<s:if test="%{symphony>0}">
						<input type="radio" name="symphony" value="true" checked>yes
						<input type="radio" name="symphony" value="false">no
					</s:if>
					<s:else>
						<input type="radio" name="symphony" value="true">yes
						<input type="radio" name="symphony" value="false" checked>no
					</s:else>
				</td>
			</tr>
			<tr bgcolor="#eeeeff">
				<td>録音日</td>
				<td><input type="text" name="date" value="<s:property value="date" />"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="変更"></td>
			</tr>
			</table>
		</s:form>
		</div>
		</div>

	</body>
</html>
