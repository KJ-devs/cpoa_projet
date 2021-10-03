package IDAO;
import DAO.DAO;
import Metier.Abonnement;
import java.sql.*;
import java.util.Date;
import java.util.List;
public interface AbonnementIDAO<Abonnement> extends DAO<Abonnement> {
	List<Abonnement> getByDateDeb(Date date_deb);
	
    List<Abonnement> getByDateFin(Date date_fin);
}
