package fr.diginamic;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.diginamic.dao.AdditifDaoMysql;
import fr.diginamic.dao.AllergeneDaoMysql;
import fr.diginamic.dao.CategorieDaoMysql;
import fr.diginamic.dao.IngredientDaoMysql;
import fr.diginamic.dao.MarqueDaoMysql;
import fr.diginamic.dao.ProduitDaoMysql;
import fr.diginamic.entities.Additif;
import fr.diginamic.entities.Allergene;
import fr.diginamic.entities.Categorie;
import fr.diginamic.entities.Ingredient;
import fr.diginamic.entities.Marque;
import fr.diginamic.entities.Produit;


/** Intégrer les données de open food facts en base de données locale MariaDb
 * 
 * @author acer
 *
 */
public class IntegrationOpenFoodFacts {
	private static final Logger LOG = LoggerFactory.getLogger( IntegrationOpenFoodFacts.class);
	
	/** Nom du fichier source */
	private static final String fileName = "c:/tmp/open-food-facts-jpa.csv";

	/** tableau des ingrédients pour un produit*/
	static private String[]							tabIngredient;
	/** Une ligne d'ingrédient pour un produit */
	static private String							ligneIngredient;
	/** tableau des Allergènes pour un produit*/
	static private String[]							tabAllergene;
	/** Une ligne d'allergene pour un produit */
	static private String							ligneAllergene;
	/** tableau des Additifs pour un produit*/
	static private String[]							tabAdditif;
	/** Une ligne d'additif pour un produit */
	static private String							ligneAdditif;

	
	/** Transforme un un nombre réèl en chaine de caractères en float
	 * 
	 * @param 	libNombre
	 * @return  float 
	 */
	private static float getFloat( String libNombre) {
		float				nbr 	= 0.0f;
		try {
			nbr  	= Float.parseFloat( libNombre);
			
		}catch( NumberFormatException e) {
			nbr = 0.0f;
		}
		return nbr;
	}

