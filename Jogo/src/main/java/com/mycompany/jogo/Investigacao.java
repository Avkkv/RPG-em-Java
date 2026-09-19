package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;
import com.mycompany.jogo.personagens.Suspeito;

import java.util.Scanner;

public class Investigacao {
    private String nomeCaso;
    private int progresso;

    private String[] locais;
    private boolean[] locaisInvestigados;

    private int quantidadeLocaisInvestigados;
    private boolean casoResolvido;

    public Investigacao(String nomeCaso) {

        this.nomeCaso = nomeCaso;
        this.progresso = 0;

        this.locais = new String[]{
            "Delegacia",
            "Posto Central",
            "Estação Abandonada"
        };

        this.locaisInvestigados = new boolean[locais.length];

        this.quantidadeLocaisInvestigados = 0;
        this.casoResolvido = false;
    }

    public String getNomeCaso() {
        return nomeCaso;
    }

    public int getProgresso() {
        return progresso;
    }

    public boolean isCasoResolvido() {
        return casoResolvido;
    }

    public void mostrarProgresso() {

        System.out.println("\n================================");
        System.out.println("          " + nomeCaso);
        System.out.println("================================");

        System.out.println("Progresso: " + progresso + "%");
        System.out.println("Locais investigados: " + quantidadeLocaisInvestigados + "/" + locais.length);

        System.out.println("================================");
    }

    public void mostrarLocais() {

        System.out.println("\n======= LOCAIS =======");

        for (int i = 0; i < locais.length; i++) {
            System.out.println(
                    (i + 1) + " - "
                    + locais[i]
                    + (locaisInvestigados[i]
                    ? " [Investigado]"
                    : "")
            );
        }

        System.out.println("======================");
    }

    private static final int XP_POR_INVESTIGAR = 30;
    public void investigarLocal(int numeroLocal, Investigador investigador) {

        if (numeroLocal < 1 || numeroLocal > locais.length) {
            System.out.println("\nLocal inválido.");
            return;
        }

        int indice = numeroLocal - 1;

        if (locaisInvestigados[indice]) {
            System.out.println("\nEste local já foi investigado.");
            return;
        }

        locaisInvestigados[indice] = true;
        quantidadeLocaisInvestigados++;

        System.out.println("\nVocê está investigando: " + locais[indice]);

        criarPistaDoLocal(indice, investigador);

        atualizarProgresso();
        
        System.out.println("Exp: "+ XP_POR_INVESTIGAR);
        investigador.ganharExperiencia(XP_POR_INVESTIGAR);
    }

    private void criarPistaDoLocal(int indice, Investigador investigador) {

        Pista pista = null;

        switch (indice) {
            case 0:
                pista = new Pista(
                    1,
                    "Registro antigo do Caso 47 encontrado nos arquivos da delegacia.",
                    "Documento",
                    7,
                    false
                );

                break;

            case 1:
                pista = new Pista(
                    2,
                    "Recibo encontrado no posto central na noite do desaparecimento.",
                    "Documento",
                    8,
                    false
                );

                break;

            case 2:

                pista = new Pista(
                    3,
                    "Fotografia mostrando uma pessoa próxima à estação.",
                    "Fotografia",
                    10,
                    false
                );

                break;
        }

        if (pista != null) {
            System.out.println("\nNova pista encontrada!");
            System.out.println(pista.getDescricao());

            investigador.adicionarPista(pista);
        }
    }

    private void atualizarProgresso() {
        progresso = (quantidadeLocaisInvestigados * 100) / locais.length;
    }

    public void resolverCaso() {

        casoResolvido = true;

        progresso = 100;

        System.out.println("\nO Caso foi marcado como resolvido!");
    }

    public void iniciar(Investigador investigador, Suspeito suspeito, Scanner scanner) {

        boolean jogoAtivo = true;

        System.out.println("\n================================");
        System.out.println("          " + nomeCaso);
        System.out.println("================================");

        System.out.println("\nVocê recebeu uma nova investigação.");

        while (jogoAtivo) {

            System.out.println("\n================================");
            System.out.println("        MENU DE INVESTIGAÇÃO");
            System.out.println("================================");

            System.out.println("1 - Investigar um local");
            System.out.println("2 - Ver pistas");
            System.out.println("3 - Analisar uma pista");
            System.out.println("4 - Ver progresso");
            System.out.println("5 - Interrogar suspeito");
            System.out.println("6 - Sair");

            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    mostrarLocais();

                    System.out.print("\nEscolha o local para investigar: ");
                    int numeroLocal = scanner.nextInt();

                    investigarLocal(numeroLocal, investigador);

                    break;
                case 2:
                    investigador.mostrarPistas();

                    break;
                case 3:
                    if (investigador.getQuantidadePistas() == 0) {
                        System.out.println("\nVocê ainda não possui nenhuma pista.");
                    } else {
                        investigador.mostrarPistas();
                        System.out.print("\nEscolha a pista que deseja analisar: ");

                        int numeroPista = scanner.nextInt();

                        investigador.analisarPista(numeroPista);
                    }

                    break;
                case 4:
                    mostrarProgresso();

                    break;
                case 5:
                    if (!investigador.estaVivo()) {
                        System.out.println("\nVocê não pode iniciar um interrogatório.");
                    } else if (suspeito.getResistencia() <= 0) {
                        System.out.println("\nEste suspeito já foi derrotado.");
                    } else {
                        Combate combate = new Combate(investigador, suspeito, scanner);
                        combate.iniciar();
                    }

                    break;
                case 6:
                    jogoAtivo = false;
                    System.out.println("\nInvestigação encerrada.");

                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        }
    }
}
