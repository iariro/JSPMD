<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 最近の録音一覧</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 最近の録音一覧</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<table>
				<tr>
					<td></td>
					<th width="35">1月</th>
					<th width="35">2月</th>
					<th width="35">3月</th>
					<th width="35">4月</th>
					<th width="35">5月</th>
					<th width="35">6月</th>
					<th width="35">7月</th>
					<th width="35">8月</th>
					<th width="35">9月</th>
					<th width="35">10月</th>
					<th width="35">11月</th>
					<th width="35">12月</th>
				</tr>

				<s:iterator value="yearMonthCountTable">
				<tr>
					<th><s:property value="year" />年</th>
					<s:iterator value="monthCount" var="count">
						<s:if test="%{#count>0}">
							<td align="right"><s:property /></td>
						</s:if>
						<s:else>
							<td>　</td>
						</s:else>
					</s:iterator>
				</tr>
				</s:iterator>
			</table>

			<br>

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

				<s:iterator value="recentRecords">
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
