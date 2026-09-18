package com.mycompany.jogo.personagens;

import com.mycompany.jogo.Pista;

public class Investigador extends Personagem {
    private int investigacao;
    private int persuasao;
    private int observacao;
    private int experiencia;

    private static final int MAX_PISTAS = 20;
    private Pista[] pistas; // O investigador possui um conjunto de objetos do tipo Pista
    private int quantidadePistas;
    
    public Investigador (String nome, int vida, int vidaMaxima, int nivel,
                        int ataque, int defesa, int investigacao,
                        int persuasao, int observacao) {
        
        super(nome, vida, vidaMaxima, nivel, ataque, defesa);
        
        this.investigacao = investigacao;
        this.persuasao = persuasao;
        this.observacao = observacao;
        this.experiencia = 0;
        
        this.pistas = new Pista[MAX_PISTAS]; // Cria espaço para 20 referências a objetos Pista
        this.quantidadePistas = 0;
    }
    
    public int getInvestigacao() {
        return investigacao;
    }
    public int getPersuasao() {
        return persuasao;
    }
    public int getObservacao() {
        return observacao;
    }
    public int getExperiencia() {
        return experiencia;
    }
    
    public void ganharExperiencia(int experiencia) {
        this.experiencia += experiencia;
    }

    // Adiciona uma nova pista ao investigador
    public void adicionarPista(Pista pista) {

        if (quantidadePistas < MAX_PISTAS) {
            pistas[quantidadePistas] = pista;
            quantidadePistas++;

            System.out.println("Nova pista adicionada!");
        } else {
            System.out.println("O investigador não pode carregar mais pistas.");
        }
    }

    // Retorna a quantidade de pistas que o investigador possui
    public int getQuantidadePistas() {
        return quantidadePistas;
    }

    // Mostra todas as pistas do investigador
    public void mostrarPistas() {

        if (quantidadePistas == 0) {
            System.out.println("Você ainda não possui nenhuma pista.");
            return;
        }

        System.out.println("\n========== PISTAS ==========");

        for (int i = 0; i < quantidadePistas; i++) {
            System.out.println("\nPista " + (i + 1));
            System.out.println("Tipo: " + pistas[i].getTipo());
            System.out.println("Descrição: " + pistas[i].getDescricao());
            System.out.println("Importância: " + pistas[i].getImportancia());
            System.out.println("Analisada: " + pistas[i].isAnalisada());
        }

        System.out.println("=============================");
    }

    // Retorna uma pista pelo número escolhido pelo jogador
    public Pista getPista(int numero) {

        if (numero >= 1 && numero <= quantidadePistas) {
            return pistas[numero - 1];
        }

        return null;
    }
}