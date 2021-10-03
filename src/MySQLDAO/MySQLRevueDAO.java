package MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import Connexion.Connexion;
import IDAO.RevueIDAO;
import Metier.Periodicite;
import Metier.Revue;
import java.sql.*;

public class MySQLRevueDAO implements RevueIDAO<Revue> {
	private static MySQLRevueDAO instance;
	private int id_periodicite;
	private int id_revue;
	private List<Revue> ListeRevue = new ArrayList<>();
    private String titre;
    private String description;
    private Float tarif_numero;
    private String visuel;
    private Revue revue;
    private Connexion connexion = new Connexion();
    private Connection Connect = connexion.creeConnexion();
    private MySQLRevueDAO() {}

    public static MySQLRevueDAO getInstance() {
        if (instance == null) {
            instance = new MySQLRevueDAO();
        }
        return instance;
    }



    @Override
    public boolean create(Revue objet) {

        return false;
    }

    @Override
    public boolean update(Revue objet) {
        try {
            PreparedStatement requete = Connect.prepareStatement("update Revue SET description = ? , tarif_numero = ?, titre = ? , visuel = ? , id_periodicite = ? WHERE id_revue = ?");
            requete.setString(1, objet.getDescription());
            requete.setFloat(2, objet.getTarif());
            requete.setString(3, objet.getTitre());
            requete.setString(4, objet.getVisuel());
            requete.setInt(5,objet.getId_periodicite());
            requete.setInt(6, objet.getId_revue());
            requete.executeUpdate();

            return true;
        } catch (SQLException sqle) {
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }

    }

    @Override
    public boolean delete(Revue objet) {
        try {
            PreparedStatement requete = Connect.prepareStatement("delete from Revue where id_revue=?");
            requete.setInt(1,objet.getId_revue());
            requete.executeUpdate();
            return true;
        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }
    }
	@Override
    public Revue getById(int id) {

         id_periodicite = 0;
         titre = null;
         description = null;
         tarif_numero = null;
         visuel = null;
         revue = null;

        try {
            PreparedStatement req = Connect.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE id_revue = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            while (res.next()){
                id_periodicite =res.getInt("id_periodicite");
                titre = res.getString("titre");
                description = res.getString("description");
                tarif_numero = res.getFloat("tarif_numero");
                visuel = res.getString("visuel");

                revue = new Revue(id_revue,titre,description,tarif_numero,visuel, id_periodicite);
            }
            
            return revue;

        }catch (SQLException sqle){
            System.out.println("Pb dans le select"+sqle.getMessage());
            return null;
        }

    }
    @Override
    public List<Revue> getByDescription(String description) {
        int id = 0;
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE description = ? ");
            req.setString(1,description);
            ResultSet res = req.executeQuery();
            while (res.next()){
                 id_revue = res.getInt("id_revue");
                 titre = res.getString("titre");
                 tarif_numero = res.getFloat("tarif_numero");
                 id_periodicite = res.getInt("id_periodicite");
                 visuel = res.getString("visuel");

                 revue = new Revue(id_revue,titre,description,tarif_numero,visuel, id_periodicite);
                ListeRevue.add(revue);
            }
            return ListeRevue;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }
    

    @Override
    public List<Revue> getByTitre(String titre) {
        int id = 0;
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE description = ? ");
            req.setString(1,titre);
            ResultSet res = req.executeQuery();
            while (res.next()){
                 id_revue = res.getInt("id_revue");
                 description = res.getString("description");
                 tarif_numero = res.getFloat("tarif_numero");
                 id_periodicite = res.getInt("id_periodicite");
                 visuel = res.getString("visuel");

                revue = new Revue(id_revue,titre,description,tarif_numero,visuel, id_periodicite);
                ListeRevue.add(revue);
            }
            return ListeRevue;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Revue> getByVisuel(String visuel) {

        int id = 0;
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE description = ? ");
            req.setString(1,visuel);
            ResultSet res = req.executeQuery();
            while (res.next()){
                 id_revue = res.getInt("id_revue");
                 titre = res.getString("titre");
                 description = res.getString("description");
                 tarif_numero = res.getFloat("tarif_numero");
                 id_periodicite = res.getInt("id_periodicite");

                revue = new Revue(id_revue,titre,description,tarif_numero,visuel, id_periodicite);
                ListeRevue.add(revue);
            }
            return ListeRevue;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

	@Override
	public List<Revue> getAll() {
		List<Revue> ListeRevue = new ArrayList<>();

		return ListeRevue;
	}
}

