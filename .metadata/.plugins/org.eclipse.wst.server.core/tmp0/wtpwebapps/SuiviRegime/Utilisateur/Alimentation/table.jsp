<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	AnalyseRegime regime = (AnalyseRegime)request.getAttribute("regimeActif");
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
	String evolution = (String)request.getAttribute("evolution");
%>
         <div id="page-inner">
             <div class="row">
             	<div class="col-xs-12">
             		<h2>Alimentation</h2>
             		<p class="text-muted">
             			Les Alimentations affich&eacute;s sont la liste des alimentations enregistr&eacute;s pour le r&eacute;gime actif. 
             			<br>Activez le r�gime que vous voudriez suivre pour pouvoir inscrire et suivre les alimentation le concernant.
             			<br>Si aucun r&eacute;gime n'est encore disponible, n'h&eacute;sitez pas &agrave; en cr&eacute;er un !
             		</p>
             	</div>
             </div>
		    <hr>
             <% if(regime != null){ %>
		     <div class="row">
		     	<div class="col-xs-12">
		     		<a href="/SuiviRegime/Utilisateur/Alimentation/add" class="btn btn-danger square-btn-adjust">
		     		Ajouter une alimentation
		     		</a>
		     	</div>
		     <hr>
             	<div class="col-xs-12">
				<% if(liste.getListe() != null && !liste.getListe().isEmpty()){ %>
					<div class="panel panel-default">
		                <div class="panel-heading">
		                    Liste des alimentations enregistr&eacute;s
		                </div>
		                <div class="panel-body">
		                    <div class="table-responsive">
		                        <table class="table table-hover">
		                            <thead>
		                                <tr>
		                                    <th>#</th>
		                                    <th>Date</th>
		                                    <th>P&eacute;riode</th>
		                                    <th>Repas</th>
		                                    <th>Boisson</th>
		                                </tr>
		                            </thead>
	                                <tbody>
	                                <% for(Alimentation a : (List<Alimentation>)liste.getListe()){ %>
	                                    <tr>
	                                        <td><% out.print(a.getId()); %></td>
	                                        <td><% out.print(a.getDate()); %></td>
	                                        <td><% out.print(a.getPeriodeString()); %></td>
	                                        <td><% out.print(a.getRepas()); %></td>
	                                        <td><% out.print(a.getBoisson()); %></td>
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
                <!-- /. ROW  -->
            <% } else{ %>
     			<div class="alert alert-danger">
				   <% out.print("Aucune alimentation enregistr&eacute;e"); %> 
				</div>
	        <% } %>  
		        </div> 
	        </div>
		     <hr>
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
		     <%-- <% } %> --%>
             <% } %>       
	    </div>
             <!-- /. PAGE INNER  -->

<!-- MORRIS CHART SCRIPTS -->
<script src="/SuiviRegime/js/morris/raphael-2.1.0.min.js"></script>
<script src="/SuiviRegime/js/morris/morris.js"></script>
             
<script type="text/javascript">
Morris.Line({
    element: 'evolution-chart',
    data: <% out.print(evolution); %>,
    xkey: 'y',
    ykeys: ['a'],
    labels: ['Poids'],
    hideHover: 'auto',
    resize: true
});
</script>             
<%@ include file="../includes/footer.jsp" %>