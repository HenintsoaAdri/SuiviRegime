<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% 
	Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	AnalyseRegime regime = (AnalyseRegime)request.getAttribute("regime");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header">Utilisateurs - Poids</h2>
                    <ul class="breadcrumb">
					  <li><a href="/SuiviRegime/Administrateur/Utilisateur">Utilisateurs</a></li>
					  <li><a href="/SuiviRegime/Administrateur/Utilisateur/detail?utilisateur.id=<% out.print(user.getId()); %>"><% out.print(user.getFullName()); %></a></li>
					  <li class="active">D&eacute;tails Poids</li>
					</ul>
                </div>
                <!-- /.col-lg-12 -->
                <div class="col-lg-12">
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            D&eacute;tails de r&eacute;gime suivi par l'utilisateur
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div class="col-sm-6">
                        		<p>Nom &amp; Pr&eacute;nom : <% out.print(user.getFullName()); %><br>
		                            <% out.print(user.getFullDateNaissanceString()); %><br>
		                            <% out.print(user.getSexeString()); %><br>
		                            Adresse : <% out.print(user.getAdresse()); %><br>
		                            Email : <% out.print(user.getEmail()); %></p>
                        	</div>
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
                                    <tr>
                                        <td><% out.print(regime.getId()); %></td>
                                        <td><% out.print(regime.getDebutString()); %></td>
                                        <td><% out.print(regime.getFinString()); %></td>
                                        <td><% out.print(regime.getJourRestant()); %></td>
                                        <td><% out.print(regime.getPoidsInitial()); %></td>
                                        <td><% out.print(regime.getPoidsObjectif()); %></td>
                                        <td><% out.print(regime.getPoidsPerduParSemaineString()); %></td>
                                        <td><% out.print(regime.getMinuteSportParSemaineString()); %></td>
                                        <td class=<% if(regime.isActive()){ %>"success"> Actif <span class="glyphicon glyphicon-ok"></span>
                                        	<% }else{ %>"warning"> Inactif <span class="glyphicon glyphicon-remove"></span>
                                        	<% } %></td>
                                        <td><a class="btn btn-default" href="/SuiviRegime/Administrateur/Utilisateur/Regime/Alimentation?regime.id=<% out.print(regime.getId()); %>">D&eacute;tails aliment.</a></td>
                                        <td><a class="btn btn-default" href="/SuiviRegime/Administrateur/Utilisateur/Regime/Sport?regime.id=<% out.print(regime.getId()); %>">D&eacute;tails sport</a></td>
                                        <td><a class="btn btn-danger" href="/SuiviRegime/Administrateur/Utilisateur/Regime/delete?id=<% out.print(regime.getId()); %>">Supprimer</a></td>
                                    </tr>
                                </tbody>
                            </table>
                        	<% if(liste.getListe() != null && !liste.getListe().isEmpty()){ %>
                            <table width="100%" class="table table-striped table-hover">
                                <thead>
		                                <tr>
		                                    <th>#</th>
		                                    <th>Date</th>
		                                    <th>Poids</th>
		                                </tr>
		                            </thead>
                                <tbody>
                                <% for(Poids a : (List<Poids>)liste.getListe()){ %>
                                    <tr>
                                        <td><% out.print(a.getId()); %></td>
                                        <td><% out.print(a.getDateString()); %></td>
                                        <td><% out.print(a.getValeur()); %></td>
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
                            else out.print("Aucun poids enregistr&eacute; pour l'instant"); %>
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
                 
<%@ include file="../includes/footer.jsp" %><!DOCTYPE html>