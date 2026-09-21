# RPG em Java - "O Caso 47"

## Resumo

O Caso 47 é um RPG investigativo ambientado em uma investigação criminal na qual o jogador assume o papel de um investigador responsável por descobrir a verdade por trás de uma série de acontecimentos suspeitos.

O caso começa após uma denúncia envolvendo o desaparecimento de documentos e possíveis irregularidades financeiras relacionadas a uma empresa. Na noite em que os acontecimentos ocorreram, existem registros de movimentações suspeitas, alterações em documentos e informações que não coincidem com os depoimentos das pessoas envolvidas. Um detalhe chama a atenção desde o início: o horário 23:47 aparece repetidamente em diferentes evidências.

Ao longo da investigação, o jogador visita diferentes locais, encontra pistas e precisa analisá-las para descobrir informações ocultas. Essas informações são utilizadas durante os interrogatórios, nos quais o investigador pode questionar, pressionar, observar o comportamento dos suspeitos e apresentar evidências capazes de colocá-los em contradição.

O caso envolve quatro suspeitos principais: Ricardo, empresário ligado às movimentações financeiras; Helena, jornalista que investigava a empresa; Marcos, ex-policial relacionado à investigação anterior; e Beatriz, contadora que possui conhecimento sobre os documentos financeiros. Cada personagem possui seu próprio depoimento, comportamento, contradições e motivo para esconder determinadas informações.

Embora todos tenham algum envolvimento com o caso, nem todos são culpados. O jogador precisa separar as mentiras relacionadas a interesses pessoais daquelas que escondem a verdadeira origem do crime.

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
Criar Investigacao
      |
      v
Menu de Investigação
      |
      +-------------------------------+
      |               |               |
      v               v               v
Investigar        Ver/Analisar     Ver progresso
  local              pistas
      |
      v
Encontrar pista
      |
      v
Adicionar ao Investigador
      |
      v
Interrogar Suspeito
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
│   ├── Investigacao.java
│   ├── Habilidade.java
│   ├── Recompensa.java
│   ├── Deducao.java
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
   |                    |              |
   |                    |              |
   |                  utiliza       possui
   |                    |              |
   v                    v              v
 Pista  <----------- Evidências     Recompensa
   |                                   |
   |                                   v
   |                              Habilidade
   |
   +--------- Deducao <--------- Suspeitos[]
                |
                v
        Conclusão / Finais

  Investigacao
       |
       +---- controla locais
       +---- descobre pistas
       +---- controla progresso
       +---- controla menu
       +---- controla suspeitos
```

A classe `Personagem` serve como base para `Investigador` e `Suspeito`. A classe `Pista` é independente, mas pode ser armazenada pelo investigador e utilizada durante os interrogatórios. A classe `Investigacao` controla o fluxo do caso, os locais, o progresso, a descoberta das pistas e os três suspeitos. A classe `Combate` coordena o confronto entre investigador e suspeito. `Recompensa` e `Habilidade` cuidam da progressão obtida ao vencer interrogatórios, enquanto `Deducao` utiliza as evidências e os suspeitos para determinar a conclusão do caso.

---

# Como executar

O projeto deve ser executado pela classe `Jogo`.

Atualmente, `Jogo` cria o `Scanner`, solicita o nome do jogador, apresenta os objetivos, instancia o `Investigador` e a `Investigacao`, e inicia a partida chamando `iniciar()` da investigação. Os suspeitos são criados e administrados pela própria `Investigacao`.

A partir desse ponto, o jogador controla o andamento do jogo pelo terminal.

Durante o desenvolvimento, `Jogo` também pode ser utilizado para testar individualmente as classes e suas interações.


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
experiencia     -> quantidade de experiência acumulada pelo investigador
proximoNivelXp  -> quantidade de experiência necessária para o próximo nível
pistas[]        -> conjunto de pistas encontradas pelo investigador
quantidadePistas   -> quantidade atual de pistas armazenadas
habilidades[]      -> conjunto de habilidades conquistadas pelo investigador
quantidadeHabilidades -> quantidade atual de habilidades armazenadas
```

