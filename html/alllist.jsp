<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 曲一覧</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 曲一覧</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<table>
				<tr>
					<th>ID</th>
					<th>MD</th>
					<th>作曲家</th>
					<th>ナンバー</th>
					<th>登録日</th>
					<th>交響曲</th>
					<th>備考</th>
					<th colspan="2">変更・削除</th>
				</tr>

				<s:iterator value="diskAndMusicCollection">
					<tr>
						<td><s:property value="recordId" /></td>
						<td><s:property value="diskId" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="title" /></td>
						<td><s:property value="date" /></td>

						<s:if test="%{symphony}">
							<td>交響曲</td>
						</s:if>
						<s:else>
							<td></td>
						</s:else>

						<td><s:property value="memo" /></td>

						<td>
							<s:form action="updaterecord1" theme="simple">
							    <input type="hidden" name="recordId" value="<s:property value="recordId" />">
								<s:submit value="変更" />
							</s:form>
						</td>
						<td>
							<s:form action="deleterecord" theme="simple">
							    <input type="hidden" name="recordId" value="<s:property value="recordId" />">
								<input type="hidden" name="diskId" value="<s:property value="diskId" />">
								<input type="hidden" name="name" value="<s:property value="name" />">
								<input type="hidden" name="title" value="<s:property value="title" />">
								<s:submit value="削除" />
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
