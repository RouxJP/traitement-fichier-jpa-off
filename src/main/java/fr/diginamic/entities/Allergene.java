package fr.diginamic.entities;

public class Allergene {
	private String 	libelle ;
	private long	nbrFoisPresent;	
	
	public Allergene( String libelle) {
		this.libelle 		= libelle ;
		this.nbrFoisPresent = 1L;
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
