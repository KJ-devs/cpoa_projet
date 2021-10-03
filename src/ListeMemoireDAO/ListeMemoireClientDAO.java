package ListeMemoireDAO;

import java.util.List;

import IDAO.ClientIDAO;
import Metier.Client;
import Metier.Periodicite;
import Metier.Client;

public class ListeMemoireClientDAO implements ClientIDAO<Client> {
	private static ListeMemoireClientDAO instance;
	private List<Client> donnees;
	public static ListeMemoireClientDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireClientDAO();
		}
			return instance;
			
	}
	@Override
	public boolean create(Client objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Client objet) {
		
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
	public boolean delete(Client objet) {

		Client supprime;
		
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
	public Client getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByCodePostal(String code_postal) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByNo_Rue(String no_rue) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByVille(String ville) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByVoie(String voie) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getByPays(String pays) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
