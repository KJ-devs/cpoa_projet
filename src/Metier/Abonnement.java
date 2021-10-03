package Metier;

import java.util.Date;
import java.util.Objects;

public class Abonnement {
	private int id_abonnement;
	private Date date_deb;
	private Date date_fin;
	private int id_client;
	private int id_Revue;
	public Abonnement(int id_abonnement, Date date_deb, Date date_fin, int id_client, int id_Revue) {
		super();
		this.id_abonnement = id_abonnement;
		this.date_deb = date_deb;
		this.date_fin = date_fin;
		this.id_client = id_client;
		this.id_Revue = id_Revue;
	}
	public int getId_abonnement() {
		return id_abonnement;
	}
	public void setId_abonnement(int id_abonnement) {
		this.id_abonnement = id_abonnement;
	}
	public Date getDate_deb() {
		return date_deb;
	}
	public void setDate_deb(Date date_deb) {
		this.date_deb = date_deb;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_Revue() {
		return id_Revue;
	}
	public void setId_Revue(int id_Revue) {
		this.id_Revue = id_Revue;
	}
	@Override
	public int hashCode() {
		return Objects.hash(date_deb, date_fin, id_Revue, id_abonnement, id_client);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abonnement other = (Abonnement) obj;
		return Objects.equals(date_deb, other.date_deb) && Objects.equals(date_fin, other.date_fin)
				&& id_Revue == other.id_Revue && id_abonnement == other.id_abonnement && id_client == other.id_client;
	}
	@Override
	public String toString() {
		return "Abonnement [id_abonnement=" + id_abonnement + ", date_deb=" + date_deb + ", date_fin=" + date_fin
				+ ", id_client=" + id_client + ", id_Revue=" + id_Revue + "]";
	}
	
}
