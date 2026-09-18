# RPG em Java - "O Caso 47"

## Resumo

**O Caso 47** é um RPG investigativo desenvolvido em Java para ser executado pelo terminal.

O jogador assume o papel de um investigador responsável por solucionar um caso misterioso. Em vez de utilizar apenas combates físicos, o jogo utiliza **interrogatórios, investigação e apresentação de evidências** como principal forma de confronto.

Durante o jogo, o investigador encontra e analisa pistas, interage com suspeitos e utiliza seus atributos para aumentar suas chances de sucesso durante os interrogatórios. O sistema de combate utiliza o `Random` para introduzir aleatoriedade nos resultados.

A proposta do jogo é fazer com que o jogador não apenas avance por combates, mas precise **interpretar informações e conectar pistas para descobrir a verdade por trás do Caso 47**.

> **Observação:** algumas funcionalidades da proposta, como o sistema completo de investigação, conclusão do caso e múltiplos finais, ainda serão desenvolvidas.

---

## Lógica do Jogo

A lógica geral planejada para o jogo funciona da seguinte maneira:

```text
Início do jogo
      |
      v
Criar Investigador
      |
      v
Investigar locais
      |
      v
Encontrar pistas
      |
      v
Analisar e armazenar pistas
      |
      v
Encontrar Suspeitos
      |
      v
Iniciar interrogatório
      |
      v
Escolher ações durante o combate
      |
      +------------------------------+
      |                              |
      v                              v
 Questionar / Pressionar /      Apresentar pista
 Observar                       |
      |                          v
      +-------------> Random <---+
                       |
                       v
                 Resultado da ação
                       |
                       v
              Suspeito perde resistência
                       |
                       v
                 Interrogatório
                  termina?
                  /       \
                Não       Sim
                 |          |
                 v          v
             Próximo     Recompensa /
              turno      progressão
```

O jogo utiliza uma estrutura de turnos. Durante um interrogatório, o jogador escolhe uma ação e o sistema calcula o resultado considerando os atributos envolvidos e um valor aleatório.

---

## Estrutura do Projeto

A estrutura atual do projeto está organizada da seguinte forma:

```text
src/
│
├── com.mycompany.jogo/
│   ├── Pista.java
│   ├── Combate.java
│   └── Jogo.java
│
└── com.mycompany.jogo.personagens/
    ├── Personagem.java
    ├── Investigador.java
    └── Suspeito.java
```

### Relação entre as classes

```text
                 Personagem
                (classe abstrata)
                 /          \
                /            \
       Investigador        Suspeito
             |
             |
          possui
             |
             v
            Pista

Investigador -------- Combate -------- Suspeito
                          |
                          |
                       utiliza
                          |
                          v
                         Pista
```

A classe `Personagem` serve como base para `Investigador` e `Suspeito`. A classe `Pista` é independente, mas pode ser armazenada pelo investigador e utilizada durante os interrogatórios. A classe `Combate` coordena o confronto entre investigador e suspeito.

---

# Como executar

O projeto deve ser executado pela classe `Jogo`.

A classe `Jogo` será responsável por criar os objetos necessários e iniciar o fluxo do jogo.

Durante o desenvolvimento, ela também pode ser utilizada para testar individualmente as classes e suas interações.


---

# Classes

## 1. Personagem

**Arquivo:** `Personagem.java`

**Pacote:** `com.mycompany.jogo.personagens`

`Personagem` é uma **classe abstrata** que representa as características comuns a todos os personagens do jogo.

Como ela é abstrata, não será criada uma instância diretamente de `Personagem`. Ela serve de base para outras classes, como `Investigador` e `Suspeito`.

### Atributos

```text
nome         -> nome do personagem
vida         -> quantidade atual de vida
vidaMaxima   -> limite máximo de vida
nivel        -> nível atual do personagem
ataque       -> valor utilizado para calcular ataques
defesa       -> valor utilizado para reduzir danos
```

### Métodos

```text
receberDano(int dano):
Diminui a vida do personagem sem permitir que ela fique negativa.

estaVivo():
Verifica se a vida do personagem é maior que zero.

atacar(Personagem alvo):
Calcula o dano com base no ataque do personagem e na defesa do alvo.

aumentarNivel():
Aumenta o nível e melhora os atributos básicos do personagem.

mostrarStatus():
Exibe no terminal as principais informações do personagem.
```

Além desses métodos, a classe possui getters para os atributos necessários às outras classes e setters controlados para `vida` e `nivel`.

---

## 2. Investigador

**Arquivo:** `Investigador.java`

**Pacote:** `com.mycompany.jogo.personagens`

`Investigador` herda de `Personagem` e representa o personagem controlado pelo jogador.

