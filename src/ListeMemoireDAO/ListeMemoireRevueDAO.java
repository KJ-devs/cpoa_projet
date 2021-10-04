package ListeMemoireDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import IDAO.RevueIDAO;
import Metier.Periodicite;
import Metier.Revue;
import DAO.DAO;

public class ListeMemoireRevueDAO implements RevueIDAO<Revue>{
	private static ListeMemoireRevueDAO instance;
	private List<Revue> donnees;
	private List<Revue> Revue = new ArrayList<>();
	public static ListeMemoireRevueDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireRevueDAO();
		}
			return instance;
			
	}
	private ListeMemoireRevueDAO() {

		this.donnees = new ArrayList<Revue>();

		this.donnees.add(new Revue(1, "Monster", null, 1, null, 1));
		this.donnees.add(new Revue(2, "Hunter", null, 2, null, 2));
	}
	@Override
	public boolean create(Revue objet) {

		objet.setId_revue(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId_revue(objet.getId_revue() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Revue objet) {
		
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
		int idx = this.donnees.indexOf(new Revue(id, "test",null,1, null, 5));
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
		return null;
	}
}