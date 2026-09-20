package com.mycompany.jogo;

public class Pista {
    private int id;
    private String descricao;
    private String tipo;
    private int importancia;
    private boolean analisada;

    private String informacaoRevelada;
    private String perguntaDesbloqueada;

    public Pista(int id, String descricao, String tipo,
                int importancia, String informacaoRevelada, String perguntaDesbloqueada) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.importancia = importancia;
        this.analisada = false;

        this.informacaoRevelada = informacaoRevelada;
        this.perguntaDesbloqueada = perguntaDesbloqueada;
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
    public String getInformacaoRevelada() {
        return informacaoRevelada;
    }
    public String getPerguntaDesbloqueada() {
        return perguntaDesbloqueada;
    }
    
    public void analisar() {
        if (!analisada) {
            analisada = true;

            System.out.println("\nA pista foi analisada!");
            System.out.println("Informação descoberta: " + informacaoRevelada);

            if (perguntaDesbloqueada != null && !perguntaDesbloqueada.isEmpty()) {
                System.out.println("\nNova pergunta desbloqueada:");
                System.out.println(perguntaDesbloqueada);
            }
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

        if (analisada) {
            System.out.println("Informação descoberta: " + informacaoRevelada);

            if (perguntaDesbloqueada != null && !perguntaDesbloqueada.isEmpty()) {
                System.out.println("Pergunta desbloqueada: " + perguntaDesbloqueada);
            }
        }
    }
}