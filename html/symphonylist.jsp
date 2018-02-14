<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 交響曲一覧</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>MDリスト - 交響曲一覧</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

		<table>
			<s:iterator value="records" var="record">
				<s:if test="%{#record==null}">
					<tr>
						<th>作曲家</th>

						<s:iterator value="numbers">
							<th width="25"><s:property /></th>
						</s:iterator>
						<th width="250">他</th>
					</tr>
				</s:if>

				<s:else>
					<tr>
						<s:if test="%{#record.completed}">
							<td bgcolor='lightblue'><s:property value="composer" /></td>
						</s:if>
						<s:else>
							<td><s:property value="composer" /></td>
						</s:else>

						<s:iterator value="#record.numbers" var="number">
							<s:if test="%{#number==0}">
								<td bgcolor='#ccccff'></td>
							</s:if>
							<s:elseif test="%{#number==1}">
								<td bgcolor='lightblue'></td>
							</s:elseif>
							<s:elseif test="%{#number==2}">
								<td></td>
							</s:elseif>
							<s:elseif test="%{#number==3}">
								<td bgcolor='#cccccc'></td>
							</s:elseif>
						</s:iterator>

						<td><s:property value="etc" /></td>
					</tr>
				</s:else>
			</s:iterator>
			</table>

		</div>
		</div>
		</div>

	</body>
</html>
