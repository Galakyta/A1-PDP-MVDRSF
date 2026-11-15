package comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;
//veiculos joga pra 8 e limita a 5k
public class ComissaoVeiculos implements RegraComissao {

    private static final BigDecimal percentual = new BigDecimal("0.08");


    private static final BigDecimal limite = new BigDecimal("5000.00");


    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = valorVenda.multiply(percentual);
        
        return comissao.min(limite).setScale(2, RoundingMode.HALF_UP);
    }
}