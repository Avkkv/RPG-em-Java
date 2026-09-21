package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;

public class Recompensa {
    private String descricao;
    private int experiencia;
    private Habilidade habilidade;

    public Recompensa(String descricao, int experiencia, Habilidade habilidade) {
        this.descricao = descricao;
        this.experiencia = experiencia;
        this.habilidade = habilidade;
    }

    public String getDescricao() {
        return descricao;
    }
    public int getExperiencia() {
        return experiencia;
    }
    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void entregar(Investigador investigador) {

        System.out.println("\n================================");
        System.out.println("          RECOMPENSA");
        System.out.println("================================");

        System.out.println(descricao);

        if (experiencia > 0) investigador.ganharExperiencia(experiencia);
        if (habilidade != null) investigador.adicionarHabilidade(habilidade);

        System.out.println("================================");
    }
}
