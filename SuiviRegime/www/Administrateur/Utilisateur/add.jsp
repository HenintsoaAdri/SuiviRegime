<%@ include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Nouvel utilisateur</h1>
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
                          <form role="form" action="/SuiviRegime/Administrateur/Utilisateur/save" method="post">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label>Nom</label>
										<input type="text" name="utilisateur.nom"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Pr&eacute;nom</label>
										<input type="text" name="utilisateur.prenom"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Date de naissance</label>
										<input type="text" name="dateNaissance"/>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="utilisateur.sexe" value="F" checked>
                                            Femme
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="utilisateur.sexe" value="M">
                                            Homme
                                        </label>
                                    </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                        <fieldset>
                                            <div class="form-group">
                                                <label>Adresse</label>
												<input type="text" name="utilisateur.adresse"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Adresse email</label>
												<input type="text" name="utilisateur.email"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Mot de passe</label>
												<input type="text" name="password"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Confirmer le mot de passe</label>
												<input type="text" name="confirmPassword"/>
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
<%@ include file="../includes/footer.jsp" %>