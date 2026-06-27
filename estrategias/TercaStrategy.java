package estrategias;

// Terca-feira: avancar nas tarefas pendentes.
public class TercaStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", avance nas suas tarefas pendentes.";
    }

    public Prioridade prioridade() {
        return Prioridade.ALTA;
    }
}
