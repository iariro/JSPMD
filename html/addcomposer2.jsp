<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>MDリスト - 作曲家登録</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>

		<h1>MDリスト - 作曲家登録</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<s:property value="composer" />
			を作曲家ID=
			<s:property value="newId" />
			番で登録しました。

		</div>
		</div>
		</div>

	</body>
</html>
