package Main;

import FactoryDAO.DAOFactory;
import FactoryDAO.Persistance;
import IDAO.PeriodiciteIDAO;
import Metier.Periodicite;

public class Main {

	public static void main(String[] args) {
		DAOFactory daof = getDAOFactory(Persistance.MYSQL);
		PeriodiciteIDAO dao = daof.getPeriodicteIDAO();
		System.out.println(dao.getById(1));
		System.out.println(dao.create(new Periodicite(7, "me")));
		for (Periodicite periodicite : dao.getAll()) {
			System.out.println(periodicite);
		}
		Periodicite p1 = new Periodicite(1, "toto");
		Periodicite p2 = new Periodicite(2, "toto");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1.equals(p2));
	}
}
