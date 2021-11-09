package javafx.controlleurs;


import javafx.fxml.Initializable;
import model.daofactory.DAOFactory;
import model.daofactory.Persistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlAccueil implements Initializable {


    @FXML private MenuBar myMenuBar;

    @FXML private RadioButton radioButtonListeMemoire;

    @FXML private RadioButton radioButtonSQl;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private static DAOFactory dao ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioButtonListeMemoire.setSelected(true);
        dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
    }
    public static DAOFactory getDao() {
        return dao;
    }
    public void getDAOMethode() {
        if (radioButtonListeMemoire.isSelected()) {
            dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
        } else if (radioButtonSQl.isSelected()) {
            dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        }
    }

    // PERMET D'ACCEDER AUX DIFFERENTES PAGES
    @FXML
    void goToPageAbonnement() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreAbonnement.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void goToPageAccueil() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreAccueil.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void goToPageClient() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreClient.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void goToPageRevue() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreRevue.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    public void goToPagePeriodicite() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetrePeriodicite.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }





}