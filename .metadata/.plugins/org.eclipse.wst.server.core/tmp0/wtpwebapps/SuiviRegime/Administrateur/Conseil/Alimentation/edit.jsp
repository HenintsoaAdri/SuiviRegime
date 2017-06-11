<%@ include file="../../includes/header.jsp" %>
<% AlimentationConseil conseil = (AlimentationConseil)request.getAttribute("item");
	if(conseil == null) conseil = (AlimentationConseil)request.getAttribute("alimentation");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header">Modifier un conseil alimentation</h2>
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
                          <form role="form" action="/SuiviRegime/Administrateur/Conseil/Alimentation/update" method="post">
                            <div class="row">
                            <input type="hidden" name="alimentation.id" value="<% out.print(conseil.getId()); %>">
                                <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Nom du conseil</label>
                                            <input class="form-control" type="text" required name="alimentation.nom" value="<% out.print(conseil.getNom()); %>">
                                            <p class="help-block">Donner un nom au conseil aidera l'utilisateur à savoir à quel genre de conseil il lit.</p>
                                        </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h2>Conseil</h2>
                                        <fieldset>
                                            <div class="form-group">
                                                <label for="alimentation.matin">Matin</label>
												<textarea class="form-control" rows="4" cols="10" name="alimentation.matin"><% out.print(conseil.getMatin()); %></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="alimentation.midi">Midi</label>
												<textarea class="form-control" rows="4" cols="10" name="alimentation.midi"><% out.print(conseil.getMidi()); %></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="alimentation.soir">Soir</label>
												<textarea class="form-control" rows="4" cols="10" name="alimentation.soir"><% out.print(conseil.getSoir()); %></textarea>
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