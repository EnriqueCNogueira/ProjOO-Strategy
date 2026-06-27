package estrategias;

// Tipos de prioridade das estrategias.
public enum Prioridade {
    ALTA("ALTA"), MEDIA("MÉDIA"), BAIXA("BAIXA");

    private final String rotulo;

    Prioridade(String rotulo) {
        this.rotulo = rotulo;
    }

    public String rotulo() {
        return rotulo;
    }
}
