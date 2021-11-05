package model.listememoiredao;


import model.daofactory.DAOFactory;
import model.idao.AbonnementIDAO;
import model.idao.ClientIDAO;
import model.idao.PeriodiciteIDAO;
import model.idao.RevueIDAO;

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
	    public PeriodiciteIDAO getPeriodiciteIDAO() {

		 return ListeMemoirePeriodiciteDAO.getInstance();
	    }

	    @Override
	    public RevueIDAO getRevueIDAO() {
	        return ListeMemoireRevueDAO.getInstance();
	    }
	}
