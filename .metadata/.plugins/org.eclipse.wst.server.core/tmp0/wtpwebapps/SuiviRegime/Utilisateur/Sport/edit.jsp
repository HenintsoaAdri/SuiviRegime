<%@ include file="../includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.util.List"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	SportRegime sport = (SportRegime)request.getAttribute("sport");
	List<Sport> all = (List<Sport>)request.getAttribute("all");
%>
			<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Modifier un enregistrement</h2>   
                        <h5>Modifier une séance de sport.</h5>
                       
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
                            <input type="hidden" name="sport.id" value="<% out.print(sport.getId()); %>"/>
                            <div class="row">
                                <div class="col-md-6">
                                        <div class="form-group">
                                           <label>Sport pratiqu&eacute;</label>
                                           <% for(Sport s : all){ %>
                                           <div class="radio">
                                               <label>
                                                   <input type="radio" name="sport.sport.id" value="<% out.print(s.getId()); %>" <% if(s.getId() == sport.getSport().getId()) out.print("checked"); %> />
                                                   <% out.print(s); %>
                                               </label>
                                           </div>
                                           <% } %>
                                       </div>
    							</div>
                                <div class="col-md-6">
                                    <h3>S&eacute;ance sport</h3>
                                        <div class="form-group col-sm-6">
                                            <label>Date</label>
                                            <input type="date" class="form-control" name="date" value="<% out.print(sport.getDate()); %>"/>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label>Dur&eacute;e</label>
                                            <input type="number" class="form-control" name="rythme" value="<% out.print(sport.getRythme()); %>"/> minutes
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
                        <h3>Enregistrer une nouvelle s&eacute;ance de sport</h3>
                         <p>
                        Pour un suivi de vos activit&eacute;s plus efficace, essayer au maximum d'enregister vos s&eacute;s quotidiennement.  
                        </p>
                    </div>
                </div>
                <!-- /. ROW  -->
    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="../includes/footer.jsp" %>