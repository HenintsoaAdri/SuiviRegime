<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	AnalyseRegime regime = (AnalyseRegime)request.getAttribute("regimeActif");
%>
         <div id="page-inner">
             <div class="row">
             	<div class="col-xs-12">
             		<h2>Conseil</h2>
             		<p class="text-muted">
             			N'h&eacute;sitez pas &agrave; suivre nos conseils!
             		</p>
             	</div>
             </div>
             
             <div class="row">
                <div class="col-md-6">
	                    <div class="panel panel-primary text-center no-boder bg-color-green">
	                        <div class="panel-body">
	                            <i class="fa fa-cutlery fa-5x"></i>
	                        </div>
	                        <div class="panel-footer back-footer-green">
                				<a class="btn bg-color-green square-btn-adjust" href ="/SuiviRegime/Utilisateur/Conseil/Alimentation">Alimentation</a>                           
	                        </div>
	                    </div>
                </div>
                <div class="col-md-6">
	                    <div class="panel panel-primary text-center no-boder bg-color-red">
	                        <div class="panel-body">
	                            <i class="fa fa-heartbeat fa-5x"></i>
	                        </div>
	                        <div class="panel-footer back-footer-red">
	                            <a class="btn bg-color-red square-btn-adjust" href ="/SuiviRegime/Utilisateur/Conseil/Sport">Sport</a>	                            
	                        </div>
	                    </div>
                </div>
            </div> 
             <% if(regime != null){ %>
		     <div class="row">
                 <div class="col-md-4 col-sm-12 col-xs-12">           
					<div class="panel panel-back noti-box">
		                <span class="icon-box bg-color-green set-icon">
		                    <i class="fa fa-bell-o"></i>
		                </span>
		                <div class="text-box" >
		                    <p class="main-text"><% out.print(regime.getPoidsObjectif()); %> kg</p>
		                    <p class="text-muted">Objectif</p>
		                </div>
		             </div>
		     	</div>
                <div class="col-md-4 col-sm-12 col-xs-12">           
					<div class="panel panel-back noti-box">
		                <span class="icon-box bg-color-blue set-icon">
		                    <i class="fa fa-calendar-o"></i>
		                </span>
		                <div class="text-box" >
		                    <p class="main-text"><% out.print(regime.getJourRestant()); %> jours</p>
		                    <p class="text-muted">Restants</p>
		                </div>
		             </div>
		     	</div>
                <div class="col-md-4 col-sm-12 col-xs-12">           
					<div class="panel panel-back noti-box">
		                <span class="icon-box bg-color-brown set-icon">
		                    <i class="fa fa-sort-amount-desc"></i>
		                </span>
		                <div class="text-box" >
		                    <p class="main-text"><% out.print(regime.getPoidsPerduParSemaineString()); %></p>
		                    <p class="text-muted">&eacute;volution par Semaine</p>
		                </div>
		             </div>
		     	</div>
		     </div>
             <div class="row">
             	<div class="col-xs-12">
					<div class="panel panel-default">
		                <div class="panel-heading">
		                    R&eacute;gime Actif
		                </div>
		                <div class="panel-body">
		                    <div class="table-responsive">
		                        <table class="table table-hover">
		                            <thead>
		                                <tr>
		                                    <th>#</th>
		                                    <th>D&eacute;but</th>
		                                    <th>Fin</th>
		                                    <th>Poids Initial</th>
		                                    <th>Poids Objectif</th>
		                                    <th></th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                                <tr>
		                                    <td><% out.print(regime.getId()); %></td>
		                                    <td><% out.print(regime.getDebutString()); %></td>
		                                    <td><% out.print(regime.getFinString()); %></td>
		                                    <td><% out.print(regime.getPoidsInitialString()); %></td>
		                                    <td><% out.print(regime.getPoidsObjectifString()); %></td>
		                                    <td><a href="/SuiviRegime/Utilisateur/Regime/deactivate" class="btn btn-danger square-btn-adjust">D&eacute;sactiver</a></td>
		                                    <td><div class="btn-group-vertical">
		                                    		<a href="/SuiviRegime/Utilisateur/Regime/edit?regime.id=<% out.print(regime.getId()); %>" class="btn btn-warning square-btn-adjust">modifier</a>
		                                    		<a href="/SuiviRegime/Utilisateur/Regime/delete?regime.id=<% out.print(regime.getId()); %>" class="btn bg-color-red square-btn-adjust">suprimer</a>
		                                		</div>
		                                	</td>
		                                </tr>
		                            </tbody>
		                        </table>
						        <% if(request.getAttribute("erreur") != null){ %>    
								<div class="alert alert-danger alert-dismissable fade in">
								   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
								</div>     
								<% } %>
		                    </div>
		                  </div>
		            	</div>	
                <!-- /. ROW  -->
		        </div>
		     </div>
		     <% } %>   
	    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="../includes/footer.jsp" %>