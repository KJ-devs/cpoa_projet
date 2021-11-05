package model.listememoiredao;

import java.util.ArrayList;
import java.util.List;

import model.idao.ClientIDAO;
import model.metier.Client;


public class ListeMemoireClientDAO implements ClientIDAO<Client> {
	private static ListeMemoireClientDAO instance;
	private final List<Client> Client = new ArrayList<>();
	private final List<Client> donnees;
	private ListeMemoireClientDAO() {

		this.donnees = new ArrayList<>();

		this.donnees.add(new Client("Schwartz", "Quetin", "5", "rue principal", "57000", "Metz", "France", 1));
		this.donnees.add(new Client("Krebs", "Jeremie", "9", "rue de la mairie", "57000", "Metz", "France", 2));
	}
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

			int idx = -1;
			for (Client client : this.donnees) {
				if (client.getId() == objet.getId()) {
					idx = this.donnees.indexOf(client);
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
	public boolean delete(Client objet) {

			Client supprime;
			int idx = -1;

			for (Client client : this.donnees) {
				if (client.getId() == objet.getId())
				{
					idx = this.donnees.indexOf(client);
				}
			}
			if (idx == -1)
			{
				throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
			}
			else
			{
				supprime = this.donnees.remove(idx);
			}

			return objet.equals(supprime);
		}
	@Override
	public Client getById(int id) {
		int idx = this.donnees.indexOf(new Client(null,null,null, null,null,null,null,id));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	@Override
	public List<Client> getByCodePostal(String code_postal) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(code_postal)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	@Override
	public List<Client> getByNom(String nom) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(nom)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	@Override
	public List<Client> getByPrenom(String prenom) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(prenom)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	@Override
	public List<Client> getByNo_Rue(String no_rue) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(no_rue)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	
	@Override
	public List<Client> getByVille(String ville) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(ville)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	@Override
	public List<Client> getByVoie(String voie) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(voie)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	@Override
	public List<Client> getByPays(String pays) {
		for (Client Cl:donnees ) {
			if(Cl.getCode_postal().equalsIgnoreCase(pays)) {
				Client.add(Cl);
			}
		}
		return Client;
	}
	@Override
	public List<Client> getAll() {

		return this.donnees;
}
}
