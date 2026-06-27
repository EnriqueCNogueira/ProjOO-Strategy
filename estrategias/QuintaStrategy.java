package estrategias;

// Quinta-feira: colaborar com alguem da equipe.
public class QuintaStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", colabore com alguem da equipe hoje.";
    }

    public Prioridade prioridade() {
        return Prioridade.MEDIA;
    }
}