```java
public class Investigador extends Personagem
```

Além dos atributos herdados, possui características próprias relacionadas à investigação.

### Atributos

```text
investigacao    -> capacidade de trabalhar com evidências
persuasao       -> capacidade de obter informações em interrogatórios
observacao      -> capacidade de perceber detalhes e contradições
experiencia     -> quantidade de experiência adquirida
pistas[]        -> conjunto de pistas encontradas pelo investigador
quantidadePistas -> quantidade atual de pistas armazenadas
```

O investigador possui espaço para armazenar até 20 pistas.

### Métodos

```text
ganharExperiencia(int experiencia):
Adiciona experiência ao investigador.

adicionarPista(Pista pista):
Adiciona uma pista encontrada ao conjunto de pistas do investigador.

getQuantidadePistas():
Retorna a quantidade de pistas armazenadas.

mostrarPistas():
Exibe no terminal as pistas que o investigador possui.

getPista(int numero):
Retorna uma pista específica de acordo com o número escolhido.
```

Como herda de `Personagem`, também possui acesso aos métodos como `atacar()`, `receberDano()`, `estaVivo()` e `aumentarNivel()`.

---

## 3. Suspeito

**Arquivo:** `Suspeito.java`

**Pacote:** `com.mycompany.jogo.personagens`

`Suspeito` também herda de `Personagem`.

```java
public class Suspeito extends Personagem
```

Representa os personagens que serão investigados e interrogados pelo jogador.

### Atributos

```text
profissao       -> profissão ou ocupação do suspeito
depoimento      -> declaração dada pelo suspeito
resistencia     -> representa quanto o suspeito consegue resistir ao interrogatório
inteligencia    -> influencia a dificuldade das ações do investigador
culpado         -> informa internamente se o suspeito é culpado no caso
```

O atributo `culpado` é uma informação interna do jogo. O objetivo é que o jogador descubra a responsabilidade do suspeito por meio das pistas e dos interrogatórios.

### Métodos

```text
getProfissao()
getDepoimento()
getResistencia()
getInteligencia()
isCulpado():
Métodos utilizados para consultar os atributos do suspeito.

diminuirResistencia(int valor):
Diminui a resistência durante o interrogatório.
```

---

## 4. Pista

**Arquivo:** `Pista.java`

**Pacote:** `com.mycompany.jogo`

A classe `Pista` representa uma evidência encontrada durante a investigação.

Cada pista é um objeto independente que pode ser armazenado pelo investigador e utilizado durante um interrogatório.

### Atributos

```text
id           -> identificador da pista
descricao    -> descrição da evidência encontrada
tipo         -> categoria da pista
importancia  -> nível de relevância da pista
analisada    -> informa se a pista já foi analisada
```

Alguns exemplos de tipos de pista são:

```text
Documento
Fotografia
Depoimento
Mensagem
Objeto
```

### Métodos

```text
analisar():
Marca a pista como analisada.

mostrarPista():
Exibe todas as informações da pista.
```

Além disso, a classe possui getters para seus atributos.

Uma pista é criada inicialmente como **não analisada**.

---

## 5. Combate

**Arquivo:** `Combate.java`

**Pacote:** `com.mycompany.jogo`

No jogo, o combate não representa necessariamente uma luta física. Ele representa um **interrogatório por turnos**, no qual o investigador tenta obter informações e fazer o suspeito entrar em contradição.

Essa abordagem utiliza a liberdade proposta no trabalho para implementar combates por meio de diálogos.

### Atributos

```text
investigador  -> personagem controlado pelo jogador
suspeito      -> personagem que está sendo interrogado
rodada        -> número do turno atual
combateAtivo  -> informa se o interrogatório ainda está acontecendo
random        -> objeto utilizado para gerar aleatoriedade
```

### Ações de combate

```text
questionar();
pressionar();
observar();
apresentarPista(Pista pista);
```

### Métodos auxiliares

```text
iniciar():
Inicia o interrogatório e apresenta o menu de ações a cada rodada.

mostrarStatus():
Exibe os dados do investigador, do suspeito e da rodada.

limitarChance(int chance):
Mantém a chance calculada dentro de um intervalo permitido.

testarChance(int chance):
Utiliza Random para determinar se uma ação foi bem-sucedida.

finalizar():
Encerra o interrogatório e apresenta o resultado.
```

---

# Sistema de Combate

O combate funciona em turnos.

A cada rodada, o jogador escolhe uma ação:

```text
1 - Questionar
2 - Pressionar
3 - Observar
4 - Apresentar pista
5 - Sair
```

## Questionar

Utiliza principalmente a `persuasao` do investigador e a `inteligencia` do suspeito.

Em caso de sucesso:

```text
Suspeito perde resistência.
```

