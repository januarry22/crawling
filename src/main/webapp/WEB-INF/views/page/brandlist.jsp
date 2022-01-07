<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <script>
		$(function(){
			 $(document).ready(function() {
			       $("#keyword").keyup(function() {
			           var k = $(this).val();
			           console.log("k"+k);
			           $(".content-box").hide();
			           var text = $(".content-box > .effect-text-in > .fig-in > .inner-tag:contains('" + k + "')");
			           $(text).parent().parent().parent().show();
			       })
			   })
		     $(document).on("click", ".detail-brand-btn", function(){
					var seq = $(this).attr('data-seq');
					location.href="/brand/"+seq;
			 }) 
			 $(document).on("click", ".remove-btn", function(){
					var seq = $(this).attr('data-seq');
					var table = $(this).attr('data-type');
					var table_seq = $(this).attr('data-info');
					var param = {
						"seq" : seq,
						"table" : table,
						"table_seq" : table_seq
					}
					if(confirm("삭제 하시겠습니까?")){
						ajaxCallPost("/api/v1/store/remove",param, function(res){
							if(res.success){
								alert("삭제 되었습니다.");
								location.reload(true);
							}
						})  	
					}
			 	 	
				}) 
		});
	</script>
	
<style>
.content-box{	    
	border-style: dotted;
    border-width: thin;
    margin: 4%;
}
.inner-tag{
    font-weight: bold;
    font-size: medium;
    color: aliceblue;
    margin: 5px;
}
	
</style>
    <div class="container-scroller">
   
      <div class="container-fluid page-body-wrapper">
        <div class="main-panel">
          <div class="content-wrapper" style="height: 1200px;">
          <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-body" style="overflow:auto;">
              	<input type="text" class="form-control" id="keyword" style="max-width:100%;" placeholder="검색어를 입력해주세요.">
                    <div class="row">
                      <div class="col-12">
                        <div class="row portfolio-grid" >
                        
                        <c:forEach items="${list }" var="item" >
                          <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12 content-box" id="chk-on">
                            <figure class="effect-text-in">
                             <%--  <img src="${item.img_url}" alt="image" style="width: 100%;"> --%>
                              <figcaption class="fig-in">
                                <p class="inner-tag">${item.name}</p>
                            	 <button type="button" class="btn btn-outline-secondary btn-sm detail-brand-btn" data-seq="${item.brand_product_seq}">상세 보기</button>
                              </figcaption>
                            </figure>
                               	<input type="button" class="btn btn-warning btn-sm remove-btn" value="삭제" data-type="brand_product" data-info="brand_product_seq"  data-seq="${item.brand_product_seq}">
                          </div>	
                        
                          </c:forEach>
                          
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>