package fr.diginamic.entities;




import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="PRODUIT")
/** Produit de open food facts
 * 
 * @author acer
 *
 */
public class Produit {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	/** Identifiant généré par Mariadb */
	private int		id;
	
	@Column(name="nom_produit")
	/** Nom du produit */
	private	String						nomProduit;
	
	@Column(name="grade_nutritionnel")
	/** Qualité nutritionnelle de a à f */
	private char	gradeNutritionnel;
	
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name = "id_marque")
	private Marque	marque;	

	@ManyToMany
	@JoinTable(	name="produit_ingredient", 
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),	
	inverseJoinColumns = @JoinColumn(name="id_ingredient", referencedColumnName="id"))	
	private Set<Ingredient>				lstIngredient = new HashSet<Ingredient>();
	

	@ManyToMany
	@JoinTable(	name="produit_allergene", 
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),	
	inverseJoinColumns = @JoinColumn(name="id_allergene", referencedColumnName="id"))	
	private Set<Allergene>				lstAllergene = new HashSet<Allergene>();

	
	@ManyToMany
	@JoinTable(	name="produit_additif", 
	joinColumns= @JoinColumn(name="id_produit", referencedColumnName="id"),	
	inverseJoinColumns = @JoinColumn(name="id_additif", referencedColumnName="id"))	
	private Set< Additif>				lstAdditif = new HashSet<Additif>();
	

	@Column(name="energie_100g")
	private float energie100g ;

	@Column(name="graisse_100g")
	private float graisse100g ;
	
	@Column(name="sucres_100g")
	private float sucres100g ;
	
	@Column(name="fibres_100g")
	private float fibres100g ;
	
	@Column(name="proteines_100g")
	private float proteines100g ;
	
	@Column(name="sel_100g")
	private float sel100g ;
	
	@Column(name="vit_A1_00g")
	private float vitA100g ;
	
	@Column(name="vit_D1_00g")
	private float vitD100g ;
	
	@Column(name="vit_E_100g")
	private float vitE100g ;
	
	@Column(name="vit_K_100g")
	private float vitK100g ;
	
	@Column(name="vit_C_100g")
	private float vitC100g ;
	
	@Column(name="vit_B1_100g")
	private float vitB1100g ;
	
	@Column(name="vit_B2_100g")
	private float vitB2100g ;
	
	@Column(name="vit_PP_100g")
	private float vitPP100g ;
	
	@Column(name="vit_B6_100g")
	private float vitB6100g ;
	
	@Column(name="vit_B9_100g")
	private float vitB9100g ;
	
	@Column(name="vit_B12_100g")
	private float vitB12100g ;
	
	@Column(name="calcium_100g")
	private float calcium100g ;
	
	@Column(name="magnesium_100g")
	private float magnesium100g ;
	
	@Column(name="iron_100g")
	private float iron100g ;
	
	@Column(name="fer_100g")
	private float fer100g ;
	
	@Column(name="beta_Carotene_100g")
	private float betaCarotene100g ;
	
	@Column(name="presence_Huile_Palme")
	private float presenceHuilePalme ;	
	
	public Produit( String nom, 			char 			gradeNutritionnel, 
					Categorie	categorie, 	Marque			marque) {
		this.nomProduit 			= nom;
		this.gradeNutritionnel 		= gradeNutritionnel ;
		this.categorie 				= categorie;
		this.marque 				= marque;
	}
	
	public Produit( String nom, 			char 			gradeNutritionnel, 
					Categorie	categorie, 	Marque			marque,
					Float[]		tabComposants) {
	
		this( nom, gradeNutritionnel, categorie, marque);
		this.energie100g 		= tabComposants[0];
		this.graisse100g 		= tabComposants[1] ;
		this.sucres100g 		= tabComposants[2] ;
		this.fibres100g 		= tabComposants[3] ;
		this.proteines100g 		= tabComposants[4] ;
		this.sel100g 			= tabComposants[5] ;
		this.vitA100g 			= tabComposants[6] ;
		this.vitD100g 			= tabComposants[7] ;
		this.vitE100g 			= tabComposants[8] ;
		this.vitK100g 			= tabComposants[9] ;
		this.vitC100g 			= tabComposants[10] ;
		this.vitB1100g 			= tabComposants[11] ;
		this.vitB2100g 			= tabComposants[12] ;
		this.vitPP100g 			= tabComposants[13] ;
		this.vitB6100g 			= tabComposants[14] ;
		this.vitB9100g 			= tabComposants[15] ;
		this.vitB12100g 		= tabComposants[16] ;
		this.calcium100g 		= tabComposants[17] ;
		this.magnesium100g 		= tabComposants[18] ;
		this.iron100g 			= tabComposants[19] ;
		this.fer100g 			= tabComposants[20] ;
		this.betaCarotene100g 	= tabComposants[21] ;
		this.presenceHuilePalme = tabComposants[22] ;
		
	}
	
	public Produit() {
		
	}
