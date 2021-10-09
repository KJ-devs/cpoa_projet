package metier;

public class Adresse {
	private String numVoie;
	private String voie;
	private String codePostal;
	private String ville;
	private String Pays;
	public String getNumVoie() {
		return numVoie;
	}
	public void setNumVoie(String numVoie) {
		this.numVoie = numVoie;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String newVoie) {
		this.voie = newVoie;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return Pays;
	}
	public void setPays(String pays) {
		Pays = pays;
	}
}
