<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<tags:template title="Tesla">
	
	<jsp:attribute name="script">	
		<security:authentication property="principal" var="user"/>		
	</jsp:attribute>
	
	<jsp:body>
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-info m-r-5"></i> Seja bem vindo!</h2>
			</div>
		</div>
	</div>
	</jsp:body>
	
</tags:template>