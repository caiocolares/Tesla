<ul class="header-inner">
	<li id="menu-trigger" data-trigger="#sidebar">
		<div class="line-wrap">
			<div class="line top"></div>
			<div class="line center"></div>
			<div class="line bottom"></div>
		</div>
	</li>
	<li class="logo hidden-xs"><a href="${rootURL}"><img src="${rootURL}resources/img/logo_tesla_header.png"/></a></li>
	<li class="pull-right">
		<ul class="top-menu">
			<li class="dropdown"><a data-toggle="dropdown" href=""> <i
					class="tm-icon zmdi zmdi-email"></i> <i class="tmn-counts">2</i>
			</a>
				<div class="dropdown-menu dropdown-menu-lg pull-right">
					<div class="listview">
						<div class="lv-header">Mensagens</div>
						<div class="lv-body">
							 <a class="lv-item" href="">
								<div class="media">
									<div class="pull-left">
										<img class="lv-img-sm" src="${rootURL}resources/img/profile-pics/4.jpg" alt="">
									</div>
									<div class="media-body">
										<div class="lv-title">Thomas Alva Edison</div>
										<small class="lv-small">Ut vitae lacus sem
											ellentesque maximus, nunc sit amet varius dignissim, dui
											est consectetur neque</small>
									</div>
								</div>
							</a> <a class="lv-item" href="">
								<div class="media">
									<div class="pull-left">
										<img class="lv-img-sm" src="${rootURL}resources/img/profile-pics/4.jpg" alt="">
									</div>
									<div class="media-body">
										<div class="lv-title">John Pierpont Morgan</div>
										<small class="lv-small">Proin laoreet commodo eros
											id faucibus. Donec ligula quam, imperdiet vel ante placerat</small>
									</div>
								</div>
							</a>
						</div>
						<a class="lv-footer" href="">Ver todos</a>
					</div>
				</div></li>
			<li class="dropdown"><a data-toggle="dropdown" href=""> <i
					class="tm-icon zmdi zmdi-notifications"></i> <i
					class="tmn-counts">3</i>
			</a>
				<div class="dropdown-menu dropdown-menu-lg pull-right">
					<div class="listview" id="notifications">
						<div class="lv-header">
							Notificações

							<ul class="actions">
								<li class="dropdown"><a href="" data-clear="notification">
										<i class="zmdi zmdi-check-all"></i>
								</a></li>
							</ul>
						</div>
						<div class="lv-body">
							<a class="lv-item" href="">
								<div class="media">
									<div class="pull-left">
										<img class="lv-img-sm" src="${rootURL}resources/img/profile-pics/1.jpg" alt="">
									</div>
									<div class="media-body">
										<div class="lv-title">RH</div>
										<small class="lv-small">Cum sociis natoque penatibus
											et magnis dis parturient montes</small>
									</div>
								</div>
							</a> <a class="lv-item" href="">
								<div class="media">
									<div class="pull-left">
										<img class="lv-img-sm" src="${rootURL}resources/img/profile-pics/2.jpg" alt="">
									</div>
									<div class="media-body">
										<div class="lv-title">Sistema</div>
										<small class="lv-small">Nunc quis diam diamurabitur
											at dolor elementum, dictum turpis vel</small>
									</div>
								</div>
							</a> <a class="lv-item" href="">
								<div class="media">
									<div class="pull-left">
										<img class="lv-img-sm" src="${rootURL}resources/img/profile-pics/3.jpg" alt="">
									</div>
									<div class="media-body">
										<div class="lv-title">PCP</div>
										<small class="lv-small">Phasellus a ante et est
											ornare accumsan at vel magnauis blandit turpis at augue
											ultricies</small>
									</div>
								</div>
							</a> 
						</div>

						<a class="lv-footer" href="">Ver todos</a>
					</div>

				</div></li>
			<li class="dropdown hidden-xs"><a data-toggle="dropdown"
				href=""> <i class="tm-icon zmdi zmdi-view-list-alt"></i> <i
					class="tmn-counts">2</i>
			</a>
				<div class="dropdown-menu pull-right dropdown-menu-lg">
					<div class="listview">
						<div class="lv-header">Projetos</div>
						<div class="lv-body">
							<div class="lv-item">
								<div class="lv-title m-b-5">Desenvolvimento da Coleção</div>

								<div class="progress">
									<div class="progress-bar" role="progressbar"
										aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"
										style="width: 95%">
										<span class="sr-only">95% Concluido</span>
									</div>
								</div>
							</div>
							<div class="lv-item">
								<div class="lv-title m-b-5">Implantação do Sistema</div>

								<div class="progress">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="80" aria-valuemin="0"
										aria-valuemax="100" style="width: 80%">
										<span class="sr-only">80% Concluído</span>
									</div>
								</div>
							</div>
							
						</div>

						<a class="lv-footer" href="">Ver todos</a>
					</div>
				</div></li>
			<li class="dropdown"><a data-toggle="dropdown" href=""><i
					class="tm-icon zmdi zmdi-more-vert"></i></a>
				<ul class="dropdown-menu dm-icon pull-right">							
					<li><a href=""><i class="zmdi zmdi-account-circle"></i> Usuário: ${user.login}</a></li>
					<li class="divider hidden-xs"></li>
					<li class="hidden-xs"><a data-action="fullscreen" href=""><i
							class="zmdi zmdi-fullscreen"></i> Exibir Tela Cheia</a></li>							
					<li><a href="/access/editPassword"><i class="zmdi zmdi-lock"></i> Alterar Senha</a></li>
					<li><a href="/logout"><i class="zmdi zmdi-power"></i> Logout</a></li>
				</ul>
			</li>					
		</ul>
	</li>
</ul>