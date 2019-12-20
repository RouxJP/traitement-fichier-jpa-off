package fr.diginamic.entities;

public class Additif {
	private String libelle ;
	private long	nbrFoisPresent;	
	
	public Additif( String libelle) {
		this.libelle 		= libelle ;
		this.nbrFoisPresent = 1l ;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public long getNbrFoisPresent() {
		return nbrFoisPresent;
	}

	public void setNbrFoisPresent(long nbrFoisPresent) {
		this.nbrFoisPresent = nbrFoisPresent;
	}





}
