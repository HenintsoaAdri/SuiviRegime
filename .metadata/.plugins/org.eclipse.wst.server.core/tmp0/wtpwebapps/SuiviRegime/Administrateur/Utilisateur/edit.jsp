<%@ include file="../includes/header.jsp" %>
<% Utilisateur user = (Utilisateur)request.getAttribute("item"); 
	if(user == null) user = (Utilisateur) request.getAttribute("utilisateur");
%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Utilisateur</h1>
                    <h5>Modifier un utilisateur</h5>
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
                          <form role="form" action="/SuiviRegime/Administrateur/Utilisateur/update" method="post">
                            <div class="row">
                            <input type="hidden" name="utilisateur.id" value="<% out.print(user.getId()); %>">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label>Nom</label>
										<input type="text" name="utilisateur.nom" value="<% out.print(user.getNom()); %>"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Pr&eacute;nom</label>
										<input type="text" name="utilisateur.prenom" value="<% out.print(user.getPrenom()); %>"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Date de naissance</label>
										<input type="text" name="dateNaissance" value="<% out.print(user.getDateNaissance()); %>"/>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="utilisateur.sexe" value="F" <% if(user.isFemme())out.print("checked"); %>>
                                            Femme
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="utilisateur.sexe" value="M" <% if(user.isHomme())out.print("checked"); %>>
                                            Homme
                                        </label>
                                    </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                        <fieldset>
                                            <div class="form-group">
                                                <label>Adresse</label>
												<input type="text" name="utilisateur.adresse" value="<% out.print(user.getAdresse()); %>"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Adresse email</label>
												<input type="text" name="utilisateur.email" value="<% out.print(user.getEmail()); %>"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Mot de passe</label>
												<input type="password" name="password" value="<% out.print(user.getPassword()); %>"/>
                                            </div>
                                            <div class="form-group">
                                                <label>Confirmer le mot de passe</label>
												<input type="password" name="confirmPassword" value="<% out.print(user.getPassword()); %>"/>
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