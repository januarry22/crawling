<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
    <div class="container-scroller">
   
      <div class="container-fluid page-body-wrapper">
        <div class="main-panel">
          <div class="content-wrapper" style="height: 1200px;">
          <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-body" style="overflow:auto;">
                    <div class="row">
                      <div class="col-12">
                        <div class="row portfolio-grid" >
                        
                          <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12" >
                            <figure class="effect-text-in">
                              <img src="${detail.img_url1}" alt="image">
                              <figcaption style="text-align: center;">
                                <p>${detail.name}</p>
                                <hr>
                                <p>${detail.content}</p>
                              </figcaption>
                            </figure>
                          </div>	
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