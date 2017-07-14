<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="script" fragment="true" %>

<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<c:url var="rootURL" value="/" />
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>

<!-- Vendor CSS -->
<link href="${rootURL}resources/vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
<link href="${rootURL}resources/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
<link href="${rootURL}resources/vendors/bower_components/bootstrap-select/dist/css/bootstrap-select.css" rel="stylesheet">
<link href="${rootURL}resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet">
<link href="${rootURL}resources/vendors/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"	rel="stylesheet">

<link href="${rootURL}resources/vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css" rel="stylesheet">
<!-- CSS -->
<link href="${rootURL}resources/css/app.min.1.css" rel="stylesheet">
<link href="${rootURL}resources/css/app.min.2.css" rel="stylesheet">

</head>
<body>
	
	<security:authentication property="principal" var="user"/>
	
	<header id="header" class="clearfix" data-current-skin="blue">
		<%@ include file="/WEB-INF/views/includes/header.jsp"%>		
	</header>
	<section id="main">
		<%@ include file="/WEB-INF/views/includes/menu.jsp"%>
		<section id="content">
			<div class="container">
				<jsp:doBody />
			</div>		
		</section>
	</section>
	<footer id="footer">
		<ul class="f-menu">
			<li><a href="http://www.tesla.com">Copyright &copy; 2016 Tesla Tech</a></li>
		</ul>
	</footer>
	
	<!-- Javascript Libraries -->
	<script	src="${rootURL}resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>
	<script	src="${rootURL}resources/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script	src="${rootURL}resources/vendors/bower_components/Waves/dist/waves.min.js"></script>
	<script	src="${rootURL}resources/vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
	<script src="${rootURL}resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script	src="${rootURL}resources/vendors/bower_components/bootstrap-select/dist/js/bootstrap-select.js"></script>
	<script	src="${rootURL}resources/vendors/bower_components/moment/min/moment.min.js"></script>
	<script	src="${rootURL}resources/vendors/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${rootURL}resources/vendors/fileinput/fileinput.min.js"></script>
	<script src="${rootURL}resources/vendors/input-mask/input-mask.min.js"></script>
	<script	src="${rootURL}resources/vendors/bootgrid/jquery.bootgrid.updated.min.js"></script>
	
	<script src="${rootURL}resources/vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.min.js"></script>
	
	<script src="${rootURL}resources/js/functions.js"></script>
	<jsp:invoke fragment="script"></jsp:invoke>
		
</body>
</html>