/*
 * Bootstrap Growl - Notifications popups
 */
function notify(message, type){	
	if(type == undefined)
		type = 'inverse';
	
    $.growl({ message: message },{
        type: type,
        allow_dismiss: false,
        label: 'Cancel',
        className: 'btn-xs btn-inverse',
        placement: { from: 'top', align: 'right' },
        delay: 2500,
        animate: { enter: 'animated bounceIn', exit: 'animated bounceOut' },
        offset: { x: 20, y: 85 }
    });
};

/* 
 * Redirect page 
 */
function href(location){
	window.location = location;
}

/* 
 * Redirect pageNumber 
 */
function hrefPageNumber(pageNumber){
	$('#pageNumber').val(pageNumber);
	$('#formSearch').submit();
}

/* 
 * Configure TableList and Modal for entity model 
 */
function createObject(idComp, attr, prefixRemove){
	var obj = {};
	$('#' + idComp  + ' input,#' + idComp  + ' select').each(function(i,field){ 
		var key = $(field).attr(attr).split(prefixRemove)[1];
		obj[key] = $(field).val();
	});
	return obj;
}

/*
 * personSearch
 */
$.fn.personSearch = function(p){
	if(p.urlAjax == undefined){
		console.log("No param urlAjax, exit...");
		return;
	}
	if(p.nameInput == undefined){
		console.log("No param nameInput, exit...");
		return;
	}	
	if(p.removePerson == undefined){
		p.removePerson = true;
	}
	var DB = {};
	var compId = $(this).attr('id');
	var strInit = '<div class="row">' +
		'<div class="col-sm-5">' +
			'<div style="font-size:1.1em; font-weight:bold; padding-bottom:5px;"><i class="zmdi zmdi-account-box"></i> Pessoa</div>' +
			'<input type="text" class="form-control personSearchNameInfo" placeholder="Nome" disabled="disabled">' +
		'</div>' +
		'<div class="col-sm-5">' +
			'<div style="font-size:1.1em; font-weight:bold; padding-bottom:5px;">&nbsp;</div>' +
			'<input type="text" class="form-control personSearchFantasyInfo" placeholder="Apelido" disabled="disabled">' +	                        
		'</div>' +
		'<div class="col-sm-2">' +
			'<div>&nbsp;</div>' +
			'<button type="button" class="btn btn-showmore-info btn-info btn-icon waves-effect waves-circle waves-float"><i class="zmdi zmdi-plus"></i></button>' +
			'&nbsp;' +
			'<button type="button" class="btn btn-search bgm-teal btn-icon waves-effect waves-circle waves-float"><i class="zmdi zmdi-search"></i></button>';
			if(p.removePerson){
				strInit += '&nbsp; - &nbsp;' +
				'<button type="button" class="btn btn-person-remove btn-warning btn-icon waves-effect waves-circle waves-float"><i class="zmdi zmdi-close"></i></button>';
			}
		strInit += '</div>' +
		'</div>' +
		'<div class="row table-showmore" style="padding-top:10px; display:none;">' +
		'<div class="col-sm-12">' +
			'<div class="table-responsive personSearchTableShowMore">' +
				'<div class="alert alert-info alert-dismissible" role="alert">' +
		            '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
		            'Nenhum usuário selecionado!' +
		        '</div>' +
			'</div>' +
		'</div>' +
	'</div>' +
	'<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">' +
		'<div class="modal-dialog  modal-lg">' +
			'<div class="modal-content">' +
				'<div class="card-header">' +
					'<h2><i class="zmdi zmdi-account-box"></i> Procurar Pessoa</h2>' +
					'<div class="actions">' +
						'<button type="button" class="btn btn-personSearch btn-primary btn-sm"><i class="zmdi zmdi-search"></i> Pesquisar</button>' +
						'&nbsp;&nbsp;&nbsp;' +
						'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
					'</div>' +
				'</div>' +
				'<div class="modal-body">' +
					'<div class="card-body card-padding" style="padding:10px 10px;">' +
						'<div class="row">' +
							'<div class="col-sm-5">' +
			                	'<div class="form-group fg-line">' +
			                    	'<label>Nome</label>' +
			                        '<input type="text" class="form-control personSearchName">' +
			                    '</div>' +
			                '</div>' +
			                '<div class="col-sm-5">' +
		                        '<div class="form-group fg-line">' +
		                            '<label class="labelFantasy">Apelido</label>' +
		                            '<input type="text" class="form-control personSearchFantasy">' +                                
		                        '</div>' +
		                    '</div>' +
		                    '<div class="col-sm-2">' +
		                    	'<button type="button" class="btn btn-showmore-modal btn-info btn-icon waves-effect waves-circle waves-float" style="margin:10px 0 10px 0;"><i class="zmdi zmdi-plus"></i></button>' +
		                    '</div>' +
						'</div>' +
						'<div class="boxPersonSearchAdvanced" style="display:none;">' +
							'<div class="row">' +
								'<div class="col-sm-6">' +
				                	'<div class="form-group fg-line">' +
					             		'<label>Tipo:</label>' +	
										'<div class="radio">' +
					               			'<label>' +
					                   			'<input type="radio" name="personSearchType" value="FISICA" checked="checked">' +
					                   			'<i class="input-helper"></i>' +
					                   			'Física' +
					               			'</label>' +
					               			'&nbsp;&nbsp;' +
					               			'<label>' +
					                   			'<input type="radio" name="personSearchType" value="JURIDICA">' +
					                   			'<i class="input-helper"></i>' +
					                   			'Jurídica' +
					               			'</label>' +
					               			'&nbsp;&nbsp;' +
					               			'<label>' +
					                   			'<input type="radio" name="personSearchType" value="MISTA">' +
					                   			'<i class="input-helper"></i>' +
					                   			'Mista' +
					               			'</label>' +
					          			'</div>' +
				          			'</div>' +
								'</div>' +												
							'</div>' +
							'<div class="row">' +
				                '<div class="boxPersonFisica">' +
					            	'<div class="col-sm-6">' +
					                	'<div class="form-group fg-line">' +
					                    	'<label>CPF</label>' +
					                        '<input type="text" class="form-control input-mask personSearchCpf" data-mask="000.000.000-00" placeholder="000.000.000-00">' +
										'</div>' +
									'</div>' +
					                '<div class="col-sm-6">' +
					                	'<div class="form-group fg-line">' +
					                    	'<label>RG</label>' +
					                        '<input type="text" class="form-control personSearchRg" data-mask="00000000000000000000">' +
										'</div>' +
									'</div>' +
								'</div>' +
				                '<div class="boxPersonJuridica" style="display:none;">' +
					            	'<div class="col-sm-6">' +
										'<div class="form-group fg-line">' +
											'<label>CNPJ</label>' +
											'<input type="text" class="form-control input-mask personSearchCnpj" data-mask="00.000.000/0000-00" placeholder="00.000.000/0000-00">' +
										'</div>' +
									'</div>' +                                
									'<div class="col-sm-6">' +
					                	'<div class="form-group fg-line">' +
					                    	'<label>IE</label>' +
					                        '<input type="text" class="form-control personSearchIe" data-mask="00000000000000000000">' +
					                    '</div>' +
									'</div>' +
								'</div>' +
							'</div>' +
						'</div>' +
						'<div class="personSearchList">' +
							'<div class="alert alert-info alert-dismissible" role="alert">' +
					            '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
					            'reencha o formulário para pesquisa!' +
					        '</div>' +
						'</div>' +					
					'</div>' +
				'</div>' +			
			'</div>' +
		'</div>' +
	'</div>';
	$(this).append(strInit);
	setMaskInputs();
	
	$('#' + compId + ' .btn-showmore-info').click(function(){
		$('#' + compId + ' .btn-showmore-info i').toggleClass('zmdi-plus');
		$('#' + compId + ' .btn-showmore-info i').toggleClass('zmdi-minus');
		$('#' + compId + ' .table-showmore').slideToggle();
	});	
	$('#' + compId + ' .btn-person-remove').click(function(){
		var strTableShowMore = '<div class="alert alert-info alert-dismissible" role="alert">' +
			'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
		    'Nenhuma pessoa selecionada!' +
		'</div>';
		$('#' + compId + ' input[name="' + p.nameInput + '"]').val("");
		$('#' + compId + ' .personSearchNameInfo').val("");
		$('#' + compId + ' .personSearchFantasyInfo').val("");
		$('#' + compId + ' .personSearchTableShowMore').html(strTableShowMore);
	});	
	$('#' + compId + ' .btn-showmore-modal').click(function(){
		$('#' + compId + ' .btn-showmore-modal i').toggleClass('zmdi-plus');
		$('#' + compId + ' .btn-showmore-modal i').toggleClass('zmdi-minus');
		$('#' + compId + ' .boxPersonSearchAdvanced').slideToggle();
	});	
	$('#' + compId + ' .btn-search').click(function(){
		$('#' + compId + ' .modal').modal('show');		
	});	
	$('#' + compId + ' input[name="personSearchType"]').click(function(){
		var str = '<div class="alert alert-info alert-dismissible" role="alert">' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
            'Preencha o formulário para pesquisa!' +
        '</div>';
		$('#' + compId + ' .personSearchList').html(str);
		var selected = $('#' + compId + ' input[name="personSearchType"]:checked').val();		
		if(selected == 'FISICA'){
			$('#' + compId + ' .boxPersonFisica').show();
			$('#' + compId + ' .boxPersonJuridica').hide();
			$('#' + compId + ' input[name="personSearchCpf"]').val('');
			$('#' + compId + ' input[name="personSearchRg"]').val('');
			$('#' + compId + ' .labelFantasy').html('Apelido');			
		}
		else if(selected == 'JURIDICA'){
			$('#' + compId + ' .boxPersonJuridica').show();
			$('#' + compId + ' .boxPersonFisica').hide();
			$('#' + compId + ' input[name="personSearchCnpj"]').val('');
			$('#' + compId + ' input[name="personSearchIe"]').val('');
			$('#' + compId + ' .labelFantasy').html('Razão Social');
		}
		else{
			$('#' + compId + ' .boxPersonFisica').show();
			$('#' + compId + ' .boxPersonJuridica').show();	
			$('#' + compId + ' .labelFantasy').html('Razão Social');
		}		
	});	
	$('#' + compId + ' .btn-personSearch').click(function(){		
		getListPerson(0);
	});
	
	function getListPerson(_pageNumber){
		var name = $('#' + compId + ' .personSearchName').val();
		var fantasy = $('#' + compId + ' .personSearchFantasy').val();
		var personType = $('#' + compId + ' input[name="personSearchType"]:checked').val();
		var cpf = $('#' + compId + ' .personSearchCpf').val();
		var rg = $('#' + compId + ' .personSearchRg').val();
		var cnpj = $('#' + compId + ' .personSearchCnpj').val();
		var ie = $('#' + compId + ' .personSearchIe').val();
		var pageNumber = _pageNumber;
		var link = p.urlAjax + "?name=" + name + "&fantasy=" + fantasy + "&personType=" + personType + "&cpf=" + cpf + "&rg=" + rg + "&cnpj=" + cnpj + "&ie=" + ie + "&pageNumber=" + pageNumber;		
		$.ajax({
			url: link,
			type: "GET",
			dataType: "json",
			success: function(data){				
				var list = data.list;
				var pageNumber = data.pageNumber;
				var firstPage = data.pagination.firstPage;
				var lastPage = data.pagination.lastPage;
				var betweenStart = data.pagination.betweenStart;
				var betweenEnd = data.pagination.betweenEnd;
				var total = data.pagination.total;	
				var str = '';
				var strTableTHEAD = '';
				var strTableTBODY = '';
				DB = list;
				if(list.length > 0){	
					if(personType == "FISICA" || personType == "MISTA"){
						strTableTHEAD += 	'<th>CPF</th>' +
											'<th>RG</th>';						
					}
					if(personType == "JURIDICA" || personType == "MISTA"){
						strTableTHEAD += 	'<th>CNPJ</th>' +
											'<th>IE</th>';						
					}
					str = '<div class="table-responsive">' +		
						'<table class="table table-striped table-vmiddle">' +
						'<thead>' +
							'<tr>' +
								'<th>Nome</th>' +
								'<th>Fantasia</th>' +
								'<th>Tipo</th>' +
								strTableTHEAD +
								'<th></th>' +
							'</tr>' +
						'</thead>' +
						'<tbody>';
					if(personType == "FISICA"){
						for(var i=0; i<list.length; i++){
							str += '<tr>' +
								'<td>' + list[i].name + '</td>' +
								'<td>' + list[i].fantasy + '</td>' +
								'<td>' + list[i].personType + '</td>' +
								'<td>' + list[i].cpf + '</td>' +
								'<td>' + list[i].rg + '</td>' +
								strTableTBODY +
								'<td><button type="button" class="btn btn-info waves-effect buttonSelectPerson" data-index="' + i + '"><i class="zmdi zmdi-mail-reply"></i></button></td>' +
							'</tr>';
						}
					}
					else if(personType == "JURIDICA"){
						for(var i=0; i<list.length; i++){
							str += '<tr>' +
								'<td>' + list[i].name + '</td>' +
								'<td>' + list[i].fantasy + '</td>' +
								'<td>' + list[i].personType + '</td>' +
								'<td>' + list[i].cnpj + '</td>' +
								'<td>' + list[i].ie + '</td>' +
								strTableTBODY +
								'<td><button type="button" class="btn btn-info waves-effect buttonSelectPerson" data-index="' + i + '"><i class="zmdi zmdi-mail-reply"></i></button></td>' +
							'</tr>';
						}					
					}
					else if(personType == "MISTA"){
						for(var i=0; i<list.length; i++){
							str += '<tr>' +
								'<td>' + list[i].name + '</td>' +
								'<td>' + list[i].fantasy + '</td>' +
								'<td>' + list[i].personType + '</td>' +
								'<td>' + list[i].cpf + '</td>' +
								'<td>' + list[i].rg + '</td>' +
								'<td>' + list[i].cnpj + '</td>' +
								'<td>' + list[i].ie + '</td>' +
								strTableTBODY +
								'<td><button type="button" class="btn btn-info waves-effect buttonSelectPerson" data-index="' + i + '"><i class="zmdi zmdi-mail-reply"></i></button></td>' +
							'</tr>';
						}
					}
					str += '</tbody>' +
						'</table>' +
					'</div>' +
					'<div id="data-table-basic-footer" class="bootgrid-footer container-fluid">' +
						'<div class="row">' +
							'<div class="col-sm-7">' +
								'<ul class="pagination">';				
					if(pageNumber == 0){
						str += '<li class="first disabled">' +
								'<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-more-horiz"></i></a>' +
							'</li>' +
							'<li class="prev disabled">' +
								'<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-chevron-left"></i></a>' +
							'</li>';
					}
					else{
						str += '<li class="first">' +
						'<a href="javascript:void(0);" class="button buttonPaginationPerson" data-item="0"><i class="zmdi zmdi-more-horiz"></i></a>' +
						'</li>' +
						'<li class="prev">' +
							'<a href="javascript:void(0);" class="button buttonPaginationPerson" data-item="' + (pageNumber - 1) + '"><i class="zmdi zmdi-chevron-left"></i></a>' +
						'</li>';
					}				
					for(var i=firstPage; i<=lastPage; i++){
						if(pageNumber == i){
							str += '<li class="active">' +
							'<a href="javascript:void(0);" class="button buttonPaginationPerson" data-item="' + i + '">' + (i + 1) + '</a>' +
							'</li>';
						}
						else{
							str += '<li>' +
								'<a href="javascript:void(0);" class="button buttonPaginationPerson" data-item="' + i + '">' + (i + 1) + '</a>' +
							'</li>';
						}					
					}
					if(pageNumber == lastPage){
						str += '<li class="next disabled">' +
								'<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-chevron-right"></i></a>' +
							'</li>' +
							'<li class="last disabled">' +
								'<a href="javascript:void(0);" class="button"><i class="zmdi zmdi-more-horiz"></i></a>' +
							'</li>';
					}
					else{
						str += '<li class="next">' +
								'<a href="javascript:void(0);" class="button buttonPaginationPerson" data-item="' + (pageNumber + 1) + '"><i class="zmdi zmdi-chevron-right"></i></a>' +
							'</li>' +
					    	'<li class="last">' +
								'<a href="javascript:void(0);" class="button buttonPaginationPerson" data-item="' + lastPage + '"><i class="zmdi zmdi-more-horiz"></i></a>' +
							'</li>';
					}			
					str += '</ul>' +
							'</div>' +
							'<div class="col-sm-5 infoBar">' +
								'<div class="infos">Mostrando ' + betweenStart + ' a ' + betweenEnd + ' de ' + total + ' registros</div>' +
							'</div>' +
						'</div>' +
					'</div>';
				}
				else{
					str = '<div class="alert alert-warning alert-dismissible" role="alert">' +
		            	'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
		            	'Nenhum registro encontrado!' +
		            '</div>';
				}
				$('#' + compId + ' .personSearchList').html(str);				
				$('#' + compId + ' .buttonSelectPerson').click(function(){
					var _index = $(this).attr('data-index');
					var person = DB[_index];
					setSelectedPerson(person);					
					$('#' + compId + ' .modal').modal('hide');
				});				
				$('#' + compId + ' .buttonPaginationPerson').click(function(){
					var _pageNumber = $(this).attr('data-item');
					getListPerson(_pageNumber);
				});				
			},
			error: function(){
				console.log('error get person');
			}
		});
	}	
	function setSelectedPerson(person){
		var strTableShowMoreTHEAD = '';
		var strTableShowMoreTBODY = '';					
		if(person.personType == "FISICA" || person.personType == "MISTA"){
			strTableShowMoreTHEAD += 	'<td><b>CPF</b></td>' +
										'<td><b>RG</b></td>';
			strTableShowMoreTBODY += 	'<td>' + person.cpf + '</td>' + 
									 	'<td>' + person.rg + '</td>';
		}
		if(person.personType == "JURIDICA" || person.personType == "MISTA"){
			strTableShowMoreTHEAD += 	'<td><b>CNPJ</b></td>' +
										'<td><b>IE</b></td>';
			strTableShowMoreTBODY += 	'<td>' + person.cnpj + '</td>' + 
		 								'<td>' + person.ie + '</td>';
		}										
		var strTableShowMore = '<table class="table table-condensed table-bordered">' +
			'<thead>' +
				'<tr>' +
					'<td><b>Tipo</b></td>' +
					strTableShowMoreTHEAD +																
				'</tr>' +
			'</thead>' +
			'<tbody>' +								
				'<tr>' +
					'<td>' + person.personType + '</td>' +
					strTableShowMoreTBODY +
				'</tr>' +
			'</tbody>' +
		'</table>';
		$('#' + compId + ' input[name="' + p.nameInput + '"]').val(person.id);
		$('#' + compId + ' .personSearchNameInfo').val(person.name);
		$('#' + compId + ' .personSearchFantasyInfo').val(person.fantasy);
		$('#' + compId + ' .personSearchTableShowMore').html(strTableShowMore);
	}
		
	if(p.person != undefined && p.person != null){
		setSelectedPerson(p.person);
	}	
}

