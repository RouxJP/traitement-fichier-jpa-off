package fr.diginamic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.io.FileUtils;

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



public class IntegrationOpenFoodFacts {
	private static final String fileName = "c:/tmp/open-food-facts-jpa.csv";

	static private Map<String, Ingredient>			lstIngredientExistant;
	static private Map<String, Allergene>			lstAllergeneExistant;
	static private Map<String, Additif>				lstAdditifExistant;
	static private ArrayList<Produit>				lstProduit;
	static private String[]							tabIngredient;
	static private String							ligneIngredient;
	static private String[]							tabAllergene;
	static private String							ligneAllergene;
	static private String[]							tabAdditif;
	static private String							ligneAdditif;

	/** Lire le contenu du fichier open-food-facts et cahrgers les différentes données 
	 * en mémoire sous forme de liste
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
/**	
	static private  void chargerFichier() throws FileNotFoundException, IOException {
		Produit 		produit;
		List<String>	lignes;
		String[]		tabDonnees;
		String[]		tabIngredient;
		String			ligneIngredient;
		String[]		tabAllergene;
		String			ligneAllergene;
		String[]		tabAdditif;
		String			ligneAdditif;
		
		lstIngredientExistant 	= new HashMap<String, Ingredient>();
		lstAllergeneExistant 	= new HashMap<String, Allergene>();
		lstAdditifExistant 		= new HashMap<String, Additif>();
		
		// Ouvrir le fichier des données Open Food Facts
		File 	file = new File( fileName);
		if( !file.exists() ) {
			throw new FileNotFoundException( "Le fichier " + fileName + " n'existe pas");
		}
		
		// Charger la liste des existants
		lignes = FileUtils.readLines( file, "UTF-8");	

		for (String ligne: lignes){
			// Supprimer les [ ...] de liste
			tabDonnees 			= ligne.split( "|");
			
			String sqlReq = "select f from Categorie f where libelle = :lib ";
			TypedQuery<Categorie> query1 = em.createQuery( sqlReq, Categorie.class);

			
			/
			if( tabDonnees[4].length() >= 2) {
				ligneIngredient 	= tabDonnees[4].substring( 1, tabDonnees[4].length()-1);
			}else {
				ligneIngredient 	= "" ;
			}
			tabIngredient		= ligneIngredient.split( ",");
			
			if( tabDonnees[30].length() >= 2) {
				ligneAllergene 		= tabDonnees[30].substring( 1, tabDonnees[30].length()-1);
			}else {
				ligneAllergene      = "";
			}
			tabAllergene		= ligneAllergene.split( ",");
			
			if( tabDonnees[31].length() >= 2) {
				ligneAdditif 		= tabDonnees[31].substring( 1, tabDonnees[31].length()-1);
			}else {
				ligneAdditif		= "" ;
			}
			tabAdditif			= ligneAdditif.split( ",");
			
			
			for( String curIngredient : tabIngredient) {
				if( ( curIngredient != null) 
					&& 
					( !curIngredient.trim().contentEquals( "") )){
						if( lstIngredientExistant.get( curIngredient.trim()) == null){
							lstIngredientExistant.put( curIngredient.trim(), 
													new Ingredient( curIngredient.trim()));
							
						}
				}
				
			}
			
			Allergene allergeneExistant ;
			Long		nbrAllergenePresent;
			for( String curAllergene : tabAllergene) {
				if( ( curAllergene != null) 
						&& 
						( !curAllergene.trim().contentEquals( "") )){
					if( lstAllergeneExistant.get( curAllergene.trim()) == null){
						lstAllergeneExistant.put( curAllergene.trim(), 
												new Allergene( curAllergene.trim()));
						
					}else {
						allergeneExistant = lstAllergeneExistant.get( curAllergene.trim());
						nbrAllergenePresent = allergeneExistant.getNbrFoisPresent() + 1 ;
						allergeneExistant.setNbrFoisPresent( nbrAllergenePresent);
						lstAllergeneExistant.put( curAllergene.trim(), allergeneExistant);
					}
					
				}
				
			}

			
			Additif additifExistant ;
			Long	nbrAdditifPresent;
			for( String curAdditif : tabAdditif) {
				if( ( curAdditif != null) 
						&& 
						( !curAdditif.trim().contentEquals( "") )){
					if( lstAdditifExistant.get( curAdditif.trim()) == null){
						lstAdditifExistant.put( curAdditif.trim(), 
												new Additif( curAdditif.trim()));
						
					}else {
						additifExistant = lstAdditifExistant.get( curAdditif.trim());
						nbrAdditifPresent = additifExistant.getNbrFoisPresent() + 1 ;
						additifExistant.setNbrFoisPresent( nbrAdditifPresent);
						lstAdditifExistant.put( curAdditif.trim(), additifExistant);
					}
					
				}
			}
			
		}
		
		// Charger la liste de Produits
		for (String ligne: lignes){
				produit = new Produit( ligne);
				lstProduit.add( produit);
		}
		System.out.println("Nombre de lignes :" + lignes.size());	
		
		
	}
	**/
	
