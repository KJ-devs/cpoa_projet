package ListeMemoireDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import IDAO.AbonnementIDAO;
import Metier.Abonnement;
import Metier.Client;
import Metier.Periodicite;

public class ListeMemoireAbonnementDAO implements AbonnementIDAO<Abonnement> {
	private static ListeMemoireAbonnementDAO instance;
	private List<Abonnement> donnees;
	
	private ListeMemoireAbonnementDAO() {

		this.donnees = new ArrayList<Abonnement>();

		this.donnees.add(new Abonnement(1, null, null, 1, 1));
		this.donnees.add(new Abonnement(2, null, null, 2, 2));
	}
	public static ListeMemoireAbonnementDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireAbonnementDAO();
		}
			return instance;
			}
	@Override
	public Abonnement getById(int id) {
		int idx = this.donnees.indexOf(new Abonnement(id,null,null,1,2));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	
	@Override
	public boolean create(Abonnement objet) {

		objet.setId_abonnement(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId_abonnement(objet.getId_abonnement() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Abonnement objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Abonnement objet) {

		Abonnement supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
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
		return null;
	}
}
