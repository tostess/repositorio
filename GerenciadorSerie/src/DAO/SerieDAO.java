/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Serie;
import org.hibernate.Session;

/**
 *
 * @author ComputaDor
 */
public class SerieDAO extends HibernateDAO<Serie>{
    
    public SerieDAO(){
        super(Serie.class);
    }
    
    public double estatistica(Serie serie){
        double estatistica = 1.1;
        return estatistica;
    }
    
    @Override
    public List<Serie> listar() throws Exception {
        List<Serie> lista = criarCriteria().list();
                
        return lista;

    }

    
}
