/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Serie;

/**
 *
 * @author ComputaDor
 */
public class SerieDAO implements InterfaceDAO{
    
    @Override
    public Connection conectar() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn
                = DriverManager.getConnection("jdbc:mysql://localhost:3306/series", "root", "");
        return conn;
    }

    
    @Override
    public void incluir(Object o) throws Exception {
        Connection conn = conectar();
        try {
            Serie c = (Serie) o;//instanciando contato num objeto genérico.
            String sql = "INSERT INTO serie(serie, temporadas, emissora, episodios, classificacao, status, img) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, c.getSerie());
            ps.setInt(2, c.getTemporadas());
            ps.setString(3, c.getEmissora());
            ps.setInt(4, c.getEpisodios());
            ps.setFloat(5, c.getClassificacao());
            ps.setBoolean(6, c.getStatus());
            ps.setString(7, c.getImg());
            ps.execute();
        } finally {
            conn.close();
        }
    }

    
    @Override
    public void editar(Object o) throws Exception {
        Connection conn = conectar();
        try {

            Serie c = (Serie) o;
            String sql = "UPDATE serie SET serie=?, temporadas= ? , emissora =?, episodios =?, classificacao =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getSerie());
            ps.setInt(2, c.getTemporadas());
            ps.setString(3, c.getEmissora());
            ps.setInt(4, c.getEpisodios());
            ps.setFloat(5, c.getClassificacao());
           // ps.setInt(6, c.getId());
            ps.execute();
        } finally {
            conn.close();
        }
    }

    
    @Override
    public void excluir(Object o) throws Exception {
        Connection conn = conectar();
        try {
            Serie c = (Serie) o;
            String sql = "DELETE FROM serie WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.execute();
        } finally {
            conn.close();
        }
    }

    
    @Override
    public Object pesquisar(int id) throws Exception {

        String sql = "SELECT * FROM  serie WHERE id=" + id;
        Connection conn = conectar();
        Serie c = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Serie();
                c.setId(rs.getInt("id"));
                c.setSerie(rs.getString("serie"));
                c.setTemporadas(rs.getInt("temporadas"));
                c.setEmissora(rs.getString("emissora"));
                c.setEpisodios(rs.getInt("episodios"));
                c.setClassificacao(rs.getFloat("classificacao"));
                c.setImg(rs.getString("img"));

            }
        } finally {
            conn.close();
        }
        return c;
    }
    public double estatistica(Serie serie){
        double estatistica = 1.1;
        return estatistica;
    }
    
    @Override
    public List<Object> listar() throws Exception {
        List<Object> lista = new ArrayList();
        String sql = "SELECT * FROM  serie";
        Connection conn = conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Serie c = new Serie();
                c.setId(rs.getInt("id"));
                c.setSerie(rs.getString("serie"));
                c.setTemporadas(rs.getInt("temporadas"));
                c.setEmissora(rs.getString("emissora"));
                c.setEpisodios(rs.getInt("episodios"));
                c.setClassificacao(rs.getFloat("classificacao"));
                c.setImg(rs.getString("img"));
                lista.add(c);
            }
        } finally {
            conn.close();
        }
        return lista;

    }

    
}
