<%@ include file="../../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% 
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Conseils d'Alimentation</h1>
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
                            Liste des conseils pour l'alimentation
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<% if(liste.getListe() != null && !liste.getListe().isEmpty()) %>
                            <table width="100%" class="table table-striped table-hover">
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
                                        <td><a class="btn btn-warning" href="/SuiviRegime/Administrateur/Conseil/Alimentation/edit?item.id=<% out.print(c.getId()); %>">Modifier</a></td>
                                        <td><a class="btn btn-danger" href="/SuiviRegime/Administrateur/Conseil/Alimentation/delete?id=<% out.print(c.getId()); %>">Supprimer</a></td>
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
                 
<%@ include file="../../includes/footer.jsp" %>