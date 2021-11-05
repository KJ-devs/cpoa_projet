package model.mysqldao;
import java.sql.SQLException;
import java.util.*;
import model.idao.PeriodiciteIDAO;
import model.metier.Periodicite;
import model.connexion.Connexion;


import java.sql.*;
public class MySqlPeriodiciteDAO implements PeriodiciteIDAO<Periodicite> {

    private static MySqlPeriodiciteDAO instance;
    private final Connexion connexion = new Connexion();
    private final Connection Connect = connexion.creeConnexion();
    private int id_periodicite;
    private String libelle;

    public static MySqlPeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new MySqlPeriodiciteDAO();
        }
        return instance;
    }



    @Override
    public boolean create(Periodicite objet) {
        try {
            PreparedStatement req = Connect.prepareStatement("Insert into Periodicite (libelle) value (?)");
            req.setString(1, objet.getLibelle());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Periodicite objet) {
        try {
            PreparedStatement req = Connect.prepareStatement("update Periodicite SET libelle = ? WHERE id_periodicite = ?");
            req.setString(1, objet.getLibelle());
            req.setInt(2,objet.getId());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Periodicite objet) {
        try {
            PreparedStatement req = Connect.prepareStatement("delete from Periodicite where id_periodicite=?");
            req.setInt(1,objet.getId());
            req.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Periodicite> getByLib(String libelle){
        int id = 0;
	        try{
                List<Periodicite> ListPeriodicite = new ArrayList<>();
	            PreparedStatement req = Connect.prepareStatement("SELECT id_periodicite FROM Periodicite WHERE libelle = ? ");
	            req.setString(1,libelle);
	            ResultSet res = req.executeQuery();
	            while (res.next()){
	                id =res.getInt("id_periodicite");
                    Periodicite periodicite = new Periodicite(id, libelle);
	                ListPeriodicite.add(periodicite);
	            }
	            return  ListPeriodicite;
	
	        }catch (SQLException e){
	            System.out.println("Pb dans select " + e.getMessage());
	            return null ;
	        }
	    }
    @Override
    public Periodicite getById(int id) {
        String libelle = null;
	        try{
	            PreparedStatement req = Connect.prepareStatement("SELECT libelle FROM Periodicite WHERE id_periodicite = ? ");
	            req.setInt(1,id);
	            ResultSet res = req.executeQuery();
	            while (res.next()){
	                libelle =res.getString("libelle");
	            }
	            return new Periodicite(id,libelle);
	
	
	
	        }catch (SQLException e){
	            System.out.println("Pb dans select " + e.getMessage());
	            return null ;
	        }
	    }



	@Override
	public List<Periodicite> getAll() {
        try {
            List<Periodicite> listePeriodicite = new ArrayList<>();
            PreparedStatement req = Connect.prepareStatement("SELECT * FROM Periodicite");
            ResultSet res = req.executeQuery();

            while (res.next()){
                 id_periodicite = res.getInt(1);
                 libelle = res.getString(2);
                Periodicite periodicite = new Periodicite(id_periodicite,libelle);
                listePeriodicite.add(periodicite);
            }
            return listePeriodicite;

        }catch (SQLException sqlException){
            System.out.println("Problème select : "+sqlException.getMessage());
            return null;
        }
    }





}	





