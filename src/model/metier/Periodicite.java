package model.metier;

public class Periodicite {



	private int id;
	private String libelle;



	public Periodicite(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}



	public int getId() {
		return id;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Periodicite other = (Periodicite) obj;
		return id == other.getId();
	}



	@Override
	public String toString() {
		return  libelle;
	}
}

