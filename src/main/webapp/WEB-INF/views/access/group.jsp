<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<tags:template title="Grupos de Acesso">
<jsp:attribute name="script">
<script type="text/javascript">
function saveGroup(){
	if($('#formGroupTransaction')[0])
		$('#formGroupTransaction').submit();
	else
		notify('Indique corretamente as trasações de um grupo!');		
}
function editGroup(){
	if($('#formGroupTransaction')[0]){
		var group_name = $('#groupId option:selected').text();
		$('#group_name_modal').val(group_name);
		$('#editGroupModal').modal('show');	
	}	
}
function editGroupOk(){
	var group_name = $('#group_name_modal').val();
	$('#groupId option:selected').text(group_name);
	$('#group_name').val(group_name);
	$('#groupId').selectpicker('refresh');
	$('#editGroupModal').modal('hide');
}
$(function(){
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
            Grupo salvo com sucesso!
        </div>
	</c:if>
	<!-- End Success Message -->

	<!-- Start Card -->
	<div class="card">
		<div class="card-header">
			<h2><i class="zmdi zmdi-group-work"></i> Grupos <small>Selecione as transações que deverão pertencer ao Grupo, filtrando por Grupo, Módulo e Recurso</small></h2>
			<div class="actions">
				<button type="button" onclick="saveGroup();" formmethod="post" class="btn btn-primary btn-sm"><i class="zmdi zmdi-save"></i> Salvar</button>
				<button type="button" data-toggle="modal" data-target="#addGroupModal" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus-circle"></i> Novo Grupo</button>
			</div>
		</div>
		<div class="card-body card-padding">
		
			<!-- Start formSearchGroup -->
			<form:form id="formSearchGroup" method="get">
		
				<div class="row">
					<div class="col-sm-4">
	                	<div class="form-group fg-line">
	                    	<label>Grupo</label><br>
	                    	<div class="col-xs-10" style="padding-left:0px;">
		                    	<select id="groupId" name="groupId" onchange="$('#moduleId').val(''); $('#resourceId').val(''); this.form.submit();" class="selectpicker">
									<option value="">...</option>
		                           	<c:forEach items="${groups}" var="g">
		                           		<option value="${g.id}" ${g.id == groupId ? 'selected="selected"' : ''}>${g.name}</option>
		                           	</c:forEach>	                           	
		                        </select>	                        
	                        </div>
	                        <div class="col-xs-2">
	                        	<button type="button" onclick="editGroup();" class="btn btn-default waves-effect pull-right ${transactions != null ? '' : 'disabled'}"><i class="zmdi zmdi-edit"></i></button>	                        	
	                        </div>
	                    </div>
	                </div>
	        		<div class="col-sm-4">
	                	<div class="form-group fg-line">
		                	<label>Módulo</label>
		                	<c:choose>
								<c:when test="${modules == null}">
									<br><b>...</b>
								</c:when>
								<c:otherwise>
									<select id="moduleId" name="moduleId" onchange="$('#resourceId').val(''); this.form.submit();" class="selectpicker">
										<option value="">...</option>
										<c:forEach items="${modules}" var="m">
			                           		<option value="${m.id}" ${m.id == moduleId ? 'selected="selected"' : ''}>${m.name}</option>
			                           	</c:forEach>
			                        </select>
								</c:otherwise>
							</c:choose>
						</div>  
					</div>
					<div class="col-sm-4">
	                	<div class="form-group fg-line">
							<label>Recurso</label>
							<c:choose>
								<c:when test="${resources == null}">
									<br><b>...</b>
								</c:when>
								<c:otherwise>
									<select id="resourceId" name="resourceId" onchange="this.form.submit();" class="selectpicker">
										<option value="">...</option>
										<c:forEach items="${resources}" var="r">
				                       		<option value="${r.id}" ${r.id == resourceId ? 'selected="selected"' : ''}>${r.name}</option>
				                       	</c:forEach>
				                    </select>
								</c:otherwise>
							</c:choose>							           
						</div>  
					</div>
				</div>	
			
			</form:form>
			<!-- End formSearchGroup -->
						
			<c:if test="${transactions != null}">
				
				<!-- Start formGroupTransaction -->
				<form:form id="formGroupTransaction" method="post" action="${rootURL}/access/group/save" modelAttribute="group">
				
					<input type="hidden" name="id" value="${groupSelected.id}"/>
					<input type="hidden" id="group_name" name="name" value="${groupSelected.name}"/>
					<input type="hidden" name="groupId" value="${groupId}"/>
					<input type="hidden" name="moduleId" value="${moduleId}"/>
					<input type="hidden" name="resourceId" value="${resourceId}"/>					
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
					          			<input type="hidden" name="_transactions[]" value="${t.id}"/>					          			
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
				          			<input type="hidden" name="transactions" value=""/>
				          			<input title="toggle all" type="checkbox" class="all pull-right">
				          		</a>
				          		<c:forEach items="${transactionsSelected}" var="t" varStatus="i">
					          		<a href="javascript:void(0);" class="list-group-item" ${resourceId == t.moduleResource.id ? '' : 'style="display:none;"'}>
					          			${t.name}
					          			<input type="checkbox" class="pull-right">
					          			<input type="hidden" name="transactions[${i.index}]" value="${t.id}"/>					          			
					          		</a>
					          	</c:forEach>				          		
				          	</div>
				        </div>	
					</div>
				
				</form:form>
				<!-- End formGroupTransaction -->
					
			</c:if>			
		</div>
	</div>
	<!-- End Card -->		
		
		
	<!-- Start addGroupModal -->
	<div id="addGroupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		
		<!-- Start formGroup -->
		<form:form id="formGroup" method="post" action="${rootURL}/access/group/save" modelAttribute="group">

			<div class="modal-dialog">
				<div class="modal-content clearfix">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>        
						<h4 class="modal-title">Novo Grupo</h4>
					</div>	
					<div class="modal-body">  		
						<div class="row">
							<div class="col-sm-12">
	                        	<div class="fg-line">
	                            	<label>Nome</label>
	                                <input type="text" name="name" class="form-control input-sm" maxlength="50">
								</div>
							</div>						
						</div>
					</div>
					<div class="modal-footer">
						<a href="javascript:void(0);" class="btn btn-small btn-default" data-dismiss="modal">Cancelar</a>
						<button type="submit" class="btn btn-primary">OK</button>
					</div>
				</div>
			</div>	
		
		</form:form>
		<!-- End formGroup -->
		
	</div>
	<!-- End addGroupModal -->
	
	<!-- Start editGroupModal -->
	<div id="editGroupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content clearfix">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>        
					<h4 class="modal-title">Editar Grupo</h4>
				</div>	
				<div class="modal-body">  		
					<div class="row">
						<div class="col-sm-12">
                        	<div class="fg-line">
                            	<label>Nome</label>
                                <input type="text" id="group_name_modal" class="form-control input-sm" maxlength="50">
							</div>
						</div>						
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:void(0);" class="btn btn-small btn-default" data-dismiss="modal">Cancelar</a>
					<button type="button" onclick="editGroupOk();" class="btn btn-primary">OK</button>
				</div>
			</div>
		</div>		
	</div>
	<!-- End editGroupModal -->

</jsp:body>
</tags:template>