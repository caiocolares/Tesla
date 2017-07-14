<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<tags:template title="Tesla - Pessoas">
<jsp:attribute name="script">
<script	src="${rootURL}/resources/vendors/bower_components/webcam/webcam.min.js"></script>
<script type="text/javascript">

var _DB_PERSON_ADDRESS = [];
var _DB_PERSON_PHONES = [];

$(function(){
	$('#personAddress').listAndModal({
		listDB: _DB_PERSON_ADDRESS,
		prefix: 'address',
		lineTable: function(obj){
			return '<tr id="line_personAddress_' + obj.index + '">' +
				'<td class="text-left">' + (Number(obj.index) + 1) + '</td>' +
				'<td class="text-left">' + obj.zipCode + '</td>' +
				'<td class="text-left">' + obj.addressType + '</td>' +
				'<td class="text-left">' + obj.streetType + ' ' + obj.patio + ' Nº ' + obj.number + '</td>' +
				'<td class="text-left">' + obj['city.name'] + '</td>' +
				'<td class="text-left">' + obj.uf + '</td>' +
				'<td class="text-left">' +
					'<input type="hidden" name="address[' + obj.index + '].index" value="' + obj.index + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].id" value="' + obj.id + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].zipCode" value="' + obj.zipCode + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].streetType" value="' + obj.streetType + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].addressType" value="' + obj.addressType + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].patio" value="' + obj.patio + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].number" value="' + obj.number + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].complement" value="' + obj.complement + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].district" value="' + obj.district + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].reference" value="' + obj.reference + '"/>' +
					'<input type=hidden name="address[' + obj.index + '].city.id" value="' + obj['city.id'] + '"/>' +
					'<input type=hidden name="address[' + obj.index + '].city.name" value="' + obj['city.name'] + '"/>' +
					'<input type="hidden" name="address[' + obj.index + '].uf" value="' + obj.uf + '"/>' +
					'<button type="button" data-row-index="' + obj.index + '" class="btn btn-icon command-edit waves-effect waves-circle"><span class="zmdi zmdi-edit"></span></button>' +
					'<button type="button" data-row-index="' + obj.index + '" class="btn btn-icon command-delete waves-effect waves-circle"><span class="zmdi zmdi-delete"></span></button>' +	
				'</td>' +
			'</tr>';				
		},
		prepareEdit: function(obj){
			refreshListCity(obj['city.id'], 'address_uf', 'boxAddressListCity', 'address_city.id');
		},
		prepareSave: function(){
			$('#address_city\\.name').val($('#address_city\\.id option:selected').text());
		}
	});
	$('#personContact').listAndModal({
		listDB: _DB_PERSON_PHONES,
		prefix: 'phones',
		lineTable: function(obj){
			return '<tr id="line_personContact_' + obj.index + '">' +
				'<td class="text-left">' + (Number(obj.index) + 1) + '</td>' +
				'<td class="text-left">' + obj.phoneType + '</td>' +
				'<td class="text-left">' + obj.ddd + '</td>' +
				'<td class="text-left">' + obj.number + '</td>' +
				'<td class="text-left">' + obj.company + '</td>' +
				'<td class="text-left">' +
					'<input type="hidden" name="phones[' + obj.index + '].index" value="' + obj.index + '"/>' +
					'<input type="hidden" name="phones[' + obj.index + '].id" value="' + obj.id + '"/>' +
					'<input type="hidden" name="phones[' + obj.index + '].phoneType" value="' + obj.phoneType + '"/>' +
					'<input type="hidden" name="phones[' + obj.index + '].ddd" value="' + obj.ddd + '"/>' +
					'<input type="hidden" name="phones[' + obj.index + '].number" value="' + obj.number + '"/>' +
					'<input type="hidden" name="phones[' + obj.index + '].company" value="' + obj.company + '"/>' +
					'<button type="button" data-row-index="' + obj.index + '" class="btn btn-icon command-edit waves-effect waves-circle"><span class="zmdi zmdi-edit"></span></button>' +
					'<button type="button" data-row-index="' + obj.index + '" class="btn btn-icon command-delete waves-effect waves-circle"><span class="zmdi zmdi-delete"></span></button>' +	
				'</td>' +
			'</tr>';				
		}
	});	
});

function refreshListCity(cityId, cityUFId, cityRenderId, selectIdName){
	var UF = $('#' + cityUFId).val();
	$.ajax({
		url: '${pageContext.request.contextPath}/city/' + UF + '.json',
		type: "GET",
		dataType: "json",
		success: function(data){
			var str = '<select id="' + selectIdName + '" name="' + selectIdName + '" class="selectpicker">';
			for(var i=0; i<data.length; i++){
		    	str += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
		    }
			str += '</select>';
			$('#' + cityRenderId).html(str);
			$('#' + cityRenderId + ' .selectpicker').selectpicker();
			if(cityId.length == 7)
				$('#' + cityRenderId + ' .selectpicker').selectpicker('val', cityId);
		},
		error: function(){
			console.log('error get cities');
		}
	});	
}

