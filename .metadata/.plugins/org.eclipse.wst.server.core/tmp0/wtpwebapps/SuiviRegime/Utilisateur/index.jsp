<%@ include file="includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<% Utilisateur user = (Utilisateur)session.getAttribute("user"); 
	AnalyseRegime regime = (AnalyseRegime)session.getAttribute("regime");
%>
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Mon Tableau de Bord</h2>   
                        <h5>Bonjour <% out.print(user.getFullName()); %> , Heureux de vous revoir! </h5>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                  <hr />
                <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-red set-icon">
                    <i class="fa fa-envelope-o"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><% out.print(regime.getMinuteSportParSemaineString()); %></p>
                    <p class="text-muted">Sport par Semaine</p>
                </div>
             </div>
		     </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><% out.print(regime.getPoidsObjectif()); %> kg</p>
                    <p class="text-muted">Objectif</p>
                </div>
             </div>
		     </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bell-o"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><% out.print(regime.getJourRestant()); %> jours</p>
                    <p class="text-muted">Restants</p>
                </div>
             </div>
		     </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">           
			<div class="panel panel-back noti-box">
                <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                <div class="text-box" >
                    <p class="main-text"><% out.print(regime.getPoidsPerduParSemaineString()); %></p>
                    <p class="text-muted">Perdus par Semaine</p>
                </div>
             </div>
		     </div>
			</div>
                 <!-- /. ROW  -->
            <hr />                
            <div class="row">
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <div class="panel back-dash">
                        <i class="fa fa-bullhorn fa-2x"></i><strong> &nbsp; TIPS DU JOUR</strong>
                        <% AlimentationConseil tips =  (AlimentationConseil)request.getAttribute("randomTips");%>
                        <p class="text-muted"><% out.print(tips.getNom()); %></p>
                        <p>Matin : <% out.print(tips.getMatin()); %></p>
                        <p>Midi : <% out.print(tips.getMidi()); %></p>
                        <p>Soir : <% out.print(tips.getSoir()); %></p>
                    </div>
                       
                </div>
                <div class="col-md-6 col-sm-12 col-xs-12 ">
                    <div class="panel ">
			          <div class="main-temp-back">
			            <div class="panel-body">
			              <div class="row">
			                <div class="col-xs-12 text-left"> <i class="fa fa-cloud fa-3x"></i> Antananarivo </div>
			                <div class="col-xs-12 text-right">
			                  <div class="text-temp"> <% out.print(DateUtil.getInstance().LocalDateToString(LocalDate.now())); %> </div>
			                </div>
			              </div>
			            </div>
			          </div>
	          		</div>
                    <div class="panel panel-back noti-box row">
                    	<div class="col-xs-2">
			                <span class="icon-box bg-color-green set-icon">
			                    <i class="fa fa-address-book"></i>
			                </span>
		                </div>
		                <div class="text-box col-xs-10" >
		                    <p class="main-text">Mes informations</p>
		                    <p class="text-muted">Nom & Pr&eacute;nom : <% out.print(user.getFullName()); %></p>
                            <p class="text-muted"><% out.print(user.getFullDateNaissanceString()); %></p>
                            <p class="text-muted"><% out.print(user.getSexeString()); %></p>
                            <p class="text-muted">Adresse : <% out.print(user.getAdresse()); %></p>
                            <p class="text-muted">Adresse email : <% out.print(user.getEmail()); %></p>
		                </div>
             		</div>
    			</div>              
        	</div>
    	</div>
             <!-- /. PAGE INNER  -->
<%@ include file="includes/footer.jsp" %>