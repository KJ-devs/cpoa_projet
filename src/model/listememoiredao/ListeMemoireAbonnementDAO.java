package model.listememoiredao;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import model.idao.AbonnementIDAO;
import model.metier.Abonnement;

public class ListeMemoireAbonnementDAO implements AbonnementIDAO<Abonnement> {
	private static ListeMemoireAbonnementDAO instance;
	private final List<Abonnement> donnees;
	
	private ListeMemoireAbonnementDAO() {

		this.donnees = new ArrayList<>();
		this.donnees.add(new Abonnement(1,null,null, null, null));
		this.donnees.add(new Abonnement(2, null,null, null, null));
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
