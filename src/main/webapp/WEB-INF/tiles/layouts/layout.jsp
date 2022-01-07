<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>크롤링 </title>

	<script src="/resources/js/commonAjax.js"></script>

	<script src="https://www.jsviews.com/download/jsrender.js"></script>
 	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/1.0.11/jsrender.js"></script>
	
   <!--  <link rel="stylesheet" href="/resources/assets/classic-vertical/css/style.css" />
    <link rel="stylesheet" href="/resources/assets/scss/classic-vertical/style.scss" />
    <link rel="stylesheet" href="/resources/assets/scss/classic-vertical/_layouts.scss" />
    <link rel="stylesheet" href="/resources/assets/scss/classic-vertical/_navbar.scss" />
    <link rel="stylesheet" href="/resources/assets/scss/classic-vertical/_sidebar.scss" />
    <link rel="stylesheet" href="/resources/assets/scss/classic-vertical/_variables.scss" />

    <script src="/resources/assets/fonts"></script>
    <script src="/resources/assets/images"></script> -->
    
    <link rel="stylesheet" href="/resources/assets/vendors/dropify/dropify.min.css">
    <link rel="stylesheet" href="/resources/assets/vendors/jquery-file-upload/uploadfile.css">
    <link rel="stylesheet" href="/resources/assets/vendors/jquery-tags-input/jquery.tagsinput.min.css">
    
    <!-- plugins:css -->
    <link rel="stylesheet" href="/resources/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="/resources/assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <link rel="stylesheet" href="/resources/assets/vendors/jvectormap/jquery-jvectormap.css">
    <link rel="stylesheet" href="/resources/assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="/resources/assets/vendors/x-editable/bootstrap-editable.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/resources/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="/resources/assets/vendors/css/vendor.bundle.base.css">
  
 <!--    <link rel="stylesheet" href="/resources/node_modules/owl-carousel-2/assets/owl.carousel.min.css" />
    <link rel="stylesheet" href="/resources/node_modules/owl-carousel-2/assets/owl.theme.default.min.css" /> -->
    <!-- End layout styles -->
    <link rel="stylesheet" href="/resources/assets/css/classic-horizontal/style.css">
    <link rel="stylesheet" href="/resources/assets/vendors/font-awesome/css/font-awesome.min.css" />
    <!-- endinject -->
    <!-- End layout styles -->
    <link rel="shortcut icon" href="/resources/assets/images/favicon.png" />
    <!-- ck 에디터  -->
	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>

    <link rel="stylesheet" href="/resources/assets/vendors/jquery-file-upload/uploadfile.css">
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
</head>

<body id="body">

    <div class="container-scroller">
  		  <!-- Main wrapper  -->
		<tiles:insertAttribute name="headerAdmin" />
   
      <div class="container-fluid page-body-wrapper">
        <div class="main-panel" style="height:1200px;">
		
		<tiles:insertAttribute name="contentAdmin" />
		<tiles:insertAttribute name="footerAdmin" />
		</div>
		</div>
		</div>
		 <!-- container-scroller -->
    <!-- plugins:js -->
   <!--  <script src="/resources/assets/vendors/js/vendor.bundle.base.js"></script> -->
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="/resources/assets/vendors/moment/moment.min.js"></script>
    <script src="/resources/assets/vendors/fullcalendar/fullcalendar.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="/resources/assets/js/off-canvas.js"></script>
    <script src="/resources/assets/js/hoverable-collapse.js"></script>
    <script src="/resources/assets/js/misc.js"></script>
    <script src="/resources/assets/js/settings.js"></script>
    <script src="/resources/assets/js/todolist.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="/resources/assets/js/calendar.js"></script>
    <!-- End custom js for this page -->
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="/resources/assets/vendors/datatables.net/jquery.dataTables.js"></script>
    <script src="/resources/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
    <script src="/resources/assets/vendors/jquery-tags-input/jquery.tagsinput.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="/resources/assets/js/data-table.js"></script>
    
    <!-- endinject -->
    <!-- plugin js for this page -->
    <script src="/resources/assets/vendors/summernote/dist/summernote-bs4.min.js"></script>
    <script src="/resources/assets/vendors/tinymce/tinymce.min.js"></script>
    <script src="/resources/assets/vendors/quill/quill.min.js"></script>
    <script src="/resources/assets/vendors/simplemde/simplemde.min.js"></script>
    <!-- plugin js for this page -->
    <!-- inject:js -->
    <script src="/resources/assets/js/off-canvas.js"></script>
    <script src="/resources/assets/js/hoverable-collapse.js"></script>
    <script src="/resources/assets/js/misc.js"></script>
    <script src="/resources/assets/js/settings.js"></script>
    <script src="/resources/assets/js/todolist.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="/resources/assets/js/editorDemo.js"></script>
    
    <script src="/resources/assets/js/js-grid.js"></script>
    <script src="/resources/assets/vendors/jsgrid/jsgrid.min.js"></script>
    
    <!-- aler custom 창 -->
   <!--    <script src="/resources/assets/vendors/js/vendor.bundle.base.js"></script>   
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="/resources/assets/vendors/sweetalert/sweetalert.min.js"></script>
    <script src="/resources/assets/vendors/jquery.avgrund/jquery.avgrund.min.js"></script> 
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="/resources/assets/js/off-canvas.js"></script>
    <script src="/resources/assets/js/hoverable-collapse.js"></script>
    <script src="/resources/assets/js/misc.js"></script>
    <script src="/resources/assets/js/settings.js"></script>
    <script src="/resources/assets/js/todolist.js"></script>
  <!--   <script src="/resources/assets/js/inputmask.js"></script>
    <script src="/resources/assets/js/form-repeater.js"></script> -->
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="/resources/assets/js/alerts.js"></script>
    <script src="/resources/assets/js/avgrund.js"></script>
    
    <script src="/resources/assets/js/x-editable.js"></script>
    <script src="/resources/assets/vendors/dropify/dropify.min.js"></script>
    <script src="/resources/assets/vendors/jquery-file-upload/jquery.uploadfile.min.js"></script>
    <script src="/resources/assets/vendors/jquery-tags-input/jquery.tagsinput.min.js"></script>
    <script src="/resources/assets/vendors/dropzone/dropzone.js"></script>
    <script src="/resources/assets/js/dropify.js"></script>
    <script src="/resources/assets/js/dropzone.js"></script>
    <script src="/resources/assets/js/jquery-file-upload.js"></script>
    <script src="/resources/assets/js/modal-demo.js"></script>
    <!-- plugins:js -->
    <!-- endinject -->
</body>
</html>