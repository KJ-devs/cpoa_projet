package model.metier;


import java.util.Date;
import java.util.Objects;

public class Abonnement {
	private int id_abonnement;
	private Date date_deb;
	private Date date_fin;
	private Client client;
	private Revue revue;
	public Abonnement(int id_abonnement, Date date_deb, Date date_fin, Client client, Revue revue) {
		super();
		this.id_abonnement = id_abonnement;
		this.date_deb = date_deb;
		this.date_fin = date_fin;
		this.client = client;
		this.revue = revue;
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
	public Client getClient() {

		return client;
	}
	public void setId_client(Client client) {

		this.client = client;
	}
	public Revue getRevue() {

		return revue;
	}
	public void setId_Revue(Revue Revue) {

		this.revue = revue;
	}
	@Override
	public int hashCode() {

		return Objects.hash(date_deb, date_fin, revue, id_abonnement, client);
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
				&& revue == other.revue && id_abonnement == other.id_abonnement && client == other.client;
	}
	@Override
	public String toString() {
		return "Abonnement [id_abonnement=" + id_abonnement + ", date_deb=" + date_deb + ", date_fin=" + date_fin
				+ ", id_client=" + client + ", id_Revue=" + revue + "]";
	}
	
}
