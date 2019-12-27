package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.diginamic.entities.Produit;


public class ProduitDaoMysql implements ProduitDao {

	@Override
	/** Récupérer le produit
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public Produit getProduit(EntityManager em, String libelle) {
		Produit Produit = null;
		String sqlReq = "select f from Produit f where nom_produit = :lib ";		
		TypedQuery<Produit> query1 = em.createQuery( sqlReq, Produit.class);
		query1.setParameter( "lib", libelle);
		try {
			Produit = query1.getSingleResult();
			
		}catch( NoResultException e) {
			return null;
		}
		return Produit;
	}

	@Override
	/** Insérer le produit
	 * *
	 * @param em
	 * @param libelle
	 * @return
	 */
	public void insererProduit(EntityManager em, Produit produit) {
		em.persist( produit);

	}

}
