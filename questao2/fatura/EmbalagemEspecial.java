package fatura;

import java.math.BigDecimal;

// fixa 180
public class EmbalagemEspecial extends ServicoAdicional {

    private static final BigDecimal custo = new BigDecimal("180.00");

    public EmbalagemEspecial(ItemFatura embrulhado) {


        super(embrulhado);
        
    }

    @Override
    protected String nomeServico() {
        return "embalagem super especial";
    }

    @Override
    protected BigDecimal custoServico() {

        return custo;
    }
}
