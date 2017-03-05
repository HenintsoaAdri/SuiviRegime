package s6.suiviRegime.dao;

import java.sql.ResultSet;

import s6.suiviRegime.modele.*;

public class Creation {
	
	public static Utilisateur creerUtilisateur(ResultSet res) throws Exception{
		Utilisateur model = new Utilisateur(
				res.getInt("IDUTILISATEUR"),
				res.getString("NOM"), 
				res.getString("PRENOM"),
				res.getDate("DATENAISSANCE").toLocalDate(),
				res.getString("SEXE"),
				res.getString("EMAIL"),
				res.getString("IDENTIFIANT"),
				res.getString("PASSWORD"),
				res.getString("ADRESSE"));
		return model;
	}
	
	public static SportRegime creerSportRegime(ResultSet res, Regime regime) throws Exception {
		SportRegime model = new SportRegime(
				res.getInt("IDSPORT"),
				res.getString("SPORT"),
				res.getString("ACTIVITES"),
				regime,
				res.getDate("DATESPORT").toLocalDate(),
				res.getInt("RYTHMESPORT"));
		return model;
	}
	public static SportRegime creerSportRegime(ResultSet res) throws Exception {
		return creerSportRegime(res,creerRegime(res));
	}

	public static Sport creerSport(ResultSet res) throws Exception {
		Sport model = new Sport(
				res.getInt("IDSPORT"),
				res.getString("SPORT"),
				res.getString("ACTIVITES"));
		return model;
	}

	public static Poids creerPoids(ResultSet res, Regime regime) throws Exception {
		Poids model = new Poids(
				res.getInt("IDPOIDS"),
				regime,
				res.getDate("DATEPOIDS").toLocalDate(),
				res.getFloat("POIDS"));
		return model;
	}
	public static Poids creerPoids(ResultSet res) throws Exception {
		return creerPoids(res,creerRegime(res));
	}
	
	public static Alimentation creerAlimentation(ResultSet res, Regime regime) throws Exception{
		Alimentation model = new Alimentation(
				res.getInt("IDALIMENTATION"),
				regime,
				res.getString("REPAS"),
				res.getString("BOISSON"),
				res.getInt("PERIODE"),
				res.getDate("DATEALIMENTATION").toLocalDate());
		return model;
	}
	public static Alimentation creerAlimentation(ResultSet res) throws Exception{
		return creerAlimentation(res,creerRegime(res));
	}

	public static Regime creerRegime(ResultSet res, Utilisateur utilisateur) throws Exception{
		Regime model = new Regime(
				res.getInt("IDREGIME"),
				utilisateur,
				res.getDate("DATEDEBUT").toLocalDate(),
				res.getDate("DATEFIN").toLocalDate(),
				res.getFloat("POIDSOBJECTIF"));
		return model;
	}
	public static Regime creerRegime(ResultSet res) throws Exception{
		return creerRegime(res, creerUtilisateur(res));
	}
	
	public static SportConseil creerSportConseil(ResultSet res) throws Exception{
		SportConseil model = new SportConseil(
				res.getInt("IDCONSEILSPORT"),
				res.getString("SPORT"),
				res.getString("ACTIVITES"),
				res.getInt("IDSPORT"),
				res.getFloat("RYTHMECONSEIL"),
				res.getString("DETAIL"));
		return model;
	}
	public static SportConseil creerSportConseil(ResultSet res, Sport sport) throws Exception{
		SportConseil model = new SportConseil(
				res.getInt("IDCONSEILSPORT"),
				sport.getSport(),
				sport.getActivite(),
				sport.getId(),
				res.getFloat("RYTHMECONSEIL"),
				res.getString("DETAIL"));
		return model;
	}
	
	public static AlimentationConseil creerAlimentationConseil(ResultSet res) throws Exception{
		AlimentationConseil model = new AlimentationConseil(
				res.getInt("IDCONSEILALIMENTATION"),
				res.getString("NOMCONSEILALIMENTATION"),
				res.getString("MATIN"),
				res.getString("MIDI"),
				res.getString("SOIR"));
		return model;
	}

}
