
package comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;

//strategyzada coim 20 de valor no perceuntual
public class ComissaoArte implements RegraComissao {

    private static final BigDecimal percentual = new BigDecimal("0.20");


    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        
        return valorVenda.multiply(percentual).setScale(2, RoundingMode.HALF_UP);
        
        //tendo que entender o math direito ate q ta legal po

    }
}