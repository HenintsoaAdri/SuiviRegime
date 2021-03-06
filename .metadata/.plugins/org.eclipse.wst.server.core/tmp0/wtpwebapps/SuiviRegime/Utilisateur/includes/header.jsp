<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="s6.suiviRegime.modele.*" %>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Following You ! Mon Tableau de Bord</title>
	<!-- BOOTSTRAP STYLES-->
    <link href="/SuiviRegime/css/bootstrap.min.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="/SuiviRegime/css/font-awesome.min.css" rel="stylesheet" />
     <!-- MORRIS CHART STYLES-->
    <link href="/SuiviRegime/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="/SuiviRegime/css/custom.css" rel="stylesheet" />
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Following You !</a> 
            </div>
		    <div style="color: white;
						padding: 15px 50px 5px 50px;
						float: right;
						font-size: 16px;"> 
				Bonjour, <% out.print(((Utilisateur)session.getAttribute("user")).getFullName()); %>! &nbsp; <a href="/SuiviRegime/Utilisateur/Deconnexion" class="btn btn-danger square-btn-adjust">Deconnexion</a>
			</div>
        </nav>   
           <!-- /. NAV TOP  -->
                <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
					<li class="text-center">
                    	<!-- <img src="img/find_user.png" class="user-image img-responsive"/> -->
                    	<a><i class="fa fa-id-card-o fa-5x" aria-hidden="true"></i></a>
					</li>
                    <li>
                        <a class="active-menu"  href="/SuiviRegime/Utilisateur"><i class="fa fa-dashboard fa-3x"></i> Tableau de bord</a>
                    </li>
                     <li>
                        <a  href="/SuiviRegime/Utilisateur/Regime"><i class="fa fa-calendar fa-2x"></i> R&eacute;gimes</a>
                    </li>
                    <li>
                        <a  href="/SuiviRegime/Utilisateur/Poids"><i class="fa fa-line-chart fa-2x"></i> Poids et &Eacute;volution</a>
                    </li>
					<li>
                        <a   href="/SuiviRegime/Utilisateur/Alimentation"><i class="fa fa-cutlery fa-2x"></i> Alimentation quotidienne</a>
                    </li>	
                      <li  >
                        <a  href="/SuiviRegime/Utilisateur/Sport"><i class="fa fa-heartbeat fa-2x"></i> Sports</a>
                    </li>
                    <li  >
                        <a  href="/SuiviRegime/Utilisateur/Conseil"><i class="fa fa-bullhorn fa-2x"></i> Conseils pratiques</a>
                    </li>				
                </ul>
               
            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
    <script src="/SuiviRegime/js/jquery.min.js"></script>
        <div id="page-wrapper" >