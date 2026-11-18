package fatura;

import java.math.BigDecimal;

//decorator como interface usando o bigd


public interface ItemFatura {
    String descricao();
    BigDecimal custo();
    
    BigDecimal valorVenda();

}
