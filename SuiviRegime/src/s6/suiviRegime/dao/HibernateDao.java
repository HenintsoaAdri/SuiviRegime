package s6.suiviRegime.dao;

import s6.suiviRegime.modele.BaseModele;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDao {
    private SessionFactory sessionFactory; // = HibernateUtil.getSessionFactory();

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(BaseModele obj) throws Exception{
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.save(obj);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public void findById(BaseModele obj) throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.load(obj,obj.getId());
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public List<BaseModele> findAll(BaseModele obj)  throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(obj.getClass());
            return criteria.list();
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
}
