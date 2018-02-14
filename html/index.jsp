<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv=Content-Style-Type content=text/css>
		<link rel="stylesheet" type="text/css" href="hatena.css">
		<title>MD曲管理</title>
	</head>

	<body>
		<h1>MD曲管理</h1>

		<h3>表示</h3>
		<ul>
			<s:form action="symphonylist" theme="simple">
				<s:submit value="交響曲一覧表" />
				<s:checkbox name="excludeNoNumber" />番号なしのみを除外
				<s:select name="namevalue" list="#{ '1':'知名度1','2':'知名度2','3':'知名度3' }"/>
			</s:form>
			<s:form action="alllist" theme="simple">
				<s:submit value="曲一覧リスト" />
				<s:select name="allListNo" list="pageAndDiskIdCollection" listKey="pageId" listValue="diskIdRange" value="%{lastPageIndex}" />
			</s:form>
			<s:form action="composeronlylist" theme="simple">
				<input type="hidden" name="composerName" value="ハイドン">
				<input type="hidden" name="composerId" value="83">
				<input type="hidden" name="maxNumber" value="105">
				<input type="hidden" name="perDisk" value="3">
				<s:submit value="ハイドン" />
			</s:form>
			<s:form action="composeronlylist" theme="simple">
				<input type="hidden" name="composerName" value="モーツァルト">
				<input type="hidden" name="composerId" value="84">
				<input type="hidden" name="maxNumber" value="41">
				<input type="hidden" name="perDisk" value="2">
				<s:submit value="モーツァルト" />
			</s:form>
			<s:form action="etclist" theme="simple">
				<s:submit value="交響曲以外一覧リスト" />
			</s:form>
			<s:form action="composerlist" theme="simple">
				<s:submit value="作曲家リスト" />
			</s:form>
			<s:form action="disklist" theme="simple">
				<s:submit value="ディスクリスト表示" />
			</s:form>
			<s:form action="recentrecord" theme="simple">
				<s:submit value="最近の録音一覧" />
			</s:form>
		</ul>

		<h3>検索</h3>
		<ul>
			<s:form action="search1" theme="simple">
				<s:submit value="検索" />
			</s:form>
		</ul>

		<h3>登録</h3>
		<ul>
			<s:form action="addrecord1" theme="simple">
				<s:submit value="録音登録" />
			</s:form>
			<a href="addcomposer1.jsp">作曲家登録</a>
		</ul>
	</body>
</html>
