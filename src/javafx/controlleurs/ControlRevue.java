package javafx.controlleurs;


import model.daofactory.DAOFactory;
import model.daofactory.Persistance;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlRevue implements Initializable {

    @FXML private ChoiceBox<Periodicite> choiceBoxPerio;

    @FXML private MenuBar myMenuBar;


    @FXML private TableView<Revue> tableViewRevue;

    @FXML private TableColumn<Revue,Integer> columnIdRevue;

    @FXML private TableColumn<Revue, String> columnTitreRevue;

    @FXML private TableColumn<Revue, String> columnDescriptionRevue;

    @FXML private TableColumn<Revue, Float> columnTarifRevue;

    @FXML private TableColumn<Revue,String> columnVisuelRevue;

    @FXML private TableColumn<Revue,Integer> columnPeriodiciteRevue;

    @FXML private TextArea txt_DescriptionRevue;

    @FXML private TextField txt_TarifRevue;

    @FXML private TextField txt_TitreRevue;

    @FXML private Label labelVerifRevue;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private DAOFactory dao = ControlAccueil.getDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tableViewRevue.getItems().clear();
        columnIdRevue.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("Id_revue"));
        columnTitreRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("Titre"));
        columnDescriptionRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("Description"));
        columnVisuelRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("Visuel"));
        columnTarifRevue.setCellValueFactory(new PropertyValueFactory<Revue, Float>("Tarif"));
        columnPeriodiciteRevue.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("Id_periodicite"));
        this.choiceBoxPerio.setItems(FXCollections.observableArrayList(dao.getPeriodiciteIDAO().getAll()));
        refreshTableRevue();

    }

    private void refreshTableRevue() {
        this.tableViewRevue.getItems().clear();
        this.tableViewRevue.getItems().addAll(dao.getRevueIDAO().getAll());
    }

    // Verification des entrées dans les textfield
    private boolean verificationRevue() {
        boolean check = true;
        String messageErreur = "";
        if (txt_TitreRevue.getText().trim().equals("")) {
            messageErreur = messageErreur + " Saisir un titre\n";
            check = false;
        } else if (txt_TitreRevue.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
            // si correspond a un nombre alors saisir un texte
            messageErreur = messageErreur + "Saisir un titre sans nombre\n";
            check = false;
        }

        // verifie si la description est vide
        if (txt_DescriptionRevue.getText().trim().equals("")) {
            messageErreur = messageErreur + " Saisir une description\n";
            check = false;
        }
        // verifie si le tarif est vide ou s'il correspond bien a un nombre
        if (txt_TarifRevue.getText().trim().equals("")) {
            messageErreur = messageErreur + " Saisir un tarif\n";
            check = false;
        } else if (!(txt_TarifRevue.getText().trim().matches("[+-]?\\d*(\\.\\d+)?"))) {
            // si ne correspond pas a un nombre alors saisir un nombre
            messageErreur = messageErreur + " Veuillez saisir un nombre pas un texte dans tarif\n";
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

    public void creationRevue(ActionEvent actionEvent) {
        if (verificationRevue()) {
            labelVerifRevue.setText("Creation reussis");
            Revue revue = new Revue(0,txt_TitreRevue.getText(),txt_DescriptionRevue.getText(),Float.parseFloat(txt_TarifRevue.getText()),null,Integer.parseInt(Integer.toString(choiceBoxPerio.getSelectionModel().getSelectedItem().getId())));
            dao.getRevueIDAO().create(revue);
            refreshTableRevue();
        }
    }

    public void modifierSelectedReue(ActionEvent actionEvent) {
        // recupère l'index de la ligne selectionner
        int index = tableViewRevue.getSelectionModel().getSelectedIndex();
        if (verificationRevue()) {
            labelVerifRevue.setText("Modification reussis");
            Revue revue = new Revue(columnIdRevue.getCellData(index), txt_TitreRevue.getText(), txt_DescriptionRevue.getText(), Float.parseFloat(txt_TarifRevue.getText()), null, Integer.parseInt(Integer.toString(choiceBoxPerio.getSelectionModel().getSelectedItem().getId())));
            dao.getRevueIDAO().update(revue);
            refreshTableRevue();
        }



    }

    public void supprimerSelectedRevue(ActionEvent actionEvent) {
        dao.getRevueIDAO().delete(tableViewRevue.getSelectionModel().getSelectedItem());
        refreshTableRevue();
    }

    public void selectRevuePutIntoTextField(MouseEvent mouseEvent) {
        tableViewRevue.getSelectionModel().selectedIndexProperty().addListener((v, oldValue, newValue) -> {
            Revue revue = tableViewRevue.getSelectionModel().getSelectedItem();
                    if (tableViewRevue.isFocused() == true) {
                        choiceBoxPerio.getSelectionModel().select((Periodicite) dao.getPeriodiciteIDAO().getById(revue.getId_periodicite()));
                        txt_TitreRevue.setText(revue.getTitre());
                        txt_DescriptionRevue.setText(revue.getDescription());
                        txt_TarifRevue.setText(Float.toString(revue.getTarif()));
                    }
        });
    }



    // PERMET DACCEDER AUX DIFFERENTES PAGES
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


