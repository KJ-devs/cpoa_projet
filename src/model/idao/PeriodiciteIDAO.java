package model.idao;

import java.util.List;

import model.dao.DAO;

public interface PeriodiciteIDAO<Periodicite>  extends DAO<Periodicite>{
	List<Periodicite> getByLib(String libelle);


}