O investigador possui espaço para armazenar até 20 pistas e até 10 habilidades.

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

analisarPista(int numero):
Localiza uma pista pelo número e chama o método de análise da pista.

getPista(int numero):
Retorna uma pista específica de acordo com o número escolhido.

analisarPista(int numero):
Analisa uma pista escolhida pelo jogador e concede experiência de acordo com a importância da pista.

getQuantidadePistasAnalisadas():
Retorna quantas pistas já foram analisadas.

possuiPistaAnalisada(int id):
Verifica se uma pista específica já foi analisada.

getProximoNivelXp():
Retorna a experiência necessária para atingir o próximo nível.

mostrarProgresso():
Exibe nível e experiência atual do investigador.

adicionarHabilidade(Habilidade habilidade):
Adiciona uma habilidade recebida como recompensa e aplica seus bônus aos atributos de investigação.

mostrarHabilidades():
Exibe as habilidades conquistadas pelo investigador.
```

Como herda de `Personagem`, também possui acesso aos métodos como `atacar()`, `receberDano()`, `estaVivo()` e `aumentarNivel()`.

Ao subir de nível, o `Investigador` também aumenta seus atributos específicos de investigação (`investigacao`, `persuasao` e `observacao`). A experiência excedente permanece acumulada para o próximo nível.

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
culpado             -> informa internamente se o suspeito é culpado no caso
pistaContradicaoId  -> identifica a pista que pode gerar uma contradição específica
contradicao         -> texto da contradição associada à evidência
motivo              -> motivo associado ao suspeito para a etapa de dedução
recompensa          -> recompensa recebida quando o interrogatório é vencido
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

getMotivo():
Retorna o motivo associado ao suspeito.

getRecompensa():
Retorna a recompensa associada ao suspeito.
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
analisada             -> informa se a pista já foi analisada
informacaoRevelada    -> informação descoberta após a análise
perguntaDesbloqueada  -> pergunta especial que pode ser liberada pela pista
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

Uma pista é criada inicialmente como **não analisada**. O jogador pode escolher a opção de análise no menu de investigação. A análise revela uma informação narrativa e pode desbloquear uma pergunta especial; depois disso, a pista pode ser apresentada como evidência durante um interrogatório.

---

## 5. Investigacao

**Arquivo:** `Investigacao.java`

**Pacote:** `com.mycompany.jogo`

`Investigacao` controla o fluxo principal do caso e permite que o jogador conduza a investigação diretamente pelo terminal.

### Atributos

```text
nomeCaso                     -> nome da investigação
progresso                    -> percentual de avanço do caso
locais[]                     -> locais disponíveis para investigação
locaisInvestigados[]         -> indica quais locais já foram investigados
quantidadeLocaisInvestigados -> quantidade de locais já investigados
casoResolvido                -> informa se o caso foi marcado como resolvido
suspeitos[]                  -> conjunto de suspeitos do caso
quantidadeSuspeitos          -> quantidade de suspeitos cadastrados
```

### Métodos

```text
mostrarProgresso():
Exibe o progresso atual do caso.

mostrarLocais():
Exibe os locais disponíveis e indica quais já foram investigados.

investigarLocal(int numeroLocal, Investigador investigador):
Investiga o local selecionado, impede investigação repetida, cria a pista correspondente
e concede experiência ao investigador.

criarPistaDoLocal(...):
Cria a pista associada ao local investigado e a adiciona ao investigador.

atualizarProgresso():
Atualiza o percentual de locais investigados.

resolverCaso():
Marca o caso como resolvido e define o progresso como 100%.

iniciar(Investigador investigador, Scanner scanner):
Controla o menu principal da investigação e permite ao jogador investigar locais,
consultar e analisar pistas, acompanhar o progresso, selecionar suspeitos,
concluir o caso e iniciar interrogatórios.

