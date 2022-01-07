<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
 
<script>
var categoryId_val;
var storeId_val;

function urlSplit(url){
	var cons = url.replaceAll('/','-');	
	var result = cons.split('-');
	categoryId_val = result[5].replace('?cp=1','');
	storeId_val = result[3];
}
$(function(){

		$('.loader').css('display','none');
	
	 	$(document).on("click", ".start-btn", function(){
	 		$('.loader').css('display','block');
			var keyword = $("input[name=keyword]").val();
			urlSplit(keyword);
			
			var storeId = storeId_val;
			var categoryId = categoryId_val;
			var pageSize = $("input[name=pageSize]").val();
			var page = $("input[name=page]").val();
			var writer_name = $("input[name=writer_name]:checked").val();
			
			
		/* 	var storeId = "bebedekids";
			var categoryId = "f301482ae5df4a6d8412d96b0bbce752";
			var pageSize = "20"; */
		 	ajaxCallGet("/api/v1/smartstore?storeId="+storeId+"&categoryId="+categoryId+"&pageSize="+pageSize+"&page="+page+"&writer_name="+writer_name, function(res){
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
				var keyword = $("input[name=keyword]").val();
				urlSplit(keyword);
				
				var storeId = storeId_val;
				var categoryId = categoryId_val;
				var pageSize = $("input[name=pageSize]").val();
				var page = $("input[name=page]").val();
				var writer_name = $("input[name=writer_name]:checked").val();
				
				console.log("writer_name"+writer_name);
	  	 	ajaxCallGet("/api/v1/smartstore/save?storeId="+storeId+"&categoryId="+categoryId+"&pageSize="+pageSize+"&page="+page+"&writer_name="+writer_name, function(res){
					if(res.success){
						$('.loader').css('display','none');
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
                  	
                    <h4 class="card-title">스마트 스토어</h4>
                     <div class="dot-opacity-loader loader">
                            <span></span>
                            <span></span>
                            <span></span>
                          </div>
                     <div class="form-group row">
                    	 <button type="button" class="btn btn-outline-warning btn-lg guide-btn" data-toggle="modal" data-target="#content_guide">가이드를 확인해주세요. </button>
                      </div>
                    <p class="card-description">스마트 스토어의 링크를 입력해주세요.</p>
                 	<hr>
                	 <div class="form-group row">
                      <label class="col-sm-2 col-form-label">링크</label>
                        <div class="col-sm-9">
                           <input type="text" name="keyword" class="form-control">
                        </div>
                      </div>
                 <%--     <div class="form-group row">
                      <label class="col-sm-2 col-form-label">정렬 기준</label>
                        <div class="col-sm-9">
                        	<select id="sort" class="form-control">
                        	 	<c:forEach var="item" items="${sort }">
                        		<option data-sort="${item.sort_seq}" value="${item.name}">${item.content}</option>
                        		</c:forEach>
                        	</select>
                        </div>
                      </div> --%>
                      <div class="form-group row">
                      <label class="col-sm-2 col-form-label">페이지 수( 20 이상 입력해주세요. )</label>
                        <div class="col-sm-9">
                           <input type="number" name="pageSize" class="form-control" value="20">
                        </div>
                      </div>
                         <div class="form-group row">
                      <label class="col-sm-2 col-form-label">페이지</label>
                        <div class="col-sm-9">
                           <input type="number" name="page" class="form-control" value="1">
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
                            <th>내용</th>
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
                        <label class="login-label" for="title">1. 스마트 스토어 접속 > 상단의 카테고리 중 선택 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide_store1.png"></div>
                      </div>
                  	 <div class="form-group">
                        <label class="login-label" for="title">2. 상단의 링크 복사 > 링크 입력란에 붙여넣기 </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide_store2.png"></div>
                      </div>
                      	 <div class="form-group">
                        <label class="login-label" for="title">3. 원하는 페이지수 입력 start / save </label>
                        <div class="img-container">
                        	<img style="width:100%" src="/resources/img/guide6_store.png"></div>
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