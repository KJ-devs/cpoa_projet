package MySQLDAO;

import FactoryDAO.DAOFactory;
import IDAO.AbonnementIDAO;
import IDAO.ClientIDAO;
import IDAO.PeriodiciteIDAO;
import IDAO.RevueIDAO;

public class MySQLDAOFactory extends DAOFactory{

		@Override
		public AbonnementIDAO getAbonnementIDAO() {
			return MySQLAbonnementDAO.getInstance();
		}
	
		@Override
		public ClientIDAO getClientIDAO() {
			 return MySQLClientDAO.getInstance();
		}
	
		@Override
		public PeriodiciteIDAO getPeriodicteIDAO() {
			return MySQLPeriodiciteDAO.getInstance();
		}
	
		@Override
		public RevueIDAO getRevueIDAO() {
			return MySQLRevueDAO.getInstance();
		}
}


