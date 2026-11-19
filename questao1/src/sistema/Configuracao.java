package sistema;

import java.math.BigDecimal;

/**
 * Singleton responsável pelas configurações globais da aplicação.
 * Os valores podem ser personalizados por propriedades da JVM:
 * -Dapp.regiao=... -Dapp.orcamento=...
 */
public final class Configuracao {
    private final String regiaoPadrao;
    private final BigDecimal orcamentoMensalMaximo;

    private Configuracao() {
        this.regiaoPadrao = System.getProperty("app.regiao", "sa-east-1");
        this.orcamentoMensalMaximo = new BigDecimal(
                System.getProperty("app.orcamento", "5000.00"));

        if (regiaoPadrao.isBlank()) {
            throw new IllegalArgumentException("A região padrão não pode ser vazia.");
        }
        if (orcamentoMensalMaximo.signum() < 0) {
            throw new IllegalArgumentException("O orçamento não pode ser negativo.");
        }
    }

    private static class InstanciaHolder {
        private static final Configuracao INSTANCIA = new Configuracao();
    }

    public static Configuracao getInstancia() {
        return InstanciaHolder.INSTANCIA;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public BigDecimal getOrcamentoMensalMaximo() {
        return orcamentoMensalMaximo;
    }
}
