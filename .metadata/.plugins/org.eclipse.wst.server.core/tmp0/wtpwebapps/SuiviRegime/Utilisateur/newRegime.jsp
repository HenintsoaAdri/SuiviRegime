<%@ include file="includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<% Utilisateur user = (Utilisateur)session.getAttribute("user"); 
	AnalyseRegime regime = (AnalyseRegime)session.getAttribute("regime");
%>
<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Nouveau r&eacute;gime</h2>   
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
                           <form role="form">
                            <div class="row">
                                <div class="col-md-6">
                                    <h3>Dur&eacute;e du r&eacute;gime</h3>
                                        <div class="form-group col-sm-6">
                                            <label>D&eacute;but</label>
                                            <input type="date" class="form-control" />
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label>Fin</label>
                                            <input type="date" class="form-control" />
                                        </div>
                                            <p class="help-block">Format de date support&eacute; : aaaa-mm-jj ou jj-mm-aaaa.</p>                                 
    							</div>
                                <div class="col-md-6">
                                    <h3>Poids</h3>
                                        <div class="form-group col-sm-6">
                                            <label>Poids initial</label>
                                            <input type="date" class="form-control" /> kg
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label>Poids objectif</label>
                                            <input type="date" class="form-control" /> kg
                                        </div>
                                </div>
                            </div>
                            <div>
                                <button type="submit" class="btn btn-default">Cr&eacute;er</button>
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
                        <h3>Cr&eacute;er votre nouveau r&eacute;gime</h3>
                         <p>
                        Pour un suivi de votre poids et de vos habitudes alimentaires et sportives, cr�ez un nouveau r&eacute;gime! On vous aidera &agrave; rep&eacute;rer les bonnes habitudes qui vous font perdre vos kilos en trop! De bon conseils par jour vous seront offert!  
                        </p>
                    </div>
                </div>
                <!-- /. ROW  -->
    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="includes/footer.jsp" %>