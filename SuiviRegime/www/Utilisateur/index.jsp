<%@ include file="includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.util.List"%>
<%  Utilisateur user = (Utilisateur)request.getAttribute("user"); 
	AnalyseRegime regime = (AnalyseRegime)request.getAttribute("regimeActif");
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("listeRegime");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Mon Tableau de Bord</h2>   
                        <h5>Bonjour <% out.print(user.getFullName()); %> , Heureux de vous revoir! </h5>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                  <hr />
                <div class="row">
                  <%
                  if(regime != null){ %>
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
		    <% }else{ %>
				<div class="col-xs-12">
			<%  if(!liste.getListe().isEmpty()){%>
					<p class="text-muted">Activez un r&eacute;gime pour commencer votre exp&eacute;rience avec Following You !</p>
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
					<a href="Utilisateur/Regime/add" class="btn btn-danger square-btn-adjust">Cr&eacute;er votre R&eacute;gime</a>
					</div>
					<% } %>
				</div>
                 <!-- /. ROW  -->
	            <hr />                
	            <div class="row">
	                <div class="col-md-6 col-sm-12 col-xs-12">
	                    <div class="panel back-dash">
	                        <i class="fa fa-bullhorn fa-2x"></i><strong> &nbsp; TIPS DU JOUR</strong>
	                        <% AlimentationConseil tips =  (AlimentationConseil)request.getAttribute("randomTips");%>
	                        <p class="text-muted"><% out.print(tips.getNom()); %></p>
	                        <p>Matin : <% out.print(tips.getMatin()); %></p>
	                        <p>Midi : <% out.print(tips.getMidi()); %></p>
	                        <p>Soir : <% out.print(tips.getSoir()); %></p>
	                    </div>
	                       
	                </div>
	                <div class="col-md-6 col-sm-12 col-xs-12 ">
	                    <div class="panel ">
				          <div class="main-temp-back">
				            <div class="panel-body">
				              <div class="row">
				                <div class="col-xs-12 text-left"> <i class="fa fa-cloud fa-3x"></i> Antananarivo </div>
				                <div class="col-xs-12 text-right">
				                  <div class="text-temp"> <% out.print(DateUtil.getInstance().LocalDateToString(LocalDate.now())); %> </div>
				                </div>
				              </div>
				            </div>
				          </div>
		          		</div>
	                    <div class="panel panel-back noti-box">
	                    	<div class="row">
		                    	<div class="col-xs-2">
					                <span class="icon-box bg-color-green set-icon">
					                    <i class="fa fa-info"></i>
					                </span>
				                </div>
				                <div class="text-box col-xs-10" >
				                    <p class="main-text">Mes Informations</p>
				                    <p class="text-muted">Nom &amp; Pr&eacute;nom : <% out.print(user.getFullName()); %><br>
		                            <% out.print(user.getFullDateNaissanceString()); %><br>
		                            <% out.print(user.getSexeString()); %><br>
		                            Adresse : <% out.print(user.getAdresse()); %><br>
		                            Email : <% out.print(user.getEmail()); %></p>
				                	<a class="btn btn-primary" href="/SuiviRegime/Utilisateur/edit">Modifier mes informations</a>
				                </div>
			                </div>
	             		</div>
	    			</div>              
	        	</div>
	    	</div>
             <!-- /. PAGE INNER  -->
<%@ include file="includes/footer.jsp" %>