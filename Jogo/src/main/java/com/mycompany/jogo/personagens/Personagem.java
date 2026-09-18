package com.mycompany.jogo.personagens;

public abstract class Personagem {
    private final String nome;
    private int vida;
    private int vidaMaxima;
    private int nivel;
    private int ataque;
    private int defesa;
    
    // Construtor
    public Personagem (String nome, int vida, int vidaMaxima, int nivel, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    
    public int getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }
    
    // Setter da vida
    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > vidaMaxima) {
            this.vida = vidaMaxima;
        } else {
            this.vida = vida;
        }
    }
    
    // Setter do nível
    public void setNivel(int nivel) {
        if (nivel > 0) {
            this.nivel = nivel;
        }
    }
    
    // Receber dano
    public void receberDano(int dano) {
        if (dano > 0) {
            vida -= dano;

            if (vida < 0) {
                vida = 0;
            }
        }
    }
    
    // Verifica se o personagem ainda está vivo
    public boolean estaVivo(){
        return vida > 0;
    }
    
    // Ataque
    public void atacar(Personagem alvo) {
        if (!this.estaVivo()) {
            return;
        }
        if (!alvo.estaVivo()) {
            return;
        }
        
        int dano = ataque - alvo.getDefesa();
        
        // O ataque sempre causa pelo menos 1 de dano
        if (dano < 1) {
            dano = 1;
        }
        
        alvo.receberDano(dano);
    }
    
    // Evolução de nível
    public void aumentarNivel(){
        nivel++;
        
        ataque += 5;
        defesa += 3;
        
        vidaMaxima += 20;
        
        // Ao subir de nível, recupera toda a vida
        vida = vidaMaxima;
    }
    
    // Exibe informações do personagem
    public void mostrarStatus() {
        System.out.println("=================================");
        System.out.println("STATUS");
        System.out.println("=================================");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + vida + "/" + vidaMaxima);
        System.out.println("Nível: " + nivel);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("=================================");
    }
}
