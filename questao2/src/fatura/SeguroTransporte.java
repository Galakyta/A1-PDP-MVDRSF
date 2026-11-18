package fatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

//joga 1.5 nn o acumulado
public class SeguroTransporte extends ServicoAdicional {

    private static final BigDecimal percentual = new BigDecimal("0.015");

    public SeguroTransporte(ItemFatura embrulhado) {
        super(embrulhado);
    }

    @Override
    protected String nomeServico() {
        return "Seguro de transporte";
    }

    @Override
    protected BigDecimal custoServico() {
        return valorVenda().multiply(percentual).setScale(2, RoundingMode.HALF_UP);
    }
}
