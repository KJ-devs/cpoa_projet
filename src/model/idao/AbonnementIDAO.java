package model.idao;
import model.dao.DAO;

import java.sql.Date;
import java.util.List;
public interface AbonnementIDAO<Abonnement> extends DAO<Abonnement> {
    List<Abonnement> getByDateDeb(Date date_deb);

    List<Abonnement> getByDateFin(Date date_fin);
}
