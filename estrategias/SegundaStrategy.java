package estrategias;

// Segunda-feira: organizar prioridades.
public class SegundaStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", organize suas prioridades para comecar bem a semana.";
    }

    public Prioridade prioridade() {
        return Prioridade.ALTA;
    }
}
