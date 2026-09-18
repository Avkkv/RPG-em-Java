package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;
import com.mycompany.jogo.personagens.Suspeito;

public class Jogo {

    public static void main(String[] args) {
        Investigador investigador = new Investigador(
                "Alexandre",
                100,
                100,
                1,
                20,
                10,
                15,
                12,
                10
        );

        Suspeito suspeito = new Suspeito(
                "Ricardo",
                80,
                80,
                1,
                15,
                8,
                "Empresário",
                "Eu nunca estive naquela estação.",
                60,
                70,
                true
        );

        Pista pista1 = new Pista(
                1,
                "Recibo encontrado no posto central.",
                "Documento",
                8,
                false
        );

        System.out.println("=== INVESTIGADOR ===");
        investigador.mostrarStatus();

        System.out.println("\n=== SUSPEITO ===");
        suspeito.mostrarStatus();

        System.out.println("\nRicardo está vivo?");
        System.out.println(suspeito.estaVivo());

        System.out.println("\nInvestigador ataca Ricardo!");
        investigador.atacar(suspeito);

        System.out.println("Vida de Ricardo: " + suspeito.getVida());

        System.out.println("=== PISTA ===");
        pista1.mostrarPista();
        System.out.println("\n=== ANALISANDO ===");
        pista1.analisar();
        System.out.println("\n=== DEPOIS DA ANÁLISE ===");
        pista1.mostrarPista();
    }
}
