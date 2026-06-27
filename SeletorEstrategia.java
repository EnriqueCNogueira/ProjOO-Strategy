// Selector: associa cada dia a sua estrategia e devolve a adequada (ou a nula).
import estrategias.EstrategiaDia;
import estrategias.EstrategiaNula;
import estrategias.SegundaStrategy;
import estrategias.TercaStrategy;
import estrategias.QuartaStrategy;
import estrategias.QuintaStrategy;
import estrategias.SextaStrategy;
import estrategias.SabadoStrategy;
import estrategias.DomingoStrategy;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.text.Normalizer;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SeletorEstrategia {
    private static final EstrategiaDia NULA = new EstrategiaNula();
    private static final Locale BR = Locale.forLanguageTag("pt-BR");

    private final Map<DayOfWeek, EstrategiaDia> estrategias = new EnumMap<>(DayOfWeek.class);
    private final Map<String, DayOfWeek> nomes = new HashMap<>();

    public SeletorEstrategia() {
        estrategias.put(DayOfWeek.MONDAY, new SegundaStrategy());
        estrategias.put(DayOfWeek.TUESDAY, new TercaStrategy());
        estrategias.put(DayOfWeek.WEDNESDAY, new QuartaStrategy());
        estrategias.put(DayOfWeek.THURSDAY, new QuintaStrategy());
        estrategias.put(DayOfWeek.FRIDAY, new SextaStrategy());
        estrategias.put(DayOfWeek.SATURDAY, new SabadoStrategy());
        estrategias.put(DayOfWeek.SUNDAY, new DomingoStrategy());

        nomes.put("segunda", DayOfWeek.MONDAY);
        nomes.put("terca", DayOfWeek.TUESDAY);
        nomes.put("quarta", DayOfWeek.WEDNESDAY);
        nomes.put("quinta", DayOfWeek.THURSDAY);
        nomes.put("sexta", DayOfWeek.FRIDAY);
        nomes.put("sabado", DayOfWeek.SATURDAY);
        nomes.put("domingo", DayOfWeek.SUNDAY);
    }

    public EstrategiaDia para(DayOfWeek dia) {
        return estrategias.getOrDefault(dia, NULA);
    }

    public EstrategiaDia paraNome(String nomeDia) {
        return para(nomes.get(normalizar(nomeDia)));
    }

    // Nome do dia em portugues, devolve o texto informado se o dia for invalido.
    public String nomeDia(String nomeDia) {
        DayOfWeek dia = nomes.get(normalizar(nomeDia));
        return dia == null ? nomeDia : nomeDia(dia);
    }

    public String nomeDia(DayOfWeek dia) {
        return dia.getDisplayName(TextStyle.FULL, BR);
    }

    private String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        return Normalizer.normalize(texto.trim().toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
