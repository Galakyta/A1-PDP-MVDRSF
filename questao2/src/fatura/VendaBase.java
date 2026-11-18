package fatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendaBase implements ItemFatura {

    private final String nomeLote;
    private final BigDecimal valorVenda;
//concreto
    public VendaBase(String nomeLote, BigDecimal valorVenda) {
        this.nomeLote = nomeLote;
        this.valorVenda = valorVenda.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String descricao() {
        return "lote: " + nomeLote + "valor de venda R$ : " + valorVenda;
    }

    @Override
    public BigDecimal custo() {

        return valorVenda;
    }

    @Override
    public BigDecimal valorVenda() {

        return valorVenda;
    }
}
