package s6.suiviRegime.modele;

import java.util.List;

public class BaseModelePagination {
	List<? extends BaseModele> liste;
	Class<? extends BaseModele> classe;
	int maxResult;
	int page;
	long totalResult;
	
	public BaseModelePagination(Class<? extends BaseModele> model, int maxResult, int page){
		setClasse(model);
		setMaxResult(maxResult);
		setPage(page);
	}
	
	public List<? extends BaseModele> getListe() {
		return liste;
	}
	public void setListe(List<? extends BaseModele> liste) {
		this.liste = liste;
	}
	public Class<? extends BaseModele> getClasse() {
		return classe;
	}
	public void setClasse(Class<? extends BaseModele> classe) {
		this.classe = classe;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(long total) {
		this.totalResult = total;
	}
	
	public int getNombrePage(){
		return (int) ((getTotalResult()/getMaxResult()) + 1);
	}
	public boolean isMoreThanOne(){
		return getNombrePage() > 1;
	}
	public int getFirstResult() {
		return (getPage()*getMaxResult());
	}
}