function refreshTabs(compCheckbox){
	var name = $(compCheckbox).attr('name');
	var selected = $(compCheckbox).is(':checked');
	if(name == 'customer.enabled'){
		if(selected){
			$('#tab_customer').show();
			$('a[aria-controls="tabCustomer"]').tab('show');
		}
		else{
			$('#tab_customer').hide();
		}
	}
	else if(name == 'provider.enabled'){
		if(selected){
			$('#tab_provider').show();
			$('a[aria-controls="tabProvider"]').tab('show');
		}
		else{
			$('#tab_provider').hide();
		}
	}
	else if(name == 'employee.enabled'){
		if(selected){
			$('#tab_employee').show();
			$('a[aria-controls="tabEmployee"]').tab('show');
		}
		else{
			$('#tab_employee').hide();
		}
	}
	else if(name == 'broker.enabled'){
		if(selected){
			$('#tab_broker').show();
			$('a[aria-controls="tabBroker"]').tab('show');			
		}
		else{
			$('#tab_broker').hide();
		}
	}
	else if(name == 'workgroup.enabled'){
		if(selected){
			$('#tab_workgroup').show();
			$('a[aria-controls="tabWorkGroup"]').tab('show');			
		}
		else{
			$('#tab_workgroup').hide();
		}
	}
	else if(name == 'user.enabled'){
		if(selected){
			$('#tab_user').show();
			$('a[aria-controls="tabUser"]').tab('show');			
		}
		else{
			$('#tab_user').hide();
		}
	}
	if(!selected)
		$('a[aria-controls="tabAddress"]').tab('show');
}

function refreshPersonType(){
	var selected = $('input[name="personType"]:checked').val();
	if(selected == 'FISICA'){
		$('#boxPersonFisica').show();
		$('#boxPersonJuridica').hide();
		$('#labelFantasy').html('Apelido');
	}
	else if(selected == 'JURIDICA'){
		$('#boxPersonJuridica').show();
		$('#boxPersonFisica').hide();
		$('#labelFantasy').html('Razão Social');
	}
	else{
		$('#boxPersonFisica').show();
		$('#boxPersonJuridica').show();
		$('#labelFantasy').html('Razão Social');
	}
}

$('#webcamModal').on('shown.bs.modal', function (event) {
	Webcam.set({
		width: 320,
		height: 240,
		image_format: 'jpeg',
		jpeg_quality: 90
	});	
	Webcam.attach( '#cameraModal' );	
});

function refreshWorkGroupType(){
	var value = $('select[name="workgroup.workgroupType"]').val();
	if(value == 'SUBSIDIARY'){
		$('#boxWorkGroupMaster').show();
	}
	else{
		$('#boxWorkGroupMaster').hide();
	}
}

function take_snapshot() {
	Webcam.snap( function(data_uri) {
		$('#user_photo').html('<input type="hidden" name="photo" value="'+data_uri+'"/><img src="'+data_uri+'" class="img-responsive"/>');
		$('#webcamModal').modal('hide');
	});
}

