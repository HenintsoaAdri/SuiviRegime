<%@ include file="../../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Nouveau conseil alimentation</h1>
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
                          <form role="form" action="/SuiviRegime/Administrateur/Conseil/Alimentation/save" method="post">
                            <div class="row">
                                <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Nom du conseil</label>
                                            <input class="form-control" type="text" required name="alimentation.nom">
                                            <p class="help-block">Donner un nom au conseil aidera l'utilisateur à savoir à quel genre de conseil il lit.</p>
                                        </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Conseils</h1>
                                        <fieldset>
                                            <div class="form-group">
                                                <label for="alimentation.matin">Matin</label>
												<textarea rows="4" cols="10" name="alimentation.matin"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="alimentation.midi">Midi</label>
												<textarea class="form-control" rows="4" cols="10" name="alimentation.matin"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="alimentation.soir">Soir</label>
												<textarea class="" rows="4" cols="10" name="alimentation.matin"></textarea>
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