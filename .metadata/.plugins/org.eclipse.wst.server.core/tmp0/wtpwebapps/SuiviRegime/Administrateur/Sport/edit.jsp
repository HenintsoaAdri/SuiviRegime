<%@page import="java.util.List"%>
<%@ include file="../includes/header.jsp" %>
<% Sport sport = (Sport)request.getAttribute("item");
if(sport == null) sport = (Sport)request.getAttribute("sport"); %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Nouveau sport</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Informations
                        </div>
                        <div class="panel-body">
                          <form role="form" action="/SuiviRegime/Administrateur/Sport/update" method="post">
                            <div class="row">
                            <input type="hidden" name="sport.id" value="<% out.print(sport.getId()); %>">
                                <div class="col-lg-6">
                                     <div class="form-group">
                                        <label>Nom</label>
										<input type="text" name="sport.libelle" value = "<% out.print(sport.getLibelle()); %>"/>
                                        <p class="help-block">Libell&eacute; du sport (ex : Fitness, musculation ...)</p>
                                     </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                     <div class="form-group">
                                        <label>Activit&eacute;</label>
										<input type="text" name="sport.activite" value="<% out.print(sport.getActivite()); %>"/>
                                     </div>
                                     <button type="submit" class="btn btn-success">Enregistrer</button>
                                </div>
						        <% if(request.getAttribute("erreur") != null){ %>    
								<div class="alert alert-danger alert-dismissable fade in">
								   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
								</div>     
								<% } %>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                          </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
<%@ include file="../includes/footer.jsp" %>