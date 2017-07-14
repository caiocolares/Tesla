<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:url var="rootURL" value="/" />
<html>
<head>

		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tesla - Login</title>
        
        <!-- Vendor CSS -->
        <link href="${rootURL}resources/vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="${rootURL}resources/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
            
        <!-- CSS -->
        <link href="${rootURL}resources/css/app.min.1.css" rel="stylesheet">
        <link href="${rootURL}resources/css/app.min.2.css" rel="stylesheet">


</head>

 	 <body class="login-content">
 	 
 	 <div class="card">
 	    <c:if test="${info != null }">
	  		<div class="alert alert-success alert-dismissible" role="alert">
               <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
               ${info }
           </div>
        </c:if>
        <c:if test="${error != null }">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                ${error }
                            </div>
        </c:if>                   
      </div>                       
    
        <!-- Login -->
        <div class="lc-block toggled" id="l-login">
        
        	<form:form id="loginForm" method="post" action="${rootURL}login" modelAttribute="user" role="form" >
	            
	            <img src="${rootURL}resources/img/logo_tesla.png"/>
	            
	            <br><br>
	            
	            <div class="input-group m-b-20">
	                <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
	                <div class="fg-line">
	                    <input type="text" id="username" name="username" class="form-control" placeholder="Usuário">
	                </div>
	            </div>	            
	            <div class="input-group m-b-20">
	                <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
	                <div class="fg-line">
	                    <input type="password" id="password" name="password" class="form-control" placeholder="Senha">
	                </div>
	            </div>
            	
                <button class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></button>
            
            </form:form>
            
            <ul class="login-navigation">
                <li data-block="#l-forget-password" class="bgm-orange">Esqueceu a Senha?</li>
            </ul>
        </div>
        
        <!-- Forgot Password -->
        <div class="lc-block" id="l-forget-password">
        <form:form id="loginPassword" method="post" action="${rootURL}password" role="form" >
            <p class="text-left">Digite o email do usuário. Uma nova senha será gerada e enviada para seu email.</p>
            
            <div class="input-group m-b-20">
                <span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
                <div class="fg-line">
                    <input type="text" name="email" class="form-control" placeholder="Email Address">
                </div>
            </div>
            
            <button class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></button>
            
            <ul class="login-navigation">
                <li data-block="#l-login" class="bgm-green">Login</li>                
            </ul>
            </form:form>
        </div>
        
        <!-- Older IE warning message -->
        <!--[if lt IE 9]>
            <div class="ie-warning">
                <h1 class="c-white">Warning!!</h1>
                <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
                <div class="iew-container">
                    <ul class="iew-download">
                        <li>
                            <a href="http://www.google.com/chrome/">
                                <img src="img/browsers/chrome.png" alt="">
                                <div>Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.mozilla.org/en-US/firefox/new/">
                                <img src="img/browsers/firefox.png" alt="">
                                <div>Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com">
                                <img src="img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.apple.com/safari/">
                                <img src="img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                                <img src="img/browsers/ie.png" alt="">
                                <div>IE (New)</div>
                            </a>
                        </li>
                    </ul>
                </div>
                <p>Sorry for the inconvenience!</p>
            </div>   
        <![endif]-->
        
        <!-- Javascript Libraries -->
        <script src="${rootURL}resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="${rootURL}resources/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="${rootURL}resources/vendors/bower_components/Waves/dist/waves.min.js"></script>
        <script src="${rootURL}resources/js/functions.js"></script>
        
    </body>
</html>