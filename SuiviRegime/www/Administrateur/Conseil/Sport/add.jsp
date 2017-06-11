<%@page import="java.util.List"%>
<%@ include file="../../includes/header.jsp" %>

<% List<Sport> liste = (List<Sport>) request.getAttribute("all");%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Nouveau conseil sportif</h1>
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
                          <form role="form" action="/SuiviRegime/Administrateur/Conseil/Sport/save" method="post">
                            <div class="row">
                                <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Sport</label>
                                            <select class="form-control" required name="sport.sport.id">
                                            	<% for(Sport s : liste){ %>
                                            	<option value="<% out.print(s.getId()); %>"><% out.print(s); %></option>
                                            	<% } %>
                                            </select>
                                            <p class="help-block">Veuillez s&eacute;l&eacute;ctionner le sport correspondant &agrave; votre conseil.</p>
                                        </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Conseils</h1>
                                        <fieldset>
                                            <div class="form-group">
                                                <label for="disabledSelect">Rythme</label>
												<input type="number" name="sport.rythme"/>minutes
                                            </div>
                                            <div class="form-group">
                                                <label for="disabledSelect">D&eacute;tails</label>
												<textarea class="form-control" rows="4" cols="10" name="sport.details"></textarea>
                                            </div>
                                            <button type="submit" class="btn btn-success">Enregistrer</button>
                                        </fieldset>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
						        <% if(request.getAttribute("erreur") != null){ %>    
								<div class="alert alert-danger alert-dismissable fade in">
								   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
								</div>     
								<% } %>
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
<%@ include file="../../includes/footer.jsp" %>