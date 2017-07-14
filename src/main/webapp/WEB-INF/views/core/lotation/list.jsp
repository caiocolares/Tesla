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
	<c:if test="${person != null}">
		person = {	id: 		"${person.id}",
					name:		"${person.name}",
					fantasy: 	"${person.fantasy}",
					personType:	"${person.personType}",
					cpf:		"${person.cpf}",
					rg:			"${person.rg}",
					cnpj:		"${person.cnpj}",
					ie:			"${person.ie}"				
		};
	</c:if>
	$('#myPerson').personSearch({
		urlAjax: '${pageContext.request.contextPath}/person/search.json',
		nameInput: 'personId',
		person: person		
	});
</script>
</jsp:attribute>
<jsp:body>
	
	<!-- Start Success Message -->
	<c:if test="${success != null}">
		<div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
            Lotação salva com sucesso!
        </div>
	</c:if>
	<!-- End Success Message -->

	<!-- Start Form -->
	<form:form id="formSearch" method="get">

		<!-- Start Search -->
		<div class="card">
			<div class="card-header">
				<h2><i class="zmdi zmdi-puzzle-piece"></i> Lotação</h2>
				<div class="actions">
					<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-search"></i> Pesquisar</button>
					<button type="button" onclick="href('${rootURL}/core/lotation/form');" class="btn btn-primary btn-sm"><i class="zmdi zmdi-plus-circle"></i> Novo</button>
				</div>
			</div>
			<div class="card-body card-padding">
				<div class="row">
					<div class="col-sm-6">
	                	<div class="form-group fg-line">
	                    	<label>Setor</label>
	                        <select name="sectorId" class="selectpicker">
								<option value="">...</option>
								<c:forEach items="${sectors}" var="s">
									<option value="${s.id}" ${s.id == sectorId ? 'selected="selected"' : ''}>${s.name}</option>	
								</c:forEach>							
	                        </select>
	                    </div>
	                </div>
					<div class="col-sm-6">
	                	<div class="form-group fg-line">
	                    	<label>Cargo</label>
	                        <select name="roleId" class="selectpicker">
								<option value="">...</option>
								<c:forEach items="${roles}" var="r">
									<option value="${r.id}" ${r.id == roleId ? 'selected="selected"' : ''}>${r.name}</option>	
								</c:forEach>							
	                        </select>
	                    </div>
	                </div>          
				</div>				
				<div id="myPerson" class="card-header ch-alt m-b-20" style="padding: 13px 13px;">
					<input type="hidden" name="personId">
				</div>
                <input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}">				
			</div>
		</div>
		<!-- End Search -->
		
		<!-- Start List -->
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
									<th>Setor</th>
									<th>Cargo</th>
									<th>Pessoa</th>
									<th>Início</th>
									<th>Término</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="l">
									<tr>
										<td>${l.sector.name}</td>
										<td>${l.role.name}</td>
										<td>${l.person.name}</td>
										<td><fmt:formatDate value="${l.startLotation}" pattern="dd/MM/yyyy"/></td>
										<td><fmt:formatDate value="${l.endLotation}" pattern="dd/MM/yyyy"/></td>
										<td>
											<button type="button" onclick="href('${rootURL}/core/lotation/form?lotation=${l.id}')" class="btn btn-info waves-effect"><i class="zmdi zmdi-edit"></i></button>											
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
		<!-- End List -->
		
	</form:form>
	<!-- End Form -->
	
</jsp:body>
</tags:template>