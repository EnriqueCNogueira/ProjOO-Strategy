# Estratégia por Dia da Semana (Strategy + Null Object)

Atividade de Programação Orientada a Objetos. O programa identifica o dia da semana
(atual ou informado manualmente) e delega a execução de uma ação a uma **estratégia**
específica daquele dia, aplicando o design pattern **Strategy**. A ausência de estratégia
é tratada de forma segura com o **Null Object Pattern**.

## Como executar

Java puro, sem dependências externas.

```bash
# Compilar (a partir da raiz do projeto)
javac -d out src/Main.java src/SeletorEstrategia.java src/estrategias/*.java

# Dia atual (sem argumentos)
java -cp out Main

# Dia informado manualmente + informação do usuário
java -cp out Main segunda "Enrique"

# Dia atual com informação do usuário
java -cp out Main hoje "Enrique"
```

**Argumentos:** `java Main <dia> <info>`
- `<dia>`: `segunda`, `terca`, `quarta`, `quinta`, `sexta`, `sabado`, `domingo`
  (com ou sem acento, maiúsculas/minúsculas indiferentes) ou `hoje` para o dia atual.
  Ausente → usa o dia atual.
- `<info>`: nome, tarefa ou meta do usuário. Ausente → usa um valor padrão.

## Exemplos de execução

### Entrada válida

```
$ java -cp out Main segunda "Enrique"
Dia (informado): segunda
Acao: Enrique, organize suas prioridades para comecar bem a semana.
Prioridade: ALTA
```

### Entrada inválida / sem estratégia

```
$ java -cp out Main blargh "Enrique"
Dia (informado): blargh
Acao: Nenhuma estrategia associada a este dia. Nada a fazer.
Prioridade: BAIXA
```

O dia inválido não interrompe a execução: a estratégia nula responde de forma segura.

## Estrutura das estratégias

A família do Strategy fica no pacote `estrategias/` (pasta dedicada); o seletor e o ponto
de entrada ficam na raiz de `src/`:

```
src/
├── Main.java
├── SeletorEstrategia.java
└── estrategias/
    ├── EstrategiaDia.java      (interface)
    ├── Prioridade.java         (enum)
    ├── EstrategiaNula.java     (Null Object)
    ├── SegundaStrategy.java
    ├── TercaStrategy.java
    ├── QuartaStrategy.java
    ├── QuintaStrategy.java
    ├── SextaStrategy.java
    ├── SabadoStrategy.java
    └── DomingoStrategy.java
```

| Componente | Arquivo | Papel |
|---|---|---|
| Abstração da estratégia | `estrategias/EstrategiaDia.java` | Interface com `executar(String info)` e `prioridade()`. |
| Estratégias concretas | `estrategias/SegundaStrategy.java` … `DomingoStrategy.java` | Uma por dia; define a mensagem da ação e a prioridade. |
| Estratégia segura (Null Object) | `estrategias/EstrategiaNula.java` | Resposta segura quando não há estratégia; nunca retorna `null` nem lança exceção. |
| Prioridade | `estrategias/Prioridade.java` | Enum `ALTA`, `MEDIA`, `BAIXA`. |
| Selector/Context | `SeletorEstrategia.java` | Mapeia cada dia à sua estratégia e devolve a adequada (ou a nula). |
| Ponto de entrada | `Main.java` | Obtém o dia (atual ou manual) e a info, e executa a estratégia. |

Cada dia tem sua própria classe de estratégia. O `SeletorEstrategia` guarda as
associações em um `EnumMap<DayOfWeek, EstrategiaDia>` e usa `getOrDefault` para devolver a
estratégia certa ou, na falta dela, a `EstrategiaNula`. A mensagem de cada dia vive dentro
da respectiva classe — **não há cadeia de `if/else` nem `switch`** para escolher a
mensagem; a seleção é polimórfica.

A prioridade de cada dia:

| Dia | Ação | Prioridade |
|---|---|---|
| Segunda | organize suas prioridades | ALTA |
| Terça | avance nas tarefas pendentes | ALTA |
| Quarta | revise o andamento das atividades | MÉDIA |
| Quinta | colabore com alguém da equipe | MÉDIA |
| Sexta | registre o que foi concluído | MÉDIA |
| Sábado | estudo livre ou descanso | BAIXA |
| Domingo | planeje a próxima semana | BAIXA |
| (sem estratégia) | resposta segura | BAIXA |

## Questões de reflexão

**1. Como evitar verificações repetidas de valores nulos no código principal?**

Concentrando a decisão em um único ponto e nunca devolvendo `null`. O `SeletorEstrategia`
usa `getOrDefault(dia, NULA)`, então qualquer dia inválido ou sem estratégia já volta como
um objeto válido (a estratégia nula). O fluxo principal em `Main` simplesmente chama
`estrategia.executar(info)` e `estrategia.prioridade()` sem nenhum `if (estrategia != null)`
espalhado pelo código.

**2. Qual padrão de projeto pode ser utilizado para representar a ausência de uma
estratégia de forma segura?**

O **Null Object Pattern**. Em vez de retornar `null`, devolve-se um objeto que implementa a
mesma interface (`EstrategiaDia`) e tem um comportamento neutro e seguro — aqui, a classe
`EstrategiaNula`, que informa que não há ação e retorna prioridade `BAIXA`.

**3. Como esse padrão seria incorporado à solução?**

A `EstrategiaNula` implementa a interface `EstrategiaDia` como qualquer outra estratégia.
Ela é registrada como valor padrão no `SeletorEstrategia`: quando o dia pedido não está no
mapa (dia inexistente, nome inválido ou sem associação), o `getOrDefault` retorna a
instância nula. Para o resto do programa ela é indistinguível de uma estratégia normal,
então o código que a usa não precisa saber que a estratégia "não existe".
