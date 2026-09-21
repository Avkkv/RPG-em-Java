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

    private Historia historia;

    private static final int MAX_SUSPEITOS = 3;
    private Suspeito[] suspeitos;
    private int quantidadeSuspeitos;

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

        this.historia = new Historia();

        this.suspeitos = new Suspeito[MAX_SUSPEITOS];
        this.quantidadeSuspeitos = 0;

        criarSuspeitos();
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

    // Habilidade e Recompensa por interrogar Ricardo
    Habilidade analiseFinanceira = new Habilidade(
        "Análise Financeira",
        "Aumenta sua capacidade de interpretar documentos financeiros.",
        5,
        0,
        0
    );
    Recompensa recompensaRicardo = new Recompensa(
        "Você descobriu informações importantes sobre as finanças de Ricardo.",
        30,
        analiseFinanceira
    );

    // Habilidade e Recompensa por interrogar Helena
    Habilidade leituraCorporal = new Habilidade(
        "Leitura Corporal",
        "Aumenta sua capacidade de perceber comportamentos suspeitos.",
        0,
        0,
        5
    );
    Recompensa recompensaHelena = new Recompensa(
        "Helena compartilhou informações sobre o comportamento da vítima.",
        30,
        leituraCorporal
    );

    // Habilidade e Recompensa por interrogar Marcos
    Habilidade interrogatorioAvancado = new Habilidade(
        "Interrogatório Avançado",
        "Aumenta sua capacidade de obter informações durante interrogatórios.",
        0,
        5,
        0
    );
    Recompensa recompensaMarcos = new Recompensa(
        "Os documentos de Marcos revelaram falhas na investigação original.",
        30,
        interrogatorioAvancado
    );

    private void criarSuspeitos() {

        suspeitos[0] = new Suspeito(
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
                true,
                3,
                "A fotografia contradiz seu depoimento: "
                + "você aparece próximo à estação justamente às 23:47.",
                "Encobrir um esquema financeiro",
                recompensaRicardo
        );

        quantidadeSuspeitos++;

        suspeitos[1] = new Suspeito(
                "Helena",
                70,
                70,
                1,
                12,
                7,
                "Jornalista",
                "Eu não tinha qualquer relação com a vítima.",
                50,
                60,
                false,
                2,
                "O recibo contradiz parte do seu depoimento sobre "
                + "onde você estava naquela noite.",
                "Vingança",
                recompensaHelena
        );

        quantidadeSuspeitos++;

        suspeitos[2] = new Suspeito(
                "Marcos",
                90,
                90,
                2,
                18,
                10,
                "Ex-policial",
                "A investigação original foi conduzida corretamente.",
                70,
                80,
                false,
                1,
                "O documento da delegacia mostra que partes "
                + "do relatório original foram omitidas.",
                "Encobrir erros da investigação",
                recompensaMarcos
        );

        quantidadeSuspeitos++;
    }

    public void mostrarSuspeitos() {

        System.out.println("\n======= SUSPEITOS =======");

        for (int i = 0; i < quantidadeSuspeitos; i++) {
            System.out.println((i + 1) + " - " + suspeitos[i].getNome());
        }

        System.out.println("=========================");
    }

    public Suspeito getSuspeito(int numero) {

        if (numero >= 1 && numero <= quantidadeSuspeitos) {
            return suspeitos[numero - 1];
        }

        return null;
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

        historia.mostrarCapitulo(indice);
        criarPistaDoLocal(indice, investigador);

        atualizarProgresso();
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
                    "O registro mostra que o caso foi arquivado após inconsistências "
                    + "na linha do tempo dos depoimentos. Há uma referência ao horário 23:47.",
                    "Ricardo, como você explica estar na estação às 23:47?"
                );

                break;

            case 1:
                pista = new Pista(
                    2,
                    "Recibo encontrado no posto central na noite do desaparecimento.",
                    "Documento",
                    8,
                    "O recibo registra um abastecimento às 23:32, "
                    + "horário próximo ao desaparecimento.",
                    "O que explica você abastecer o carro naquele horário justamente ao lado do desaparecimento?"
                );

                break;

            case 2:

                pista = new Pista(
                    3,
                    "Fotografia mostrando uma pessoa próxima à estação.",
                    "Fotografia",
                    10,
                    "A análise da fotografia revela uma segunda pessoa ao fundo, "
                    + "próxima à entrada da estação. O rosto não está visível.",
                    "Você conhece essa pessoa da foto?"
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

    private void mostrarResultadoDeducao(int resultado) {

        System.out.println("\n================================");

        switch (resultado) {
            case Deducao.ACUSACAO_ERRADA:
                System.out.println("ACUSAÇÃO INCORRETA");
                System.out.println("O investigador acusou a pessoa errada.");
                break;
            case Deducao.VERDADE_REVELADA:
                System.out.println("VERDADE REVELADA!");
                System.out.println("A verdade por trás do Caso 47 foi revelada!");
                resolverCaso();

                break;
            case Deducao.FINAL_SECRETO:
                System.out.println("FINAL SECRETO!");
                System.out.println("Você descobriu a verdade e também o segredo por trás do número 47!");
                resolverCaso();

                break;
            case Deducao.INCONCLUSIVO:
                System.out.println("CASO INCONCLUSIVO");
                System.out.println("As evidências ainda não são suficientes.");
                break;
        }

        System.out.println("================================");
    }

    public void iniciar(Investigador investigador, Scanner scanner) {

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
            System.out.println("6 - Ver habilidades");
            System.out.println("7 - Concluir caso");
            System.out.println("8 - Sair");

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
                    investigador.mostrarProgresso();

                    break;
                case 5:
                    if (!investigador.estaVivo()) {
                        System.out.println("\nVocê não pode iniciar um interrogatório.");
                    } else {

                        mostrarSuspeitos();

                        System.out.print("\nEscolha o suspeito que deseja interrogar: ");
                        int numeroSuspeito = scanner.nextInt();

                        Suspeito suspeitoEscolhido = getSuspeito(numeroSuspeito);

                        if (suspeitoEscolhido == null) {
                            System.out.println("\nSuspeito inválido.");
                        } else if (suspeitoEscolhido.getResistencia() <= 0) {
                            System.out.println("\nEste suspeito já foi derrotado.");
                        } else {
                            Combate combate = new Combate(investigador, suspeitoEscolhido, scanner);
                            combate.iniciar();
                        }
                    }

                    break;
                case 6:
                    investigador.mostrarHabilidades();
                    break;
                case 7:
                    Deducao deducao = new Deducao(investigador, suspeitos);

                    int resultado = deducao.realizar(scanner);
                    mostrarResultadoDeducao(resultado);

                    break;
                case 8:
                    jogoAtivo = false;
                    System.out.println("\nInvestigação encerrada.");

                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
        }
    }
}
