<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<tags:template title="Tesla - Setores">
<jsp:body>

	<!-- Start Error Message -->
	<c:if test="${error != null }">
		<div class="alert alert-danger alert-dismissible" role="alert">
	    	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        ${error}
		</div>
	</c:if>
	<!-- End Error Message -->

	<!-- Start Form -->
	<form:form action="${rootURL}/core/sector/form" method="post">
	
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-assignment"></i> Setores</h2>
				<div class="actions">
					<button type="button" onclick="href('${rootURL}/core/sector');" class="btn btn-primary btn-sm"><i class="zmdi zmdi-caret-left-circle"></i> Voltar</button>
					<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-save"></i> Salvar</button>										
				</div>
			</div>
			<c:choose>
				<c:when test="${edit}">	
					<input type="hidden" name="sector" value="${sector.id}">
					<div class="card-body card-padding">
						<div class="row">
							<div class="col-sm-4">
								<div class="form-group fg-line">
									<label>Empresa</label>
									<input type="text" value="${sector.workgroup.person.name}" class="form-control" readonly="readonly">																
		                        </div>
							</div>	
							<div class="col-sm-4">
								<div class="form-group fg-line">
									<label>Vinculado ao Setor</label>
									<input type="text" value="${sector.headOffice.name}" class="form-control"  readonly="readonly">
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group fg-line">
									<label>Descrição</label>
									<input type="text" name="name" value="${sector.name}" class="form-control" placeholder="Nome do Setor">
								</div>
							</div>
						</div>								
					</div>					
				</c:when>
				<c:otherwise>
					<div class="card-body card-padding">
						<div class="row">
							<div class="col-sm-4">
								<div class="form-group fg-line">
									<label>Empresa</label>
									<select name="workgroup" class="selectpicker">
										<c:forEach items="${workgroups}" var="wg">
											<option value="${wg.id}">${wg.person.name}</option>	
										</c:forEach>						
				                    </select>							
		                        </div>
							</div>	
							<div class="col-sm-4">
								<div class="form-group fg-line">
									<label>Vinculado ao Setor</label>
									<select name=headOffice class="selectpicker">
										<option value="">...</option>
										<c:forEach items="${sectors}" var="s">
											<option value="${s.id}">${s.name}</option>	
										</c:forEach>						
				                    </select>							
		                        </div>
							</div>	
							<div class="col-sm-4">
								<div class="form-group fg-line">
									<label>Descrição</label>
									<input type="text" name="name" class="form-control" placeholder="Nome do Setor">
								</div>
							</div>								
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>				
		
	</form:form>
	<!-- End Form -->

</jsp:body>
</tags:template>