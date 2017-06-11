<%@ include file="../includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	Alimentation alim = (Alimentation)request.getAttribute("alimentation");
%>
			<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Enregistrement</h2>   
                        <h5>Modifier une alimentation.</h5>
                       
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
                           <form method="post" role="form" action="/SuiviRegime/Utilisateur/Alimentation/update">
                           <input type="hidden" name="alimentation.id" value="<% out.print(alim.getId()); %>"/>
                           <div class="row">
                               <div class="col-md-6">
                                   <h3>Votre Alimentation</h3>
                                      <div class="form-group col-sm-6">
                                          <label>Date</label>
                                          <input type="date" class="form-control" name="alimentation.date" value="<% out.print(alim.getDate()); %>"/>
                                      </div>
                                      <div class="form-group col-sm-6">
                                          <label>P&eacute;riode</label>
                                          <select name="alimentation.periode" class="form-control">
                                          <% for(int i = 1; i<4; i++){ %>
                                          	<option value="<% out.print(i);%>" <% if(alim.getPeriode() == i) out.print(" selected");  %>>
                                          		<% out.print(alim.getPeriodeStringValue(i));%>
                                          	</option>
                                          <% } %>
                                          </select>
                                       </div>
                                       <p class="help-block">Format de date support&eacute; : aaaa-mm-jj ou jj-mm-aaaa.</p>                                 
    							</div>
                                <div class="col-md-6">
                                       <div class="form-group col-sm-6">
                                           <label>Repas</label>
                                           <textarea rows="2" class="form-control" name="alimentation.repas">
                                           <% out.print(alim.getRepas()); %>
                                           </textarea>
                                       </div>
                                       <div class="form-group col-sm-6">
                                           <label>Boisson</label>
                                           <textarea rows="1" class="form-control" name="alimentation.boisson">
                                           <% out.print(alim.getBoisson()); %>
                                           </textarea>
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