/*
 * ListAndModal
 */
$.fn.listAndModal = function(p){	
	if(p.listDB == undefined){
		console.log("No param listDB, exit...");
		return;
	}
	if(p.prefix == undefined){
		console.log("No param prefix, exit...");
		return;
	}
	if(p.lineTable == undefined){
		console.log("No function lineTable, exit...");
		return;
	}
	
	var compId = $(this).attr('id');
	var modalId = compId + 'Modal';
	var lineTag = 'line_' + $(this).attr('id') + '_';	
	var length = $('#' + compId + ' .tableList tbody tr').length;
		
	function refreshTable(){
		var str = '';
		for(var i=0; i<p.listDB.length; i++){
			str += p.lineTable(p.listDB[i]);
		}
		$('#' + compId +' .tableList tbody').html(str);
		setButtonsActions();
	}
	
	//Prepare Edit Button
	//Prepare Remove Button
	function setButtonsActions(){
		$('#' + compId + ' .command-edit').click(function(){		
			var index = $(this).attr('data-row-index');
			var obj = createObject(lineTag + index, 'name', p.prefix + '[' + index + '].');
			for(var key in obj){
				var field = '#' + modalId + ' #' + p.prefix + '_' + key.replace('.', '\\.');
				if($(field)[0]){
					if($(field).hasClass('selectpicker')){
						$(field).selectpicker('val', obj[key]);
					}
					else{
						$(field).val(obj[key]);
					}
				}		
			}
			if(p.prepareEdit)
				p.prepareEdit(obj);
			
			$('#' + modalId).modal('show');
		});
		$('#' + compId + ' .command-delete').click(function(){
			var arrTmp = [];
			var index = $(this).attr('data-row-index');
			for(var i=0; i<p.listDB.length; i++){
				if(p.listDB[i].index != index){
					var newObj = p.listDB[i];
					newObj.index = arrTmp.length;
					arrTmp.push(newObj);
				}
			}
			p.listDB = arrTmp;
			refreshTable();
		});
	}
	
	//Populate listDB
	for(var i=0; i<length; i++){
		var obj = createObject(lineTag + i, 'name', p.prefix + '[' + i + '].');
		p.listDB.push(obj);
	}	
		
	//Prepare Add Button
	$('#' + compId + ' .tableAdd').click(function(){
		$('#' + modalId + ' input').val('');
		$('#' + modalId).modal('show');		
	});
	
	//Prepare Edit Button
	//Prepare Remove Button
	setButtonsActions();
	
	//Save Button
	$('#' + modalId + ' .saveEntity').click(function(){
		if(p.prepareSave)
			p.prepareSave();
		
		var obj = createObject(modalId, 'id', p.prefix + '_');
		console.log(obj);
		if(obj.index == ""){ //create
			obj.index = p.listDB.length;		 
			p.listDB.push(obj);	
		}
		else{ //edit
			for(var i=0;i<p.listDB.length;i++){
				if(p.listDB[i].index == obj.index){
					p.listDB[i] = obj;
					break;
				}			
			}
		}
		$('#' + modalId).modal('hide');
		refreshTable();
	});	
}

