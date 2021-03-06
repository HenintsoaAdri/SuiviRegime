<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
         <div id="page-inner">
             <div class="row">
             	<div class="col-xs-12">
             		<h2>Alimentation</h2>
             		<p class="text-muted">
             			Voici une liste de quelques conseils d'Alimentation que vous pourriez suivre! N'h�sitez pas � enregistrer votre poids et voir quels conseils vous conviennent le mieux!
             		</p>
             	</div>
             </div>
             <% if(liste.getListe() != null && !liste.getListe().isEmpty()){ %>
		     <div class="row">
             	<div class="col-xs-12">
					<div class="panel panel-default">
		                <div class="panel-heading">
		                    Conseils alimentations
		                </div>
		                <div class="panel-body">
		                    <div class="table-responsive">
		                        <table class="table table-hover">
		                            <thead>
		                                <tr>
		                                    <th>#</th>
		                                    <th>Nom</th>
		                                    <th>Matin</th>
		                                    <th>Midi</th>
		                                    <th>Soir</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            <% for(AlimentationConseil c : (List<AlimentationConseil>)liste.getListe()){ %>
		                                <tr>
		                                    <td><% out.print(c.getId()); %></td>
		                                    <td><% out.print(c.getNom()); %></td>
		                                    <td><% out.print(c.getMatin()); %></td>
		                                    <td><% out.print(c.getMidi()); %></td>
		                                    <td><% out.print(c.getSoir()); %></td>
		                                    
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
		        </div>  
	        </div>
	        <% }%>       
	    </div>
             <!-- /. PAGE INNER  -->                          
<%@ include file="../includes/footer.jsp" %>