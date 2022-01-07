<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
 
<script>
$(function(){

	$('.loader').css('display','none');
	 $(document).on("click", ".start-btn", function(){
			$('.loader').css('display','block');
			var sort = $("#sort").val();
			var keyword = $("input[name=keyword]").val();
			var pageSize = $("input[name=pageSize]").val();
		
			var param = {
					"sort" : sort,
					"keyword" : keyword,
					"pageSize" : pageSize
			}
	 		ajaxCallGet("/api/v1/naver/sort?keyword="+keyword+"&pageSize="+pageSize+"&sort="+sort, function(res){
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
				var sort = $("#sort").val();
				var keyword = $("input[name=keyword]").val();
				var pageSize = $("input[name=pageSize]").val();
				var sort_type_seq = $("#sort").children("option:selected").attr('data-sort');
		 	 	ajaxCallGet("/api/v1/naver/sort/save?keyword="+keyword+"&pageSize="+pageSize+"&sort="+sort+"&sort_type_seq="+sort_type_seq+"&type_seq=2", function(res){
					if(res.success){
						$('.loader').css('display','none');
	                    $(".data-len").text(res.record);
		                   alert("저장되었습니다. ");
					}
				})  
			}) 
			

})



</script>
 
 
<script type="text/x-jsrender" id="selectRstList">
		{{for data}}
  			<tr>
               <td>{{:name}}</td>
               <td>{{:price}}</td>
               <td>{{:mallName}}</td>
				{{for imgs}}
				<td>{{:url}}</td>
				{{/for}}
               <td>{{:bizplBaseAddr}}</td>
        	</tr>
		{{/for}}
</script>
          <div class="content-wrapper">
          
     		  <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">네이버 쇼핑 (전체 기준)</h4>
                     <div class="dot-opacity-loader loader">
                            <span></span>
                            <span></span>
                            <span></span>
                          </div>
                     	 <div class="form-group row">
                    	 <button type="button" class="btn btn-outline-warning btn-lg guide-btn" data-toggle="modal" data-target="#content_guide">가이드를 확인해주세요. </button>
                      </div>
                    <p class="card-description">검색어와 정렬기준, 페이지 수를 입력해주세요. </p>
                 	<hr>
                	 <div class="form-group row">
                      <label class="col-sm-2 col-form-label">검색어</label>
                        <div class="col-sm-9">
                           <input type="text" name="keyword" class="form-control">
                        </div>
                      </div>
                     <div class="form-group row">
                      <label class="col-sm-2 col-form-label">정렬 기준</label>
                        <div class="col-sm-9">
                        	<select id="sort" class="form-control">
                        	 	<c:forEach var="item" items="${sort }">
                        		<option data-sort="${item.sort_seq}" value="${item.name}">${item.content}</option>
                        		</c:forEach>
                        	</select>
                        </div>
                      </div>
                      <div class="form-group row">
                      <label class="col-sm-2 col-form-label">페이지 수</label>
                        <div class="col-sm-9">
                           <input type="number" name="pageSize" class="form-control" value="1">
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
                            <th>가격</th>
                            <th>쇼핑몰 이름</th>
                            <th>이미지 </th>
                            <th>쇼핑몰 주소</th>
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
                        <label class="login-label" for="title">1. 상단 검색어 입력창의 검색어 > 검색어 입력란에 입력 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide_store3.png"></div>
                      </div>
                  	 <div class="form-group">
                        <label class="login-label" for="title">2. 하단의 카테고리 항목 선택 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide_store4.png"></div>
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