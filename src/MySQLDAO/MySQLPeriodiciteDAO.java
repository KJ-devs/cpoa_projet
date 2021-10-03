package MySQLDAO;
import java.sql.SQLException;
import java.util.*;
import IDAO.PeriodiciteIDAO;
import Metier.Periodicite;
import Connexion.Connexion;
import java.sql.*;
public class MySQLPeriodiciteDAO implements PeriodiciteIDAO<Periodicite> {

    private static MySQLPeriodiciteDAO instance;
    private Connexion connexion = new Connexion();
    private Connection Connect = connexion.creeConnexion();
    private MySQLPeriodiciteDAO() {}
    private List<Periodicite> ListPeriodicite = new ArrayList<>();
    
    public static MySQLPeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new MySQLPeriodiciteDAO();
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
	            PreparedStatement req = Connect.prepareStatement("SELECT id_periodicite FROM Periodicite WHERE libelle = ? ");
	            req.setString(1,libelle);
	            ResultSet res = req.executeQuery();
	            while (res.next()){
	                id =res.getInt("id_periodicite");
	                Periodicite Periodicite = new Periodicite(id,libelle);
	                ListPeriodicite.add(Periodicite);
	            }
	            return ListPeriodicite;
	
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
		List<Periodicite> ListePeriodicite = new ArrayList<>();

		return ListePeriodicite;
	}



	@Override
	public ArrayList<Periodicite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}	





