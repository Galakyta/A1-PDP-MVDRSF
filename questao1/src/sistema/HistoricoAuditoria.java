package sistema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class HistoricoAuditoria {
    private final List<String> registros = new ArrayList<>();
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private HistoricoAuditoria() {
    }

    private static class InstanciaHolder {
        private static final HistoricoAuditoria INSTANCIA = new HistoricoAuditoria();
    }

    public static HistoricoAuditoria getInstancia() {
        return InstanciaHolder.INSTANCIA;
    }

    public synchronized void registrar(String mensagem) {
        String registro = "[" + LocalDateTime.now().format(FORMATO) + "] " + mensagem;


        registros.add(registro);
    }

    public synchronized List<String> getRegistros() {
        return Collections.unmodifiableList(new ArrayList<>(registros));
    }

    public synchronized void imprimir() {
        System.out.println();
        System.out.println("historico pra auditoria");
        if (registros.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (String registro : registros) {
                System.out.println(registro);
            }
        }
        System.out.println("/////////////////////////////");
    }
}
