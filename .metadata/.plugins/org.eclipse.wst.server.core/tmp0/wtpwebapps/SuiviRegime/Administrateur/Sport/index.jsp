<%@ include file="../includes/header.jsp" %>
<%@page import="java.util.List"%>
<% 
	BaseModelePagination liste = (BaseModelePagination)request.getAttribute("liste");
	int pageNumero = (int)request.getAttribute("page");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Sport</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Liste des sports
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
                                        <th>Nb. conseil total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(AnalyseSport s : (List<AnalyseSport>)liste.getListe()){ %>
                                    <tr>
                                        <td><% out.print(s.getId()); %></td>
                                        <td><% out.print(s.getLibelle()); %></td>
                                        <td><% out.print(s.getActivite()); %></td>
                                        <td><% out.print(s.getConseilTotal()); %></td>
                                        <td><a class="btn btn-danger" href="/SuiviRegime/Administrateur/Sport/delete?id=<% out.print(s.getId()); %>">Supprimer</a></td>
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
                 
<%@ include file="../includes/footer.jsp" %>