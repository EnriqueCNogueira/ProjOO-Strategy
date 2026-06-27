package estrategias;

// Quarta-feira: revisar o andamento das atividades.
public class QuartaStrategy implements EstrategiaDia {
    public String executar(String info) {
        return info + ", revise o andamento das suas atividades.";
    }

    public Prioridade prioridade() {
        return Prioridade.MEDIA;
    }
}
