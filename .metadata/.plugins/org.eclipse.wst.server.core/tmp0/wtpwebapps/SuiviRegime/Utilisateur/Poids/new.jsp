<%@ include file="../includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	AnalyseRegime regime = (AnalyseRegime)request.getAttribute("regimeActif");
%>
			<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Nouvel enregistrement</h2>   
                        <h5>Enregistrer un nouveau poids.</h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                 <div class="row">
	                <div class="col-md-6 col-sm-12 col-xs-12">           
						<div class="panel panel-back noti-box">
			                <span class="icon-box bg-color-blue set-icon">
			                    <i class="fa fa-calendar-o"></i>
			                </span>
			                <div class="text-box" >
			                    <p class="main-text"><% out.print(regime.getPoidsInitial()); %> kg</p>
			                    <p class="text-muted">Initial</p>
			                </div>
			             </div>
			     	</div>
	                <div class="col-md-6 col-sm-12 col-xs-12">           
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
			     </div>
	             <div class="row">
	                <div class="col-md-12">
	                    <!-- Form Elements -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                            Informations
	                        </div>
	                        <div class="panel-body">
	                           <form method="post" role="form" action="/SuiviRegime/Utilisateur/Poids/save">
	                            <div class="row">
	                                <div class="col-md-6">
	                                    <h3>Votre poids</h3>
	                                        <div class="form-group col-sm-6">
	                                            <label>Date</label>
	                                            <input type="date" class="form-control" name="date" value="<% out.print(LocalDate.now()); %>"/>
	                                        </div>
	                                        <div class="form-group col-sm-6">
	                                            <label>Valeur</label>
	                                            <input type="number" class="form-control" name="valeur"/>
	                                        </div>
	                                            <p class="help-block">Format de date support&eacute; : aaaa-mm-jj ou jj-mm-aaaa.</p>                                 
	    							</div>
	                            </div>
							        <% if(request.getAttribute("erreur") != null){ %>    
									<div class="alert alert-danger alert-dismissable fade in">
									   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
									</div>     
									<% } %>
	                            <div style="float: right;">
	                                <button type="submit" class="btn btn-success">Enregistrer</button>
	                            </div>                                    
	                          </form>
	                        </div>
	                    </div>
	                     <!-- End Form Elements -->
	                </div>
	            </div>
                <!-- /. ROW  -->
                <div class="row">
                    <div class="col-md-12">
                        <h3>Enregistrer un nouveau poids</h3>
                         <p>
                        Pour un suivi de votre poids plus efficace, essayer au maximum d'enregister votre poids quotidiennement. Un seul poids uniquement sera enregistré par jour!  
                        </p>
                    </div>
                </div>
                <!-- /. ROW  -->
    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="../includes/footer.jsp" %>