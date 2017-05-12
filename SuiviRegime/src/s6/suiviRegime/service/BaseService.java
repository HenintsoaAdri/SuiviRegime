package s6.suiviRegime.service;
import java.util.List;

import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.BaseModele;

public class BaseService {
	private HibernateDao dao;
	private BaseService(){
		if(dao == null) dao = new HibernateDao();
	}
	private static class Holder
	{		
		private final static BaseService instance = new BaseService();
	}
	public static BaseService getInstance(){
		return Holder.instance;
	}
	
	public HibernateDao getDao() {
		return dao;
	}
	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}

	public void save(BaseModele model) throws Exception{;
		dao.save(model);
	}
	public BaseModele get(String idBase, BaseModele model) throws Exception{
		try{
			int id = Integer.parseInt(idBase.trim());
			model.setId(id);
			dao.findById(model);
			return model;
		}catch(NumberFormatException e){
			throw new Exception("Element introuvable, valeur incorrecte");
		}
	}	
	public void delete(String idBase, BaseModele base) throws Exception{
		dao.delete(get(idBase, base));
	}
	public void update(BaseModele model) throws Exception{
		dao.update(model);
	}
	public BaseModele getRandom(BaseModele model){
		return dao.getRandom(model);
	}
	public List<BaseModele> findAll(BaseModele model) throws Exception{
		return dao.findAll(model);
	}
}
