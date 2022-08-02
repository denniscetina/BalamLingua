package com.example.balamlingua;

public class Cronometro {
    String milisegundos,segundos,minutos;

    public Cronometro(String milisegundos, String segundos, String minutos) {
        this.milisegundos = milisegundos;
        this.segundos = segundos;
        this.minutos = minutos;
    }
    public Cronometro(){

    }

    public String getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(String milisegundos) {
        this.milisegundos = milisegundos;
    }

    public String getSegundos() {
        return segundos;
    }

    public void setSegundos(String segundos) {
        this.segundos = segundos;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }
}
