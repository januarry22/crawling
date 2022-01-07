<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="https://shopping.pstatic.net/display/p/static/20210617151644/common.css">
<link rel="stylesheet" type="text/css" href="https://shopping.pstatic.net/display/p/static/20210617151644/ac_panel.css">
<link rel="stylesheet" type="text/css" href="https://shopping.pstatic.net/display/p/static/20210617151644/common_sw.css">
<link rel="stylesheet" type="text/css" href="https://ssl.pstatic.net/spi/css/20210531/spi_standard_https.css">
<link rel="stylesheet" type="text/css" href="https://editor-static.pstatic.net/v/basic/1.5.0/css/se.viewer.desktop.css?v=20210617151644">
<link rel="stylesheet" type="text/css" href="https://ssl.pstatic.net/storefarm.editor/static/viewer/common/se_viewer_storefarm_pc.css?v=20210617151644">
<link rel="stylesheet" type="text/css" href="https://shopping.pstatic.net/display/p/static/20210617151644/product_tagging.css">
<link rel="stylesheet" type="text/css" href="https://ssl.pstatic.net/storefarm.editor/static/dist/viewer/1577682057878/1.0/css/se_viewer_storefarm_pc.css">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/js/commonAjax.js"></script>
<script src="https://www.jsviews.com/download/jsrender.js"></script>
<script>
		//http://goodbaby.co.kr/naver/sort?keyword=티엠포미스틱&pageSize=20&sort=price_asc
		//	sort : rel(네이버쇼핑순), price_asc(낮은가격순), price_dsc(높은가격순), date(등록순), 리뷰순(review) (필수)
	/* 	var keyword = "${param.keyword}";
		var pageSize = "${param.pageSize}";
		var sort = "${param.sort}"; */
		var keyword = "${keyword}";
		var pageSize = "${pageSize}";
		var sort = "${sort}";
		
		$(function(){
			ajaxCallGet("/api/v1/naver/sort?keyword="+keyword+"&pageSize="+pageSize+"&sort="+sort, function(res){
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
	</script>
<script type="text/x-jsrender" id="selectRstList">
		{{for data}}
			<div style="margin:30px 0px; border-top:3px solid black">
				<h1>{{:name}}({{:price}})(오혜성)</h1>
				<h1>{{:mallName}}({{:bizplBaseAddr}})</h1>
				{{for imgs}}
					<img src="{{:url}}" style="width:300px">
				{{/for}}
				<div>
				</div>
			</div>
		{{/for}}
</script>
</head>
<body style="display: none">

</body>
</html>