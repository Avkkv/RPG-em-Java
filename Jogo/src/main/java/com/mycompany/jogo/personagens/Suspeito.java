package com.mycompany.jogo.personagens;

import com.mycompany.jogo.Pista;
import com.mycompany.jogo.Recompensa;

public class Suspeito extends Personagem {
    private String profissao;
    private String depoimento;
    private int resistencia;
    private int inteligencia;
    private boolean culpado;

    private int pistaContradicaoId;
    private String contradicao;

    private String motivo;
    private Recompensa recompensa;
    
    public Suspeito (String nome, int vida, int vidaMaxima, int nivel,
                    int ataque, int defesa, String profissao, String depoimento,
                    int resistencia, int inteligencia, boolean culpado,
                    int pistaContradicaoId, String contradicao, String motivo, Recompensa recompensa) {
        
        super(nome, vida, vidaMaxima, nivel, ataque, defesa);
        
        this.profissao = profissao;
        this.depoimento = depoimento;
        this.resistencia = resistencia;
        this.inteligencia = inteligencia;
        this.culpado = culpado;

        this.pistaContradicaoId = pistaContradicaoId;
        this.contradicao = contradicao;

        this.motivo = motivo;
        this.recompensa = recompensa;
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
    public boolean possuiContradicao(Pista pista) {
        return pista.getId() == pistaContradicaoId;
    }
    public String getContradicao() {
        return contradicao;
    }
    public String getMotivo() {
        return motivo;
    }
    public Recompensa getRecompensa() {
        return recompensa;
    }
    
    public void diminuirResistencia(int valor) {
        if (valor > 0) {
            resistencia -= valor;

            if (resistencia < 0) {
                resistencia = 0;
            }
        }
    }
}