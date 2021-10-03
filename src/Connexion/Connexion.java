package Connexion;
import java.sql.*;
public class Connexion {

    public Connection creeConnexion() {
        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/krebs47u_projet_cpoa";
        String login = "krebs47u_appli";
        String pwd = "jonas2010";
        Connection maConnexion = null;

        try {
            maConnexion = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion" + sqle.getMessage());
        }
        return maConnexion;
    }
}