package estrategias;

// Domingo: planejar a proxima semana.
public class DomingoStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", planeje a sua proxima semana.";
    }

    public Prioridade prioridade() {
        return Prioridade.BAIXA;
    }
}