/**	
	public Produit( String ligne) {
		String[] 	tabDonnees ;
		String[]	tabIngredient;
		String		ligneIngredient;
		String[]	tabAllergene;
		String		ligneAllergene;
		String[]	tabAdditif;
		String		ligneAdditif;
		Ingredient  ingredient;
		Allergene  	allergene;
		Additif  	additif;
		
		categorie 			= new Categorie();
		marque				= new Marque();
		gradeNutritionnel 	= ' '; 							// a à f
		lstIngredient 		= new HashSet<Ingredient>();
		lstAllergene 		= new HashSet<Allergene>();
		lstAdditif 			= new HashSet<Additif>();
		
		tabDonnees = ligne.split( ";");

		this.setNomProduit( tabDonnees[ 0].replace( "\"", ""));
		setGradeNutritionnel( tabDonnees[ 1].charAt(0));
		categorie.setLibelle( tabDonnees[ 2]);
		marque.setNom(tabDonnees[ 3]);
		
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
			ingredient = ComposantsExistant.lstIngredientExistant.get(curIngredient.trim());
			this.lstIngredient.add( ingredient);
		}
		for( String curAllergene : tabAllergene) {
			allergene = ComposantsExistant.lstAllergeneExistant.get(curAllergene.trim());
			this.lstAllergene.add( allergene);
		}
		for( String curAdditif : tabAdditif) {
			additif = ComposantsExistant.lstAdditifExistant.get(curAdditif.trim());
			this.lstAdditif.add( additif);
		}
		
	}

	public char getGradeNutritionnel() {
		return gradeNutritionnel;
	}

	public void setGradeNutritionnel(char gradeNutritionnel) {
		this.gradeNutritionnel = gradeNutritionnel;
	}

	public Categorie getCategorie() {
		return categorie;
	}


	public Marque getMarque() {
		return marque;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
**/
/** Getter
 * @return the nomProduit
 */
public String getNomProduit() {
	return nomProduit;
}
/** Setter
 * @param nomProduit the nomProduit to set
 */
public void setNomProduit(String nomProduit) {
	this.nomProduit = nomProduit;
}
/** Getter
 * @return the gradeNutritionnel
 */
public char getGradeNutritionnel() {
	return gradeNutritionnel;
}
/** Setter
 * @param gradeNutritionnel the gradeNutritionnel to set
 */
public void setGradeNutritionnel(char gradeNutritionnel) {
	this.gradeNutritionnel = gradeNutritionnel;
}
/** Getter
 * @return the categorie
 */
public Categorie getCategorie() {
	return categorie;
}
/** Setter
 * @param categorie the categorie to set
 */
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}
/** Getter
 * @return the marque
 */
public Marque getMarque() {
	return marque;
}
/** Setter
 * @param marque the marque to set
 */
public void setMarque(Marque marque) {
	this.marque = marque;
}

/** Getter
 * @return the energie100g
 */
public float getEnergie100g() {
	return energie100g;
}

/** Setter
 * @param energie100g the energie100g to set
 */
public void setEnergie100g(float energie100g) {
	this.energie100g = energie100g;
}

/** Getter
 * @return the graisse100g
 */
public float getGraisse100g() {
	return graisse100g;
}

/** Setter
 * @param graisse100g the graisse100g to set
 */
public void setGraisse100g(float graisse100g) {
	this.graisse100g = graisse100g;
}

/** Getter
 * @return the sucres100g
 */
public float getSucres100g() {
	return sucres100g;
}

/** Setter
 * @param sucres100g the sucres100g to set
 */
public void setSucres100g(float sucres100g) {
	this.sucres100g = sucres100g;
}

/** Getter
 * @return the fibres100g
 */
public float getFibres100g() {
	return fibres100g;
}

/** Setter
 * @param fibres100g the fibres100g to set
 */
public void setFibres100g(float fibres100g) {
	this.fibres100g = fibres100g;
}

/** Getter
 * @return the proteines100g
 */
public float getProteines100g() {
	return proteines100g;
}

/** Setter
 * @param proteines100g the proteines100g to set
 */
public void setProteines100g(float proteines100g) {
	this.proteines100g = proteines100g;
}

/** Getter
 * @return the sel100g
 */
public float getSel100g() {
	return sel100g;
}

/** Setter
 * @param sel100g the sel100g to set
 */
public void setSel100g(float sel100g) {
	this.sel100g = sel100g;
}

/** Getter
 * @return the vitA100g
 */
public float getVitA100g() {
	return vitA100g;
}

/** Setter
 * @param vitA100g the vitA100g to set
 */
public void setVitA100g(float vitA100g) {
	this.vitA100g = vitA100g;
}

/** Getter
 * @return the vitD100g
 */
public float getVitD100g() {
	return vitD100g;
}

/** Setter
 * @param vitD100g the vitD100g to set
 */
public void setVitD100g(float vitD100g) {
	this.vitD100g = vitD100g;
}

/** Getter
 * @return the vitE100g
 */
public float getVitE100g() {
	return vitE100g;
}

/** Setter
 * @param vitE100g the vitE100g to set
 */
public void setVitE100g(float vitE100g) {
	this.vitE100g = vitE100g;
}

