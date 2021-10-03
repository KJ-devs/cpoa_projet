package DAOFactory;

import ListeMemoireDAO.ListeMemoireDAOFactory;
import MySQLDAO.MySQLDAOFactory;
import IDAO.AbonnementIDAO;
import IDAO.ClientIDAO;
import IDAO.PeriodiciteIDAO;
import IDAO.RevueIDAO;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(Persistance cible){

        DAOFactory daoF = null;

        switch (cible) {
            case MYSQL:
                daoF = new MySQLDAOFactory();
                break;
            case LISTE_MEMOIRE:
                daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }
    public abstract AbonnementIDAO getAbonnementIDAO();
    public abstract ClientIDAO getClientIDAO();
    public abstract PeriodiciteIDAO getPeriodicteIDAO();
    public abstract RevueIDAO getRevueIDAO();

}