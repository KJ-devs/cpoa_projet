package ListeMemoireDAO;

import java.util.List;

import IDAO.RevueIDAO;
import Metier.Revue;

public class ListeMemoireRevueDAO implements RevueIDAO<Revue>{
	private static ListeMemoireRevueDAO instance;
	public static ListeMemoireRevueDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireRevueDAO();
		}
			return instance;
			
	}
	@Override
	public Revue getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean create(Revue object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Revue object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Revue object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Revue> getByDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Revue> getByTitre(String titre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Revue> getByVisuel(String visuel) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Revue> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}