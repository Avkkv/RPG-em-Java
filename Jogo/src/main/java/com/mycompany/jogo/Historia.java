package com.mycompany.jogo;

public class Historia {
    private String[] capitulos;

    public Historia() {

        capitulos = new String[]{

            "O Caso 47 foi arquivado há alguns anos após o desaparecimento de uma pessoa investigada pela polícia.",

            "Novas evidências surgiram recentemente. O registro original apresenta inconsistências na linha do tempo.",

            "As primeiras evidências apontam para a antiga estação, onde uma pessoa foi vista na noite do desaparecimento.",

            "A investigação leva até a residência da vítima. Há sinais de que ela estava investigando alguém antes de desaparecer.",

            "O Arquivo Municipal revela documentos que não estavam presentes na investigação original.",

            "As últimas evidências apontam para o círculo próximo a Ricardo. Agora cabe ao investigador descobrir a verdade."
        };
    }

    public void mostrarCapitulo(int numero) {

        if (numero >= 0 && numero < capitulos.length) {

            System.out.println("\n================================");
            System.out.println("            HISTÓRIA");
            System.out.println("================================");

            System.out.println(capitulos[numero]);

            System.out.println("================================");
        }
    }
}
