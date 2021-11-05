package javafx.controlleurs;

import model.daofactory.DAOFactory;
import model.daofactory.Persistance;
import model.metier.Client;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML private TextField txt_NumeroClient;

    @FXML private TextField txt_PaysClient;

    @FXML private TextField txt_PrenomClient;

    @FXML private TextField txt_VilleClient;

    @FXML private TextField txt_VoieClient;

    @FXML private Label labelVerifClient;

    @FXML private Button btnImporter;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private DAOFactory dao = ControlAccueil.getDao();





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
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
        if (txt_NumeroClient.getText().trim().equals("")) {
            messageErreur = messageErreur + "Saisir un numero Client\n";
            check = false;
        } else if (!(txt_NumeroClient.getText().trim().matches("[+-]?\\d*(\\.\\d+)?"))) {
            messageErreur = messageErreur + "Saisir un nombre dans numero Client\n";
            check = false;
        }
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
    void creationClient(ActionEvent event) {
        if (verificationClient()) {
            labelVerifClient.setText("creation reussis");
            Client client = new Client(txt_NomClient.getText(), txt_PrenomClient.getText(), txt_NoRueClient.getText(), txt_VoieClient.getText(), txt_CodePostalClient.getText(), txt_VilleClient.getText(), txt_PaysClient.getText(), Integer.parseInt(txt_NumeroClient.getText()));
            dao.getClientIDAO().create(client);
            refreshTableClient();
        }
    }

    public void modifierSelectedClient(ActionEvent actionEvent) {
        if (verificationClient()) {
            labelVerifClient.setText("Modification reussis");
            Client client = new Client(txt_NomClient.getText(), txt_PrenomClient.getText(), txt_NoRueClient.getText(), txt_VoieClient.getText(), txt_CodePostalClient.getText(), txt_VilleClient.getText(), txt_PaysClient.getText(), Integer.parseInt(txt_NumeroClient.getText()));
            dao.getClientIDAO().update(client);
            refreshTableClient();
        }

    }
    
    public void supprimerSelectedClient(ActionEvent actionEvent) {
        dao.getClientIDAO().delete(tableViewClient.getSelectionModel().getSelectedItem());
        refreshTableClient();

    }


    public void selectClientPutIntoTextField(MouseEvent mouseEvent) {
        tableViewClient.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> {
            Client client = tableViewClient.getSelectionModel().getSelectedItem();//classe du model
            if (tableViewClient.isFocused() == true) {
                txt_NumeroClient.setText(Integer.toString(client.getId()));
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
    void importerClient(ActionEvent event) throws IOException {
        FileChooser parcourirFichiers = new FileChooser();
        File selectedFile = parcourirFichiers.showOpenDialog(null);
        //parcourirFichiers.setInitialDirectory(new File("C:\\Users\\krebs\\OneDrive\\Documents"));
        //parcourirFichiers.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
        if (selectedFile != null) { // si on choisit un fichier , on fait la methode

            BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath())); // recupère le chemin du dossier
            String line ="";
            try {

                while((line =reader.readLine()) != null) {
                    String[] row = line.split(";");
                    Client client = new Client(row[0],row[1],row[2],row[3],row[4],row[5],row[6],Integer.parseInt(row[7]));
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


