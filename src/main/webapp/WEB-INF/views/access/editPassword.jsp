<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<tags:template title="Tesla - Alterar Senha">
<jsp:body>

	<!-- Start Error Message -->
	<c:if test="${error != null }">
		<div class="alert alert-danger alert-dismissible" role="alert">
	    	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        ${error}
		</div>
	</c:if>
	<!-- End Error Message -->
	
	<!-- Start Success Message -->
	<c:if test="${success != null}">
		<div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
            Senha alterada com sucesso!
        </div>
	</c:if>
	<!-- End Success Message -->
	
	<!-- Start form -->
	<form:form method="post">
	
		<!-- Start Card -->
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-lock"></i> Alterar Senha</h2>
				<div class="actions">
					<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-save"></i> Salvar</button>				
				</div>
			</div>
			<div class="card-body card-padding">
				<div class="row">
					<div class="col-sm-offset-3 col-sm-6">					
						<div class="col-sm-12">
		                	<div class="form-group fg-line">
		                    	<label>Senha Antiga</label>
		                    	<input type="password" name="password_old" class="form-control" maxlength="20">
		                    </div>
		                </div>		                
		                <div class="col-sm-12">
		                	<div class="form-group fg-line">
		                    	<label>Nova Senha</label>
		                    	<input type="password" name="password_new" class="form-control" maxlength="20">
		                    </div>
		                </div>		                
		                <div class="col-sm-12">
		                	<div class="form-group fg-line">
		                    	<label>Repetir Nova Senha</label>
		                    	<input type="password" name="password_new_repeat" class="form-control" maxlength="20">
		                    </div>
		                </div>					
					</div>					
				</div>
			</div>
		</div>
		<!-- End Card -->	
	
	</form:form>
	<!-- End form -->
	
</jsp:body>
</tags:template>