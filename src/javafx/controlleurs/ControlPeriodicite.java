package javafx.controlleurs;

import model.daofactory.DAOFactory;
import model.daofactory.Persistance;
import model.metier.Periodicite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlPeriodicite implements Initializable {
    @FXML private TableColumn<Periodicite, Integer> columnIdPeriodicite;

    @FXML private TableColumn<Periodicite, String> columnTitrePeriodicite;

    @FXML private TableView<Periodicite> tableViewPeriodicite;

    @FXML private TextField txt_LibellePeriodicite;

    @FXML private Label labelVerifPeriodicite;

    @FXML
    private MenuBar myMenuBar;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private DAOFactory dao = ControlAccueil.getDao();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnIdPeriodicite.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("Id"));
        columnTitrePeriodicite.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("Libelle"));
        refreshTablePeriodicite();
    }
    private void refreshTablePeriodicite() {

        // initialise table Periodicite
        tableViewPeriodicite.getItems().clear();
        this.tableViewPeriodicite.getItems().addAll(dao.getPeriodiciteIDAO().getAll());
    }
    private boolean verificationPeriodicite() {
        boolean check = true;
        String messageErreur ="";

        if (txt_LibellePeriodicite.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un Libelle\n";
            check = false;
        }
        if (txt_LibellePeriodicite.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            messageErreur = messageErreur + "Saisir une chaine de caractere dans libelle\n";
            check = false;
        }
        if (!check) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Probleme");
            dialog.setContentText(messageErreur);
            dialog.showAndWait();
        }
        return check;
    }
    @FXML
    void creationPeriodicite(ActionEvent event) {

        if (verificationPeriodicite()) {
            labelVerifPeriodicite.setText("Creation reussis");
            Periodicite periodicite = new Periodicite(0,txt_LibellePeriodicite.getText());
            dao.getPeriodiciteIDAO().create(periodicite);
            refreshTablePeriodicite();
        }
    }    @FXML
    void modifierSelectedPeriodicite(ActionEvent event) {
        int index = tableViewPeriodicite.getSelectionModel().getSelectedIndex();
        if (verificationPeriodicite()) {
            labelVerifPeriodicite.setText("Modification reussis");
            Periodicite periodicite = new Periodicite(columnIdPeriodicite.getCellData(index),txt_LibellePeriodicite.getText());
            dao.getPeriodiciteIDAO().update(periodicite);
            refreshTablePeriodicite();
        }

    }

    @FXML
    void supprimerSelectedPeriodicite(ActionEvent event) {
        labelVerifPeriodicite.setText("Supprimer Reussis");
        dao.getPeriodiciteIDAO().delete(tableViewPeriodicite.getSelectionModel().getSelectedItem());
        refreshTablePeriodicite();
    }

    @FXML
    void selectPeriodicitePutIntoTextField(MouseEvent event) {
        tableViewPeriodicite.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> {
            Periodicite periodicite = tableViewPeriodicite.getSelectionModel().getSelectedItem();//classe du model
            if (tableViewPeriodicite.isFocused() == true) {
                txt_LibellePeriodicite.setText(periodicite.getLibelle());
            }
        });
    }

    // Permet d'acceder aux differentes pages
    @FXML
    void goToPageAccueil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreAccueil.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    void goToPageAbonnement(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreAbonnement.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    void goToPageClient(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreClient.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void goToPageRevue(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreRevue.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }
    @FXML
    public void goToPagePeriodicite(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetrePeriodicite.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }



}
