package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;
import com.mycompany.jogo.personagens.Suspeito;

import java.util.Scanner;

public class Jogo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Abertura do jogo
        System.out.println("========================================");
        System.out.println("            O CASO 47");
        System.out.println("========================================");

        System.out.println("\nVocê foi chamado para investigar um caso misterioso.");
        System.out.println("Este caso foi arquivado há alguns anos, mas novas evidências surgiram recentemente.");
        System.out.println("Então, agora você é o Investigador que vai resolver este mistério!");

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

        System.out.println("- Explorar locais que possuam pistas");
        System.out.println("- Encontrar e analisar essas pistas");
        System.out.println("- Interrogar suspeitos");
        System.out.println("- Descobrir a verdade por trás do caso");

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