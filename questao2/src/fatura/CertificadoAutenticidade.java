package fatura;

import java.math.BigDecimal;

//fixa 300
public class CertificadoAutenticidade extends ServicoAdicional {

    private static final BigDecimal custo = new BigDecimal("300.00");

    public CertificadoAutenticidade(ItemFatura embrulhado) {
        super(embrulhado);


    }

    @Override
    protected String nomeServico() {
        return "certificacao de autenticidade";
    }

    @Override
    protected BigDecimal custoServico() {
        
        return custo;
    }
}
