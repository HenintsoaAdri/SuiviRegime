package s6.suiviRegime.dao;

import java.sql.ResultSet;

import s6.suiviRegime.modele.*;

public class Creation {
	
	public static Utilisateur creerUtilisateur(ResultSet res) throws Exception{
		Utilisateur model = new Utilisateur(
				res.getInt("IDPROFIL"),
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

	public static SportRegime creerSportRegime(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Sport creerSport(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Poids creerPoids(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Alimentation creerAlimentation(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Regime creerRegime(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

	public static SportConseil creerSportConseil(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

	public static AlimentationConseil creerAlimentationConseil(ResultSet res) {
		// TODO Auto-generated method stub
		return null;
	}

}
