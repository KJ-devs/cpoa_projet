package ListeMemoireDAO;

import java.util.List;

import IDAO.ClientIDAO;
import Metier.Client;

public class ListeMemoireClientDAO implements ClientIDAO<Client> {
	private static ListeMemoireClientDAO instance;
	public static ListeMemoireClientDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireClientDAO();
		}
			return instance;
			
	}
	@Override
	public Client getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean create(Client object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Client object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Client object) {
		// TODO Auto-generated method stub
		return false;
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
