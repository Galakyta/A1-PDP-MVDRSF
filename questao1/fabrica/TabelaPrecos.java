package fabrica;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Preços de um provedor. Existe para o custo poder ser estimado ANTES de criar qualquer recurso (RF05).
public class TabelaPrecos {

    private static final BigDecimal HORAS_NO_MES = new BigDecimal("730");

    private final BigDecimal custoHoraMaquina;
    private final BigDecimal custoGbMes;
    private final BigDecimal custoFilaMes;

    public TabelaPrecos(BigDecimal custoHoraMaquina, BigDecimal custoGbMes, BigDecimal custoFilaMes) {
        this.custoHoraMaquina = custoHoraMaquina;
        this.custoGbMes = custoGbMes;
        this.custoFilaMes = custoFilaMes;
    }

    // RF04: máquinas x custo por hora x 730 + GB x custo por GB + custo fixo da fila
    public BigDecimal custoMensal(int quantidadeMaquinas, int gb) {
        BigDecimal maquinas = custoHoraMaquina
                .multiply(HORAS_NO_MES)
                .multiply(BigDecimal.valueOf(quantidadeMaquinas));
        BigDecimal armazenamento = custoGbMes.multiply(BigDecimal.valueOf(gb));
        return maquinas.add(armazenamento).add(custoFilaMes).setScale(2, RoundingMode.HALF_UP);
    }
}