Em caso de falha:

```text
Investigador perde uma pequena quantidade de vida.
```

## Pressionar

É uma alternativa mais arriscada.

Em caso de sucesso:

```text
Suspeito perde uma quantidade maior de resistência.
```

Em caso de falha:

```text
Investigador sofre dano.
```

## Observar

Utiliza principalmente o atributo `observacao` do investigador.

Em caso de sucesso:

```text
Suspeito perde uma pequena quantidade de resistência.
```

## Apresentar pista

O investigador escolhe uma pista que possui.

A pista precisa estar analisada para poder ser utilizada como evidência.

A chance de sucesso considera:

```text
investigação do investigador + importância da pista - inteligência do suspeito
```

O resultado também utiliza `Random`.

---

# Uso do Random

O sistema utiliza:

```java
Random random = new Random();
```

Para cada ação, é calculada uma chance de sucesso.

Depois é gerado um número aleatório entre 1 e 100.

Exemplo:

```text
Chance de sucesso: 65%

Número sorteado: 42

42 <= 65

Resultado: SUCESSO
```

Ou:

```text
Chance de sucesso: 65%

Número sorteado: 82

82 > 65

Resultado: FALHA
```

Dessa forma, o mesmo interrogatório pode ter resultados diferentes em partidas diferentes.

---

# Sistema de Pistas

As pistas são parte central da proposta do jogo.

O fluxo planejado é:

```text
Encontrar pista
      ↓
Criar objeto Pista
      ↓
Adicionar ao Investigador
      ↓
Analisar pista
      ↓
Utilizar como evidência
      ↓
Confrontar suspeito
```

O investigador pode consultar as pistas armazenadas antes ou durante um interrogatório.

Exemplo:

```text
========== PISTAS ==========

Pista 1
Tipo: Documento
Descrição: Recibo encontrado no posto central.
Importância: 8
Analisada: true

Pista 2
Tipo: Fotografia
Descrição: Fotografia mostrando uma pessoa próxima à estação.
Importância: 10
Analisada: false
```

---

# Progressão do Investigador

O personagem começa no nível 1.

Ao longo do jogo, ele poderá receber experiência por superar interrogatórios e avançar na investigação.

Ao subir de nível, os atributos básicos do personagem são aumentados.

A progressão prevista é:

```text
Vitórias / progresso
        ↓
    Experiência
        ↓
     Novo nível
        ↓
Melhoria dos atributos
```

O sistema completo de experiência, recompensas e progressão narrativa ainda será expandido.

---

# História e Estrutura Narrativa

A história do jogo gira em torno do misterioso **Caso 47**.

O jogador recebe a missão de investigar um caso antigo que foi arquivado e começa a encontrar evidências que colocam diferentes pessoas sob suspeita.

A investigação será construída em etapas, envolvendo:

```text
Local de investigação
        ↓
Testemunhas
        ↓
Pistas
        ↓
Suspeitos
        ↓
Interrogatórios
        ↓
Conexão entre evidências
        ↓
Conclusão do caso
```

O significado definitivo do número **47** ainda faz parte da construção da história. Uma das possibilidades é que o caso seja oficialmente o **47º caso arquivado** de determinado período. Outra possibilidade é que o número apareça repetidamente em diferentes evidências e tenha um significado maior dentro da trama.

---

# Diferencial do Jogo

O principal diferencial de **O Caso 47** é substituir o combate tradicional por **interrogatórios e dedução**.

O jogador não depende apenas de força ou ataque. Ele precisa:

- analisar pistas;
- escolher a evidência adequada;
- utilizar os atributos do investigador;
- lidar com a aleatoriedade dos interrogatórios;
- identificar contradições;
- conectar informações obtidas de diferentes personagens.

Uma mecânica planejada para a versão final é o **sistema de dedução**, no qual o jogador deverá formular uma teoria sobre o caso com base nas pistas coletadas.

Também está prevista a possibilidade de diferentes finais de acordo com as evidências e decisões tomadas durante a investigação.

---

# Estado Atual do Projeto

Até o momento, já foram estruturadas as seguintes partes:

```text
[✓] Classe Personagem
[✓] Herança para Investigador
[✓] Herança para Suspeito
[✓] Classe Pista
[✓] Inventário de pistas do Investigador
[✓] Classe Combate
[✓] Combate por interrogatório
[✓] Sistema de ações
[✓] Uso de Random
[✓] Resistência do Suspeito
[✓] Uso de pistas no interrogatório
[ ] Sistema completo de investigação
[ ] Recompensas
[ ] Sistema completo de experiência e níveis
[ ] História completa
[ ] Sistema de dedução
[ ] Conclusão do caso
[ ] Múltiplos finais
```
