package model.connexion;
import java.sql.*;
public class Connexion {

    private String login = "krebs47u_appli";
    private String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/krebs47u_projet_cpoa";
    private String pwd = "jonas2010";

    public Connection creeConnexion() {
        Connection maConnexion = null;
        try {
            if (maConnexion == null || maConnexion.isClosed()) {
                maConnexion = DriverManager.getConnection(this.url , this.login, this.pwd);
            }
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion" +
                    sqle.getMessage());
        }
        return maConnexion;
    }
}