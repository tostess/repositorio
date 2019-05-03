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
import static controller.MenuController.menu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Serie;

/**
 *
 * @author ComputaDor
 */
public class IncluirController extends MenuController{

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
    HibernateDAO dao = new SerieDAO();
    private String imgg = "";
    @FXML
    private ImageView imgView;
    @FXML
    private ChoiceBox<Integer> assistidosChoice;
    Serie serie=null;
    
    
    
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

           
            HibernateUtil.beginTransaction();
            if(this.serie == null)
            dao.incluir(c);
            else dao.editar(c);
            HibernateUtil.commit();

            //apagarCamposGravacao();
            Util.fecharTela(btnGravar);

        } catch (Exception ex) {
            HibernateUtil.rollback();
            JOptionPane.showMessageDialog(null, ex);
            
           
        }finally{
                HibernateUtil.close();
            }
        //adicionar tudo 
        

    }
    @FXML
    private void imagem(ActionEvent event) {
        int x = Integer.parseInt(txtEpisodios.getText());
        for(int i = 1; i<=x;i++){
        assistidosChoice.getItems().addAll(i);
        }
        

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

    void setSerie(Serie c) {
        this.serie = c;
        txtSerie.setText(this.serie.getSerie());
    
    }

    
}
