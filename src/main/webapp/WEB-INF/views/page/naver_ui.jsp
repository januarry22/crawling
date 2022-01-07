<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
 
<script>

var data_link;
var store_link;

function urlSplit(url){
	var result = url.substring(26, url.lastIndexOf(''));
	store_link = result;
}

function urlReplace(link,size){
	var reSize ="&pageSize="+size+"&";
	var result = link.replace('&pageSize=10&',reSize);
	data_link = result;
}
$(function(){
		console.log("dddd")
	 $('.loader').css('display','none');
	 
	 $(document).on("click", ".start-btn", function(){
			$('.loader').css('display','block');
			var link = $("input[name=link]").val();
			var refer = $("input[name=refer]").val();
			var pageSize = $("input[name=pageSize]").val();
			var writer_name = $("input[name=writer_name]:checked").val();

			urlSplit(refer);
			urlReplace(link,pageSize);
			
		/* 	console.log("data_link"+data_link);
			console.log("store_link"+store_link); */

  	/* 		ajaxCallGet("/api/v1/naver?target="+target+"&pageSize="+pageSize, function(res){
				if(!res.success){
				 	alert(res.errorMsg) 
				}else{
                    $(".tbody").html($("#selectRstList").render(res));
				}
			})   */
			var param = {
  				"link" : data_link,
  				"refer" : store_link,
  				"type_seq" : "1",
				"writer":writer_name
  			}
  	 	ajaxCallPost("/api/v1/naver",param, function(res){
				if(!res.success){
				 	alert(res.errorMsg) 
				}else{
					$('.loader').css('display','none');
                    $(".tbody").html($("#selectRstList").render(res));
                    $(".data-len").text(res.record);
				}
			})      
		
		}) 
		 $(document).on("click", ".save-btn", function(){
				$('.loader').css('display','block');
				var link = $("input[name=link]").val();
				var full_link = $("input[name=refer]").val();
				var refer = $("input[name=refer]").val();
				var pageSize = $("input[name=pageSize]").val();
				var writer_name = $("input[name=writer_name]").val();
				urlSplit(refer);
				urlReplace(link,pageSize);
	  			var param = {
	  				"link" :link,
	  				"refer" : refer,
	  				"full_link" : full_link,
	  				"type_seq" : "1",
					"writer_name":writer_name
	  			}
	  			ajaxCallPost("/api/v1/naver/save",param, function(res){
					if(!res.success){
					 	alert(res.errorMsg) 
					}else{
						$('.loader').css('display','none');
	                    $(".tbody").html($("#selectRstList").render(res));
	                    $(".data-len").text(res.record);
	                    alert("저장되었습니다. ");
					}
				})    
			}) 
				
		$("input[name=writer_name]").click(function(){
			localStorage.setItem("writer_name", $(this).val());
		})
		var temp_writer_name = localStorage.getItem("writer_name");
		if(temp_writer_name){
			$("."+temp_writer_name).attr("checked", true);
		}
})



</script>
 
 
<script type="text/x-jsrender" id="selectRstList">
		{{for data}}
  			<tr>
               <td>{{:name}}</td>
			   <td>{{:writer_name}}</td>
				{{for imgs}}
				<td>{{:url}}</td>
				{{/for}}
        	</tr>
		{{/for}}