/** Getter
 * @return the vitK100g
 */
public float getVitK100g() {
	return vitK100g;
}

/** Setter
 * @param vitK100g the vitK100g to set
 */
public void setVitK100g(float vitK100g) {
	this.vitK100g = vitK100g;
}

/** Getter
 * @return the vitC100g
 */
public float getVitC100g() {
	return vitC100g;
}

/** Setter
 * @param vitC100g the vitC100g to set
 */
public void setVitC100g(float vitC100g) {
	this.vitC100g = vitC100g;
}

/** Getter
 * @return the vitB1100g
 */
public float getVitB1100g() {
	return vitB1100g;
}

/** Setter
 * @param vitB1100g the vitB1100g to set
 */
public void setVitB1100g(float vitB1100g) {
	this.vitB1100g = vitB1100g;
}

/** Getter
 * @return the vitB2100g
 */
public float getVitB2100g() {
	return vitB2100g;
}

/** Setter
 * @param vitB2100g the vitB2100g to set
 */
public void setVitB2100g(float vitB2100g) {
	this.vitB2100g = vitB2100g;
}

/** Getter
 * @return the vitPP100g
 */
public float getVitPP100g() {
	return vitPP100g;
}

/** Setter
 * @param vitPP100g the vitPP100g to set
 */
public void setVitPP100g(float vitPP100g) {
	this.vitPP100g = vitPP100g;
}

/** Getter
 * @return the vitB6100g
 */
public float getVitB6100g() {
	return vitB6100g;
}

/** Setter
 * @param vitB6100g the vitB6100g to set
 */
public void setVitB6100g(float vitB6100g) {
	this.vitB6100g = vitB6100g;
}

/** Getter
 * @return the vitB9100g
 */
public float getVitB9100g() {
	return vitB9100g;
}

/** Setter
 * @param vitB9100g the vitB9100g to set
 */
public void setVitB9100g(float vitB9100g) {
	this.vitB9100g = vitB9100g;
}

/** Getter
 * @return the vitB12100g
 */
public float getVitB12100g() {
	return vitB12100g;
}

/** Setter
 * @param vitB12100g the vitB12100g to set
 */
public void setVitB12100g(float vitB12100g) {
	this.vitB12100g = vitB12100g;
}

/** Getter
 * @return the calcium100g
 */
public float getCalcium100g() {
	return calcium100g;
}

/** Setter
 * @param calcium100g the calcium100g to set
 */
public void setCalcium100g(float calcium100g) {
	this.calcium100g = calcium100g;
}

/** Getter
 * @return the magnesium100g
 */
public float getMagnesium100g() {
	return magnesium100g;
}

/** Setter
 * @param magnesium100g the magnesium100g to set
 */
public void setMagnesium100g(float magnesium100g) {
	this.magnesium100g = magnesium100g;
}

/** Getter
 * @return the iron100g
 */
public float getIron100g() {
	return iron100g;
}

/** Setter
 * @param iron100g the iron100g to set
 */
public void setIron100g(float iron100g) {
	this.iron100g = iron100g;
}

/** Getter
 * @return the fer100g
 */
public float getFer100g() {
	return fer100g;
}

/** Setter
 * @param fer100g the fer100g to set
 */
public void setFer100g(float fer100g) {
	this.fer100g = fer100g;
}

/** Getter
 * @return the betaCarotene100g
 */
public float getBetaCarotene100g() {
	return betaCarotene100g;
}

/** Setter
 * @param betaCarotene100g the betaCarotene100g to set
 */
public void setBetaCarotene100g(float betaCarotene100g) {
	this.betaCarotene100g = betaCarotene100g;
}

/** Getter
 * @return the presenceHuilePalme
 */
public float getPresenceHuilePalme() {
	return presenceHuilePalme;
}

/** Setter
 * @param presenceHuilePalme the presenceHuilePalme to set
 */
public void setPresenceHuilePalme(float presenceHuilePalme) {
	this.presenceHuilePalme = presenceHuilePalme;
}

/** Getter
 * @return the lstIngredient
 */
public Set<Ingredient> getLstIngredient() {
	return lstIngredient;
}

/** Setter
 * @param lstIngredient the lstIngredient to set
 */
public void setLstIngredient(Set<Ingredient> lstIngredient) {
	this.lstIngredient = lstIngredient;
}

/** Getter
 * @return the lstAllergene
 */
public Set<Allergene> getLstAllergene() {
	return lstAllergene;
}

/** Setter
 * @param lstAllergene the lstAllergene to set
 */
public void setLstAllergene(Set<Allergene> lstAllergene) {
	this.lstAllergene = lstAllergene;
}

/** Getter
 * @return the id
 */
public int getId() {
	return id;
}

/** Setter
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}

/** Getter
 * @return the lstAdditif
 */
public Set<Additif> getLstAdditif() {
	return lstAdditif;
}

/** Setter
 * @param lstAdditif the lstAdditif to set
 */
public void setLstAdditif(Set<Additif> lstAdditif) {
	this.lstAdditif = lstAdditif;
}

}
