// Auditor.java
package leilao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Auditor implements Interessado {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final List<String> registros = new ArrayList<>();

    @Override
    public String getNome() { return "Auditor"; }

    @Override
    public void receberLance(Lance lance) {
        String registro = LocalDateTime.now().format(FORMATO)
                + " | Lote " + lance.getNomeLote()
                + " | " + lance.getAutor().getNome()
                + " | R$ " + lance.getValor();
        registros.add(registro);
        System.out.println("[Auditor] " + registro);
    }

    public List<String> getRegistros() { return registros; }
}