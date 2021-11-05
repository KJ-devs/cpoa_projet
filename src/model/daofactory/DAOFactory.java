package model.daofactory;

import model.listememoiredao.ListeMemoireDAOFactory;
import model.mysqldao.MySqlDAOFactory;
import model.idao.AbonnementIDAO;
import model.idao.ClientIDAO;
import model.idao.PeriodiciteIDAO;
import model.idao.RevueIDAO;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(Persistance cible){

        DAOFactory daoF = null;

        switch (cible) {
            case MYSQL:
                daoF = new MySqlDAOFactory();
                break;
            case LISTE_MEMOIRE:
                daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }
    public abstract AbonnementIDAO getAbonnementIDAO();
    public abstract ClientIDAO getClientIDAO();
    public abstract PeriodiciteIDAO getPeriodiciteIDAO();
    public abstract RevueIDAO getRevueIDAO();

}