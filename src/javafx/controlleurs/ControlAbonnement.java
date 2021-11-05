package javafx.controlleurs;

import model.daofactory.DAOFactory;
import model.daofactory.Persistance;
import model.metier.Abonnement;
import model.metier.Client;
import model.metier.Periodicite;
import model.metier.Revue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ControlAbonnement implements Initializable {

    private Abonnement abonnement;
    @FXML
    private TableColumn<Abonnement, Integer> columnClientAbonnement;

    @FXML
    private TableColumn<Abonnement, Integer> columnRevueAbonnement;

    @FXML
    private TableColumn<Abonnement, Date> columnDateDebAbonnement;

    @FXML
    private TableColumn<Abonnement, Date> columnDateFinAbonnement;

    @FXML
    private ChoiceBox<Client> choiceBoxClient;

    @FXML
    private ChoiceBox<Revue> choiceBoxRevue;

    @FXML
    private TableView<Abonnement> tableViewAbonnement;


    @FXML
    private DatePicker dateDebAbo;

    @FXML
    private DatePicker dateFinAbo;
    @FXML
    private MenuBar myMenuBar;


    private Parent root;
    private Stage stage;
    private Scene scene;
    private DAOFactory dao = ControlAccueil.getDao();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
        this.choiceBoxClient.setItems(FXCollections.observableArrayList(dao.getClientIDAO().getAll()));
        this.choiceBoxRevue.setItems(FXCollections.observableArrayList(dao.getRevueIDAO().getAll()));
        columnClientAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("Id_client"));
        columnRevueAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("Id_Revue"));
        columnDateDebAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("Date_deb"));
        columnDateFinAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("Date_fin"));
        refreshTableAbonnement();


    }

    private void refreshTableAbonnement() {
        // initialise table Periodicite
        tableViewAbonnement.getItems().clear();
        this.tableViewAbonnement.getItems().addAll(dao.getAbonnementIDAO().getAll());
    }
    public void selectAbonnementPutIntoTextField(ActionEvent actionEvent) {
        tableViewAbonnement.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> {
            Abonnement abonnement = tableViewAbonnement.getSelectionModel().getSelectedItem();//classe du model
            if (tableViewAbonnement.isFocused() == true) {
                choiceBoxClient.getSelectionModel().select((Client) dao.getClientIDAO().getById(abonnement.getId_client()));
                choiceBoxRevue.getSelectionModel().select((Revue) dao.getRevueIDAO().getById(abonnement.getId_Revue()));
            }
        });
    }
    public void creationAbonnement(ActionEvent actionEvent)  {
        int client = Integer.parseInt(Integer.toString(choiceBoxClient.getSelectionModel().getSelectedItem().getId()));
        int revue = Integer.parseInt(Integer.toString(choiceBoxRevue.getSelectionModel().getSelectedItem().getId_revue()));
        Date dateDeb =  Date.from(dateDebAbo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date dateFin =  Date.from(dateFinAbo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        String messageErreur="";
        boolean check = true;

        if (dateDeb.after(dateFin)) {
            messageErreur = messageErreur +"Saisir une date de debut avant une date de fin d'abonnement\n";
            check = false;
        }
        if(check) {
            Abonnement abonnement = new Abonnement(0, dateDeb, dateFin,client, revue);
            dao.getAbonnementIDAO().create(abonnement);
            refreshTableAbonnement();
        } else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Probleme");
            dialog.setContentText(messageErreur);
            dialog.showAndWait();
        }

    }

    public void modifierSelectedAbonnement(ActionEvent actionEvent) {
        int client = Integer.parseInt(Integer.toString(choiceBoxClient.getSelectionModel().getSelectedItem().getId()));
        int revue = Integer.parseInt(Integer.toString(choiceBoxRevue.getSelectionModel().getSelectedItem().getId_revue()));
        Date dateDeb =  Date.from(dateDebAbo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date dateFin =  Date.from(dateFinAbo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        String messageErreur="";
        boolean check = true;
        if (dateDeb == null || dateFin == null) {
            messageErreur = messageErreur + "Saisir une date de debut et une date de fin d'abonnement\n";
            check = false;
        }
        if (dateDeb.after(dateFin)) {
            messageErreur = messageErreur +"Saisir une date de debut avant une date de fin d'abonnement\n";
            check = false;
        }
        if(check) {
            Abonnement abonnement = new Abonnement(0, dateDeb, dateFin, client, revue);
            dao.getAbonnementIDAO().update(abonnement);
            refreshTableAbonnement();
        } else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Probleme");
            dialog.setContentText(messageErreur);
            dialog.showAndWait();
        }
    }

    public void supprimerSelectedAbonnement(ActionEvent actionEvent) {
        dao.getAbonnementIDAO().delete(tableViewAbonnement.getSelectionModel().getSelectedItem());
        refreshTableAbonnement();
    }
    // PERMET D'ACCEDER AUX DIFFERENTES PAGES
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
    void goToPageAccueil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../vue/FenetreAccueil.fxml"));
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
