<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Authentification Utilisateur</title>    
    <link rel="stylesheet" href="/SuiviRegime/css/normalize.css">
    <link rel="stylesheet" href="/SuiviRegime/css/bootstrap.min.css">
	<link rel="stylesheet" href="/SuiviRegime/css/login.css">

  </head>
  <body>

    <div class="form">
      
      <ul class="tab-group">
        <li class="tab"><a href="#inscription">Inscription</a></li>
        <li class="tab active"><a href="#connexion">Connexion</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="inscription">   
          <h1>Inscrivez vous gratuitement</h1>
          
          <form action="/SuiviRegime/Utilisateur/new" method="post">
          
	          <div class="top-row">
	            <div class="field-wrap">
	              <label>
	                Nom<span class="req">*</span>
	              </label>
	              <input type="text" required autocomplete="off" name="utilisateur.nom" />
	            </div>
	        
	            <div class="field-wrap">
	              <label>
	                Pr&eacute;nom<span class="req">*</span>
	              </label>
	              <input type="text" required autocomplete="off" name="utilisateur.prenom" />
	            </div>
	          </div>
	
	          <div class="field-wrap">
	            <label>
	              Adresse Email<span class="req">*</span>
	            </label>
	            <input type="email" required autocomplete="off" name="utilisateur.email" />
	          </div>
	          
	          <div class="field-wrap">
	            <label>
	              Date de naissance<span class="req">*</span>
	            </label>
	            <input type="date" required autocomplete="off" name="dateNaissance"/>
	          </div>
	          
	          <div class="field-wrap">
	            <label>
	              Femme
	            </label>
				  <input type="radio" name="utilisateur.sexe" value="F" checked/>
	          </div>
	          
	          <div class="field-wrap">
	            <label>
	              Homme
	            </label>
	              <input type="radio" name="utilisateur.sexe" value="M"/>
	          </div>
	          
	          <div class="field-wrap">
	            <label>
	              Mot de passe<span class="req">*</span>
	            </label>
	            <input type="password" required autocomplete="off" name="password"/>
	          </div>
	          
	          <div class="field-wrap">
	            <label>
	              Confirmer votre Mot de passe<span class="req">*</span>
	            </label>
	            <input type="password" required autocomplete="off"/>
	          </div>
	          
	          <div class="field-wrap">
	            <label>
	              Adresse
	            </label>
	            <input type="text" autocomplete="off" name="utilisateur.adresse"/>
	          </div>	          
	          <button type="submit" class="button button-block">S'inscrire</button>
	          
          </form>

        </div>
        
        <div id="connexion">   
          <h1>Vous revoil�!</h1>
          
          <form action="/SuiviRegime/Utilisateur/Connexion" method="post">
          
            <div class="field-wrap">
            <label>
              Adresse Email<span class="req">*</span>
            </label>
            <input type="email"required name="utilisateur.email"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Mot de passe<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="utilisateur.password"/>
          </div>
           <% if(request.getAttribute("erreur") != null){ %>    
		  <div class="alert alert-danger alert-dismissable fade in">
		    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		    <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
		  </div>     
		  <% } %>
          <button class="button button-block">Se Connecter</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
	</div> <!-- /form -->
    <script src='/SuiviRegime/js/jquery.min.js'></script>
    <script src='/SuiviRegime/js/bootstrap.min.js'></script>
    <script src="/SuiviRegime/js/login.js"></script>
  </body>
</html>
