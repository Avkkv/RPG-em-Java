package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;
import com.mycompany.jogo.personagens.Suspeito;

import java.util.Scanner;

public class Deducao {
    public static final int INCONCLUSIVO = 0;
    public static final int ACUSACAO_ERRADA = 1;
    public static final int VERDADE_REVELADA = 2;
    public static final int FINAL_SECRETO = 3;

    private Investigador investigador;
    private Suspeito[] suspeitos;

    public Deducao(Investigador investigador, Suspeito[] suspeitos) {
        this.investigador = investigador;
        this.suspeitos = suspeitos;
    }

    public int realizar(Scanner scanner) {

        if (investigador.getQuantidadePistasAnalisadas() < 3) {
            System.out.println("\nVocê ainda não possui evidências suficientes.");
            System.out.println("Analise pelo menos 3 pistas antes de concluir o caso.");

            return INCONCLUSIVO;
        }

        System.out.println("\n================================");
        System.out.println("        CONCLUSÃO DO CASO");
        System.out.println("================================");

        System.out.println("\nQuem você considera responsável pelo caso?");

        for (int i = 0; i < suspeitos.length; i++) {
            System.out.println((i + 1) + " - " + suspeitos[i].getNome());
        }

        System.out.print("\nEscolha: ");
        int escolhaSuspeito = scanner.nextInt();

        if (escolhaSuspeito < 1 || escolhaSuspeito > suspeitos.length) {
            System.out.println("Escolha inválida.");

            return INCONCLUSIVO;
        }

        Suspeito escolhido = suspeitos[escolhaSuspeito - 1];

        System.out.println("\nQual foi o motivo?");
        System.out.println("1 - Vingança");
        System.out.println("2 - Dinheiro");
        System.out.println("3 - Encobrir erros da investigação");
        System.out.println("4 - Encobrir um esquema financeiro");

        System.out.print("\nEscolha: ");
        int escolhaMotivo = scanner.nextInt();

        String motivoEscolhido;

        switch (escolhaMotivo) {
            case 1:
                motivoEscolhido = "Vingança";
                break;
            case 2:
                motivoEscolhido = "Dinheiro";
                break;
            case 3:
                motivoEscolhido = "Encobrir erros da investigação";
                break;
            case 4:
                motivoEscolhido = "Encobrir um esquema financeiro";
                break;
            default:
                System.out.println("Opção de motivo inválida.");
                return INCONCLUSIVO;
        }

        if (!escolhido.isCulpado()) {
            return ACUSACAO_ERRADA;
        }
        if (!motivoEscolhido.equals(escolhido.getMotivo())) {
            return INCONCLUSIVO;
        }

        int pistasAnalisadas = investigador.getQuantidadePistasAnalisadas();

        if (pistasAnalisadas == investigador.getQuantidadePistas()) {
            return FINAL_SECRETO;
        }

        return VERDADE_REVELADA;
    }
}
