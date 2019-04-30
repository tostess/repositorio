/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ComputaDor
 */
public class Serie {
    private String serie;
    private Integer temporadas;
    private String emissora;
    private Integer episodios;
    private Float classificacao;
    private Boolean status;
    private String img;
    //private Float estatistica;
    
    private int id;
    
    public Serie(){
    
    }

    public Serie(String serie, Integer temporadas, String emissora, Integer episodios, Float classificacao, Boolean status, String img) {
        this.serie = serie;
        this.temporadas = temporadas;
        this.emissora = emissora;
        this.episodios = episodios;
        this.classificacao = classificacao;
        this.status = status;
        this.img = img;
    }

    public Serie(String serie, int temporadas, String emissora, int episodios, Float classificacao ) {
        this.serie = serie;
        this.temporadas = temporadas;
        this.emissora = emissora;
        this.episodios = episodios;
        this.classificacao = classificacao;
        //this.status = status;
       // this.img = img;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Integer temporadas) {
        this.temporadas = temporadas;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public Float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Float classificacao) {
        this.classificacao = classificacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
    
}
