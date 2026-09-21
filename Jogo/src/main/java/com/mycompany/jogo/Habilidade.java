package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;

public class Habilidade {
    private String nome;
    private String descricao;

    private int bonusInvestigacao;
    private int bonusPersuasao;
    private int bonusObservacao;

    public Habilidade(String nome, String descricao, int bonusInvestigacao,
                      int bonusPersuasao, int bonusObservacao) {

        this.nome = nome;
        this.descricao = descricao;

        this.bonusInvestigacao = bonusInvestigacao;
        this.bonusPersuasao = bonusPersuasao;
        this.bonusObservacao = bonusObservacao;
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public int getBonusInvestigacao() {
        return bonusInvestigacao;
    }
    public int getBonusPersuasao() {
        return bonusPersuasao;
    }
    public int getBonusObservacao() {
        return bonusObservacao;
    }
}
