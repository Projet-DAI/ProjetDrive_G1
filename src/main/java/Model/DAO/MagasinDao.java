package Model.DAO;

import java.util.*;


import org.hibernate.Session;
import org.hibernate.query.Query;

import Model.metier.Magasin;


public class MagasinDao {
	
	public List<Magasin> searchMagasinsByKeyword(String keyword) {
        List<Magasin> resultMagasins = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // 使用HQL进行模糊搜索
            String hql = "FROM Magasin M WHERE M.nomMagasin LIKE :keyword OR M.adressMagasin LIKE :keyword";
            Query<Magasin> query = session.createQuery(hql, Magasin.class);
            query.setParameter("keyword", "%" + keyword + "%");

            resultMagasins = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMagasins;
    }
	
}
