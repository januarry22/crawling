<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
 <script>
 $(function(){
     $(document).on("click", ".start-btn", function(){
         var type=$(this).attr("data-type");
         location.href="/"+type;
     });
     $(document).on("click", ".list-btn", function(){
         var type_seq=$(this).attr("data-type");
         location.href="/list/"+type_seq;
     });

    /*   $(document).on("click", ".test-btn", function(){
         ajaxCallGet("/api/v1/site/save", function(res){
             if(!res.success){
             }
         } tlswldnjs
     });  */
 }) 
 </script>
    <div class="container-scroller">
      <!-- partial:../../partials/_horizontal-navbar.html -->
    
      <!-- partial -->
      <div class="container-fluid page-body-wrapper">
        <div class="main-panel">
          <div class="content-wrapper" style="height: 1200px;">
          <div class="row">
              <div class="col-md-3 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">네이버 쇼핑 (전체 기준)</h4>
                    <div class="row my-4">
                      <div class="col-lg-7 mx-auto text-center">
                      <div class="loader-demo-box">
                          <div class="pixel-loader"></div>
                        </div>
                         <div class="preview card-text mb-0 mt-4 start-btn" data-type="naverSort_ui">
                         <button type="button" class="btn btn-outline-secondary btn-fw up-btn">시작하기 <i class="fa fa-arrow-right"></i></button>
                      	
                        </div>
                        <div class="preview card-text mb-0 mt-4 list-btn" data-type="2">
                      	<button type="button" class="btn btn-outline-secondary btn-fw up-btn"> 리스트 보기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                      </div>
                    </div>
                   <div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded justify-content-between">
                        <div>
                          <h6 class="mb-1">전체 기준 검색어 / 정렬 크롤링 실행</h6>
                        </div>
                        <div class="align-self-center">
                          <h6 class="font-weight-bold mb-0"></h6>
                        </div>
                      </div>
                    </div>
                    <div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded">
                        <div>
                          <h6 class="mb-1">참고 </h6>
                          <p class="card-text">
                          	<a href="https://shopping.naver.com">사이트 이동</a></p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            <div class="col-md-3 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">shopping.naver</h4>
                    <div class="row my-4">
                      <div class="col-lg-7 mx-auto text-center">
                	    <div class="loader-demo-box">
                          <div class="pixel-loader"></div>
                        </div>
				  	   <div class="preview card-text mb-0 mt-4 start-btn" data-type="naver_ui">
                      	 <button type="button" class="btn btn-outline-secondary btn-fw up-btn">시작하기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                        <div class="preview card-text mb-0 mt-4 list-btn" data-type="1">
                      	<button type="button" class="btn btn-outline-secondary btn-fw up-btn"> 리스트 보기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                      </div>
                    </div>
                    <div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded justify-content-between">
                        <div>
                          <h6 class="mb-1">네이버 쇼핑 스토어 크롤링 실행</h6>
                        </div>
                        <div class="align-self-center">
                          <h6 class="font-weight-bold mb-0"></h6>
                        </div>
                      </div>
                    </div>
                    <div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded">
                        <div>
                          <h6 class="mb-1">참고 </h6>
                          <p class="card-text">
                          	<a href="https://shopping.naver.com/window/kids/home">사이트 이동</a></p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
               <div class="col-md-3 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">smartstore.naver</h4>
                    <div class="row my-4">
                      <div class="col-lg-7 mx-auto text-center">
                       <div class="loader-demo-box">
                          <div class="pixel-loader"></div>
                        </div>
                        <div class="preview card-text mb-0 mt-4 start-btn" data-type="smartstore_ui">
                      	<button type="button" class="btn btn-outline-secondary btn-fw up-btn"> 시작하기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                     <!--      <div class="preview card-text mb-0 mt-4 test-btn">
                              <button type="button" class="btn btn-outline-secondary btn-fw up-btn"> 테스트 <i class="fa fa-arrow-right"></i> </button>
                          </div> -->
					    <div class="preview card-text mb-0 mt-4 list-btn" data-type="3">
                      	 <button type="button" class="btn btn-outline-secondary btn-fw up-btn">리스트 보기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                      </div>
                    </div>
                 	<div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded justify-content-between">
                        <div>
                          <h6 class="mb-1">스마트 스토어 크롤링 실행 </h6>
                        </div>
                        <div class="align-self-center">
                          <h6 class="font-weight-bold mb-0"></h6>
                        </div>
                      </div>
                    </div>
                    <div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded">
                        <div>
                          <h6 class="mb-1">참고 </h6>
                          <p class="card-text">
                          	<a href="https://shopping.naver.com">사이트 이동</a></p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              
                  <div class="col-md-3 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">brandstore.naver</h4>
                    <div class="row my-4">
                      <div class="col-lg-7 mx-auto text-center">
                       <div class="loader-demo-box">
                          <div class="pixel-loader"></div>
                        </div>
                        <div class="preview card-text mb-0 mt-4 start-btn" data-type="brandstore">
                      	<button type="button" class="btn btn-outline-secondary btn-fw"> 시작하기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                        <div class="preview card-text mb-0 mt-4 list-btn" data-type="4">
                      	 <button type="button" class="btn btn-outline-secondary btn-fw up-btn">리스트 보기 <i class="fa fa-arrow-right"></i> </button>
                        </div>
                        </div>
                    </div>
                 	<div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded justify-content-between">
                        <div>
                          <h6 class="mb-1">Brand 스토어 크롤링 실행 </h6>
                        </div>
                        <div class="align-self-center">
                          <h6 class="font-weight-bold mb-0"></h6>
                        </div>
                      </div>
                    </div>
                    <div class="row mt-3">
                      <div class="col-12 bg-gray-dark d-flex flex-row py-3 px-4 rounded">
                        <div>
                          <h6 class="mb-1">참고 </h6>
                          <p class="card-text">
                     	<a href="https://brand.naver.com">사이트 이동</a></p>
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