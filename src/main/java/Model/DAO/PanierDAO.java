package Model.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.metier.LignePanier;
import Model.metier.Panier;

public class PanierDAO {

	public Panier getPanierById(int panierId) {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			session.beginTransaction();
			Panier panier = session.get(Panier.class, panierId);
			// Charger les lignes de panier associées au panier
			if (panier != null) {
				// Forcer le chargement des lignes de panier
				panier.getLignesPanier().size();
			}
			session.getTransaction().commit();
			return panier;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateLignePanierQuantity(int panierId, String productId, int newQuantity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			// 获取购物车中的产品
			LignePanier lignePanier = (LignePanier) session.createQuery(
							"FROM LignePanier WHERE panier.idPanier = :panierId AND produit.idProduit = :productId")
					.setParameter("panierId", panierId)
					.setParameter("productId", productId)
					.uniqueResult();

			// 更新产品数量
			if (lignePanier != null) {
				lignePanier.setQuantite(newQuantity);
				session.update(lignePanier);
			}

			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}
}
