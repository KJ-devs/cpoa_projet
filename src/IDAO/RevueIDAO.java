package IDAO;

import java.util.List;

import DAO.DAO;

public interface RevueIDAO<Revue> extends DAO<Revue> {
	 	List<Revue> getByDescription(String description);
	    List<Revue> getByTitre(String titre);
	    List<Revue> getByVisuel(String visuel);
}
