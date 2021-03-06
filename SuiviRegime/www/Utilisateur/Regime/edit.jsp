<%@ include file="../includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	Regime regime = (Regime)request.getAttribute("regime");
%>
			<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Modifier un r&eacute;gime</h2>   
                        <h5>Cr&eacute;er votre nouveau r&eacute;gime.</h5>
                       
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
                           <form method="post" role="form" action="/SuiviRegime/Utilisateur/Regime/update">
                           	<input type="hidden" name="regime.id" value="<% out.print(regime.getId()); %>"/>
                            <div class="row">
                                <div class="col-md-6">
                                    <h3>Dur&eacute;e du r&eacute;gime</h3>
                                        <div class="form-group col-sm-6">
                                            <label>D&eacute;but</label>
                                            <input type="date" class="form-control" name="debut" value="<% out.print(regime.getDebut()); %>"/>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label>Fin</label>
                                            <input type="date" class="form-control" name="fin" value="<% out.print(regime.getFin()); %>"/>
                                        </div>
                                            <p class="help-block">Format de date support&eacute; : aaaa-mm-jj ou jj-mm-aaaa.</p>                                 
    							</div>
                                <div class="col-md-6">
                                    <h3>Poids</h3>
                                        <div class="form-group col-sm-6">
                                            <label>Poids initial</label>
                                            <input type="number" class="form-control" name="poidsInitial" value="<% out.print(regime.getPoidsInitial()); %>"/> kg
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label>Poids objectif</label>
                                            <input type="number" class="form-control" name="poidsObjectif" value="<% out.print(regime.getPoidsObjectif()); %>"/> kg
                                        </div>
                                        
	                                    <div class="form-group col-sm-6">
	                                        <label>Activ&eacute;
	                                        	<input type="checkbox" class="form-control" name="active" value="<% out.print(regime.isActive()); %>" <% if(regime.isActive()) out.print("checked"); %>/>
	                                        </label>
	                                    </div>
                                </div>
                            </div>
						        <% if(request.getAttribute("erreur") != null){ %>    
								<div class="alert alert-danger alert-dismissable fade in">
								   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
								</div>     
								<% } %>
                            <div style="float: right;">
                                <button type="submit" class="btn btn-success">Modifier</button>
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
                        <h3>Modifier votre r&eacute;gime</h3>
                         <p>
                        Pour un suivi de votre poids et de vos habitudes alimentaires et sportives, cr�ez un nouveau r&eacute;gime! On vous aidera &agrave; rep&eacute;rer les bonnes habitudes qui vous font perdre vos kilos en trop! De bon conseils par jour vous seront offert! Donnez-nous la p&eacute;riode du r&eacute;gime, votre poids initial et le poids id�al, on s'occupe de tout! 
                        </p>
                    </div>
                </div>
                <!-- /. ROW  -->
    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="../includes/footer.jsp" %>