/*
 * PickList
 */
$.fn.pickList = function(){
	var compId = $(this).attr('id');	
	function refreshList(){
		var items = $("#" + compId + " .list2 input[type='checkbox']:not('.all')");
		var length = items.length;
		if(length > 0){
			items.each(function(idx,item){
				var choice = $(item);
				choice.parent().find('input[type="hidden"]').each(function(){
					var propName = $(this).attr('name');
					if(propName.indexOf('_') == 0){
						propName = propName.substr(1);
						propName = propName.replace(/\[\]/g, '[' + idx + ']');
					}
					else{
						propName = propName.replace(/\[[0-9]\]/g, '[' + idx + ']');
					}
					$(this).attr('name', propName);					
				});
			});
		} 	
	}	
	$('#' + compId + ' .add').click(function(){
	    $('.all').prop("checked",false);
	    var items = $("#" + compId + " .list1 input[type='checkbox']:checked:not('.all')");
	    var n = items.length;
	    if (n > 0) {
	      items.each(function(idx,item){
	    	var choice = $(item);
	    	choice.prop("checked",false);
	    	choice.parent().appendTo("#" + compId + " .list2");	        
	      });
	      refreshList();
	  	}
	});
	$('#' + compId + ' .remove').click(function(){
	    $('.all').prop("checked",false);
	    var items = $("#" + compId + " .list2 input[type='checkbox']:checked:not('.all')");
	    var n = items.length;
	    if (n > 0) {
			items.each(function(idx,item){
				var choice = $(item);
				choice.prop("checked",false);
				choice.parent().appendTo("#" + compId + " .list1");
				choice.parent().find('input[type="hidden"]').each(function(){
					var propName = $(this).attr('name');					
					propName = '_' + propName.replace(/\[[0-9]\]/g, '[]');					
					$(this).attr('name', propName);
				});
		      
		    });
			refreshList();
	    }
	});
	$('#' + compId + ' .all').click(function(e){
		e.stopPropagation();
		var $this = $(this);
	    if($this.is(":checked")) {
	    	$this.parents('.list-group').find("[type=checkbox]").prop("checked",true);
	    }
	    else {
	    	$this.parents('.list-group').find("[type=checkbox]").prop("checked",false);
	        $this.prop("checked",false);
	    }
	});	
	$('#' + compId + ' .list-group a').click(function(e){		  
	    e.stopPropagation();	  
	  	var $this = $(this).find("[type=checkbox]");
	    if($this.is(":checked")) {
	    	$this.prop("checked",false);
	    }
	    else {
	    	$this.prop("checked",true);
	    }	  
	    if ($this.hasClass("all")) {
	    	$this.trigger('click');
	    }
	});
	$('#' + compId + ' [type=checkbox]').click(function(e){
		e.stopPropagation();
	});
}


