package s6.suiviRegime.dao;

import java.sql.ResultSet;

import s6.suiviRegime.modele.*;

public class Creation {
	
	public static Utilisateur creerUtilisateur(ResultSet res) throws Exception{
		Utilisateur model = new Utilisateur(
				res.getInt("IDUTILISATEUR"),
				res.getString("NOMUTILISATEUR"), 
				res.getString("PRENOMUTILISATEUR"),
				res.getDate("DATENAISSANCEUTILISATEUR"),
				res.getString("SEXEUTILISATEUR"),
				res.getString("EMAILUTILISATEUR"),
				res.getString("IDENTIFIANTUTILISATEUR"),
				res.getString("PASSWORDUTILISATEUR"),
				res.getString("ADRESSEUTILISATEUR"));
		return model;
	}
	
	public static SportRegime creerSportRegime(ResultSet res, Regime regime) throws Exception {
		SportRegime model = new SportRegime(
				res.getInt("IDSPORT"),
				res.getString("LIBELLESPORT"),
				res.getString("ACTIVITESSPORT"),
				regime,
				res.getDate("DATESPORT"),
				res.getInt("RYTHMESPORT"));
		return model;
	}
	public static SportRegime creerSportRegime(ResultSet res) throws Exception {
		return creerSportRegime(res,creerRegime(res));
	}

	public static Sport creerSport(ResultSet res) throws Exception {
		Sport model = new Sport(
				res.getInt("IDSPORT"),
				res.getString("LIBELLESPORT"),
				res.getString("ACTIVITESSPORT"));
		return model;
	}

	public static Poids creerPoids(ResultSet res, Regime regime) throws Exception {
		Poids model = new Poids(
				res.getInt("IDPOIDS"),
				regime,
				res.getDate("DATEPOIDS"),
				res.getFloat("VALEURPOIDS"));
		return model;
	}
	public static Poids creerPoids(ResultSet res) throws Exception {
		return creerPoids(res,creerRegime(res));
	}
	
	public static Alimentation creerAlimentation(ResultSet res, Regime regime) throws Exception{
		Alimentation model = new Alimentation(
				res.getInt("IDALIMENTATION"),
				regime,
				res.getString("REPASALIMENTATION"),
				res.getString("BOISSONALIMENTATION"),
				res.getInt("PERIODEALIMENTATION"),
				res.getDate("DATEALIMENTATION"));
		return model;
	}
	public static Alimentation creerAlimentation(ResultSet res) throws Exception{
		return creerAlimentation(res,creerRegime(res));
	}

	public static Regime creerRegime(ResultSet res, Utilisateur utilisateur) throws Exception{
		Regime model = new Regime(
				res.getInt("IDREGIME"),
				utilisateur,
				res.getDate("DATEDEBUTREGIME"),
				res.getDate("DATEFINREGIME"),
				res.getFloat("POIDSOBJECTIFREGIME"));
		return model;
	}
	public static Regime creerRegime(ResultSet res) throws Exception{
		return creerRegime(res, creerUtilisateur(res));
	}
	
	public static SportConseil creerSportConseil(ResultSet res) throws Exception{
		SportConseil model = new SportConseil(
				res.getInt("IDSPORTCONSEIL"),
				res.getInt("IDSPORT"),
				res.getString("LIBELLESPORT"),
				res.getString("ACTIVITESSPORT"),
				res.getFloat("RYTHMESPORTCONSEIL"),
				res.getString("DETAILSPORTCONSEIL"));
		return model;
	}
	public static SportConseil creerSportConseil(ResultSet res, Sport sport) throws Exception{
		SportConseil model = new SportConseil(
				res.getInt("IDSPORTCONSEIL"),
				sport,
				res.getFloat("RYTHMESPORTCONSEIL"),
				res.getString("DETAILSPORTCONSEIL"));
		return model;
	}
	
	public static AlimentationConseil creerAlimentationConseil(ResultSet res) throws Exception{
		AlimentationConseil model = new AlimentationConseil(
				res.getInt("IDALIMENTATIONCONSEIL"),
				res.getString("NOMALIMENTATIONCONSEIL"),
				res.getString("MATINALIMENTATIONCONSEIL"),
				res.getString("MIDIALIMENTATIONCONSEIL"),
				res.getString("SOIRALIMENTATIONCONSEIL"));
		return model;
	}

}
