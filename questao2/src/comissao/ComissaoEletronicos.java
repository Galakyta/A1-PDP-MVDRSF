package comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;

//12 joga o minimo p 50
public class ComissaoEletronicos implements RegraComissao {

    private static final BigDecimal percentual = new BigDecimal("0.12");
    
    private static final BigDecimal min = new BigDecimal("50.00");


    @Override

    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = valorVenda.multiply(percentual);

        return comissao.max(min).setScale(2, RoundingMode.HALF_UP);
        
}
}