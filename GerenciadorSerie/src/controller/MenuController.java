/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.HibernateDAO;
import DAO.SerieDAO;
import Util.HibernateUtil;
import Util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Serie;

/**
 * FXML Controller class
 *
 * @author ComputaDor
 */
public class MenuController implements Initializable {

    @FXML
    private TableColumn<Serie, String> serieCItem;
    @FXML
    private TableColumn<Serie, Integer> temporadasCItem;
    @FXML
    private TableColumn<Serie, String> emissoraCItem;
    @FXML
    private TableColumn<Serie, Integer> episodiosCItem;
    @FXML
    private TableColumn<Serie, Float> classificacaoCItem;
    @FXML
    private TableColumn<Serie, Boolean> statusCItem;
    @FXML
    private TableColumn<Serie, Integer> idCItem;
    @FXML
    private TableView<Object> tableView;

    @FXML
    private ImageView imgView;

    HibernateDAO dao = new SerieDAO();
    private TextField txtSerie;
    private TextField txtTemporadas;
    private TextField txtEmissora;
    private TextField txtClassificacao;
    private TextField txtEpisodios;
    private Button btnGravar;
    private String imgg = "";
    private ImageView imgViewIncluir;
    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnExluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnUpdate;
    @FXML
    private ProgressBar progressBar;

    public static MenuController menu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menu = this;
        try {
            serieCItem.setCellValueFactory(new PropertyValueFactory<>("Serie"));
            temporadasCItem.setCellValueFactory(new PropertyValueFactory<>("Temporadas"));
            emissoraCItem.setCellValueFactory(new PropertyValueFactory<>("Emissora"));
            episodiosCItem.setCellValueFactory(new PropertyValueFactory<>("Episodios"));
            classificacaoCItem.setCellValueFactory(new PropertyValueFactory<>("Classificacao"));
            statusCItem.setCellValueFactory(new PropertyValueFactory<>("Status"));
            idCItem.setCellValueFactory(new PropertyValueFactory<>("Id"));

            //System.out.println(dao.listar());
            update();
        } catch (Exception ex) {
            System.out.println("deu ruim table");
        }
        // TODO
    }

    void update() {
        try {
            tableView.getItems().setAll(dao.listar());
            System.out.println("teste5");

            //Image imageObject = new Image("/img/20180403121621_816.png");
            //imgView.setImage(imageObject);
        } catch (Exception ex) {
            Logger.getLogger("erro");
        }
    }

    @FXML
    private void incluir(ActionEvent event) {
        chamarTela("/view/Incluir.fxml");
        update();
    }

    public void chamarTela(String arquivo) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource(arquivo));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }

    }

    @FXML
    private void showImg(MouseEvent event) {
        System.out.println("a");

        Serie c = (Serie) tableView.getSelectionModel().getSelectedItem();
        System.out.println(c.getImg());

        if(c.temImg()){
            Image imageObject = new Image(c.getImg());
             imgView.setImage(imageObject);
        }

       

    }

    @FXML
    public void excluir(ActionEvent event) {
        Serie c = (Serie) tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir");
        alert.setHeaderText("Aviso");
        alert.setContentText("Deseja realmente excluir " + c.getSerie() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                
            HibernateUtil.beginTransaction();
            dao.excluir(c);
            HibernateUtil.commit();
                update();
            } catch (Exception ex) {
                HibernateUtil.rollback();
                System.out.println("Erro: " + ex);
            }finally{
                HibernateUtil.close();
            }

        }
        update();

    }

    @FXML
    public void updatee(ActionEvent event) {
        update();
    }

    @FXML
    private void editar(ActionEvent event) {
        Serie c = (Serie) tableView.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/view/Incluir.fxml"));
            
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            IncluirController controller = loader.getController();
            controller.setSerie(c);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
       
    }

}
