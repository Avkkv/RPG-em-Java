package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;
import com.mycompany.jogo.personagens.Suspeito;

import java.util.Scanner;

public class Jogo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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

        Investigacao investigacao = new Investigacao("CASO 47");
        
        investigacao.iniciar(investigador, suspeito, scanner);

        scanner.close();
    }
}