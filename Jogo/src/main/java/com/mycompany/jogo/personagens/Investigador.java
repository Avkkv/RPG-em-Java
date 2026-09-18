package com.mycompany.jogo.personagens;

public class Investigador extends Personagem {
    private int investigacao;
    private int persuasao;
    private int observacao;
    private int experiencia;
    
    public Investigador (String nome, int vida, int vidaMaxima, int nivel,
                        int ataque, int defesa, int investigacao,
                        int persuasao, int observacao) {
        
        super(nome, vida, vidaMaxima, nivel, ataque, defesa);
        
        this.investigacao = investigacao;
        this.persuasao = persuasao;
        this.observacao = observacao;
        this.experiencia = 0;   
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
}