getSuspeito(int numero):
Retorna um suspeito específico de acordo com o número escolhido.

getQuantidadeSuspeitos():
Retorna a quantidade de suspeitos cadastrados.

mostrarSuspeitos():
Exibe no terminal os suspeitos disponíveis para interrogatório.
```

### Menu de investigação

```text
1 - Investigar um local
2 - Ver pistas
3 - Analisar uma pista
4 - Ver progresso
5 - Interrogar suspeito
6 - Ver habilidades
7 - Concluir caso
8 - Sair
```

A classe `Investigacao` utiliza o mesmo `Scanner` recebido pelo método `iniciar()` e o repassa para `Combate`.

---

## 6. Habilidade

**Arquivo:** `Habilidade.java`

**Pacote:** `com.mycompany.jogo`

`Habilidade` representa uma habilidade que pode ser conquistada pelo investigador como parte das recompensas dos interrogatórios. Cada habilidade possui uma descrição e bônus específicos para os atributos de investigação.

### Atributos

```text
nome                 -> nome da habilidade
descricao            -> descrição do efeito da habilidade
bonusInvestigacao    -> bônus aplicado à investigação
bonusPersuasao       -> bônus aplicado à persuasão
bonusObservacao      -> bônus aplicado à observação
```

---

## 7. Recompensa

**Arquivo:** `Recompensa.java`

**Pacote:** `com.mycompany.jogo`

`Recompensa` representa o conjunto de benefícios concedidos ao investigador após vencer um interrogatório. Ela pode conceder experiência e uma habilidade.

### Atributos

```text
descricao   -> descrição da recompensa
experiencia -> quantidade de EXP concedida
habilidade  -> habilidade associada à recompensa, quando houver
```

### Métodos

```text
getDescricao():
Retorna a descrição da recompensa.

getExperiencia():
Retorna a experiência da recompensa.

getHabilidade():
Retorna a habilidade associada.

entregar(Investigador investigador):
Entrega a recompensa ao investigador, concedendo EXP e habilidade quando configuradas.
```

Cada suspeito pode possuir uma recompensa diferente.

---

## 8. Deducao

**Arquivo:** `Deducao.java`

**Pacote:** `com.mycompany.jogo`

`Deducao` controla a etapa de conclusão do Caso 47. O jogador escolhe qual suspeito considera responsável e qual seria o motivo, e o sistema verifica essas escolhas com base nas informações cadastradas e nas evidências analisadas.

### Estados de resultado

```text
INCONCLUSIVO     -> evidências insuficientes ou conclusão não sustentada
ACUSACAO_ERRADA  -> suspeito escolhido não é o responsável
VERDADE_REVELADA -> suspeito e motivo corretos, com evidências suficientes
FINAL_SECRETO    -> conclusão correta com todas as pistas disponíveis analisadas
```

### Métodos

```text
realizar(Scanner scanner):
Conduz a etapa de dedução, solicita as escolhas do jogador e retorna o resultado da conclusão.
```

A dedução exige um número mínimo de pistas analisadas antes que o jogador possa chegar a uma conclusão. O resultado pode levar a diferentes finais do jogo.

---

## 9. Combate

**Arquivo:** `Combate.java`

**Pacote:** `com.mycompany.jogo`

No jogo, o combate não representa necessariamente uma luta física. Ele representa um **interrogatório por turnos**, no qual o investigador tenta obter informações e fazer o suspeito entrar em contradição.

Essa abordagem utiliza a liberdade proposta no trabalho para implementar combates por meio de diálogos.

### Atributos

```text
investigador  -> personagem controlado pelo jogador
suspeito      -> personagem que está sendo interrogado
pistaEspecial -> pista que desbloqueou uma pergunta especial
rodada        -> número do turno atual
combateAtivo  -> informa se o interrogatório ainda está acontecendo
random        -> objeto utilizado para gerar aleatoriedade
scanner       -> entrada utilizada pelo jogador durante o interrogatório
```

### Ações de combate

```text
questionar();
pressionar();
observar();
apresentarPista(Pista pista);
fazerPerguntaEspecial();
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
5 - Fazer pergunta especial (quando desbloqueada)
6 - Sair
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

