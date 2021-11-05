package model.mysqldao;

import model.daofactory.DAOFactory;
import model.idao.AbonnementIDAO;
import model.idao.ClientIDAO;
import model.idao.PeriodiciteIDAO;
import model.idao.RevueIDAO;

public class MySqlDAOFactory extends DAOFactory{

		@Override
		public AbonnementIDAO getAbonnementIDAO() {

			return MySqlAbonnementDAO.getInstance();
		}
	
		@Override
		public ClientIDAO getClientIDAO() {
			 return MySqlClientDAO.getInstance();
		}
	
		@Override
		public PeriodiciteIDAO getPeriodiciteIDAO() {
			return MySqlPeriodiciteDAO.getInstance();
		}
	
		@Override
		public RevueIDAO getRevueIDAO() {
			return MySqlRevueDAO.getInstance();
		}
}


