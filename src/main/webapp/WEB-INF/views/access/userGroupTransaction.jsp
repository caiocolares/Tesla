<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<tags:template title="Tesla - Usuários">
<jsp:attribute name="script">
<script type="text/javascript">
function refreshForm(){
	var userId = $('#user').val();
	var moduleId = $('#moduleId').val();
	var resourceId = $('#resourceId').val();
	window.location = '${pageContext.request.contextPath}/access/user/edit?user=' + userId + '&moduleId=' + moduleId + '&resourceId=' + resourceId;
}

$(function(){
	$('#listGroups').pickList();
	$('#listTransactions').pickList();
});

</script>
</jsp:attribute>
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
            Usuário salvo com sucesso!
        </div>
	</c:if>
	<!-- End Success Message -->
	
	<!-- Start FormUser -->
	<form:form id="formUser" action="${rootURL}/access/user/save" method="post">
	
		<input type="hidden" id="user" name="user" value="${user.id}">
		
		<!-- Start Search User -->
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-account-o"></i> Usuários</h2>
				<div class="actions">
					<button type="button" onclick="href('${rootURL}/access/user/');" class="btn btn-primary btn-sm"><i class="zmdi zmdi-caret-left-circle"></i> Voltar</button>
					<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-save"></i> Salvar</button>										
				</div>
			</div>
			<div class="card-body card-padding">
				<div class="row">
					<div class="col-sm-6">
						<label>Nome</label><br>
						${user.person.name}
					</div>
					<div class="col-sm-6">
						<label>Login</label><br>
						${user.login}
					</div>
				</div>
				 
			</div>
			
			<div class="card-header">
            	<h2>Grupos <small>Selecione os grupos que deverão pertencer a este usuário</small></h2>
            </div>
				
			<div class="card-body card-padding">	
				
				<c:if test="${groups != null}">					
					<div id="listGroups" class="row">				
						<div class="col-sm-5">
				        	<div class="list-group list1">
					          	<a href="javascript:void(0);" class="list-group-item active">
					          		Grupos Disponíveis
					          		<input type="checkbox" class="all pull-right">
					          	</a>					          
					          	<c:forEach items="${groups}" var="g">
					          		<a href="javascript:void(0);" class="list-group-item" data-toggle="tooltip" data-placement="bottom" data-original-title="${t.label}">
					          			${g.name}
					          			<input type="checkbox" class="pull-right">
					          			<input type="hidden" name="_groups[]" value="${g.id}"/>					          								          			
					          		</a>
					          	</c:forEach>
				          </div>
				        </div>
				        <div class="col-sm-2 text-center">			        
				        	<br><button type="button" class="btn bgm-teal btn-icon waves-effect waves-circle waves-float add"><i class="zmdi zmdi-arrow-forward"></i></button><br>
				        	<button type="button" class="btn bgm-teal btn-icon waves-effect waves-circle waves-float remove"><i class="zmdi zmdi-arrow-back"></i></button><br><br>			        	
				        </div>
				        <div class="col-sm-5">
				    		<div class="list-group list2">
				          		<a href="javascript:void(0);" class="list-group-item active">
				          			Grupos Selecionados
				          			<input type="hidden" name="groups" value=""/>
				          			<input title="toggle all" type="checkbox" class="all pull-right">				          			
				          		</a>
				          		<c:forEach items="${groupsSelected}" var="g" varStatus="i">
					          		<a href="javascript:void(0);" class="list-group-item" data-toggle="tooltip" data-placement="bottom" data-original-title="${t.label}">
					          			${g.name}
					          			<input type="checkbox" class="pull-right">
					          			<input type="hidden" name="groups[${i.index}]" value="${g.id}"/>					          			
					          		</a>
					          	</c:forEach>				          		
				          	</div>
				        </div>	
					</div>	
				</c:if>
				
			</div>
			
			<div class="card-header">
            	<h2>Transações <small>Selecione as transações que deverão pertencer a este usuário, filtrando por Módulo e Recurso</small></h2>
            </div>
			
			<div class="card-body card-padding">
				
				<div class="row">
					<div class="col-sm-6">
	                	<div class="form-group fg-line">
		                	<label>Módulo</label>
		                	<c:choose>
								<c:when test="${modules == null}">
									<br><b>...</b>
								</c:when>
								<c:otherwise>
									<select id="moduleId" name="moduleId" onchange="$('#resourceId').val(''); refreshForm();" class="selectpicker">
										<option value="">...</option>
										<c:forEach items="${modules}" var="m">
			                           		<option value="${m.id}" ${m.id == moduleId ? 'selected="selected"' : ''}>${m.name}</option>
			                           	</c:forEach>
			                        </select>
								</c:otherwise>
							</c:choose>
						</div>  
					</div>
					<div class="col-sm-6">
	                	<div class="form-group fg-line">
							<label>Recurso</label>
							<select id="resourceId" name="resourceId" onchange="refreshForm();" class="selectpicker">
								<option value="">...</option>
								<c:forEach items="${resources}" var="r">
		                       		<option value="${r.id}" ${r.id == resourceId ? 'selected="selected"' : ''}>${r.name}</option>
		                       	</c:forEach>
		                    </select>
						</div>  
					</div>
				</div>
				
				<c:if test="${transactions != null}">					
					<div id="listTransactions" class="row">				
						<div class="col-sm-5">
				        	<div class="list-group list1">
					          	<a href="javascript:void(0);" class="list-group-item active">
					          		Transações Disponíveis
					          		<input type="checkbox" class="all pull-right">
					          	</a>					          
					          	<c:forEach items="${transactions}" var="t">
					          		<a href="javascript:void(0);" class="list-group-item">
					          			${t.name}
					          			<input type="checkbox" class="pull-right">
					          			<input type="hidden" name="_userTransactions[]" value="${t.id}"/>					          			
					          		</a>
					          	</c:forEach>
				          </div>
				        </div>
				        <div class="col-sm-2 text-center">			        
				        	<br><button type="button" class="btn bgm-teal btn-icon waves-effect waves-circle waves-float add"><i class="zmdi zmdi-arrow-forward"></i></button><br>
				        	<button type="button" class="btn bgm-teal btn-icon waves-effect waves-circle waves-float remove"><i class="zmdi zmdi-arrow-back"></i></button><br><br>			        	
				        </div>
				        <div class="col-sm-5">
				    		<div class="list-group list2">
				          		<a href="javascript:void(0);" class="list-group-item active">
				          			Transações Selecionadas
				          			<input type="hidden" name="userTransactions" value=""/>
				          			<input title="toggle all" type="checkbox" class="all pull-right">				          			
				          		</a>
				          		<c:forEach items="${transactionsSelected}" var="t" varStatus="i">
					          		<a href="javascript:void(0);" class="list-group-item" ${resourceId == t.moduleResource.id ? '' : 'style="display:none;"'}>
					          			${t.name}
					          			<input type="checkbox" class="pull-right">
					          			<input type="hidden" name="userTransactions[${i.index}]" value="${t.id}"/>					          			
					          		</a>
					          	</c:forEach>				          		
				          	</div>
				        </div>	
					</div>	
				</c:if>
					
			</div>
												
			
		</div>
		<!-- End Search User -->		
		
	</form:form>
	<!-- End FormUser -->
	
</jsp:body>
</tags:template>