Quando uma pista corresponde à contradição específica do suspeito e a apresentação tem sucesso, uma pergunta especial é desbloqueada.

A chance de sucesso considera:

```text
investigação do investigador + importância da pista - inteligência do suspeito
```

O resultado também utiliza `Random`.

## Pergunta especial

Uma pergunta especial é desbloqueada quando o jogador apresenta com sucesso uma pista que possui uma contradição associada ao suspeito. A pergunta utiliza a informação desbloqueada pela pista para pressionar o suspeito e reduzir sua resistência.

A pergunta permanece disponível durante o interrogatório por meio de uma opção adicional no menu.

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

Na versão atual, as pistas são descobertas quando o jogador investiga determinados locais. Depois de encontradas, são armazenadas pelo investigador e precisam ser analisadas antes de serem utilizadas como evidência.

O fluxo atual é:

```text
Investigar local
      ↓
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

O jogador pode consultar e analisar as pistas pelo menu de investigação. Uma pista não analisada não pode ser apresentada como evidência durante o interrogatório.

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

A experiência é centralizada no `Investigador`. Ao atingir o limite do próximo nível, o investigador sobe de nível, mantém a experiência excedente e melhora seus atributos. A evolução também é aplicada aos atributos específicos de investigação.

---

# Sistema de Recompensas

Ao vencer um interrogatório, o investigador recebe a experiência da vitória e pode receber uma recompensa específica do suspeito derrotado. A recompensa pode conter experiência adicional e uma habilidade.

O fluxo é:

```text
Vencer interrogatório
        ↓
Experiência da vitória
        ↓
Recompensa do suspeito
        ↓
+ EXP adicional
        ↓
Nova habilidade
        ↓
Bônus nos atributos do Investigador
```

As habilidades conquistadas ficam armazenadas no investigador e podem ser consultadas pelo menu do jogo.

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

O jogo possui o **sistema de dedução**, no qual o jogador escolhe o suspeito e o motivo e o jogo verifica se as evidências analisadas sustentam a conclusão.

O sistema também possui diferentes resultados para a conclusão: acusação errada, caso inconclusivo, verdade revelada e um final secreto quando todas as pistas disponíveis são analisadas.

---

# Fluxo Atual de Jogabilidade

A versão atual já permite um primeiro ciclo de investigação:

```text
1. Iniciar o jogo
        ↓
2. Abrir menu de investigação
        ↓
3. Investigar um local
        ↓
4. Encontrar uma pista
        ↓
5. Consultar as pistas
        ↓
6. Analisar uma pista
        ↓
7. Interrogar um suspeito
        ↓
8. Apresentar uma pista analisada
        ↓
9. Desbloquear pergunta especial (quando aplicável)
        ↓
10. Utilizar a pergunta especial (quando disponível)
        ↓
11. Encerrar o interrogatório
        ↓
12. Receber EXP/recompensa em caso de vitória
        ↓
13. Consultar progresso e habilidades
        ↓
14. Concluir o caso por meio da dedução
        ↓
15. Receber um dos finais possíveis
```

# Sistema de Experiência

O investigador pode ganhar experiência em três momentos da versão atual:

```text
Investigar um local
        ↓
+30 EXP

Analisar uma pista
        ↓
Importância da pista × 5 EXP

Vencer um interrogatório
        ↓
+15 EXP
```

A experiência é acumulada pelo `Investigador`. Ao atingir a quantidade necessária para o próximo nível, o personagem sobe de nível. A experiência que exceder o limite permanece acumulada para o próximo nível.

Ao subir de nível, os atributos básicos e os atributos específicos do investigador são melhorados. O sistema também informa no terminal a quantidade de EXP recebida, a EXP atual e a ocorrência de uma nova subida de nível.

---