function take_snapshot_file() {
	var data_uri = $('#fileSelectModal .fileinput-preview img').attr('src');
	if(data_uri != undefined)
		$('#user_photo').html('<input type="hidden" name="photo" value="'+data_uri+'"/><img src="'+data_uri+'" class="img-responsive"/>');
	
	$('#fileModal').modal('hide');	
}

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
	<c:if test="${errors != null }">
		<c:forEach items="${errors}" var="error">
		<div class="alert alert-danger alert-dismissible" role="alert">
	    	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        ${error.defaultMessage}
		</div>
		</c:forEach>
	</c:if>
	<!-- End Error Message -->
	
	<!-- Start Form Person -->
	<form:form modelAttribute="person" method="POST" action="${rootURL}/person/save">			

		<div class="card" id="profile-main">
		
			<!-- Start Person Left -->
			<div class="pm-overview c-overflow">
				<div class="pmo-pic">
					<div class="p-relative">
						<div id="user_photo">						
							<c:choose>
							    <c:when test="${person.photo == null }">
							        <img class="img-responsive" src="${rootURL}/resources/img/profile.png">
							    </c:when>    
							    <c:otherwise>
							        <input type="hidden" name="photo" value="${person.photo}"/>
							        <img class="img-responsive" src="${person.photo}">							        
							    </c:otherwise>
							</c:choose>	
						</div>
						<!-- <a href="javascript:void(0);"  class="pmop-edit">
							<i class="zmdi zmdi-camera"></i>
							<span class="hidden-xs">Alterar Foto</span>
						</a> -->
						<div class="text-center" style="padding-top:10px;">
							<button type="button" data-toggle="modal" data-target="#fileModal" class="btn btn-primary btn-sm"><i class="zmdi zmdi-image"></i> Arquivo...</button>
							<button type="button" data-toggle="modal" data-target="#webcamModal" class="btn btn-primary btn-sm"><i class="zmdi zmdi-camera"></i> Webcam...</button>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group fg-line">
	                	<label>Tipo</label>				
						<div class="radio">
			                <label>
			                    <input type="radio" name="personType" onclick="refreshPersonType();" value="FISICA" ${person.personType == 'FISICA' ? 'checked="checked"' : person.personType == null ? 'checked="checked"' : ''}>
			                    <i class="input-helper"></i>
			                    Física
			                </label>
			                &nbsp;&nbsp;
			                <label>
			                    <input type="radio" name="personType" onclick="refreshPersonType();" value="JURIDICA" ${person.personType == 'JURIDICA' ? 'checked="checked"' : ''}>
			                    <i class="input-helper"></i>
			                    Jurídica
			                </label>
			                &nbsp;&nbsp;
			                <label>
			                    <input type="radio" name="personType" onclick="refreshPersonType();" value="MISTA" ${person.personType == 'MISTA' ? 'checked="checked"' : ''}>
			                    <i class="input-helper"></i>
			                    Mista
			                </label>
		            	</div>
		            </div>
				</div>
			</div>
			<!-- End Person Left -->
			
			<!-- Start Person Right -->
			<div class="pm-body clearfix">
				<div class="pmb-block">
					<div class="pmbb-header">
						<h2><i class="zmdi zmdi-account-box"></i> Pessoa</h2>
						<div class="actions">
							<button type="button" onclick="href('${rootURL}/person/');" class="btn btn-primary btn-sm"><i class="zmdi zmdi-caret-left-circle"></i> Voltar</button>
							<button type="submit" class="btn btn-primary btn-sm"><i class="zmdi zmdi-save"></i> Salvar</button>																	
						</div>
					</div>							
					<div class="row">
						<div class="col-sm-8">
                            <div class="form-group fg-line">
                                <label>Nome</label>
                                <input type="text" class="form-control" name="name" value="${person.name}">
                                <input type="hidden" name="id" value="${person.id}">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group fg-line">
                                <label>Gênero</label>                                    
	                            <select name="gender" class="selectpicker">
	                                <option value="MASCULINO" ${person.gender == 'MASCULINO' ? 'selected="selected"' : ''}>Masculino</option>
	                                <option value="FEMININO" ${person.gender == 'FEMININO' ? 'selected="selected"' : ''}>Feminino</option>
	                            </select>
                            </div>  
                        </div>
					</div>
					<div class="row">
						<div class="col-sm-8">
                            <div class="form-group fg-line">
                                <label id="labelFantasy">${person.personType == 'FISICA' ? 'Apelido' : person.personType == null ? 'Apelido' : 'Razão Social'}</label>
                                <input type="text" class="form-control" name="fantasy" value="${person.fantasy}">                                
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group fg-line">
                                <label>Nascimento / Fundação</label>
                                <div class="input-group form-group">
	                                <span class="input-group-addon"><i class="zmdi zmdi-calendar"></i></span>
	                                <div class="dtp-container fg-line">                                    
	                                    <input type="text" value="<fmt:formatDate value="${person.birth}" pattern="dd/MM/yyyy"/>" class="form-control date-picker" name="birth" placeholder="00/00/0000">
	                                </div>
	                            </div>
                            </div>  
                        </div>                        
					</div>
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group fg-line">
								<label>Município</label>
								<select id="city_uf" onchange="refreshListCity(0, 'city_uf', 'boxPersonCity', 'city');" class="selectpicker">
		                           	<c:forEach items="${listUF}" var="uf">
		                           		<option value="${uf}" ${uf == defaultUF ? 'selected="selected"' : ''}>${uf}</option>
		                           	</c:forEach>
		                        </select>
	                        </div>
						</div>
						<div class="col-sm-9">
							<div class="form-group fg-line">
								<label>&nbsp;</label>
								<div id="boxPersonCity">
									<select id="city" name="city" class="selectpicker">
										<c:forEach items="${listCity}" var="city">
			                           		<option value="${city.id}" ${city == person.city ? 'selected="selected"' : ''}>${city.name}</option>		                           		
			                           	</c:forEach>
			                        </select>
		                        </div>
	                        </div>
						</div>
					</div>
					<div class="row">
						<div id="boxPersonFisica" ${person.personType == 'MISTA' ? '' : person.personType == 'FISICA' ? '' : person.personType == null ? '' : 'style="display:none;"'}>
	                        <div class="col-sm-4">
	                            <div class="form-group fg-line">
	                                <label>CPF</label>
	                                <input type="text" value="${person.cpf}" name="cpf" class="form-control input-mask" data-mask="000.000.000-00" placeholder="000.000.000-00">
	                            </div>  
	                        </div>
	                        <div class="col-sm-4">
	                            <div class="form-group fg-line">
	                                <label>RG</label>
	                                <input type="text" value="${person.rg}" name="rg" data-mask="00000000000000000000" class="form-control">
	                            </div>
	                        </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group fg-line">
                                <label>Email</label>
                                <input type="email" value="${person.email}" name="email" class="form-control">
                            </div>
                        </div>
					    <div id="boxPersonJuridica" ${person.personType == 'MISTA' ? '' : person.personType == 'JURIDICA' ? '' : 'style="display:none;"'}>
	                        <div class="col-sm-4">
	                            <div class="form-group fg-line">
	                                <label>CNPJ</label>
	                                <input type="text" value="${person.cnpj}" name="cnpj" class="form-control input-mask" data-mask="000.000.000/0000-00" placeholder="000.000.000/0000-00">
	                            </div>  
	                        </div>                                
	                        <div class="col-sm-4">
	                            <div class="form-group fg-line">
	                                <label>IE</label>
	                                <input type="text" value="${person.ie}" name="ie" data-mask="00000000000000000000" class="form-control">
	                            </div>
	                        </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group fg-line">
                                <label>Site</label>
                                <input type="url" value="${person.site}" name="site" class="form-control">
                            </div>
                        </div>                        
					    <div class="col-sm-4 m-b-20">
                            <div class="form-group fg-line">
                                <label>Twitter</label>
                                <input type="text" value="${person.twitter}" class="form-control " name="twitter">
                            </div>
                        </div>
                        <div class="col-sm-4 m-b-20">
                            <div class="form-group fg-line">
                                <label>Facebook</label>
                                <input type="text" value="${person.facebook}" class="form-control " name="facebook">
                            </div>
                        </div>
					</div>
					<div class="row">
						<input type="hidden" name="_customer.enabled" value="true">
						<input type="hidden" name="_provider.enabled" value="true">
						<input type="hidden" name="_employee.enabled" value="true">
						<input type="hidden" name="_broker.enabled" value="true">
						<input type="hidden" name="_workgroup.enabled" value="true">
						<input type="hidden" name="_user.enabled" value="true">
						<div class="col-sm-2 m-b-20">
							<label class="checkbox checkbox-inline m-r-20"> 
								<input type="checkbox" name="customer.enabled" onclick="refreshTabs(this);" value="true" ${person.customer.enabled ? 'checked="checked"' : ''}> 
								<i class="input-helper"></i> Cliente
							</label>                             
						</div>
                        <div class="col-sm-2 m-b-20">
                        	<label class="checkbox checkbox-inline m-r-20"> 
                        		<input type="checkbox" name="provider.enabled" onclick="refreshTabs(this);" value="true" ${person.provider.enabled ? 'checked="checked"' : ''}> 
                        		<i class="input-helper"></i> Fornecedor
							</label>
						</div>
                        <div class="col-sm-2 m-b-20">
                        	<label class="checkbox checkbox-inline m-r-20"> 
                        		<input type="checkbox" name="employee.enabled" onclick="refreshTabs(this);" value="true" ${person.employee.enabled ? 'checked="checked"' : ''}> 
                        		<i class="input-helper"></i> Funcionário
							</label>
						</div>
						<div class="col-sm-2 m-b-20">
                        	<label class="checkbox checkbox-inline m-r-20"> 
                        		<input type="checkbox" name="broker.enabled" onclick="refreshTabs(this);" value="true" ${ person.broker.enabled ? 'checked="checked"' : ''}>
                        		<i class="input-helper"></i> Representante
							</label>
						</div>
						<div class="col-sm-2 m-b-20">
                        	<label class="checkbox checkbox-inline m-r-20"> 
                        		<input type="checkbox" name="workgroup.enabled" onclick="refreshTabs(this);" value="true" ${ person.workgroup.enabled ? 'checked="checked"' : ''}>
                        		<i class="input-helper"></i> Empresa
							</label>
						</div>
						<div class="col-sm-2 m-b-20">
                        	<label class="checkbox checkbox-inline m-r-20"> 
                        		<input type="checkbox" name="user.enabled" onclick="refreshTabs(this);" value="true" ${ person.user.enabled ? 'checked="checked"' : ''}>
                        		<i class="input-helper"></i> Usuário
							</label>
						</div>
					</div>
				</div>

				<!-- Start Tabs Person -->
				<div role="tabpanel">
					<ul class="tab-nav tn-justified" role="tablist">	
						<li class="waves-effect active">
							<a href="#tabAddress" aria-controls="tabAddress" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-home m-r-5"></i>Endereço</a>
						</li>
						<li class="waves-effect">
							<a href="#tabContacts" aria-controls="tabContacts" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-phone m-r-5"></i>Contato</a>
						</li>
						<li id="tab_customer" class="waves-effect" ${person.customer.enabled ? '' : 'style="display:none;"'}>
							<a href="#tabCustomer" aria-controls="tabCustomer" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-shopping-cart m-r-5"></i>Cliente</a>
						</li>
						<li id="tab_provider" class="waves-effect" ${person.provider.enabled ? '' : 'style="display:none;"'}>
							<a href="#tabProvider" aria-controls="tabProvider" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-money m-r-5"></i>Fornecedor</a>
						</li>
						<li id="tab_employee" class="waves-effect" ${person.employee.enabled ? '' : 'style="display:none;"'}>
							<a href="#tabEmployee" aria-controls="tabEmployee" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-account m-r-5"></i>Funcionário</a>
						</li>
						<li id="tab_broker" class="waves-effect" ${person.broker.enabled ? '' : 'style="display:none;"'}>
							<a href="#tabBroker" aria-controls="tabBroker" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-case m-r-5"></i>Representante</a>
						</li>
						<li id="tab_workgroup" class="waves-effect" ${person.workgroup.enabled ? '' : 'style="display:none;"'}>
							<a href="#tabWorkGroup" aria-controls="tabWorkGroup" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-store m-r-5"></i>Empresa</a>
						</li>
						<li id="tab_user" class="waves-effect" ${person.user.enabled ? '' : 'style="display:none;"'}>
							<a href="#tabUser" aria-controls="tabUser" role="tab" data-toggle="tab">
							<i class="zmdi zmdi-lock m-r-5"></i>Usuário</a>
						</li>
					</ul>					
					<div class="tab-content" style="padding-top:10px;">						
						
						<!-- Start TabAddress -->
						<div id="tabAddress" role="tabpanel" class="tab-pane active">
							<div id="personAddress">
								<div class="text-right" style="padding-right:20px;">
									<button type="button" class="tableAdd btn btn-primary btn-sm m-t-10 waves-effect">Novo Endereço</button>		                            
								</div>
								<div class="table-responsive">
									<table class="tableList table table-striped table-vmiddle bootgrid-table">
										<thead>
											<tr>
												<th>#</th>
												<th>CEP</th>
												<th>Tipo</th>
												<th>Logradouro</th>
												<th>Cidade</th>
												<th>UF</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${person.address}" var="a" varStatus="i">
												<tr id="line_personAddress_${i.index}">
													<td class="text-left">${i.count}</td>
													<td class="text-left">${a.zipCode}</td>
													<td class="text-left">${a.addressType}</td>
													<td class="text-left">${a.streetType} ${a.patio} Nº ${a.number}</td>
													<td class="text-left">${a.city.name}</td>
													<td class="text-left">${a.uf}</td>
													<td class="text-left">
														<input type="hidden" name="address[${i.index}].index" value="${i.index}"/>
														<input type="hidden" name="address[${i.index}].id" value="${a.id}"/>
														<input type="hidden" name="address[${i.index}].zipCode" value="${a.zipCode}"/>
														<input type="hidden" name="address[${i.index}].streetType" value="${a.streetType}"/>
														<input type="hidden" name="address[${i.index}].addressType" value="${a.addressType}"/>
														<input type="hidden" name="address[${i.index}].patio" value="${a.patio}"/>
														<input type="hidden" name="address[${i.index}].number" value="${a.number}"/>
														<input type="hidden" name="address[${i.index}].complement" value="${a.complement}"/>
														<input type="hidden" name="address[${i.index}].district" value="${a.district}"/>
														<input type="hidden" name="address[${i.index}].reference" value="${a.reference}"/>
														<input type=hidden name="address[${i.index}].city.id" value="${a.city.id}"/>
														<input type=hidden name="address[${i.index}].city.name" value="${a.city.name}"/>
														<input type="hidden" name="address[${i.index}].uf" value="${a.uf}"/>
														<button type="button" data-row-index="${i.index}" class="btn btn-icon command-edit waves-effect waves-circle"><span class="zmdi zmdi-edit"></span></button>
														<button type="button" data-row-index="${i.index}" class="btn btn-icon command-delete waves-effect waves-circle"><span class="zmdi zmdi-delete"></span></button>	
													</td>
												</tr>
											</c:forEach>																						
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- End TabAddress -->
						
						<!-- Start TabContacts -->	
						<div id="tabContacts" role="tabpanel" class="tab-pane">
							<div id="personContact">
								<div class="text-right" style="padding-right:20px;">
									<button type="button" class="tableAdd btn btn-primary btn-sm m-t-10 waves-effect">Novo Contato</button>		                            
								</div>
								<div class="table-responsive">
									<table class="tableList table table-striped table-vmiddle bootgrid-table">
										<thead>
											<tr>
												<th>#</th>
												<th>Tipo</th>
												<th>DDD</th>
												<th>Número</th>
												<th>Operadora</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${person.phones}" var="p" varStatus="i">
												<tr id="line_personContact_${i.index}">
													<td class="text-left">${i.count}</td>
													<td class="text-left">${p.phoneType}</td>
													<td class="text-left">${p.ddd}</td>
													<td class="text-left">${p.number}</td>
													<td class="text-left">${p.company}</td>
													<td class="text-left">
														<input type="hidden" name="phones[${i.index}].index" value="${i.index}"/>
														<input type="hidden" name="phones[${i.index}].id" value="${p.id}"/>
														<input type="hidden" name="phones[${i.index}].phoneType" value="${p.phoneType}"/>
														<input type="hidden" name="phones[${i.index}].ddd" value="${p.ddd}"/>
														<input type="hidden" name="phones[${i.index}].number" value="${p.number}"/>
														<input type="hidden" name="phones[${i.index}].company" value="${p.company}"/>
														<button type="button" data-row-index="${i.index}" class="btn btn-icon command-edit waves-effect waves-circle"><span class="zmdi zmdi-edit"></span></button>
														<button type="button" data-row-index="${i.index}" class="btn btn-icon command-delete waves-effect waves-circle"><span class="zmdi zmdi-delete"></span></button>	
													</td>
												</tr>
											</c:forEach>																						
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- End TabContacts -->
	
						<!-- Start TabCustomer -->
						<div id="tabCustomer" role="tabpanel" class="tab-pane" style="padding-top:15px; padding-right:20px;">							
							<div class="pmbb-body p-l-30">	
								<div class="row">
									<div class="col-sm-6 m-b-20">
	                                    <div class="form-group fg-line">
	                                        <label>R$ Limite de Crédito</label>
	                                        <input type="number" name="customer.creditLimit" value="${person.customer.creditLimit}" class="form-control" placeholder="0">
	                                    </div>
	                                    <br>
	                                    <div class="form-group">									
											<label>Status</label>
											<select name="customer.status" class="selectpicker">
												<option value="REGULAR" ${person.customer.status == 'REGULAR' ? 'selected="selected"' : ''}>REGULAR</option>
												<option value="BOM" ${person.customer.status == 'BOM' ? 'selected="selected"' : ''}>BOM</option>
												<option value="ÓTIMO" ${person.customer.status == 'ÓTIMO' ? 'selected="selected"' : ''}>ÓTIMO</option>
                                        	</select>
	                                    </div>	                                    		                                    
	                                </div>
	                                <div class="col-sm-6 m-b-20">  
	                                    <div class="form-group fg-line">
	                                        <label>Observações</label><br>
	                                        <textarea rows="6" cols="30" class="form-control dirty" name="customer.observation">${person.customer.observation}</textarea>
	                                    </div>
	                                </div>											
								</div>								
							</div>						
						</div>
						<!-- End TabCustomer -->
						
						<!-- Start TabProvider -->
						<div id="tabProvider" role="tabpanel" class="tab-pane" style="padding-top:15px; padding-right:20px;">
							<div class="pmbb-body p-l-30">	
								<div class="row">
									<div class="col-sm-6 m-b-20">									
										<div class="form-group">									
											<label>Status</label>
											<select name="provider.status" class="selectpicker">
												<option value="REGULAR" ${person.provider.status == 'REGULAR' ? 'selected="selected"' : ''}>REGULAR</option>
												<option value="BOM" ${person.provider.status == 'BOM' ? 'selected="selected"' : ''}>BOM</option>
												<option value="ÓTIMO" ${person.provider.status == 'ÓTIMO' ? 'selected="selected"' : ''}>ÓTIMO</option>
                                        	</select>
	                                    </div>		                                    
	                                </div>
	                                <div class="col-sm-6 m-b-20">
	                                    <div class="form-group fg-line">
	                                        <label>Contato</label>
	                                        <input type="text" name="provider.contact" value="${person.provider.contact}" class="form-control" maxlength="50">
	                                    </div>
	                                </div>											
								</div>								
							</div>
						</div>
						<!-- End TabProvider -->
						
						<!-- Start TabBroker -->
						<div id="tabBroker" role="tabpanel" class="tab-pane" style="padding-top:15px; padding-right:20px;">
							<div class="pmbb-body p-l-30">	
								<div class="row">
									<div class="col-sm-6 m-b-20">
	                                    <div class="form-group fg-line">
	                                        <label>Código</label>
	                                        <input type="number" name="broker.code" value="${person.broker.code}" class="form-control" placeholder="0" maxlength="8">
	                                    </div>	                                    		                                    
	                                </div>
	                                <div class="col-sm-6 m-b-20">
	                                	<div class="form-group fg-line">
	                                        <label>Observações</label><br>
	                                        <textarea rows="6" class="form-control dirty" name="broker.observation">${person.broker.observation}</textarea>
	                                    </div>	                                
	                                </div>											
								</div>								
							</div>						
						
						</div>
						<!-- End TabBroker -->
						
						<!-- Start TabEmployee -->
						<div id="tabEmployee" role="tabpanel" class="tab-pane" style="padding-top:15px; padding-right:20px;">
							<div class="pmbb-body p-l-30">	
								<div class="row">
									<div class="col-sm-6 m-b-20">
	                                    <div class="form-group fg-line">
	                                        <label>Matrícula</label>
	                                        <input type="text" name="employee.enrollment" value="${person.employee.enrollment}" class="form-control" maxlength="20">
	                                    </div>
	                                </div>
	                        	</div>								
							</div>							
						</div>
						<!-- End TabEmployee -->
						
						<!-- Start TabWorkGroup -->
						<div id="tabWorkGroup" role="tabpanel" class="tab-pane" style="padding-top:15px; padding-right:20px;">
							<div class="pmbb-body p-l-30">	
								<div class="row">
									<div class="col-sm-6">
			                            <div class="form-group fg-line">
			                                <label>Tipo</label>                                    
				                             <select name="workgroup.workgroupType" onchange="refreshWorkGroupType();" class="selectpicker">
				                             	<option value="">...</option>
				                                 <option value="HEAD_OFFICE" ${person.workgroup.workgroupType == 'HEAD_OFFICE' ? 'selected="selected"' : ''}>Matriz</option>
				                                 <option value="SUBSIDIARY" ${person.workgroup.workgroupType == 'SUBSIDIARY' ? 'selected="selected"' : ''}>Filial</option>
				                             </select>
			                            </div>  
			                        </div>
	                        		<div class="col-sm-6 m-b-20">
	                                    <div id="boxWorkGroupMaster" class="form-group fg-line" ${person.workgroup.workgroupType == 'SUBSIDIARY' ? '' : 'style="display:none;"'}>
	                                        <label>Informe a matriz</label>
	                                        <select name="workgroup.master" class="selectpicker">
	                                        	<option value="">...</option>
				                            	<c:forEach items="${listWorkGroup}" var="w">
		                           					<option value="${w.id}" ${person.workgroup.master.id == w.id ? 'selected="selected"' : ''}>${w.person.name}</option>
		                           				</c:forEach>
				                            </select>
	                                    </div>
	                                </div>	                                
	                        	</div>
							</div>							
						</div>
						<!-- End TabWorkGroup -->
						
						<!-- Start TabUser -->
						<div id="tabUser" role="tabpanel" class="tab-pane" style="padding-top:15px; padding-right:20px;">
							<div class="pmbb-body p-l-30">	
								<div class="row">
									<div class="col-sm-6 m-b-20">
		                                <div class="form-group fg-line">
		                                    <label>Login</label>
		                                    <input type="text" name="user.login" value="${person.user.login}" class="form-control" maxlength="20">
		                                </div>
		                            </div>
		                            <div class="col-sm-6 m-b-20">
		                                <div class="form-group">
		                                	<div class="toggle-switch">
		                                        <label for="ts1" class="ts-label"><b>Ativo?</b></label><br><br>
		                                        <input type="hidden" name="_user.active" value="true"/>
		                                        <input id="user_active_checkbox" name="user.active" type="checkbox" hidden="hidden" value="true" ${person.user.active ? 'checked="checked"' : ''}>
		                                        <label for="user_active_checkbox" class="ts-helper"></label>
		                                    </div>                          
		                                </div>		                                    
		                            </div>
								</div>
							</div>
						</div>
						<!-- End TabUser -->
						
					</div>
				</div>
				<!-- End Tabs Person -->
				
			</div>
			<!-- End Person Right -->
			
		</div>
	
	</form:form>
	<!-- End Form Person -->	
	
	<!-- Start personAddressModal -->
	<div id="personAddressModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content clearfix">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>        
					<h4 class="modal-title">Endereço</h4>
				</div>	
				<div class="modal-body">  		
					<input id="address_index" type="hidden">
					<input id="address_id" type="hidden">
					<input id="address_city.name" type="hidden">					
					<div class="row form-group">
						<div class="col-md-3">
							<div class="fg-line">
                            	<label>CEP</label>
                            	<input id="address_zipCode" type="text" class="form-control input-sm" maxlength="8">
                            </div>
						</div>
						<div class="col-md-9">
							<div class="fg-line">
		                    	<label>Tipo</label>
		                       	<select id="address_addressType" class="selectpicker">
		                           	<option value="RESIDENCIAL">RESIDENCIAL</option>
		                            <option value="COMERCIAL">COMERCIAL</option>                            
								</select>
							</div>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-3">
							<div class="fg-line">
								<label>UF</label>
								<select id="address_uf" onchange="refreshListCity(0, 'address_uf', 'boxAddressListCity', 'address_city.id');" class="selectpicker">
		                           	<c:forEach items="${listUF}" var="uf">
		                           		<option value="${uf}" ${uf == defaultUF ? 'selected="selected"' : ''}>${uf}</option>
		                           	</c:forEach>
		                        </select>
	                        </div>
						</div>
						<div class="col-md-9">
							<div class="fg-line">
								<label>Município</label>
								<div id="boxAddressListCity">
									<select id="address_city.id" name="address_city.id" class="selectpicker">
										<c:forEach items="${listCity}" var="city">
			                           		<option value="${city.id}">${city.name}</option>		                           		
			                           	</c:forEach>
			                        </select>
		                        </div>
	                        </div>
						</div>						
					</div>					
					<div class="row form-group">
						<div class="col-md-3">
							<div class="fg-line">
								<label>Logradouro</label>
								<select id="address_streetType" class="selectpicker">
									<option value="RUA">RUA</option>
									<option value="ALAMEDA">ALAMEDA</option>
									<option value="AVENIDA">AVENIDA</option>
									<option value="BECO">BECO</option>
									<option value="LARGO">LARGO</option>
									<option value="PRAÇA">PRAÇA</option>
									<option value="QUADRA">QUADRA</option>
									<option value="RODOVIA">RODOVIA</option>
									<option value="TRAVESSA">TRAVESSA</option>		                           	
		                        </select>
	                        </div>
						</div>
						<div class="col-md-6">
							<div class="fg-line">
                            	<label>&nbsp;</label>
                            	<input id="address_patio" type="text" class="form-control input-sm" maxlength="80">
                            </div>
						</div>
						<div class="col-md-3">
							<div class="fg-line">
                            	<label>Nº</label>
                            	<input id="address_number" type="tel" class="form-control input-sm" maxlength="10">
                            </div>							
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-9">
							<div class="fg-line">
                            	<label>Bairro</label>
                            	<input id="address_district" type="text" class="form-control input-sm" maxlength="100">
                            </div>
						</div>
						<div class="col-md-3">
							<div class="fg-line">
                            	<label>Complemento</label>
                            	<input id="address_complement" type="text" class="form-control input-sm" maxlength="50">
                            </div>							
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="fg-line">
                            	<label>Ponto de Refência</label>
                            	<input id="address_reference" type="text" class="form-control input-sm" maxlength="100">
                            </div>
						</div>					
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:void(0);" class="btn btn-small btn-default" data-dismiss="modal">Cancelar</a>
					<a href="javascript:void(0);" class="saveEntity btn btn-primary">OK</a>
				</div>
			</div>
		</div>	
	</div>
	<!-- End personAddressModal -->
	
	<!-- Start personContactModal -->
	<div id="personContactModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content clearfix">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>        
					<h4 class="modal-title">Contato</h4>
				</div>	
				<div class="modal-body">  		
					<input id="phones_index" type="hidden">
					<input id="phones_id" type="hidden">
					<div class="form-group fg-line">
                    	<label>Tipo</label>
                       	<select id="phones_phoneType" class="selectpicker">
                           	<option value="RESIDENCIAL">RESIDENCIAL</option>
                            <option value="COMERCIAL">COMERCIAL</option>
                            <option value="CELULAR">CELULAR</option>
						</select>
					</div>
					<div class="row">
						<div class="col-xs-2">
							<div class="fg-line">
                            	<label>DDD</label>
                            	<input id="phones_ddd" type="tel" class="form-control input-sm" maxlength="2">
                            </div>
						</div>
                        <div class="col-xs-6">
                        	<div class="fg-line">
                            	<label>Número</label>
                                <input id="phones_number" type="tel" class="form-control input-sm" maxlength="10">
							</div>
						</div>
						<div class="col-xs-4">
							<div class="fg-line">
								<label>Operadora</label>
								<input id="phones_company" type="text" class="form-control input-sm" maxlength="20">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:void(0);" class="btn btn-small btn-default" data-dismiss="modal">Cancelar</a>
					<a href="javascript:void(0);" class="saveEntity btn btn-primary">OK</a>
				</div>
			</div>
		</div>	
	</div>
	<!-- End personContactModal -->
	
	<!-- Start webcamModal -->
	<div id="webcamModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content clearfix">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Obter por Webcam</h4>
				</div>				
				<div class="modal-body text-center">
					<div id="cameraModal" style="width:320px; height:240px; margin:0 auto;"></div>
				</div>
				<div class="modal-footer">
					<a href="javascript:void(0);" class="btn btn-small btn-default" data-dismiss="modal">Cancelar</a>
					<a href="javascript:void(0);" class="removeEntity btn btn-primary" onClick="take_snapshot()"><span class="fa fa-download"></span> Capturar</a>
				</div>
			</div>
		</div>
	</div>	
	<!-- End webcamModal -->
	
	<!-- Start fileModal -->
	<div id="fileModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content clearfix">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Obter por Arquivo</h4>
				</div>				
				<div class="modal-body text-center">
					<div id="fileSelectModal" class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-preview thumbnail" data-trigger="fileinput"></div>
					    <div>
					        <span class="btn btn-info btn-file waves-effect">
					            <span class="fileinput-new">Selecione a imagem...</span>
					            <span class="fileinput-exists">Alterar...</span>
					            <input type="file" accept=".jpg, .jpeg, .png, image/jpeg, image/jpg, image/png">
					        </span>					        
					    </div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:void(0);" class="btn btn-small btn-default" data-dismiss="modal">Cancelar</a>
					<a href="javascript:void(0);" class="removeEntity btn btn-primary" onClick="take_snapshot_file()"><span class="fa fa-download"></span> Confirmar</a>
				</div>
			</div>
		</div>
	</div>	
	<!-- End fileModal -->
	
			
			
</jsp:body>
</tags:template>