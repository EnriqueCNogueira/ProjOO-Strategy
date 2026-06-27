package estrategias;

// Sabado: estudo livre ou descanso.
public class SabadoStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", aproveite para estudo livre ou descanso.";
    }

    public Prioridade prioridade() {
        return Prioridade.BAIXA;
    }
}
