package com.mycompany.jogo;

import com.mycompany.jogo.personagens.Investigador;
import com.mycompany.jogo.personagens.Suspeito;

import java.util.Random;
import java.util.Scanner;

public class Combate {
    private Investigador investigador;
    private Suspeito suspeito;
    private Pista pistaEspecial;

    private int rodada;
    private boolean combateAtivo;

    private Random random;
    private Scanner scanner;

    public Combate(Investigador investigador, Suspeito suspeito, Scanner scanner) {

        this.investigador = investigador;
        this.suspeito = suspeito;
        this.pistaEspecial = null;
        this.scanner = scanner;

        this.rodada = 1; // Em qual turno o jogador está
        this.combateAtivo = false; // Controla se o interrogatório continua

        this.random = new Random(); // responsável pela aleatoriedade
    }

    public void mostrarStatus() {

        System.out.println("\n================================");
        System.out.println("        INTERROGATÓRIO");
        System.out.println("================================");

        System.out.println("Investigador: " + investigador.getNome());
        System.out.println("Vida: " + investigador.getVida() + "/" + investigador.getVidaMaxima());

        System.out.println();

        System.out.println("Suspeito: " + suspeito.getNome());
        System.out.println("Resistência: " + suspeito.getResistencia());

        System.out.println("Rodada: " + rodada);

        System.out.println("================================");
    }

    // Método auxiliar para limitar a chance entre 10% e 90%
    private int limitarChance(int chance) {

        if (chance < 10) {
            return 10;
        }
        if (chance > 90) {
            return 90;
        }

        return chance;
    }

    // Método para sortear a chance
    private boolean testarChance(int chance) {

        int numero = random.nextInt(100) + 1;

        return numero <= chance;
    }

    // Considerar: persuasão do investigador + inteligência do suspeito
    // A inteligência dificulta o interrogatório
    public void questionar() {

        int chance = 50
                + investigador.getPersuasao()
                - (suspeito.getInteligencia() / 2);

        chance = limitarChance(chance);

        System.out.println("\nVocê questiona o suspeito...");

        if (testarChance(chance)) {
            System.out.println("O suspeito revelou uma informação importante!");
            suspeito.diminuirResistencia(10);
        } else {
            System.out.println("O suspeito conseguiu evitar a pergunta.");
            investigador.receberDano(5);
        }

    }

    // Aqui o risco é maior, porém o sucesso causa mais dano à resistência
    public void pressionar() {

        int chance = 35
                + investigador.getPersuasao()
                - (suspeito.getResistencia() / 4);

        chance = limitarChance(chance);

        System.out.println("\nVocê pressiona o suspeito.");

        if (testarChance(chance)) {
            System.out.println("O suspeito ficou nervoso e entrou em contradição!");
            suspeito.diminuirResistencia(20);
        } else {
            System.out.println("O suspeito percebeu sua estratégia.");
            investigador.receberDano(10);
        }
    }

    public void observar() {

        int chance = 50
                + investigador.getObservacao()
                - (suspeito.getInteligencia() / 2);

        chance = limitarChance(chance);

        System.out.println("\nVocê observa atentamente o comportamento do suspeito.");

        if (testarChance(chance)) {
            System.out.println("Você percebeu uma contradição no comportamento dele!");
            suspeito.diminuirResistencia(5);
        } else {
            System.out.println("Você não percebeu nada relevante.");
        }
    }

    public void apresentarPista(Pista pista) {

        if (!pista.isAnalisada()) {
            System.out.println("\nVocê ainda não analisou essa pista.");
            return;
        }

        int chance = 60
                + investigador.getInvestigacao()
                + pista.getImportancia()
                - (suspeito.getInteligencia() / 2);

        chance = limitarChance(chance);

        System.out.println("\nVocê apresenta a seguinte evidência:");
        System.out.println(pista.getDescricao());

        if (testarChance(chance)) {
            System.out.println("O suspeito entrou em contradição!");
            suspeito.diminuirResistencia(25);

            if (suspeito.possuiContradicao(pista)) {
                pistaEspecial = pista;
                System.out.println("\nNova pergunta especial desbloqueada!");
            } else {
                System.out.println("O suspeito conseguiu responder à evidência.");
                investigador.receberDano(5);
            }
        }
    }

    public void fazerPerguntaEspecial() {

        if (pistaEspecial == null) {
            System.out.println("\nNenhuma pergunta especial foi desbloqueada.");
            return;
        }

        System.out.println("\n================================");
        System.out.println("        PERGUNTA ESPECIAL");
        System.out.println("================================");

        System.out.println("\nVocê pergunta:");
        System.out.println(pistaEspecial.getPerguntaDesbloqueada());

        System.out.println("\n" + suspeito.getNome() + " fica em silêncio por alguns segundos...");
        System.out.println(suspeito.getContradicao());

        suspeito.diminuirResistencia(20);

        pistaEspecial = null;

        System.out.println("\nA resistência do suspeito diminuiu!");
    }
    
    private static final int XP_POR_VITORIA = 15; // Quantidade de Experiencia ganha por Vitoria
    
    private void finalizar() {

        combateAtivo = false;

        System.out.println("\n================================");
        System.out.println("       FIM DO INTERROGATÓRIO");
        System.out.println("================================");

        if (!investigador.estaVivo()) {
            System.out.println("O suspeito venceu o interrogatório!");
        } else if (suspeito.getResistencia() <= 0) {
            System.out.println("O investigador venceu o interrogatório!");
            
            investigador.ganharExperiencia(XP_POR_VITORIA);

            if (suspeito.getRecompensa() != null) {
                suspeito.getRecompensa().entregar(investigador);
            }
        } else {
            System.out.println("O interrogatório foi encerrado.");
            System.out.println("Não houve vencedor.");
        }

        System.out.println("\n--- STATUS FINAL ---");

        System.out.println("\nInvestigador: " + investigador.getNome());
        System.out.println("Vida: " + investigador.getVida() + "/" + investigador.getVidaMaxima());

        System.out.println("\nSuspeito: " + suspeito.getNome());
        System.out.println("Resistência: " + suspeito.getResistencia());

        System.out.println("\n================================");
    }
    
    public void iniciar() {

        combateAtivo = true;
        rodada = 1;

        System.out.println("\nO interrogatório começou!");

        while (combateAtivo) {

            mostrarStatus();

            System.out.println("\nEscolha uma ação:");
            System.out.println("1 - Questionar");
            System.out.println("2 - Pressionar");
            System.out.println("3 - Observar");
            System.out.println("4 - Apresentar pista");
            if (pistaEspecial != null) System.out.println("5 - Fazer pergunta especial");
            System.out.println("6 - Sair");

            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    questionar();
                    break;
                case 2:
                    pressionar();
                    break;
                case 3:
                    observar();
                    break;
                case 4:
                    if (investigador.getQuantidadePistas() == 0) {
                        System.out.println("\nVocê não possui nenhuma pista.");
                    } else {
                        investigador.mostrarPistas();
                        System.out.print("\nEscolha o número da pista: ");

                        int numeroPista = scanner.nextInt();

                        Pista pistaEscolhida = investigador.getPista(numeroPista);

                        if (pistaEscolhida == null) {
                            System.out.println("Pista inválida.");
                        } else {
                            apresentarPista(pistaEscolhida);
                        }
                    }

                    break;
                case 5:
                    fazerPerguntaEspecial();
                    break;
                case 6:
                    finalizar();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            rodada++;

            if (!investigador.estaVivo() || suspeito.getResistencia() <= 0) finalizar();
        }
    }
}