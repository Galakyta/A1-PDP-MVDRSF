package fatura;

import java.math.BigDecimal;

//;450
public class EntregaExpressa extends ServicoAdicional {


    private static final BigDecimal custo = new BigDecimal("450.00");


    public EntregaExpressa(ItemFatura embrulhado) {
    
    
        super(embrulhado);
    }

    @Override
    protected String nomeServico() {
        return "entrega expressa";
    }

    @Override
    protected BigDecimal custoServico() {
        return custo;
    }
}
