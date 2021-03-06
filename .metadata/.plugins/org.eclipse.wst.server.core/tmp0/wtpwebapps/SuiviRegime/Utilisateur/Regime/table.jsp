<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	AnalyseRegime regime= (AnalyseRegime)request.getAttribute("regimeActif");
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
         <div id="page-inner">
             <div class="row">
             	<div class="col-xs-12">
             		<h2>R&eacute;gime</h2>
             		<p class="text-muted">
             			Le r�gime que vous suivez actuellement est le r�gime Actif. 
             			<br>Activez le r�gime que vous voudriez suivre pour pouvoir inscrire les informations le concernant.
             			<br>Si aucun r&eacute;gime n'est encore disponible, n'h&eacute;sitez pas &agrave; en cr&eacute;er un !
             		</p>
             	</div>
             </div>
             <% if(regime != null){ %>
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
		     <div class="row">
		     	<div class="col-md-3 col-sm-6 col-xs-6">           
					<div class="panel panel-back noti-box">
		                <span class="icon-box bg-color-red set-icon">
		                    <i class="fa fa-clock-o"></i>
		                </span>
		                <div class="text-box" >
		                    <p class="main-text"><% out.print(regime.getMinuteSportParSemaineString()); %></p>
		                    <p class="text-muted">sport par Semaine</p>
		                </div>
		             </div>
			     </div>
                 <div class="col-md-3 col-sm-6 col-xs-6">           
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
                <div class="col-md-3 col-sm-6 col-xs-6">           
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
                <div class="col-md-3 col-sm-6 col-xs-6">           
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
		     </div>
			<% } %>
		     <div class="row">
             	<div class="col-xs-12">
                    <% if(liste.getListe() != null && !liste.getListe().isEmpty()){%>
					<div class="panel panel-default">
		                <div class="panel-heading">
		                    Liste des r&eacute;gimes clos
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
		                            <% for(Regime r : (List<Regime>)liste.getListe()){ %>
		                                <tr>
		                                    <td><% out.print(r.getId()); %></td>
		                                    <td><% out.print(r.getDebutString()); %></td>
		                                    <td><% out.print(r.getFinString()); %></td>
		                                    <td><% out.print(r.getPoidsInitialString()); %></td>
		                                    <td><% out.print(r.getPoidsObjectifString()); %></td>
		                                    <td><a href="/SuiviRegime/Utilisateur/Regime/activate?regime.id=<% out.print(r.getId()); %>" class="btn btn-success square-btn-adjust">activer</a></td>
		                                    <td><div class="btn-group-vertical">
		                                    		<a href="/SuiviRegime/Utilisateur/Regime/edit?regime.id=<% out.print(r.getId()); %>" class="btn btn-warning square-btn-adjust">modifier</a>
		                                    		<a href="/SuiviRegime/Utilisateur/Regime/delete?regime.id=<% out.print(r.getId()); %>" class="btn square-btn-adjust bg-color-red">suprimer</a>
		                                    	</div>
		                                    </td>
		                                </tr>
		                            <% } %>
		                            </tbody>
		                        </table>
		                         <ul class="pagination">
		                         	<% for(int i=0; i<liste.getNombrePage();i++){ %>
									<li class="<% if(i == pageNumero) out.print("active"); %>"><a href="?page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
									<% } %>
								 </ul>
								 
						        <% if(request.getAttribute("erreur") != null){ %>    
								<div class="alert alert-danger alert-dismissable fade in">
								   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
								</div>     
								<% } %>
		                    </div>
		                  </div>
		            	</div>
					<% } %>
		            	<a href="/SuiviRegime/Utilisateur/Regime/add" class="btn btn-danger square-btn-adjust">Cr&eacute;er votre R&eacute;gime</a>	
                <!-- /. ROW  -->
		        </div>  
	        </div>       
	    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="../includes/footer.jsp" %>