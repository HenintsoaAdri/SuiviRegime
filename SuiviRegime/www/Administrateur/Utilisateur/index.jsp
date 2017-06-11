<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% 
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Utilisateurs</h1>
                </div>
                <!-- /.col-lg-12 -->
                <div class="col-lg-12">
                	<a class="btn btn-success" href="/SuiviRegime/Administrateur/Utilisateur/add">
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
                            Liste des utilisateurs
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<% if(liste.getListe() != null && !liste.getListe().isEmpty()) %>
                            <table width="100%" class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nom &amp; Pr&eacute;nom</th>
                                        <th>Date de Naissance</th>
                                        <th>Age</th>
                                        <th>Sexe</th>
                                        <th>Adresse</th>
                                        <th>Email</th>
                                        <th>Nb. r&eacute;gime total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(AnalyseUtilisateur u : (List<AnalyseUtilisateur>)liste.getListe()){ %>
                                    <tr>
                                        <td><% out.print(u.getId()); %></td>
                                        <td><% out.print(u.getFullName()); %></td>
                                        <td><% out.print(u.getDateNaissanceString()); %></td>
                                        <td><% out.print(u.getAgeString()); %></td>
                                        <td><% out.print(u.getSexeString()); %></td>
                                        <td><% out.print(u.getAdresse()); %></td>
                                        <td><% out.print(u.getEmail()); %></td>
                                        <td><% out.print(u.getRegimeTotal()); %></td>
                                        <td><a class="btn btn-default" href="/SuiviRegime/Administrateur/Utilisateur/detail?utilisateur.id=<% out.print(u.getId()); %>">Voir les d&eacute;tails</a></td>
                                        <td><a class="btn btn-warning" href="/SuiviRegime/Administrateur/Utilisateur/edit?item.id=<% out.print(u.getId()); %>">Modifier</a></td>
                                        <td><a class="btn btn-danger" href="/SuiviRegime/Administrateur/Utilisateur/delete?id=<% out.print(u.getId()); %>">Supprimer</a></td>
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
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
                 
<%@ include file="../includes/footer.jsp" %><!DOCTYPE html>