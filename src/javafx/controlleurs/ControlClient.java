package javafx.controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.daofactory.DAOFactory;
import model.daofactory.Persistance;
import model.metier.Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.tools.ProcessAdresse.*;

public class ControlClient  implements Initializable {

    @FXML private TableView<Client> tableViewClient;

    @FXML private TableColumn<Client, String> columnNomClient;

    @FXML private TableColumn<Client, Integer> columnNumClient;

    @FXML private TableColumn<Client, String> columnPrenomClient;

    @FXML private TableColumn<Client, String> columnAdresseClient;

    @FXML private MenuBar myMenuBar;

    @FXML private TextField txt_CodePostalClient;

    @FXML private TextField txt_NoRueClient;

    @FXML private TextField txt_NomClient;

    @FXML private TextField txt_PaysClient;

    @FXML private TextField txt_PrenomClient;

    @FXML private TextField txt_VilleClient;

    @FXML private TextField txt_VoieClient;

    @FXML private Label labelVerifClient;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private final DAOFactory dao = ControlAccueil.getDao();





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialise table Client
        columnNumClient.setCellValueFactory(new PropertyValueFactory<Client, Integer>("Id"));
        columnNomClient.setCellValueFactory(new PropertyValueFactory<Client, String>("Nom"));
        columnPrenomClient.setCellValueFactory(new PropertyValueFactory<Client, String>("Prenom"));
        columnAdresseClient.setCellValueFactory(new PropertyValueFactory<Client, String>("Adresse"));
        refreshTableClient();
    }
    private void refreshTableClient() {
        tableViewClient.getItems().clear();
        this.tableViewClient.getItems().addAll(dao.getClientIDAO().getAll());

    }
    private boolean verificationClient() {

        boolean check = true;
        String messageErreur = "";

        if (txt_NomClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un nom\n";
            check = false;
        } else if (txt_NomClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            messageErreur = messageErreur + "Aucun chiffre est autoriser pour la saisie du nom\n";
            check = false;
        }
        if (txt_PrenomClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un prenom\n";
            check = false;
        } else if (txt_PrenomClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            messageErreur = messageErreur + "Aucun chiffre est autoriser pour la saisie du prenom\n";
            check = false;
        }
        if (txt_NoRueClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un Numero de Rue\n";
            check = false;
        } else if (!(txt_NoRueClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?"))) {
            messageErreur = messageErreur + "Saisir un nombre dans No_Rue\n";
            check = false;
        }
        if (txt_VoieClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir une Voie\n";
            check = false;
        } else if (txt_VoieClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            messageErreur = messageErreur + "Aucun chiffre est autoriser pour la saisie d'une Voie\n";
            check = false;
        }
        if (txt_VilleClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir une Ville\n";
            check = false;
        } else if (txt_VilleClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            messageErreur = messageErreur + "Aucun chiffre est autoriser pour la saisie du Ville\n";
            check = false;
        }
        if (txt_PaysClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un Pays\n";
            check = false;
        } else if (txt_PrenomClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            messageErreur = messageErreur + "Aucun chiffre autoriser pour la saisie du Pays\n";
            check = false;
        }
        if (txt_CodePostalClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un code Postal\n";
            check = false;
        }
        if (!(txt_CodePostalClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?"))) {
            messageErreur = messageErreur + "Saisir un nombre dans Code Postal\n";
            check = false;
        }
        if (!check) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setResizable(false);
            dialog.setTitle("Probleme");
            dialog.setContentText(messageErreur);
            dialog.showAndWait();
        }
        return check;
    }
   

    @FXML
    void creationClient() {

        if (verificationClient()) {
            labelVerifClient.setText("Creation reussie");
            Client client = new Client(txt_NomClient.getText(), txt_PrenomClient.getText(),txt_NoRueClient.getText(), txt_VoieClient.getText(), txt_CodePostalClient.getText(), txt_VilleClient.getText(), txt_PaysClient.getText(),0);
            normalizePays(client);
            normalizeVille(client);
            normalizeCodePostal(client);
            normalizeVoie(client);
            normalizeCodePostal(client);
            dao.getClientIDAO().create(client);
            refreshTableClient();
        }
    }



    public void modifierSelectedClient(ActionEvent actionEvent) {
        if (verificationClient()) {
            labelVerifClient.setText("Modification reussie");
            int index = tableViewClient.getSelectionModel().getSelectedIndex();
            Client client = new Client(txt_NomClient.getText(), txt_PrenomClient.getText(), txt_NoRueClient.getText(), txt_VoieClient.getText(), txt_CodePostalClient.getText(), txt_VilleClient.getText(), txt_PaysClient.getText(), columnNumClient.getCellData(index));
            normalizePays(client);
            normalizeVille(client);
            normalizeCodePostal(client);
            normalizeVoie(client);
            normalizeCodePostal(client);
            dao.getClientIDAO().update(client);
            refreshTableClient();

        }

    }
    
    public void supprimerSelectedClient() {
        labelVerifClient.setText("Supression reussie");
        dao.getClientIDAO().delete(tableViewClient.getSelectionModel().getSelectedItem());
        refreshTableClient();

    }


    public void selectClientPutIntoTextField() {
        tableViewClient.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> {
            Client client = tableViewClient.getSelectionModel().getSelectedItem();//classe du model
            if (tableViewClient.isFocused() == true) {
                txt_NomClient.setText(client.getNom());
                txt_PrenomClient.setText(client.getPrenom());
                txt_NoRueClient.setText(client.getNo_rue());
                txt_VoieClient.setText(client.getVoie());
                txt_VilleClient.setText(client.getVille());
                txt_PaysClient.setText(client.getPays());
                txt_CodePostalClient.setText(client.getCode_postal());
            }
        });
    }

    @FXML
    void importerClient() throws IOException {
        FileChooser parcourirFichiers = new FileChooser();
        File selectedFile = parcourirFichiers.showOpenDialog(null);
        if (selectedFile != null) { // si on choisit un fichier , on fait la methode

            BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath())); // recupère le chemin du dossier
            String line;
            try {

                while((line =reader.readLine()) != null) {
                    String[] row = line.split(";");
                    Client client = new Client(row[0],row[1],row[2],row[3],row[4],row[5],row[6],Integer.parseInt(row[7]));
                    normalizePays(client);
                    normalizeVille(client);
                    normalizeCodePostal(client);
                    normalizeVoie(client);
                    normalizeCodePostal(client);
                    dao.getClientIDAO().create(client);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                refreshTableClient();
                reader.close();
            }
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


