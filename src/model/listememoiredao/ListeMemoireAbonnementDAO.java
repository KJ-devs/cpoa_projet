package model.listememoiredao;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import model.idao.AbonnementIDAO;
import model.metier.Abonnement;
import model.metier.Client;
import model.metier.Periodicite;
import model.metier.Revue;
import model.tools.ConversionDate;

public class ListeMemoireAbonnementDAO implements AbonnementIDAO<Abonnement> {
	private static ListeMemoireAbonnementDAO instance;
	private final List<Abonnement> donnees;
	
	private ListeMemoireAbonnementDAO() {

		this.donnees = new ArrayList<>();
		Client client = new Client("kuntz","alban","12","rue des paysans","57720","sarreguemine","france",1);
		Client client1 = new Client("basquin","thomas","09","rue principal","67000","strasbourg","france",2);
		Periodicite p1 = new Periodicite(1,"Mensuel");
		Periodicite p2 = new Periodicite(2,"Annuel");
		Revue revue = new Revue(1, "Monster", "Histoire intriguante", 1, null, p1);
		Revue revue1 = new Revue(2, "Hunter", "Histoire incroyable d'un jeune homme nommé Julien Criquelion", 2, null, p2);
		LocalDate dateDeb = LocalDate.of(2020,10,12);
		Date dDeb = Date.valueOf(dateDeb);
		LocalDate dateFin = LocalDate.of(2021,11,12);
		Date dFin = Date.valueOf(dateFin);
		LocalDate dateDeb1 = LocalDate.of(2019,8,26);
		Date dDeb1 = Date.valueOf(dateDeb1);
		LocalDate dateFin1 = LocalDate.of(2019,10,27);
		Date dFin1 = Date.valueOf(dateFin1);
		this.donnees.add(new Abonnement(1,dDeb,dFin, client, revue));
		this.donnees.add(new Abonnement(2, dDeb1,dFin1, client1, revue1));
	}
	public static ListeMemoireAbonnementDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireAbonnementDAO();
		}
			return instance;
			}
	@Override
	public Abonnement getById(int id) {

		int idx = this.donnees.indexOf(new Abonnement(id,null,null,null,null));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	
	@Override
	public boolean create(Abonnement objet) {

		objet.setId_abonnement(3);
		// Ne fonctionne que si l'objet m�tier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId_abonnement(objet.getId_abonnement() + 1);
		}

		return this.donnees.add(objet);
	}

	@Override
	public boolean update(Abonnement objet) {

		int idx = -1;
		for (Abonnement abonnement : this.donnees) {
			if (abonnement.getId_abonnement() == objet.getId_abonnement())
			{
				idx = this.donnees.indexOf(abonnement);
			}
		}
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		}
		else {
			this.donnees.set(idx, objet);
			return true;
		}
	}

	@Override
	public boolean delete(Abonnement objet) {

		Abonnement supprime;
		
		// Ne fonctionne que si l'objet m�tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}
	@Override
	public List<Abonnement> getByDateDeb(Date date_deb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Abonnement> getByDateFin(Date date_fin) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Abonnement> getAll() {
		// TODO Auto-generated method stub
		return this.donnees;
	}
}
