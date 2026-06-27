package estrategias;

// Interface da estrategia: cada dia executa uma acao e informa sua prioridade.
public interface EstrategiaDia {
    String executar(String info);
    Prioridade prioridade();
}
