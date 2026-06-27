package estrategias;

// Sexta-feira: registrar o que foi concluido.
public class SextaStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", registre o que foi concluido nesta semana.";
    }

    public Prioridade prioridade() {
        return Prioridade.MEDIA;
    }
}
