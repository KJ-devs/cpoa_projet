package ListeMemoireDAO;

import java.util.Date;
import java.util.List;

import IDAO.AbonnementIDAO;
import Metier.Abonnement;

public class ListeMemoireAbonnementDAO implements AbonnementIDAO<Abonnement> {
	private static ListeMemoireAbonnementDAO instance;
	public static ListeMemoireAbonnementDAO getInstance() {
		if (instance==null) {
			instance = new ListeMemoireAbonnementDAO();
		}
			return instance;
			}
	@Override
	public Abonnement getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean create(Abonnement object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Abonnement object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Abonnement object) {
		// TODO Auto-generated method stub
		return false;
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
