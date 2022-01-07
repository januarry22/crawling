<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ include file="/WEB-INF/tiles/components/commonCss.jsp" %>
  <div class="horizontal-menu">
        <nav class="navbar top-navbar col-lg-12 col-12 p-0">
          <div class="container">
            <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center" style="margin-left: 45%;height:85px;">
              <a class="navbar-brand brand-logo" href="/" style="color:white; font-weight:bold; font-size:30px;">
                 januarry22@github.io<!-- <img src="/resources/assets/images/logo.svg" alt="logo" /> --></a>
              <a class="navbar-brand brand-logo-mini" href="/"><!-- <img src="/resources/assets/images/logo-mini.svg" alt="logo" /> -->
                januarry22@github.io</a>
            </div>
        
          </div>
        </nav>
        <nav class="bottom-navbar">
          <div class="container">
            <ul class="nav page-navigation">
           <!--    <li class="nav-item menu-items" style="width: 300px;">
                <a class="nav-link" href="/">
                  <i class="mdi mdi-speedometer menu-icon"></i>
                  <span class="menu-title">네이버 쇼핑</span>
                </a>
              </li>
              <li class="nav-item menu-items" style="width: 300px;">
                <a class="nav-link" href="/">
                  <i class="mdi mdi mdi-texture menu-icon"></i>
                  <span class="menu-title">스마트 스토어</span>
                </a>
              </li>
              <li class="nav-item mega-menu menu-items"  style="width: 300px;">
                <a href="/" class="nav-link">
                  <i class="mdi mdi-crosshairs-gps menu-icon"></i>
                  <span class="menu-title">- </span>
                </a> -->
              <!--   <div class="submenu">
                  <div class="col-group-wrapper row">
                    <div class="col-group col-md-4">
                      <div class="row">
                        <div class="col-12">
                          <p class="category-heading">Basic Elements</p>
                          <div class="submenu-item">
                            <div class="row">
                              <div class="col-md-6">
                                <ul>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/accordions.html">Accordion</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/buttons.html">Buttons</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/badges.html">Badges</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/breadcrumbs.html">Breadcrumbs</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/dropdowns.html">Dropdown</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/modals.html">Modals</a></li>
                                </ul>
                              </div>
                              <div class="col-md-6">
                                <ul>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/progress.html">Progress bar</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/pagination.html">Pagination</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/tabs.html">Tabs</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/typography.html">Typography</a></li>
                                </ul>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-group col-md-4">
                      <div class="row">
                        <div class="col-12">
                          <p class="category-heading">Advanced Elements</p>
                          <div class="submenu-item">
                            <div class="row">
                              <div class="col-md-6">
                                <ul>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/dragula.html">Dragula</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/carousel.html">Carousel</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/clipboard.html">Clipboard</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/context-menu.html">Context Menu</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/loaders.html">Loader</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/slider.html">Slider</a></li>
                                </ul>
                              </div>
                              <div class="col-md-6">
                                <ul>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/popups.html">Popup</a></li>
                                  <li class="nav-item"><a class="nav-link" href="../../pages/ui-features/notifications.html">Notification</a></li>
                                </ul>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-group col-md-4">
                      <p class="category-heading">Icons</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/icons/flag-icons.html">Flag Icons</a></li>
                        <li class="nav-item"> <a class="nav-link" href="../../pages/icons/mdi.html">Mdi icons</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/icons/font-awesome.html">Font Awesome</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/icons/simple-line-icon.html">Simple Line Icons</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/icons/themify.html">Themify Icons</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="nav-item menu-items">
                <a href="#" class="nav-link">
                  <i class="mdi mdi-clipboard-text menu-icon"></i>
                  <span class="menu-title">Forms</span>
                  <i class="menu-arrow"></i></a>
                <div class="submenu">
                  <ul class="submenu-item">
                    <li class="nav-item"><a class="nav-link" href="../../pages/forms/basic_elements.html">Basic Elements</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/forms/advanced_elements.html">Advanced Elements</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/forms/validation.html">Validation</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/forms/wizard.html">Wizard</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/forms/text_editor.html">Text Editor</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/forms/code_editor.html">Code Editor</a></li>
                  </ul>
                </div>
              </li>
              <li class="nav-item mega-menu menu-items">
                <a href="#" class="nav-link">
                  <i class="mdi mdi-chart-bar menu-icon"></i>
                  <span class="menu-title">Data</span>
                  <i class="menu-arrow"></i></a>
                <div class="submenu">
                  <div class="col-group-wrapper row">
                    <div class="col-group col-md-6">
                      <p class="category-heading">Charts</p>
                      <div class="submenu-item">
                        <div class="row">
                          <div class="col-md-6">
                            <ul>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/chartjs.html">Chart Js</a></li>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/morris.html">Morris</a></li>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/flot-chart.html">Flot</a></li>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/google-charts.html">Google Chart</a></li>
                            </ul>
                          </div>
                          <div class="col-md-6">
                            <ul>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/sparkline.html">Sparkline</a></li>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/c3.html">C3 Chart</a></li>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/chartist.html">Chartist</a></li>
                              <li class="nav-item"><a class="nav-link" href="../../pages/charts/justGage.html">JustGage</a></li>
                            </ul>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-group col-md-3">
                      <p class="category-heading">Table</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/tables/basic-table.html">Basic Table</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/tables/data-table.html">Data Table</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/tables/js-grid.html">Js-grid</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/tables/sortable-table.html">Sortable Table</a></li>
                      </ul>
                    </div>
                    <div class="col-group col-md-3">
                      <p class="category-heading">Maps</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/maps/mapael.html">Mapael</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/maps/vector-map.html">Vector Map</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/maps/google-maps.html">Google Map</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="nav-item mega-menu menu-items">
                <a href="#" class="nav-link">
                  <i class="mdi mdi-content-copy menu-icon"></i>
                  <span class="menu-title">Sample Pages</span>
                  <i class="menu-arrow"></i></a>
                <div class="submenu">
                  <div class="col-group-wrapper row">
                    <div class="col-group col-md-3">
                      <p class="category-heading">User Pages</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/login.html">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/login-2.html">Login 2</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/register.html">Register</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/register-2.html">Register 2</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/lock-screen.html">Lockscreen</a></li>
                      </ul>
                    </div>
                    <div class="col-group col-md-3">
                      <p class="category-heading">Error Pages</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/error-404.html">404</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/error-500.html">500</a></li>
                      </ul>
                    </div>
                    <div class="col-group col-md-3">
                      <p class="category-heading">E-commerce</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/invoice.html">Invoice</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/pricing-table.html">Pricing Table</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/orders.html">Orders</a></li>
                      </ul>
                    </div>
                    <div class="col-group col-md-3">
                      <p class="category-heading">General</p>
                      <ul class="submenu-item">
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/search-results.html">Search Results</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/profile.html">Profile</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/timeline.html">Timeline</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/news-grid.html">News grid</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/portfolio.html">Portfolio</a></li>
                        <li class="nav-item"><a class="nav-link" href="../../pages/samples/faq.html">FAQ</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="nav-item menu-items">
                <a href="#" class="nav-link">
                  <i class="mdi mdi-webpack menu-icon"></i>
                  <span class="menu-title">Apps</span>
                  <i class="menu-arrow"></i></a>
                <div class="submenu">
                  <ul class="submenu-item">
                    <li class="nav-item"><a class="nav-link" href="../../pages/apps/email.html">Email</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/apps/calendar.html">Calendar</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/apps/todo.html">Todo List</a></li>
                    <li class="nav-item"><a class="nav-link" href="../../pages/apps/gallery.html">Gallery</a></li>
                  </ul>
                </div>
              </li>
              <li class="nav-item menu-items">
                <a href="http://www.bootstrapdash.com/demo/corona-staging/jquery/documentation/documentation.html" class=" nav-link" target="_blank">
                  <i class="mdi mdi-file-document-box menu-icon"></i>
                  <span class="menu-title">Documentation</span></a>
              </li> -->
            </ul>
          </div>
        </nav>
      </div>

