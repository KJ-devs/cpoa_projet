package ListeMemoireDAO;


import FactoryDAO.DAOFactory;
import IDAO.AbonnementIDAO;
import IDAO.ClientIDAO;
import IDAO.PeriodiciteIDAO;
import IDAO.RevueIDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

	 @Override
	    public AbonnementIDAO getAbonnementIDAO() {
	        return ListeMemoireAbonnementDAO.getInstance();
	    }

	    @Override
	    public ClientIDAO getClientIDAO() {
	        return ListeMemoireClientDAO.getInstance();
	    }

	    @Override
	    public PeriodiciteIDAO getPeriodicteIDAO() {
	        return ListeMemoirePeriodiciteDAO.getInstance();
	    }

	    @Override
	    public RevueIDAO getRevueIDAO() {
	        return ListeMemoireRevueDAO.getInstance();
	    }
}
