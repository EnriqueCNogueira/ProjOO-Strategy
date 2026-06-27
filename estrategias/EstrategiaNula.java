package estrategias;

// Dia invalido: resposta segura para entradas nao reconhecidas.
public class EstrategiaNula implements EstrategiaDia {
    public String executar(String info) {
        return "Nenhuma estrategia associada a este dia. Nada a fazer.";
    }

    public Prioridade prioridade() {
        return Prioridade.BAIXA;
    }
}
