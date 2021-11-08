package com.example.app_peliculas_series.model;


import java.io.Serializable;

public class PeliculasBean implements Serializable {

    public String titulo;
    public String descripcion;
    public String poster;

    public PeliculasBean(){

    }

    public PeliculasBean(String titulo, String descripcion, String poster) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.poster = poster;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