	/** Transforme un un nombre réèl en chaine de caractères en float
	 * 
	 * @param 	libNombre
	 * @return float 
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

	public static void main(String[] args) {
		List<String>	lignes;
		String[]		tabDonnees;
		
		// Mettre à jour en base
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager		 em  = emf.createEntityManager();
		EntityTransaction	 et  = em.getTransaction();
		
		et.begin();
		
		// Ouvrir le fichier des données Open Food Facts
		File 	file = new File( fileName);
		if( !file.exists() ) {
			throw new RuntimeException( "Le fichier " + fileName + " n'existe pas");
		}
		
		// Charger la liste des existants
		try {
			lignes = FileUtils.readLines( file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException( "Le fichier " + fileName + " n'est pas lisible");
		}	

		boolean enteteLue = false;
		int i = 0;
		CategorieDaoMysql 	cateDao 		= new CategorieDaoMysql();
		MarqueDaoMysql 		marqueDao 		= new MarqueDaoMysql();
		ProduitDaoMysql 	ProduitDao 		= new ProduitDaoMysql();
		IngredientDaoMysql 	IngredientDao 	= new IngredientDaoMysql();
		
		for (String ligne: lignes){
			if( enteteLue) {
				tabDonnees 			= ligne.split( ";");
				//System.out.println( "tabDonnees[0] : " + tabDonnees[0]);		

				// Creer les Categories si pas déja existante
				String				libCategorie  	= tabDonnees[0].trim();
				Categorie 			categorie 		= cateDao.getCategorie( em, libCategorie);

				if( categorie == null) {
					categorie = new Categorie( libCategorie);
					cateDao.insererCategorie(em, categorie);
				}

				// Creer les Marques
				String			libMarque  	= tabDonnees[1].trim();
				Marque 			marque 		= marqueDao.getMarque( em, libMarque);

				if( marque == null) {
					marque = new Marque( libMarque);
					marqueDao.insererMarque(em, marque);
				}

				// Récupérer les listes
				if( tabDonnees[4].length() >= 2) {
					ligneIngredient 	= tabDonnees[4];
				}else {
					ligneIngredient 	= "" ;
				}
				tabIngredient		= ligneIngredient.split( ",");
				
				if( ( tabDonnees.length > 28) && ( tabDonnees[28].length() >= 2) ) {
					ligneAllergene 		= tabDonnees[28];
				}else {
					ligneAllergene      = "";
				}
				tabAllergene		= ligneAllergene.split( ",");
				
				if( ( tabDonnees.length > 30) && (  tabDonnees[30].length() >= 2)) {
					ligneAdditif 		= tabDonnees[30];
				}else {
					ligneAdditif		= "" ;
				}
				tabAdditif			= ligneAdditif.split( ",");
				
				
				// Creer les Produits
				String				libProduit  	= tabDonnees[2].trim();
				char				grade			= tabDonnees[3].trim().charAt(0);
				Produit				produit			= null ;
				
				produit 		= ProduitDao.getProduit( em, libProduit);
				if( produit == null) {
					Float[] tabComposants   = new Float[ 23];
					for( int j = 0 ; j < 23 ; j++) {
						if( tabDonnees.length > (j+5)) {
							// Récupérer l'information dans le ficheir
							tabComposants[ j] = getFloat( tabDonnees[j+5].trim());						
						}else {
							tabComposants[ j]  = 0.0f;
						}
					}
				
					produit = new Produit( libProduit, grade, categorie, marque, tabComposants);
					ProduitDao.insererProduit( 
							em, 
							produit);
				}
		
				// Creer les Ingredients associe au produit
				for( String curIngredient : tabIngredient) {
					if( ( curIngredient != null) 
						&& 
						( !curIngredient.trim().contentEquals( "") )){
						String				libIngredient  	= curIngredient.trim();
						Ingredient 			ingredient 		= IngredientDao.getIngredient( em, libIngredient);

						if( ingredient == null) {
							ingredient = new Ingredient( libIngredient);
							IngredientDao.insererIngredient(em, ingredient);
						}
						
						produit.getLstIngredient().add( ingredient);
					}
					
				}

			

		        
				// Creer les Allergenes
				
				// Creer les Additifs
				

				       
			}else {
				enteteLue = true;
			}
			i++;
			if( i == 4) break;

		}
		
		et.commit();
		
		em.close();
	}
}
