<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Following You ! Connexion Administrateur</title>

	<!-- Bootstrap Core CSS -->
    <link href="/SuiviRegime/css/bootstrap.min.css" rel="stylesheet" />

    <!-- MetisMenu CSS -->
    <link href="/SuiviRegime/css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/SuiviRegime/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/SuiviRegime/css/font-awesome.min.css" rel="stylesheet" />
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Veuillez vous identifier</h3>
                    </div>
                    <div class="panel-body">
                        <form method="post" role="form" action="/SuiviRegime/Administrateur/login">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Identifiant" name="admin.identifiant" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Mot de passe" name="admin.password" type="password">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="submit" class="btn btn-lg btn-success btn-block">Connexion</button>
                            </fieldset>
				           <% if(request.getAttribute("erreur") != null){ %>    
						  <div class="alert alert-danger alert-dismissable fade in">
						    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						    <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
						  </div>     
						  <% } %>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- jQuery -->
    <script src="/SuiviRegime/js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/SuiviRegime/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/SuiviRegime/js/jquery.metisMenu.js"></script>   

    <!-- Custom Theme JavaScript -->
    <script src="/SuiviRegime/js/sb-admin-2.min.js"></script>  
</body>

</html>
