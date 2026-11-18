package fabrica;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class TabelaPrecos {

    private static final BigDecimal horasNoMes = new BigDecimal("730");

    private final BigDecimal custoHoraMaquina;
    private final BigDecimal custoGbMes;
    private final BigDecimal custoFilaMes;

    public TabelaPrecos(BigDecimal custoHoraMaquina, BigDecimal custoGbMes, BigDecimal custoFilaMes) {
        this.custoHoraMaquina = custoHoraMaquina;
        this.custoGbMes = custoGbMes;
        this.custoFilaMes = custoFilaMes;
    }

  
    public BigDecimal custoMensal(int quantidadeMaquinas, int gb) {


        BigDecimal maquinas = custoHoraMaquina
                .multiply(horasNoMes)
                .multiply(BigDecimal.valueOf(quantidadeMaquinas));
        BigDecimal armazenamento = custoGbMes.multiply(BigDecimal.valueOf(gb));

        return maquinas.add(armazenamento).add(custoFilaMes).setScale(2, RoundingMode.HALF_UP);
        
    }
}
