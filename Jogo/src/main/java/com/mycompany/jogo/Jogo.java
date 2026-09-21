package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;

import java.util.Scanner;

public class Jogo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Abertura do jogo
        System.out.println("========================================");
        System.out.println("            O CASO 47");
        System.out.println("========================================");

        System.out.println("\nUma denúncia anônima chegou até a delegacia.");
        System.out.println("Documentos financeiros desapareceram.");
        System.out.println("Uma movimentação suspeita aconteceu às 23:47.");
        System.out.println("Quatro pessoas possuem alguma ligação com o caso.");

        // Nome do Investigador (Usuário)
        String nomeInvestigador;

        do {
            System.out.print("\nMas antes de tudo, quero saber o seu nome: ");

            nomeInvestigador = scanner.nextLine().trim();

            if (nomeInvestigador.isEmpty()) {
                System.out.println("O nome não pode ficar vazio querido Investigador.");
            }

        } while (nomeInvestigador.isEmpty());

        System.out.println("Perfeito " + nomeInvestigador + ", agora saiba quais serão seus objetivos!");

        // Objetivo do Investigador
        System.out.println("\n========================================");
        System.out.println("            OBJETIVOS");
        System.out.println("========================================");

        System.out.println("- Descobrir quem está mentindo,");
        System.out.println("- Reunir as evidências");
        System.out.println("- Descobrir a verdade por trás do Caso 47.");

        System.out.println("========================================");

        System.out.println("\nBoa sorte, " + nomeInvestigador + "!");

        // Criação dos Personagens
        Investigador investigador = new Investigador(
                nomeInvestigador,
                100,
                100,
                1,
                20,
                10,
                15,
                12,
                10
        );

        // Inicia a investigação
        Investigacao investigacao = new Investigacao("CASO 47");
        
        investigacao.iniciar(investigador, scanner);

        scanner.close();
    }
}