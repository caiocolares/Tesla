<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<tags:template title="Tesla - Lotação">
<jsp:attribute name="script">
<script type="text/javascript">
	var person = null;
	<c:if test="${lotation.person != null}">
		person = {	id: 		"${lotation.person.id}",
					name:		"${lotation.person.name}",
					fantasy: 	"${lotation.person.fantasy}",
					personType:	"${lotation.person.personType}",
					cpf:		"${lotation.person.cpf}",
					rg:			"${lotation.person.rg}",
					cnpj:		"${lotation.person.cnpj}",
					ie:			"${lotation.person.ie}"				
		};
	</c:if>
	$('#myPerson').personSearch({
		urlAjax: '${pageContext.request.contextPath}/person/search.json',
		nameInput: 'person',
		person: person,
		removePerson: false
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

	<!-- Start Form -->
	<form:form action="${rootURL}/core/lotation/form" method="post">
	
		<input type="hidden" name="lotation" value="${lotation.id}">
	
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-puzzle-piece"></i> Lotação</h2>
				<div class="actions">
					<button type="button" onclick="href('${rootURL}/core/lotation');" class="btn btn-primary btn-sm"><i class="zmdi zmdi-caret-left-circle"></i> Voltar</button>
					<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-save"></i> Salvar</button>										
				</div>
			</div>			
			<div class="card-body card-padding">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group fg-line">
							<label>Setor</label>
							<select name="sector" class="selectpicker">
								<c:forEach items="${sectors}" var="s">
									<option value="${s.id}" ${s.id == lotation.sector.id ? 'selected="selected"' : ''}>${s.name}</option>	
								</c:forEach>						
		                    </select>							
                        </div>
					</div>	
					<div class="col-sm-6">
						<div class="form-group fg-line">
							<label>Cargo</label>
							<select name="role" class="selectpicker">
								<c:forEach items="${roles}" var="r">
									<option value="${r.id}" ${r.id == lotation.role.id ? 'selected="selected"' : ''}>${r.name}</option>	
								</c:forEach>						
		                    </select>							
                        </div>
					</div>	
				</div>
				<div class="row">
					<div class="col-sm-3">
                           <div class="form-group fg-line">
                               <label>Início da Lotação</label>
                               <div class="input-group form-group">
                                <span class="input-group-addon"><i class="zmdi zmdi-calendar"></i></span>
                                <div class="dtp-container fg-line">                                    
                                    <input type="text" class="form-control date-picker" name="startLotation" value="<fmt:formatDate value="${lotation.startLotation}" pattern="dd/MM/yyyy"/>" placeholder="00/00/0000">
                                </div>
                            </div>
                           </div>  
                      	</div> 	
					<div class="col-sm-3">
						<div class="form-group fg-line">
                        	<label>Término da Lotação</label>
                            <div class="input-group form-group">
                            	<span class="input-group-addon"><i class="zmdi zmdi-calendar"></i></span>
                               	<div class="dtp-container fg-line">                                    
                                	<input type="text" class="form-control date-picker" name="endLotation" value="<fmt:formatDate value="${lotation.endLotation}" pattern="dd/MM/yyyy"/>" placeholder="00/00/0000">
                               	</div>
                           	</div>
                        </div>  
					</div>						
				</div>
				<div id="myPerson" class="card-header ch-alt m-b-20" style="padding: 13px 13px;">
					<input type="hidden" name="person">
				</div>				
			</div>
				
		</div>				
		
	</form:form>
	<!-- End Form -->

</jsp:body>
</tags:template>