	/** Point d'entrée de l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	 
		List<String>	lignes;
		String[]		tabDonnees;
		long			tempsDeb 	= 0l;
		long			tempsFin 	= 0l;
		
		
		// Prendre le temps au départ
		tempsDeb = System.currentTimeMillis();
		
		// Mettre à jour en base
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager		 em  = emf.createEntityManager();
		EntityTransaction	 et  = em.getTransaction();
		
		// Ouvrir une transaction
		et.begin();
		
		// Ouvrir le fichier des données Open Food Facts
		File 	file = new File( fileName);
		if( !file.exists() ) {
			throw new RuntimeException( "Le fichier " + fileName + " n'existe pas");
		}
		LOG.info( "Traiter le fichier " + fileName );
		
		// Charger la liste des données
		try {
			lignes = FileUtils.readLines( file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException( "Le fichier " + fileName + " n'est pas lisible");
		}	

		boolean 			enteteLue 		= false;
		CategorieDaoMysql 	cateDao 		= new CategorieDaoMysql();
		MarqueDaoMysql 		marqueDao 		= new MarqueDaoMysql();
		ProduitDaoMysql 	ProduitDao 		= new ProduitDaoMysql();
		IngredientDaoMysql 	IngredientDao 	= new IngredientDaoMysql();
		AllergeneDaoMysql 	AllergeneDao 	= new AllergeneDaoMysql();
		AdditifDaoMysql 	AdditifDao 		= new AdditifDaoMysql();
		
		/** Traiter ligne par ligne les données */
		int i = 0;
		String				libProduit = "";
		for (String ligne: lignes){
			if( enteteLue) {
				//tabDonnees 			= ligne.split( "\\|");
				tabDonnees 			= ligne.split( ";");

				// Creer les Categories si pas déja existante
				String				libCategorie  	= tabDonnees[0].trim();
				Categorie 			categorie 		= cateDao.getCategorie( em, libCategorie);

				if( categorie == null) {
					categorie = new Categorie( libCategorie);
					cateDao.insererCategorie(em, categorie);
				}

				// Creer les Marques si pas déja existante
				String			libMarque  	= tabDonnees[1].trim();
				Marque 			marque 		= marqueDao.getMarque( em, libMarque);

				if( marque == null) {
					marque = new Marque( libMarque);
					marqueDao.insererMarque(em, marque);
				}

				// Récupérer les listes
				if( ( tabDonnees.length > 4) && ( tabDonnees[4].length() >= 0)) {
					ligneIngredient 	= tabDonnees[4];
				}else {
					ligneIngredient 	= "" ;
				}
				tabIngredient		= ligneIngredient.split( ",");
				
				if( ( tabDonnees.length > 28) && ( tabDonnees[28].length() >= 0) ) {
					ligneAllergene 		= tabDonnees[28];
				}else {
					ligneAllergene      = "";
				}
				tabAllergene		= ligneAllergene.split( ",");
				
				if( ( tabDonnees.length > 29) && (  tabDonnees[29].length() >= 0)) {
					ligneAdditif 		= tabDonnees[29];
				}else {
					ligneAdditif		= "" ;
				}
				tabAdditif			= ligneAdditif.split( ",");
				
				libProduit  	= tabDonnees[2].trim();
				
				// Creer les Produits  si pas déja existant
				char			grade = '\0' ;
				if( (tabDonnees.length > 3) && ( tabDonnees[3].length() > 0) )  {
					grade			= tabDonnees[3].charAt(0);
				}else {
					grade = '\0' ;
				}
				
				Produit			produit			= null ;
				produit 		= ProduitDao.getProduit( em, libProduit);
				if( produit == null) {
					Float[] tabComposants   = new Float[ 23];
					for( int j = 0 ; j < 23 ; j++) {
						if( tabDonnees.length > (j+5)) {
							// Récupérer l'information dans le fichier
							tabComposants[ j] = getFloat( tabDonnees[j+5].trim());						
						}else {
							tabComposants[ j]  = 0.0f;
						}
					}
				
					produit = new Produit( libProduit, grade, categorie, marque, tabComposants);
					ProduitDao.insererProduit( 
							em, 
							produit);
				}else {
					LOG.error( "Produit : " + libProduit + " déja existant");
				}
		
				// Creer les Ingredients associe au produit
				for( String curIngredient : tabIngredient) {
					if( ( curIngredient != null) 
						&& 
						( !curIngredient.trim().contentEquals( "") )){
						String				libIngredient  	= curIngredient.trim();
						Ingredient 			ingredient 		= IngredientDao.getIngredient( em, libIngredient);

						if( ingredient == null) {
							if( libIngredient.length() > 255) {
								LOG.error("Liste des ingredients trop longue pour le produit : " + libProduit);
							}else {
								System.out.println(" >>>>ingredient : " + libIngredient);
								ingredient = new Ingredient( libIngredient);
								IngredientDao.insererIngredient(em, ingredient);
							
							}
						}
						
						produit.getLstIngredient().add( ingredient);
					}
					
				}

		        
				// Creer les Allergenes associés aux produits
				for( String curAllergene : tabAllergene) {
					if( ( curAllergene != null) 
						&& 
						( !curAllergene.trim().contentEquals( "") )){
						String				libAllergene  	= curAllergene.trim();
						Allergene 			allergene 		= AllergeneDao.getAllergene( em, libAllergene);

						if( allergene == null) {
							allergene = new Allergene( libAllergene);
							AllergeneDao.insererAllergene(em, allergene);
						}
						
						produit.getLstAllergene().add( allergene);
					}
					
				}

				
				// Creer les Additifs associés aux produits
				for( String curAdditif : tabAdditif) {
					if( ( curAdditif != null) 
						&& 
						( !curAdditif.trim().contentEquals( "") )){
						String				libAdditif  	= curAdditif.trim();
						Additif 			Additif 		= AdditifDao.getAdditif( em, libAdditif);

						if( Additif == null) {
							Additif = new Additif( libAdditif);
							AdditifDao.insererAdditif(em, Additif);
						}
						
						produit.getLstAdditif().add( Additif);
					}
					
				}
				

				       
			}else {
				enteteLue = true;
			}
			i++;
			if( i == 13450) break;
			LOG.info("produit : -- " + i + " -- : " + libProduit);

		}
		
		et.commit();
		
		em.close();
		
		// Prendre le temps à la fin
		tempsFin = System.currentTimeMillis();
		
		LOG.info("Le temps d'execution est :  " + ( tempsFin - tempsDeb) / 1000 + " sec");
		
	}
	
}
