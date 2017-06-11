<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% 
	Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Utilisateurs</h1>
                </div>
                <!-- /.col-lg-12 -->
                <div class="col-lg-12">
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Liste des r&eacute;gimes suivis par l'utilisateur
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div class="col-sm-12">
                        		<p>Nom &amp; Pr&eacute;nom : <% out.print(user.getFullName()); %><br>
		                            <% out.print(user.getFullDateNaissanceString()); %><br>
		                            <% out.print(user.getSexeString()); %><br>
		                            Adresse : <% out.print(user.getAdresse()); %><br>
		                            Email : <% out.print(user.getEmail()); %></p>
                        	</div>
                        	<% if(liste.getListe() != null && !liste.getListe().isEmpty()){ %>
                            <table width="100%" class="table table-striped table-hover">
                                <thead>
		                                <tr>
		                                    <th>#</th>
		                                    <th>D&eacute;but</th>
		                                    <th>Fin</th>
		                                    <th>Jours Restants</th>
		                                    <th>Poids Initial</th>
		                                    <th>Poids Objectif</th>
		                                    <th>Moyenne de poids perdu</th>
		                                    <th>Moyenne de sport pratiqu&eacute;</th>
		                                </tr>
		                            </thead>
                                <tbody>
                                <% for(AnalyseRegime a : (List<AnalyseRegime>)liste.getListe()){ %>
                                    <tr>
                                        <td><% out.print(a.getId()); %></td>
                                        <td><% out.print(a.getDebutString()); %></td>
                                        <td><% out.print(a.getFinString()); %></td>
                                        <td><% out.print(a.getJourRestant()); %></td>
                                        <td><% out.print(a.getPoidsInitial()); %></td>
                                        <td><% out.print(a.getPoidsObjectif()); %></td>
                                        <td><% out.print(a.getPoidsPerduParSemaineString()); %></td>
                                        <td><% out.print(a.getMinuteSportParSemaineString()); %></td>
                                        <td class=<% if(a.isActive()){ %>"success"> Actif <span class="glyphicon glyphicon-ok"></span>
                                        	<% }else{ %>"warning"> Inactif <span class="glyphicon glyphicon-remove"></span>
                                        	<% } %></td>
                                        <td><a class="btn btn-default" href="/SuiviRegime/Administrateur/Utilisateur/Regime/Poids?regime.id=<% out.print(a.getId()); %>">D&eacute;tails poids</a></td>
                                        <td><a class="btn btn-default" href="/SuiviRegime/Administrateur/Utilisateur/Regime/Alimentation?regime.id=<% out.print(a.getId()); %>">D&eacute;tails aliment.</a></td>
                                        <td><a class="btn btn-default" href="/SuiviRegime/Administrateur/Utilisateur/Regime/Sport?regime.id=<% out.print(a.getId()); %>">D&eacute;tails sport</a></td>
                                        <td><a class="btn btn-danger" href="/SuiviRegime/Administrateur/Utilisateur/Regime/delete?id=<% out.print(a.getId()); %>">Supprimer</a></td>
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
                        <% }
                            else out.print("Aucun r&eacute;gime cr&eacute;&eacute; pour l'instant"); %>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
                 
<%@ include file="../includes/footer.jsp" %>