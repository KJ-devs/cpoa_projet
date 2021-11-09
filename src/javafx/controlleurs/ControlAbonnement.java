package javafx.controlleurs;

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
import model.daofactory.DAOFactory;
import model.metier.Abonnement;
import model.metier.Client;
import model.metier.Revue;
import model.tools.ConversionDate;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ControlAbonnement implements Initializable {

    private Abonnement abonnement;
    @FXML
    private TableColumn<Abonnement, Client> columnClientAbonnement;

    @FXML
    private TableColumn<Abonnement, Revue> columnRevueAbonnement;

    @FXML
    private TableColumn<Abonnement, Date> columnDateDebAbonnement;

    @FXML
    private TableColumn<Abonnement, Date> columnDateFinAbonnement;

    @FXML
    private ChoiceBox<Client> choiceBoxClient;

    @FXML
    private ChoiceBox<Revue> choiceBoxRevue;

    @FXML
    private Label labelVerifAbonnement;

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

        this.choiceBoxClient.setItems(FXCollections.observableArrayList(dao.getClientIDAO().getAll()));
        this.choiceBoxRevue.setItems(FXCollections.observableArrayList(dao.getRevueIDAO().getAll()));
        columnClientAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Client>("client"));
        columnRevueAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Revue>("Revue"));
        columnDateDebAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("Date_deb"));
        columnDateFinAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("Date_fin"));
        refreshTableAbonnement();
        tableViewAbonnement.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> {
            Abonnement abonnement = tableViewAbonnement.getSelectionModel().getSelectedItem();

            choiceBoxRevue.getSelectionModel().select((Revue) dao.getRevueIDAO().getById(abonnement.getRevue().getId_revue()));
            choiceBoxClient.getSelectionModel().select((Client) dao.getClientIDAO().getById(abonnement.getClient().getId()));


        });


    }

    private void refreshTableAbonnement() {
        // initialise table Periodicite
        tableViewAbonnement.getItems().clear();
        this.tableViewAbonnement.getItems().addAll(dao.getAbonnementIDAO().getAll());
    }
    public void selectAbonnementPutIntoTextField(ActionEvent actionEvent) {

    }
    public boolean verifAbonnement(LocalDate dateDeb,LocalDate dateFin){

        String messageErreur = "";
        boolean check = true;

        if (dateDeb == null || dateFin == null){
            messageErreur = "Veuillez saisir des dates\n";
            check = false;
        }else if (dateDeb.isAfter(dateFin)){
            messageErreur = "Veuillez saisir une date de début valide\n";
            check = false;
        }
        if (choiceBoxClient.getValue() == null){
            messageErreur = messageErreur + "Veuillez saisir un Client\n";
            check = false;
            System.out.println(choiceBoxClient.getValue());
        }
        if (choiceBoxRevue.getValue() == null){
            messageErreur = messageErreur + "Veuillez saisir une Revue\n";
            check = false;
        }

        if (!check){
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Probleme");
            dialog.setContentText(messageErreur);
            dialog.showAndWait();
        }
        return check ;
    }
    public void creationAbonnement()  {


        if(verifAbonnement(dateDebAbo.getValue(),dateFinAbo.getValue())) {

            Date dateDeb =  ConversionDate.DateToLocalDate(dateDebAbo.getValue());
            Date dateFin =  ConversionDate.DateToLocalDate(dateFinAbo.getValue());
            labelVerifAbonnement.setText("Creation reussis");
            Abonnement abonnement = new Abonnement(0, dateDeb, dateFin,choiceBoxClient.getValue(), choiceBoxRevue.getValue());
            dao.getAbonnementIDAO().create(abonnement);
            refreshTableAbonnement();
        }

    }

    public void modifierSelectedAbonnement() {


        if(verifAbonnement(dateDebAbo.getValue(),dateFinAbo.getValue())) {

            Date dateDeb =  ConversionDate.DateToLocalDate(dateDebAbo.getValue());
            Date dateFin =  ConversionDate.DateToLocalDate(dateFinAbo.getValue());

            labelVerifAbonnement.setText("Modification reussie");
            Abonnement abonnement = new Abonnement(0, dateDeb, dateFin,choiceBoxClient.getValue(),choiceBoxRevue.getValue());
            dao.getAbonnementIDAO().update(abonnement);
            refreshTableAbonnement();
        }
    }

    public void supprimerSelectedAbonnement() {
        labelVerifAbonnement.setText("Suppression reussie");
        dao.getAbonnementIDAO().delete(tableViewAbonnement.getSelectionModel().getSelectedItem());
        refreshTableAbonnement();
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