</script>
          <div class="content-wrapper">
        	  <div class="card">
                  <div class="card-body">
                   		
                    <h4 class="card-title">네이버 쇼핑 (스토어 기준)</h4>
                        <div class="dot-opacity-loader loader">
                            <span></span>
                            <span></span>
                            <span></span>
                          </div>
                 	 <div class="form-group row">
                    	 <button type="button" class="btn btn-outline-warning btn-lg guide-btn" data-toggle="modal" data-target="#content_guide">가이드를 확인해주세요. </button>
                 
                     </div>
                      
                    <p class="card-description">링크를 입력해주세요. </p>
                 	<hr>
                	  <div class="form-group row">
                      <label class="col-sm-2 col-form-label">스토어 링크</label>
                        <div class="col-sm-9">
                            <input type="text" name="refer" class="form-control">
                        </div>
                      </div>
                	 <div class="form-group row">
                      <label class="col-sm-2 col-form-label">데이터 링크</label>
                        <div class="col-sm-9">
                     		<input type="text" name="link" class="form-control">
                        </div>
                      </div>
                       <div class="form-group row">
                      <label class="col-sm-2 col-form-label">상품 개수 </label>
                        <div class="col-sm-9">
                     		<input type="text" name="pageSize" class="form-control" value="1">
                        </div>
                      </div>
					  	  <div class="form-group row">
						<label class="col-sm-2 col-form-label">실행자명</label>
                        <div class="col-sm-9">
                           <input type="radio" value="admin1" class="admin1" name="writer_name" checked>admin1<br>
							<input type="radio" value="admin2" class="admin2" name="writer_name">admin2<br>
							<input type="radio" value="admin3" class="admin3" name="writer_name">admin3<br>
							<input type="radio" value="admin4" class="admin4" name="writer_name">admin4<br>
							<input type="radio" value="admin5" class="admin5" name="writer_name">admin5<br>
							<input type="radio" value="admin6" class="admin6" name="writer_name">admin6<br>
							<input type="radio" value="admin7" class="admin7" name="writer_name">admin7<br>
							<input type="radio" value="admin8" class="admin8" name="writer_name">admin8<br>
							<input type="radio" value="admin9" class="admin9" name="writer_name">admin9<br>
							<input type="radio" value="admin10" class="admin10" name="writer_name">admin10
                        </div>
                      </div>
                        <div class="form-group row">
                		 <button type="button" class="btn btn-info btn-fw start-btn" style="margin:auto;">start</button>
                      </div>
                      
                	 <div class="form-group row">
                		 <button type="button" class="btn btn-success btn-fw save-btn" style="margin:auto;">save</button>
                      </div>
                  </div>
                      <div class="card-body">
                      <p class="card-description"><span class="data-len"></span> 건 </p>
                  <!--   <h4 class="card-title">Basic Table</h4> -->
                    <div class="table-responsive" style="height:500px;">
                      <table class="table" style="overflow:auto;">
                        <thead>
                          <tr>
                            <th>상품명 </th>
							<th>작성자</th>
                            <th>이미지 </th>
                          </tr>
                        </thead>
                        <tbody class="tbody">
                      
                        </tbody>
                      </table>
                    </div>
                  </div>
               <!--  <div class="row">
                  <div class="col-12">
                    <div class="table-responsive">
                      <table id="order-listing" class="table">
                        <thead>
                          <tr>
                            <th></th>
                            <th>상품명 </th>
                            <th>가격</th>
                            <th>쇼핑몰 이름</th>
                            <th>이미지 </th>
                            <th>쇼핑몰 주소</th>
                          </tr>
                        </thead>
                        <tbody class="tbody">
                        렌더링
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div> -->
              </div>
              
              
               <!-- modal start  -->
        
         <div class="modal fade" id="content_guide" tabindex="-1" role="dialog" aria-labelledby="#content_guide" aria-hidden="true">
                      <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content"  style="width: 164%;">
                          <div class="modal-header">
                            <h5 class="modal-title" id="#content_guide">가이드 </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true" class="modal-close" style="color: white;font-weight: bolder;">닫기</span>
                            </button>
                          </div>
                    <div class="modal-body">
                    <form class="forms-sample">
                      <div class="form-group">
                        <label class="login-label" for="title">1. 네이버 쇼핑 > 상단의 카테고리 탭 중 선택 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide1_store.png"></div>
                      </div>
                      <div class="form-group">
                        <label class="login-label" for="title">[중요] 개발자 도구를 활성화 시켜주세요 (F12 혹은 크롬기준 [:]버튼 > 도구 > 도구더보기 > 개발자도구 ) </label>
                        								
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide2_store.png"></div>
                      </div>
                  	 <div class="form-group">
                        <label class="login-label" for="title">2. 아래 나타난 스토어들 중 크롤링을 원하는 스토어 클릭 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide2_1.png"></div>
                      </div>
                 	 <div class="form-group">
                        <label class="login-label" for="title">3. 상단 링크 복사 > 스토어 링크 입력란에 붙여넣기 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide6_store.png"></div>
                      </div>
                       <div class="form-group">
                        <label class="login-label" for="title">4. 개발자 도구 탭을 선택해주세요 </label>
                        <label class="login-label" for="title">Network > Fetch/XHR 항목선택 </label>
                          <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide4_store.png"></div>
                       </div>
                       <div class="form-group">
                        <label class="login-label" for="title">5. []사각형안의 영역 products 로 시작하는 링크 클릭 > 새탭에서 열기 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide3_store.png"></div>
                      </div>
                      <div class="form-group">
                        <label class="login-label" for="title">6. 새탭의 상단 링크 클릭 > 복사 > 데이터 링크 입력란에 붙여넣기 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide5_store.png"></div>
                      </div>
                        <div class="form-group">
                        <label class="login-label" for="title">7.원하는 페이지수 입력 > start / save 버튼 클릭 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide7_store.png"></div>
                      </div>
                    </form>
                  </div>
                   <div class="modal-footer">
                        <label class="login-label" for="title">save 후 리스트 보기에서 확인하실수 있습니다. </label>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
                
                
               	
                
                
       