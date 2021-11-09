package model.metier;

import java.util.Objects;

public class Revue {
	private int id_revue;
	private String titre;
	private String description;
	private float tarif;
	private String visuel;
	private int id_periodicite;
	private Periodicite periodicite;
	
	public Revue(int id_revue, String titre, String description, float tarif, String visuel, Periodicite periodicite) {
		super();
		this.id_revue = id_revue;
		this.titre = titre;
		this.description = description;
		this.tarif = tarif;
		this.visuel = visuel;
		this.periodicite = periodicite;
	}



	public int getId_revue() {

		return id_revue;
	}
	public void setId_revue(int id_revue) {

		this.id_revue = id_revue;
	}

	public String getTitre() {

		return titre;
	}

	public void setTitre(String titre) {

		this.titre = titre;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {

		this.tarif = tarif;
	}

	public String getVisuel() {

		return visuel;
	}

	public void setVisuel(String visuel) {

		this.visuel = visuel;
	}

	public Periodicite getPeriodicite() {

		return periodicite;
	}

	public void setId_periodicite(Periodicite periodicite) {

		this.periodicite = periodicite;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(description, id_periodicite, id_revue, tarif, titre, visuel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revue other = (Revue) obj;
		return Objects.equals(description, other.description) && id_periodicite == other.id_periodicite
				&& id_revue == other.id_revue && Float.floatToIntBits(tarif) == Float.floatToIntBits(other.tarif)
				&& Objects.equals(titre, other.titre) && Objects.equals(visuel, other.visuel);
	}

	@Override
	public String toString() {
		return id_revue + " " + titre ;
	}
	

}
