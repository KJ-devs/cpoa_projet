package MySQLDAO;

import Connexion.Connexion;
import IDAO.ClientIDAO;
import Metier.Client;
import Metier.Periodicite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MySQLClientDAO implements ClientIDAO<Client> {

    private static MySQLClientDAO instance;
    private Connexion connexion = new Connexion();
    private Connection Connect = connexion.creeConnexion();
    private MySQLClientDAO() {}
    private List<Client> ListeClient = new ArrayList<>();
   private String nom;
   private String prenom;
   private String no_rue;
   private String ville;
   private String pays;
   private String voie;
   private String code_postal;
   private int id;
	    
    public static MySQLClientDAO getInstance() {
	        if (instance == null) {
	            instance = new MySQLClientDAO();
	        }
	        return instance;
	    }

 
    @Override
    public boolean create(Client objet) {
        try{


            PreparedStatement requete = Connect.prepareStatement("insert into Client (nom,prenom, no_rue,voie,code_postal,ville,pays) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            requete.setString(1,objet.getNom());
            requete.setString(2,objet.getPrenom());
            requete.setString(3,objet.getNo_rue());
            requete.setString(4,objet.getVoie());
            requete.setString(5,objet.getCode_postal());
            requete.setString(6,objet.getVille());
            requete.setString(7,objet.getPays());

            requete.executeUpdate();
            return true;

        }catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Client objet) {
        try {
            PreparedStatement requete = Connect.prepareStatement("update Client SET nom = ? , prenom = ?, no_rue = ? , voie = ? , code_postal = ? , ville = ? , pays = ?   WHERE id_client = ?");
            requete.setString(1, objet.getNom());
            requete.setString(2, objet.getPrenom());
            requete.setString(3, objet.getNo_rue());
            requete.setString(4, objet.getVoie());
            requete.setString(5, objet.getCode_postal());
            requete.setString(6, objet.getVille());
            requete.setString(7, objet.getPays());
            requete.setInt(8, objet.getId());

            requete.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return false;
        }


    }

    @Override
    public boolean delete(Client objet) {
        try {
            PreparedStatement requete = Connect.prepareStatement("delete from Client where id_client=?");
            requete.setInt(1,objet.getId());
            requete.executeUpdate();

            return true ;


        }
        catch (SQLException sqle){
            System.out.println("Pb dans select " + sqle.getMessage());
            return false;
        }

    }

    @Override
    public Client getById(int id) {
         nom = null;
         prenom = null;
         no_rue = null;
         ville = null;
         pays = null;
         voie = null;
         code_postal = null;
        try{
  

            PreparedStatement req = Connect.prepareStatement("SELECT nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE id_client = ?  ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();
            while (res.next()){
                 prenom =res.getString("prenom");
                 no_rue=res.getString("no_rue");
                 voie =res.getString("voie");
                 nom =res.getString("nom");
                 ville = res.getString("ville");
                 pays = res.getString("pays");
                 code_postal =res.getString("code_postal");
            }



            return new Client(nom,prenom,no_rue,ville,pays,voie,code_postal,id);

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByCodePostal(String code_postal) {


        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE code_postal = ? ");
            req.setString(1,code_postal);
            ResultSet res = req.executeQuery();
            while (res.next()){
                 id =res.getInt("id_client");
                 prenom =res.getString("prenom");
                 no_rue=res.getString("no_rue");
                 voie =res.getString("voie");
                 nom =res.getString("nom");
                 ville = res.getString("ville");
                 pays = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByNom(String nom) {
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE nom = ? ");
            req.setString(1,nom);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                 prenom =res.getString("prenom");
                 no_rue=res.getString("no_rue");
                 voie =res.getString("voie");
                 code_postal =res.getString("code_postal");
                 ville = res.getString("ville");
                 pays = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByNo_Rue(String no_rue) {
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE no_rue = ? ");
            req.setString(1,no_rue);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                 prenom =res.getString("prenom");
                 nom=res.getString("nom");
                 voie =res.getString("voie");
                 code_postal =res.getString("code_postal");
                 ville = res.getString("ville");
                 pays = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByPrenom(String prenom) {
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE prenom = ? ");
            req.setString(1,prenom);
            ResultSet res = req.executeQuery();
            while (res.next()){
                 id =res.getInt("id_client");
                 no_rue =res.getString("no_rue");
                 nom=res.getString("nom");
                 voie =res.getString("voie");
                 code_postal =res.getString("code_postal");
                 ville = res.getString("ville");
                 pays = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByVille(String ville) {
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE ville = ? ");
            req.setString(1,ville);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                 no_rue =res.getString("no_rue");
                 nom=res.getString("nom");
                 voie =res.getString("voie");
                 code_postal =res.getString("code_postal");
                 prenom = res.getString("prenom");
                 pays = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByVoie(String voie) {
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE voie = ? ");
            req.setString(1,voie);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                 no_rue =res.getString("no_rue");
                 nom=res.getString("nom");
                 prenom =res.getString("prenom");
                 code_postal =res.getString("code_postal");
                 ville = res.getString("ville");
                 pays = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }

    @Override
    public List<Client> getByPays(String pays) {
        try{
            PreparedStatement req = Connect.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE pays = ? ");
            req.setString(1,pays);
            ResultSet res = req.executeQuery();
            while (res.next()){
                int id =res.getInt("id_client");
                 no_rue =res.getString("no_rue");
                 nom=res.getString("nom");
                 voie =res.getString("voie");
                 code_postal =res.getString("code_postal");
                 ville = res.getString("ville");
                 prenom = res.getString("pays");

                Client Client = new Client(nom,prenom,no_rue, voie,code_postal, ville,pays,id); 
                ListeClient.add(Client);
            }
            return ListeClient;

        }catch (SQLException e){
            System.out.println("Pb dans select " + e.getMessage());
            return null ;
        }
    }


	@Override
	public List<Client> getAll() {
		List<Client> ListeClient = new ArrayList<>();

		return ListeClient;
	}

}
