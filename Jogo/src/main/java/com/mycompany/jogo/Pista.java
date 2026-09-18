package com.mycompany.jogo;

public class Pista {
    private int id;
    private String descricao;
    private String tipo;
    private int importancia;
    private boolean analisada;

    public Pista(int id, String descricao, String tipo, int importancia, boolean analisada) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.importancia = importancia;
        this.analisada = analisada;
    }

    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getTipo() {
        return tipo;
    }
    public int getImportancia() {
        return importancia;
    }
    public boolean isAnalisada() {
        return analisada;
    }
    
    public void analisar() {
        if (!analisada) {
            analisada = true;
            System.out.println("A pista foi analisada!");
        } else {
            System.out.println("Esta pista já foi analisada!");
        }
    }
    
    public void mostrarPista() {
        System.out.println("ID: " + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Importância: " + importancia);
        System.out.println("Analisada: " + analisada);
    }
}