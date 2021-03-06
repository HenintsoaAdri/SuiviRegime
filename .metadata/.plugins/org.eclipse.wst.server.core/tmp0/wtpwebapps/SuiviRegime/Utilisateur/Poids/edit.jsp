<%@ include file="../includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	Poids poids = (Poids)request.getAttribute("poids");
%>
			<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Modifier un enregistrement</h2>   
                        <h5>Modifier un poids.</h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
               <div class="row">
                <div class="col-md-12">
                    <!-- Form Elements -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Informations
                        </div>
                        <div class="panel-body">
                           <form method="post" role="form" action="/SuiviRegime/Utilisateur/Poids/update">
                            <input type="hidden" name="poids.id" value="<% out.print(poids.getId()); %>"/>
                            <div class="row">
                                <div class="col-md-6">
                                    <h3>Votre poids</h3>
                                        <div class="form-group col-sm-6">
                                            <label>Date</label>
                                            <input type="date" class="form-control" name="date" value="<% out.print(poids.getDate()); %>"/>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label>Valeur</label>
                                            <input type="number" class="form-control" name="valeur" value="<% out.print(poids.getValeur()); %>"/>
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