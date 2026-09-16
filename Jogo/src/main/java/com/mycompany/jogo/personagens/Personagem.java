package com.mycompany.jogo.personagens;

public abstract class Personagem {
    private final String nome;
    private int vida;
    private int vidaMaxima;
    private int nivel;
    private int ataque;
    private int defesa;
    
    public Personagem (String nome, int vida, int vidaMaxima, int nivel, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }
    
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }
    
    public void receberDano(int dano) {
        
    }
    public boolean estaVivo(){
        
    }
    public void atacar(Personagem alvo) {
        
    }
    public void aumentarNivel(){
        
    }
}
