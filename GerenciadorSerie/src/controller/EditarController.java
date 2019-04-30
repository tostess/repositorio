/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.InterfaceDAO;
import DAO.SerieDAO;
import Util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Serie;

/**
 * FXML Controller class
 *
 * @author ComputaDor
 */
public class EditarController implements Initializable {

    @FXML
    private TextField txtSerie;
    @FXML
    private TextField txtTemporadas;
    @FXML
    private TextField txtEmissora;
    @FXML
    private TextField txtClassificacao;
    @FXML
    private TextField txtEpisodios;
    @FXML
    private Button btnGravar;
    @FXML
    private Button btnImg;
    InterfaceDAO dao = new SerieDAO();
    String imgg = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gravar(ActionEvent event) {
        try {
            String serie = txtSerie.getText();
            int temporadas = Integer.parseInt(txtTemporadas.getText());
            String emissora = txtEmissora.getText();
            Float classificacao = Float.parseFloat(txtClassificacao.getText());
            int episodios = Integer.parseInt(txtEpisodios.getText());
            //String imagem = imgg;
            Serie c = new Serie(serie, temporadas, emissora, episodios, classificacao, false, imgg);

            System.out.println("oloco");
            dao.incluir(c);
            //apagarCamposGravacao();
            Util.fecharTela(btnGravar);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
           
        }//adicionar tudo 
        //MenuController.menu.update();
        System.out.println("nao");

    }

    
    @FXML
    private void imagem(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolha A Foto");
        File file = fileChooser.showOpenDialog(new Stage());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.cdr")
        );
        if (file != null) {
            try {
                imgg = "/img/" + file.getName();
                FileOutputStream out = new FileOutputStream(Paths.get("").toAbsolutePath().toString() + "/src/img/" + file.getName());
                Files.copy(file.toPath(), out);

                System.out.println(imgg);
            } catch (IOException ex) {
                System.out.println("Erro: " + ex);
            }

        }

        // Image imageObject = new Image(imgg);
        //imgViewIncluir.setImage(imageObject);
    }
    
}
