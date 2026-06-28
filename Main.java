// Sistema de estrategias de atividades, baseado no dia da semana.
import estrategias.EstrategiaDia;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        SeletorEstrategia seletor = new SeletorEstrategia();

        // Teste 1: dia atual, com estrategia associada.
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        imprimir("Enrique", seletor.nomeDia(hoje), seletor.para(hoje));

        System.out.println();

        // Teste 2: dia invalido, sem estrategia associada.
        imprimir("Joao", seletor.nomeDia("feriado"), seletor.paraNome("feriado"));
    }

    private static void imprimir(String usuario, String diaTexto, EstrategiaDia estrategia) {
        System.out.println("Usuário: " + usuario);
        System.out.println("Dia consultado: " + diaTexto);
        System.out.println("Mensagem: " + estrategia.executar(usuario));
        System.out.println("Prioridade: " + estrategia.prioridade().rotulo());
    }
}
