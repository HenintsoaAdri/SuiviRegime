<%@ include file="../../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% 
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Conseils Sportif</h1>
                </div>
                <!-- /.col-lg-12 -->
                <div class="col-lg-12">
                	<a class="btn btn-success" href="/SuiviRegime/Administrateur/Conseil/Sport/add">
                		<span class="glyphicon glyphicon-plus-sign" ></span> Nouveau
                	</a>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Liste des conseils sportifs
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<% if(liste.getListe() != null && !liste.getListe().isEmpty()) %>
                            <table width="100%" class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Sport</th>
                                        <th>Activit&eacute;</th>
                                        <th>Rythme</th>
                                        <th>D&eacute;tails</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(SportConseil c : (List<SportConseil>)liste.getListe()){ %>
                                    <tr>
                                        <td><% out.print(c.getId()); %></td>
                                        <td><% out.print(c.getSport().getLibelle()); %></td>
                                        <td><% out.print(c.getSport().getActivite()); %></td>
                                        <td><% out.print(c.getRythme()); %> minutes</td>
                                        <td><% out.print(c.getDetails()); %></td>
                                        <td><a class="btn btn-warning" href="/SuiviRegime/Administrateur/Conseil/Sport/edit?item.id=<% out.print(c.getId()); %>">Modifier</a></td>
                                        <td><a class="btn btn-danger" href="/SuiviRegime/Administrateur/Conseil/Sport/delete?id=<% out.print(c.getId()); %>">Supprimer</a></td>
                                    </tr>
                                <% } %>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
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
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
                 
<%@ include file="../../includes/footer.jsp" %>