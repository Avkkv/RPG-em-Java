package com.mycompany.jogo.personagens;

public class Suspeito extends Personagem {
    private String profissao;
    private String depoimento;
    private int resistencia;
    private int inteligencia;
    private boolean culpado;
    
    public Suspeito (String nome, int vida, int vidaMaxima, int nivel,
                    int ataque, int defesa, String profissao, String depoimento,
                    int resistencia, int inteligencia, boolean culpado) {
        
        super(nome, vida, vidaMaxima, nivel, ataque, defesa);
        
        this.profissao = profissao;
        this.depoimento = depoimento;
        this.resistencia = resistencia;
        this.inteligencia = inteligencia;
        this.culpado = culpado;
    }

    public String getProfissao() {
        return profissao;
    }
    public String getDepoimento() {
        return depoimento;
    }
    public int getResistencia() {
        return resistencia;
    }
    public int getInteligencia() {
        return inteligencia;
    }
    public boolean isCulpado() {
        return culpado;
    }
    
    
}
