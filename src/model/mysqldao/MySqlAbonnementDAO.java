package model.mysqldao;

import model.connexion.Connexion;
import model.idao.AbonnementIDAO;
import model.metier.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class MySqlAbonnementDAO implements AbonnementIDAO<Abonnement>{
	  private static MySqlAbonnementDAO instance;
	  private final Connexion connexion = new Connexion();
      private final Connection Connect = connexion.creeConnexion();
      private final List<Abonnement> ListeAbonnement = new ArrayList<>();
	  private Abonnement abonne;
      private int id_Revue = 0;
      private int id_client;
      private int id_abonnement;
      private Date date_deb;
      private Date date_fin;

	    public static MySqlAbonnementDAO getInstance() {
	        if (instance == null) {
	            instance = new MySqlAbonnementDAO();
	        }
	        return instance;
	    }
	    @Override
	    public boolean create(Abonnement objet) {
			try {
				PreparedStatement requete = Connect.prepareStatement("insert into Abonnement (date_debut,date_fin,id_client,id_revue) values (?,?,?,?)");
				long timeInMilliSeconds = objet.getDate_deb().getTime();
				java.sql.Date date_deb = new java.sql.Date(timeInMilliSeconds);
				timeInMilliSeconds = objet.getDate_fin().getTime();
				java.sql.Date date_fin = new java.sql.Date(timeInMilliSeconds);

				requete.setDate(1, date_deb);
				requete.setDate(2, date_fin);
				requete.setInt(3, objet.getClient().getId());
				requete.setInt(4,objet.getRevue().getId_revue());

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
					PreparedStatement requete = Connect.prepareStatement("update Abonnement SET date_debut = ?, date_fin = ?, id_client = ?, id_revue = ? WHERE id_abonnement = ?");

					long timeInMilliSeconds = objet.getDate_deb().getTime();
					java.sql.Date date_deb = new java.sql.Date(timeInMilliSeconds);
					requete.setDate(2, date_deb);

					timeInMilliSeconds = objet.getDate_fin().getTime();
					java.sql.Date date_fin = new java.sql.Date(timeInMilliSeconds);
					requete.setDate(1, date_fin);

					requete.setInt(3,objet.getRevue().getId_revue());
					requete.setInt(4,objet.getClient().getId());
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
	            requete.setInt(1,objet.getId_abonnement());
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
	            PreparedStatement requete= Connect.prepareStatement("SELECT * FROM Abonnement WHERE id_abonnement = ? ");
	            requete.setInt(1,id_abonnement);
	            ResultSet res = requete.executeQuery();

	            while  (res.next())
	            id_Revue = res.getInt("id_revue");
	            id_client =res.getInt("id_client");
	            date_deb = res.getDate("date_deb");
	            date_fin = res.getDate("date_fin");
	            return new Abonnement(id_abonnement,date_deb,date_fin,MySqlClientDAO.getInstance().getById(id_client),MySqlRevueDAO.getInstance().getById(id_Revue));

	        }
	        catch (SQLException sql){
	            System.out.println("pb dans le select" + sql.getMessage());
	        }
	        return null;
	    }

		@Override
	    public List<Abonnement> getByDateDeb(Date date_deb) {
	        try{

	            PreparedStatement requete = Connect.prepareStatement("SELECT * FROM Abonnement  WHERE date_deb = ?");
				requete.setDate(2,(java.sql.Date) date_deb);
	            ResultSet res = requete.executeQuery();
	            while (res.next()){
	                 id_Revue = res.getInt("id_revue");
	                 id_client = res.getInt("id_client");
	                 id_abonnement = res.getInt("id_abonnement");
	                 date_fin = res.getDate("date_fin");
	                 abonne = new Abonnement(id_abonnement,date_deb,date_fin,MySqlClientDAO.getInstance().getById(id_client),MySqlRevueDAO.getInstance().getById(id_Revue));
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
	            PreparedStatement requete = Connect.prepareStatement("SELECT * FROM Abonnement WHERE date_fin = ?");
	            requete.setDate(1, date_fin);
	            ResultSet res = requete.executeQuery();
	            while (res.next()){
	                 id_Revue = res.getInt("id_revue");
	                 id_client = res.getInt("id_client");
	                 id_abonnement = res.getInt("id_abonnement");
	                 date_deb = res.getDate("date_deb");

	                 abonne = new Abonnement(id_abonnement,date_deb,date_fin,MySqlClientDAO.getInstance().getById(id_client),MySqlRevueDAO.getInstance().getById(id_Revue));
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

		try {
			List<Abonnement> ListeAbonnement = new ArrayList<>();
			PreparedStatement requete = Connect.prepareStatement("SELECT * FROM Abonnement");
			ResultSet res = requete.executeQuery();

			while (res.next()) {
				 id_abonnement = res.getInt("id_abonnement");
				 id_Revue = res.getInt("id_revue");
				 id_client = res.getInt("id_client");
				 date_deb = res.getDate("date_debut");
				 date_fin = res.getDate("date_fin");

				abonne = new Abonnement(id_abonnement, date_deb, date_fin, MySqlClientDAO.getInstance().getById(id_client),MySqlRevueDAO.getInstance().getById(id_Revue));

				ListeAbonnement.add(abonne);

			}
			return ListeAbonnement;
		}
		catch (SQLException sql){
			System.out.println("pb dans le select " + sql.getMessage());
		}
		return null;
	}


}
	    

