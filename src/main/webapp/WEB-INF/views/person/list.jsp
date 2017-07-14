<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<tags:template title="Tesla - Pessoas">
<jsp:attribute name="script">
<script type="text/javascript">
function refreshPersonType(){
	var selected = $('input[name="personType"]:checked').val();
	if(selected == 'FISICA'){
		$('#boxPersonFisica').show();
		$('#boxPersonJuridica').hide();
		$('input[name="cpf"]').val('');
		$('input[name="rg"]').val('');
		$('#labelFantasy').html('Apelido');
	}
	else if(selected == 'JURIDICA'){
		$('#boxPersonJuridica').show();
		$('#boxPersonFisica').hide();
		$('input[name="cnpj"]').val('');
		$('input[name="ie"]').val('');
		$('#labelFantasy').html('Razão Social');
	}
	else{
		$('#boxPersonFisica').show();
		$('#boxPersonJuridica').show();	
		$('#labelFantasy').html('Razão Social');
	}
}
</script>
</jsp:attribute>
<jsp:body>

	<!-- Start Success Message -->
	<c:if test="${success != null}">
		<div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
            Pessoa salva com sucesso!
        </div>
	</c:if>
	<!-- End Success Message -->

	<!-- Start Form -->
	<form:form id="formSearch" method="get">

		<!-- Start Search Person -->
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-account-box"></i> Pessoas</h2>
				<div class="actions">
					<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-search"></i> Pesquisar</button>
					<button type="button" onclick="href('${rootURL}/person/form');" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus-circle"></i> Novo</button>
				</div>
			</div>
			<div class="card-body card-padding">
				<div class="row">
					<div class="col-sm-6">
	                	<div class="form-group fg-line">
	                    	<label>Nome</label>
	                        <input type="text" class="form-control" name="name" value="${name}">
	                    </div>
	                </div>
	                <div class="col-sm-6">
                        <div class="form-group fg-line">
                            <label id="labelFantasy">${personType == 'FISICA' ? 'Apelido' : personType == null ? 'Apelido' : 'Razão Social'}</label>
                            <input type="text" class="form-control" name="fantasy" value="${fantasy}">                                
                        </div>
                    </div>
				</div>
				<div class="row">
					<div class="col-sm-6">
	                	<div class="form-group fg-line">
		             		<label>Tipo:</label>				
							<div class="radio">
		               			<label>
		                   			<input type="radio" name="personType" onclick="refreshPersonType();" value="FISICA" ${personType == 'FISICA' ? 'checked="checked"' : person.personType == null ? 'checked="checked"' : ''}>
		                   			<i class="input-helper"></i>
		                   			Física
		               			</label>
		               			&nbsp;&nbsp;
		               			<label>
		                   			<input type="radio" name="personType" onclick="refreshPersonType();" value="JURIDICA" ${personType == 'JURIDICA' ? 'checked="checked"' : ''}>
		                   			<i class="input-helper"></i>
		                   			Jurídica
		               			</label>
		               			&nbsp;&nbsp;
		               			<label>
		                   			<input type="radio" name="personType" onclick="refreshPersonType();" value="MISTA" ${personType == 'MISTA' ? 'checked="checked"' : ''}>
		                   			<i class="input-helper"></i>
		                   			Mista
		               			</label>
		          			</div>
	          			</div>  
					</div>
					<div class="col-sm-6">
						<label>&nbsp;</label>
						<select name="entitie" class="selectpicker" multiple>
							<c:forEach items="${entities}" var="pe">
								<option value="${pe.key}" ${pe.value.enabled ? 'selected' : ''}>${pe.value.name}</option>		
							</c:forEach>	
						</select>	                   
					</div>					
				</div>
				<div class="row">
	                <div id="boxPersonFisica" ${personType == 'MISTA' ? '' : personType == 'FISICA' ? '' : personType == null ? '' : 'style="display:none;"'}>
		            	<div class="col-sm-6">
		                	<div class="form-group fg-line">
		                    	<label>CPF</label>
		                        <input type="text" value="${cpf}" name="cpf" class="form-control input-mask" data-mask="000.000.000-00" placeholder="000.000.000-00">
							</div>  
						</div>
		                <div class="col-sm-6">
		                	<div class="form-group fg-line">
		                    	<label>RG</label>
		                        <input type="text" value="${rg}" name="rg" data-mask="00000000000000000000" class="form-control">
							</div>
						</div>
					</div>
	                <div id="boxPersonJuridica" ${personType == 'MISTA' ? '' : personType == 'JURIDICA' ? '' : 'style="display:none;"'}>
		            	<div class="col-sm-6">
							<div class="form-group fg-line">
								<label>CNPJ</label>
								<input type="text" value="${cnpj}" name="cnpj" class="form-control input-mask" data-mask="00.000.000/0000-00" placeholder="00.000.000/0000-00">
							</div>  
						</div>                                
						<div class="col-sm-6">
		                	<div class="form-group fg-line">
		                    	<label>IE</label>
		                        <input type="text" value="${ie}" name="ie" data-mask="00000000000000000000" class="form-control">
		                    </div>
						</div>
					</div>
				</div>
				<input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}">				
			</div>
		</div>
		<!-- End Search Person -->
		
		<!-- Start List Person -->
		<c:choose>
			<c:when test="${list == null}">
				<div class="alert alert-info alert-dismissible" role="alert">
		            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
		            Preencha o formulário para pesquisa!
		        </div>		
			</c:when>
			<c:when test="${list.size() == 0}">
				<div class="alert alert-warning alert-dismissible" role="alert">
		            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
		            Nenhum registro encontrado!
		        </div>
			</c:when>
			<c:otherwise>
				<div class="card">
					<div class="table-responsive">		
						<table class="table table-striped table-vmiddle">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Fantasia</th>
									<th>Tipo</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="person">
									<tr>
										<td>${person.name}</td>
										<td>${person.fantasy}</td>
										<td>${person.personType}</td>
										<td>
											<button type="button" onclick="href('${rootURL}/person/form/${person.id}')" class="btn btn-info waves-effect"><i class="zmdi zmdi-edit"></i></button>											
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>			
					<div id="data-table-basic-footer" class="bootgrid-footer container-fluid">
						<div class="row">
							<div class="col-sm-7">
								<ul class="pagination">
									<c:choose>
										<c:when test="${pageNumber == 0}">
											<li class="first disabled">
												<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-more-horiz"></i></a>
											</li>
											<li class="prev disabled">
												<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-chevron-left"></i></a>
											</li>
									    </c:when>
										<c:otherwise>
									    	<li class="first">
												<a href="javascript:hrefPageNumber(0);" class="button"><i class="zmdi zmdi-more-horiz"></i></a>
											</li>
											<li class="prev">
												<a href="javascript:hrefPageNumber(${pageNumber - 1});" class="button"><i class="zmdi zmdi-chevron-left"></i></a>
											</li>											
									    </c:otherwise>
									</c:choose>
									<c:forEach begin="${firstPage}" end="${lastPage}" var="p">
										<li ${pageNumber == p ? 'class="active"' : ''}>
											<a href="javascript:hrefPageNumber(${p});" class="button">${p+1}</a>
										</li>
									</c:forEach>
									<c:choose>
										<c:when test="${pageNumber == lastPage}">
											<li class="next disabled">
												<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-chevron-right"></i></a>
											</li>
											<li class="last disabled">
												<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-more-horiz"></i></a>
											</li>
									    </c:when>
									    <c:otherwise>
									    	<li class="next">
												<a href="javascript:hrefPageNumber(${pageNumber + 1});" class="button"><i class="zmdi zmdi-chevron-right"></i></a>
											</li>
									    	<li class="last">
												<a href="javascript:hrefPageNumber(${lastPage});" class="button"><i class="zmdi zmdi-more-horiz"></i></a>
											</li>										
									    </c:otherwise>
									</c:choose>
								</ul>
							</div>
							<div class="col-sm-5 infoBar">
								<div class="infos">Mostrando ${betweenStart} a ${betweenEnd} de ${total} registros</div>
							</div>
						</div>
					</div>
					<br>			
				</div>
			</c:otherwise>
		</c:choose>		
		<!-- End List Person -->
		
	</form:form>
	<!-- End Form -->
	
</jsp:body>
</tags:template>