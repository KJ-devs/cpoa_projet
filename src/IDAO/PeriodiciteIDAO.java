package IDAO;

import java.util.List;

import DAO.DAO;
import Metier.Periodicite;
import MySQLDAO.MySQLPeriodiciteDAO;

public interface PeriodiciteIDAO<Periodicite>  extends DAO<Periodicite>{
	List<Periodicite> getByLib(String libelle);
}