/*
 * Máscaras, formatação e validação 
 */
function maskInput(o,f){
  v_obj=o;
  v_fun=f;
  setTimeout("execmask()",1);
}
function execmask(){
  v_obj.value=v_fun(v_obj.value);
}
function maskInputMoney(v){
	v=v.replace(/\D/g,"");
	v=v.replace(/[0-9]{12}/,"inválido");
	v=v.replace(/(\d{1})(\d{8})$/,"$1.$2");
	v=v.replace(/(\d{1})(\d{5})$/,"$1.$2");
	v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2");
	return v;
}
function maskInputDate(v){
   	v=v.replace(/\D/g,"");                          
    v=v.replace(/^(\d{2})(\d)/,"$1/$2");                       
    v=v.replace(/(\d{2})(\d)/,"$1/$2");           
    return v;
}
function setMaskInputs(){
	$('.input-mask').each(function() {
        var input = $(this), options = {};
        if (input.attr('data-mask-reverse') === 'true') {
            options.reverse = true;
        }
        if (input.attr('data-mask-maxlength') === 'false') {
            options.maxlength = false;
        }
        input.mask(input.attr('data-mask'), options);
    });
	$('.input-mask-date').each(function(){
		$(this).keyup(function(){
			maskInput(this, maskInputDate);		
		})
		.keypress(function(){
			maskInput(this, maskInputDate);
		})
		.attr('maxlength', 10)
		.attr('placeholder', 'dd/mm/aaaa');
	});
    $('.input-mask-money').each(function(){
		$(this).keyup(function(){
			maskInput(this, maskInputMoney);		
		})
		.keypress(function(){
			maskInput(this, maskInputMoney);
		})
		.attr('maxlength', 14)
		.attr('placeholder', '0,00');
	});
}
function checkDayAndMonthDate(date) {
	var dateInt = date.replace(/\//g, '');
	if(dateInt.length != 8)
		return false;
        
    var day = parseInt(dateInt.substr(0,2), 10), month = parseInt(dateInt.substr(2,2), 10), year = parseInt(dateInt.substr(4,4), 10);
    if(day < 1 || day > 31)
		return false;
    
    if(month < 1 || month > 12)
		return false;           
    
    var test = new Date(year, month - 1, day);
    if(day != test.getDate() || month != (test.getMonth() + 1))
    	return false;    
    
	return true;    
} 
function addMonthToDate(date) {
	if(!checkDayAndMonthDate(date))
		return false;
	
	var dateInt = date.replace(/\//g, '');
	var day = parseInt(dateInt.substr(0,2), 10), month = parseInt(dateInt.substr(2,2), 10), year = parseInt(dateInt.substr(4,4), 10);
    var newDate = new Date(year, month, day);
    var newDay = newDate.getDate();
    var newMonth = newDate.getMonth() + 1;
	return ("0" + newDay).slice(-2) + '/' + ("0" + newMonth).slice(-2) + '/' + newDate.getFullYear();    
} 

/*
 * Detact Mobile Browser
 */
if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
   $('html').addClass('ismobile');
}

$(document).ready(function(){
    
    /*
     * Sidebar
     */
    (function(){
        $('body').on('click', '#menu-trigger, #chat-trigger', function(e){
            e.preventDefault();
            var x = $(this).data('trigger');
            $(x).toggleClass('toggled');
            $(this).toggleClass('open');
    	    $('.sub-menu.toggled').not('.active').each(function(){
        		$(this).removeClass('toggled');
        		$(this).find('ul').hide();
    	    });
	    $('.profile-menu .main-menu').hide();
            if (x == '#sidebar') {
                $elem = '#sidebar';
                $elem2 = '#menu-trigger';
                $('#chat-trigger').removeClass('open');
                if (!$('#chat').hasClass('toggled')) {
                    $('#header').toggleClass('sidebar-toggled');
                }
                else {
                    $('#chat').removeClass('toggled');
                }
            }
            if (x == '#chat') {
                $elem = '#chat';
                $elem2 = '#chat-trigger';
                $('#menu-trigger').removeClass('open');

                if (!$('#sidebar').hasClass('toggled')) {
                    $('#header').toggleClass('sidebar-toggled');
                }
                else {
                    $('#sidebar').removeClass('toggled');
                }
            }
            if ($('#header').hasClass('sidebar-toggled')) {
                $(document).on('click', function (e) {
                    if (($(e.target).closest($elem).length === 0) && ($(e.target).closest($elem2).length === 0)) {
                        setTimeout(function(){
                            $($elem).removeClass('toggled');
                            $('#header').removeClass('sidebar-toggled');
                            $($elem2).removeClass('open');
                        });
                    }
                });
            }
        })
        $('body').on('click', '.sub-menu > a', function(e){
            e.preventDefault();
            $(this).next().slideToggle(200);
            $(this).parent().toggleClass('toggled');
        });
    })();

    /*
     * Clear Notification
     */
    $('body').on('click', '[data-clear="notification"]', function(e){
        e.preventDefault();
        var x = $(this).closest('.listview');
        var y = x.find('.lv-item');
        var z = y.size();
        $(this).parent().fadeOut();
        x.find('.list-group').prepend('<i class="grid-loading hide-it"></i>');
        x.find('.grid-loading').fadeIn(1500);
        var w = 0;
        y.each(function(){
            var z = $(this);
            setTimeout(function(){
            z.addClass('animated fadeOutRightBig').delay(1000).queue(function(){
                z.remove();
            });
            }, w+=150);
        })
	  	setTimeout(function(){
	  	    $('#notifications').addClass('empty');
	  	}, (z*150)+200);
    });

	/*
     * Dropdown Menu
     */
    if($('.dropdown')[0]){
		$('body').on('click', '.dropdown.open .dropdown-menu', function(e){
		    e.stopPropagation();
		});	
		$('.dropdown').on('shown.bs.dropdown', function (e) {
		    if($(this).attr('data-animation')) {
				$animArray = [];
				$animation = $(this).data('animation');
				$animArray = $animation.split(',');
				$animationIn = 'animated '+$animArray[0];
				$animationOut = 'animated '+ $animArray[1];
				$animationDuration = ''
				if(!$animArray[2]) {
				    $animationDuration = 500; //if duration is not defined, default is set to 500ms
				}
				else {
				    $animationDuration = $animArray[2];
				}	
				$(this).find('.dropdown-menu').removeClass($animationOut)
				$(this).find('.dropdown-menu').addClass($animationIn);
		    }
		});
		$('.dropdown').on('hide.bs.dropdown', function (e) {
		    if($(this).attr('data-animation')) {
	    		e.preventDefault();
	    		$this = $(this);
	    		$dropdownMenu = $this.find('.dropdown-menu');
	
	    		$dropdownMenu.addClass($animationOut);
	    		setTimeout(function(){
	    		    $this.removeClass('open')
	
	    		}, $animationDuration);
    	    }
    	});
    }
    
    /*
    * Profile Menu
    */
    $('body').on('click', '.profile-menu > a', function(e){
        e.preventDefault();
        $(this).parent().toggleClass('toggled');
	    $(this).next().slideToggle(200);
    });

    /*
     * Text Feild
     */
    //Add blue animated border and remove with condition when focus and blur
    if($('.fg-line')[0]) {
        $('body').on('focus', '.fg-line .form-control', function(){
            $(this).closest('.fg-line').addClass('fg-toggled');
        })

        $('body').on('blur', '.form-control', function(){
            var p = $(this).closest('.form-group, .input-group');
            var i = p.find('.form-control').val();

            if (p.hasClass('fg-float')) {
                if (i.length == 0) {
                    $(this).closest('.fg-line').removeClass('fg-toggled');
                }
            }
            else {
                $(this).closest('.fg-line').removeClass('fg-toggled');
            }
        });
    }
    //Add blue border for pre-valued fg-flot text feilds
    if($('.fg-float')[0]) {
        $('.fg-float .form-control').each(function(){
            var i = $(this).val();

            if (!i.length == 0) {
                $(this).closest('.fg-line').addClass('fg-toggled');
            }

        });
    }
    
    /*
     * Input Mask
     */
    setMaskInputs();

    /*
     * Date Time Picker
     */
    if ($('.date-time-picker')[0]) {
	   $('.date-time-picker').datetimepicker();
    }
    if ($('.time-picker')[0]) {
    	$('.time-picker').datetimepicker({
    	    format: 'LT'
    	});
    }
    if ($('.date-picker')[0]) {
    	$('.date-picker').datetimepicker({
    	    format: 'DD/MM/YYYY'
    	})
    	.attr('maxlength', 10);
    }

    /*
     * Waves Animation
     */
    (function(){
         Waves.attach('.btn:not(.btn-icon):not(.btn-float)');
         Waves.attach('.btn-icon, .btn-float', ['waves-circle', 'waves-float']);
        Waves.init();
    })();

   /*
     * Link prevent
     */
    $('body').on('click', '.a-prevent', function(e){
        e.preventDefault();
    });

    /*
     * Collaspe Fix
     */
    if ($('.collapse')[0]) {

        //Add active class for opened items
        $('.collapse').on('show.bs.collapse', function (e) {
            $(this).closest('.panel').find('.panel-heading').addClass('active');
        });

        $('.collapse').on('hide.bs.collapse', function (e) {
            $(this).closest('.panel').find('.panel-heading').removeClass('active');
        });

        //Add active class for pre opened items
        $('.collapse.in').each(function(){
            $(this).closest('.panel').find('.panel-heading').addClass('active');
        });
    }

    /*
     * Login
     */
    if($('.login-content')[0]) {
        $('html').addClass('login-content');
        $('body').on('click', '.login-navigation > li', function(){
            var z = $(this).data('block');
            var t = $(this).closest('.lc-block');
            t.removeClass('toggled');
            setTimeout(function(){
                $(z).addClass('toggled');
            });
        })
    }

    /*
     * Fullscreen Browsing
     */
    if ($('[data-action="fullscreen"]')[0]) {
		var fs = $("[data-action='fullscreen']");
		fs.on('click', function(e) {
		    e.preventDefault();	
		    function launchIntoFullscreen(element) {
				if(element.requestFullscreen) {
				    element.requestFullscreen();
				} else if(element.mozRequestFullScreen) {
				    element.mozRequestFullScreen();
				} else if(element.webkitRequestFullscreen) {
				    element.webkitRequestFullscreen();
				} else if(element.msRequestFullscreen) {
				    element.msRequestFullscreen();
				}
		    }	
		    function exitFullscreen() {
				if(document.exitFullscreen) {
				    document.exitFullscreen();
				} else if(document.mozCancelFullScreen) {
				    document.mozCancelFullScreen();
				} else if(document.webkitExitFullscreen) {
				    document.webkitExitFullscreen();
				}
		    }
		    launchIntoFullscreen(document.documentElement);
		    fs.closest('.dropdown').removeClass('open');
		});
    }

    /*
     * Profile Edit Toggle
     */
    if ($('[data-pmb-action]')[0]) {
        $('body').on('click', '[data-pmb-action]', function(e){
            e.preventDefault();
            var d = $(this).data('pmb-action');

            if (d === "edit") {
                $(this).closest('.pmb-block').toggleClass('toggled');
            }

            if (d === "reset") {
                $(this).closest('.pmb-block').removeClass('toggled');
            }


        });
    }

    /*
     * IE 9 Placeholder
     */
    if($('html').hasClass('ie9')) {
        $('input, textarea').placeholder({
            customClass: 'ie9-placeholder'
        });
    }

    /*
     * Print
     */
    if ($('[data-action="print"]')[0]) {
        $('body').on('click', '[data-action="print"]', function(e){
            e.preventDefault();
            window.print();
        })
    } 
    
    /*
     * ScrollBar
     */
    function scrollBar(selector, theme, mousewheelaxis) {
        $(selector).mCustomScrollbar({
            theme: theme,
            scrollInertia: 100,
            axis:'yx',
            mouseWheel: {
                enable: true,
                axis: mousewheelaxis,
                preventDefault: true
            }
        });
    }
    if (!$('html').hasClass('ismobile')) {
    	if ($('.c-overflow')[0]) {
    		scrollBar('.c-overflow', 'minimal-dark', 'y');
        }
    }

});