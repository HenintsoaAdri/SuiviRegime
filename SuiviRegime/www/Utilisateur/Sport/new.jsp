<%@ include file="../includes/header.jsp" %>
<%@page import="s6.suiviRegime.utilitaire.*"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.util.List"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user");
	AnalyseRegime regime = (AnalyseRegime)request.getAttribute("regimeActif");
	List<Sport> sport = (List<Sport>)request.getAttribute("all");
%>
			<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                     <h2>Nouvel enregistrement</h2>   
                        <h5>Enregistrer une nouvelle s&eacute;ance de sport.</h5>
                       
                    </div>
                </div>
                 <!-- /. ROW  -->
                 <hr />
                 <% if(regime != null){ %>
                 <div class="row">
	                <div class="col-md-4 col-sm-12 col-xs-12">           
						<div class="panel panel-back noti-box">
			                <span class="icon-box bg-color-blue set-icon">
			                    <i class="fa fa-calendar-o"></i>
			                </span>
			                <div class="text-box" >
			                    <p class="main-text"><% out.print(regime.getPoidsInitial()); %> kg</p>
			                    <p class="text-muted">Initial</p>
			                </div>
			             </div>
			     	</div>
	                <div class="col-md-4 col-sm-6 col-xs-6">           
						<div class="panel panel-back noti-box">
			                <span class="icon-box bg-color-red set-icon">
			                    <i class="fa fa-clock-o"></i>
			                </span>
			                <div class="text-box" >
			                    <p class="main-text"><% out.print(regime.getMinuteSportParSemaineString()); %></p>
			                    <p class="text-muted">sport par Semaine</p>
			                </div>
			             </div>
				     </div>
	                <div class="col-md-4 col-sm-12 col-xs-12">           
						<div class="panel panel-back noti-box">
			                <span class="icon-box bg-color-green set-icon">
			                    <i class="fa fa-bell-o"></i>
			                </span>
			                <div class="text-box" >
			                    <p class="main-text"><% out.print(regime.getPoidsObjectif()); %> kg</p>
			                    <p class="text-muted">Objectif</p>
			                </div>
			             </div>
			     	</div>
			     </div>
			     <% } %>
	             <div class="row">
	                <div class="col-md-12">
	                    <!-- Form Elements -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                            Informations
	                        </div>
	                        <div class="panel-body">
	                           <form method="post" role="form" action="/SuiviRegime/Utilisateur/Sport/save">
	                            <div class="row">
	                                <div class="col-md-6">
	                                        <div class="form-group">
                                            <label>Sport pratiqu&eacute;</label>
                                            <% for(Sport s : sport){ %>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sport.sport.id" value="<% out.print(s.getId()); %>" checked />
                                                    <% out.print(s); %>
                                                </label>
                                            </div>
                                            <% } %>
                                        </div>
	    							</div>
	                                <div class="col-md-6">
	                                    <h3>S&eacute;ance sport</h3>
	                                        <div class="form-group col-sm-6">
	                                            <label>Date</label>
	                                            <input type="date" class="form-control" name="date" value="<% out.print(LocalDate.now()); %>"/>
	                                        </div>
	                                        <div class="form-group col-sm-6">
	                                            <label>Dur&eacute;e</label>
	                                            <input type="number" class="form-control" name="rythme" min="1"/> minutes
	                                        </div>
				                            <div style="float: right;">
				                                <button type="submit" class="btn btn-success">Enregistrer</button>
				                            </div>  
	                                </div>
	                            </div>
							        <% if(request.getAttribute("erreur") != null){ %>    
									<div class="alert alert-danger alert-dismissable fade in">
									   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
									   <strong>Probl&egrave;me!</strong> <% out.print(request.getAttribute("erreur")); %>
									</div>     
									<% } %>                                  
	                          </form>
	                        </div>
	                    </div>
	                     <!-- End Form Elements -->
	                </div>
	            </div>
                <!-- /. ROW  -->
                <div class="row">
                    <div class="col-md-12">
                        <h3>Enregistrer une nouvelle s&eacute;ance de sport</h3>
                         <p>
                        Pour un suivi de vos activit&eacute;s plus efficace, essayer au maximum d'enregister vos s&eacute;s quotidiennement.  
                        </p>
                    </div>
                </div>
                <!-- /. ROW  -->
    </div>
             <!-- /. PAGE INNER  -->
<%@ include file="../includes/footer.jsp" %>