package MySQLDAO;

import java.sql.Connection;
import java.util.*;
import java.util.Date;

import Connexion.Connexion;
import IDAO.AbonnementIDAO;
import Metier.Abonnement;
import Metier.Periodicite;

import java.sql.*;


public class MySQLAbonnementDAO implements AbonnementIDAO<Abonnement>{
	  private static MySQLAbonnementDAO instance;
	  private Connexion connexion = new Connexion();
      private Connection Connect = connexion.creeConnexion();
      private List<Abonnement> ListeAbonnement = new ArrayList<>();
      private int id_Revue = 0;
      private int id_client;
      private int id_abonnement;
      private Date date_deb;
      private Date date_fin;
      
	    private MySQLAbonnementDAO() {}

	    public static MySQLAbonnementDAO getInstance() {
	        if (instance == null) {
	            instance = new MySQLAbonnementDAO();
	        }
	        return instance;
	    }
	    @Override
	    public boolean create(Abonnement objet) {
	        try {
	            PreparedStatement requete = Connect.prepareStatement("insert into Abonnement (date_debut,date_fin,id_revue,id_client) values (?,?,?,?)");
	            requete.setInt(4, objet.getId_abonnement());
	            requete.setInt(3,objet.getId_Revue());
	            requete.setDate(2, (java.sql.Date) objet.getDate_fin());
	            requete.setDate(1, (java.sql.Date) objet.getDate_deb());
	            requete.executeUpdate();

	            return true;
	        } catch (SQLException e) {
	            System.out.println("pb dans le select" + e.getMessage());
	            return false;
	        }
	    }

	        @Override
	    public boolean update(Abonnement objet) {
	        try {
	            PreparedStatement requete = Connect.prepareStatement("update Abonnement SET date_debut = ?, date_fin = ?, id_revue = ?, id_client = ? WHERE id_abonnement = ?");

	            requete.setDate(1, (java.sql.Date) objet.getDate_deb());
	            requete.setDate(2,(java.sql.Date) objet.getDate_fin());
	            requete.setInt(3,objet.getId_Revue());
	            requete.setInt(4,objet.getId_client());
	            requete.setInt(5,objet.getId_abonnement());
	            requete.executeUpdate();

	            return true;
	        } catch (SQLException e) {
	                System.out.println("pb dans le select" + e.getMessage());
	                return false;
	            }
	    }

	    @Override
	    public boolean delete(Abonnement objet) {
	        try{
	            PreparedStatement requete = Connect.prepareStatement("delete from Abonnement where id_abonnement=?");
	            requete.setInt(1,objet.getId_client());
	            requete.executeUpdate();
	            return true;
	        }catch (SQLException sqle){
	            System.out.println("Pb dans select " + sqle.getMessage());
	        }
	        return false;
	    }
	    public Abonnement getById(int id) {
	    	id_Revue = 0;
	        try{
	            PreparedStatement requete= Connect.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_deb,date_fin, WHERE id_abonnement = ? FROM Abonnement");
	            requete.setInt(1,id_abonnement);
	            ResultSet res = requete.executeQuery();

	            while  (res.next())
	            id_Revue = res.getInt("id_revue");
	            id_client =res.getInt("id_client");
	            date_deb = res.getDate("date_deb");
	            date_fin = res.getDate("date_fin");

	            return new Abonnement(id_abonnement,date_deb,date_fin,id_client,id_Revue);

	        }
	        catch (SQLException sql){
	            System.out.println("pb dans le select" + sql.getMessage());
	        }
	        return null;
	    }

		@Override
	    public List<Abonnement> getByDateDeb(Date date_deb) {       
	        try{
	            PreparedStatement requete = Connect.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_deb,date_fin, WHERE date_deb = ? FROM Abonnement");
	            requete.setDate(1, (java.sql.Date) date_deb);
	            ResultSet res = requete.executeQuery();
	            while (res.next()){
	                 id_Revue = res.getInt("id_revue");
	                 id_client = res.getInt("id_client");
	                 id_abonnement = res.getInt("id_abonnement");
	                 date_fin = res.getDate("date_fin");

	                Abonnement abonne = new Abonnement(id_abonnement,date_deb,date_fin,id_client,id_Revue);
	                ListeAbonnement.add(abonne);
	            }

	            return ListeAbonnement;
	            
	        }catch (SQLException sqle){
	            System.out.println("Pb dans select " + sqle.getMessage());
	            return null;
	        }

	    }

		@Override
	    public List<Abonnement> getByDateFin(Date date_fin) {
	        try{
	            PreparedStatement requete = Connect.prepareStatement("SELECT id_abonnement,id_client,id_revue,date_deb,date_deb, WHERE date_fin = ? FROM Abonnement");
	            requete.setDate(1, (java.sql.Date) date_fin);
	            ResultSet res = requete.executeQuery();
	            while (res.next()){
	                 id_Revue = res.getInt("id_revue");
	                 id_client = res.getInt("id_client");
	                 id_abonnement = res.getInt("id_abonnement");
	                 date_deb = res.getDate("date_deb");

	                Abonnement abonne = new Abonnement(id_abonnement,date_deb,date_fin,id_client,id_Revue);
	                ListeAbonnement.add(abonne);
	            }

	            return ListeAbonnement;
	            
	        }catch (SQLException sqle){
	            System.out.println("Pb dans select " + sqle.getMessage());
	            return null;
	       }
	   }

		@Override
		public List<Abonnement> getAll() {
			List<Abonnement> ListeAbonnement = new ArrayList<>();

			return ListeAbonnement;
		}

}
	    

