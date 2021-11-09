package model.listememoiredao;

import model.idao.RevueIDAO;
import model.metier.Periodicite;
import model.metier.Revue;


import java.util.ArrayList;
import java.util.List;

public class ListeMemoireRevueDAO implements RevueIDAO<Revue>{
	private static ListeMemoireRevueDAO instance;
	private final List<Revue> donnees;
	private final List<Revue> Revue = new ArrayList<>();
	public static ListeMemoireRevueDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireRevueDAO();
		}
			return instance;
			
	}
	private ListeMemoireRevueDAO() {

		this.donnees = new ArrayList<>();
		Periodicite p1 = new Periodicite(1,"Mensuel");
		Periodicite p2 = new Periodicite(2,"Annuel");
		this.donnees.add(new Revue(1, "Monster", "Histoire intriguante", 1, null, p1));
		this.donnees.add(new Revue(2, "Hunter", "Histoire incroyable d'un jeune homme nommé Julien Criquelion", 2, null, p2));

	}
	@Override
	public boolean create(Revue objet) {

		objet.setId_revue(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId_revue(objet.getId_revue() + 1);
		}

		return this.donnees.add(objet);
	}

	@Override
	public boolean update(Revue objet) {
		int idx = -1;
		for (Revue revue : this.donnees) {
			if (revue.getId_revue() == objet.getId_revue()) {
				idx = this.donnees.indexOf(revue);
			}
		}
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
			return true;
		}
	}

	@Override
	public boolean delete(Revue objet) {

		Revue supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}@Override
	public Revue getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Revue(id, null,null,1, null, null));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	@Override
	public List<Revue> getByDescription(String description) {
		for (Revue Rev:donnees ) {
			if(Rev.getDescription().equalsIgnoreCase(description)) {
				Revue.add(Rev);
			}
		}
		return Revue;
	}
	@Override
	public List<Revue> getByTitre(String titre) {
		for (Revue Rev:donnees ) {
			if(Rev.getDescription().equalsIgnoreCase(titre)) {
				Revue.add(Rev);
			}
		}
		return Revue;
	}
	@Override
	public List<Revue> getByVisuel(String visuel) {
		for (Revue Rev:donnees ) {
			if(Rev.getDescription().equalsIgnoreCase(visuel)) {
				Revue.add(Rev);
			}
		}
		return Revue;
	}
	@Override
	public List<Revue> getAll() {
		// TODO Auto-generated method stub
		return this.donnees;
	}
}