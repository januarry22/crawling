<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="https://static-resource-smartstore.pstatic.net/smartstore/p/static/20210617105433/common_trendy.css">
<link rel="stylesheet" type="text/css" href="https://static-resource-smartstore.pstatic.net/smartstore/p/static/20210617105433/common.css">
<link rel="stylesheet" type="text/css" href="https://ssl.pstatic.net/spi/css/20210531/spi_standard_https.css">
<link rel="stylesheet" type="text/css" href="https://editor-static.pstatic.net/v/basic/1.24.0/css/se.viewer.desktop.css?v=20210617105433">
<link rel="stylesheet" type="text/css" href="https://ssl.pstatic.net/storefarm.editor/static/viewer/common/se_viewer_storefarm_pc.css?v=20210617105433">
<link rel="stylesheet" type="text/css" href="https://static-resource-smartstore.pstatic.net/smartstore/p/static/20210617105433/product_tagging.css">
<link rel="stylesheet" type="text/css" href="https://ssl.pstatic.net/storefarm.editor/static/dist/viewer/1577682057878/1.0/css/se_viewer_storefarm_pc.css">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/js/commonAjax.js"></script>
<script src="https://www.jsviews.com/download/jsrender.js"></script>
<script>
//http://goodbaby.co.kr/smartstore?storeId=gwfnv&categoryId=bd5000da5234467cadd378fee7cfb4c5&pageSize=20
		$(function(){
			var storeId = "${param.storeId}";
			var categoryId = "${param.categoryId}";
			var pageSize = "${param.pageSize}";
			ajaxCallGet("/api/v1/smartstore?storeId="+storeId+"&categoryId="+categoryId+"&pageSize="+pageSize, function(res){
				
				if(!res.success){
					alert(res.errorMsg)
				}else{
					setTimeout(function(){
						 var template = $.templates("#selectRstList"); // <!-- 템플릿 선언
				         var htmlOutput = template.render(res); // <!-- 렌더링 진행 -->
						 $("body").html(htmlOutput);
						 
						 setTimeout(function(){
								$("body").show();
							}, 3000)
						 
					}, 10000)
				}
			})
		})
		$.views.converters("view",
	        function(val) {
		 		var content = val.replace(/\\n/g,"").replace(/\\/g,"");
		   		return content;
	        }
	      );
	</script>
<script type="text/x-jsrender" id="selectRstList">
		{{for data}}
			<div style="margin:30px 0px; border-top:3px solid black">
				<h1>{{:name}}(오혜성)</h1>
				{{for imgs}}
					<img src="{{:url}}" style="width:300px">
				{{/for}}
				<div>
					{{view:renderContent}}
				</div>
			</div>
		{{/for}}
</script>
</head>
<body style="display: none">

</body>
</html>