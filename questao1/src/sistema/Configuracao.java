
package sistema;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


public final class Configuracao {


    private final String regiaoPadrao;
    
    private final BigDecimal orcamentoMensalMaximo;




    private Configuracao() {
        Properties propriedades = new Properties();

        


        Path arquivo = Path.of("config.properties");

        if (!Files.exists(arquivo)) {
            arquivo = Path.of("questao1", "config.properties");



        }

        try (InputStream entrada = Files.newInputStream(arquivo)) {
            propriedades.load(entrada);

        } catch (IOException erro) {
            throw new IllegalStateException(
                    "Não foi possível carregar o arquivo config.properties.",
                    erro);
        }

        this.regiaoPadrao = propriedades.getProperty("regiao", "").trim();

        try {


            this.orcamentoMensalMaximo = new BigDecimal(



                    propriedades.getProperty("orcamento", "").trim());

        } catch (NumberFormatException erro) {
            throw new IllegalArgumentException(
                    "O orçamento no config.properties é inválido.",
                    erro);
        }



        if (regiaoPadrao.isBlank()) {
            throw new IllegalArgumentException(





                    "A regiAo padrao nAo pode ser vazia.");
        }

        if (orcamentoMensalMaximo.signum() < 0) {
            throw new IllegalArgumentException(
                    "O orCamento não pode ser negativo.");
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
