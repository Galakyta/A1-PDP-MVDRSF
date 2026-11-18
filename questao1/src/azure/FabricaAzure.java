package azure;

import fabrica.FabricaDeRecursos;
import fabrica.TabelaPrecos;
import java.math.BigDecimal;
import recursos.ArmazenamentoObjetos;
import recursos.FilaDeMensagens;
import recursos.MaquinaVirtual;


public class FabricaAzure implements FabricaDeRecursos {

    private static final TabelaPrecos precos = new TabelaPrecos(

        //douze 2 50
            new BigDecimal("0.12"),    


            new BigDecimal("0.02"), 
            
            
            new BigDecimal("0.50"));   


    private int proximaMaquina = 1;
    private int proximoArmazenamento = 1;
    private int proximaFila = 1;

    @Override
    public String getNomeProvedor() { 
        return "azure"; 
    }

    @Override
    public TabelaPrecos getTabelaPrecos() {
         return precos; 
        }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new MaquinaAzure(proximaMaquina++);
    }

    @Override
    public ArmazenamentoObjetos criarArmazenamento(int gb) {
        return new ArmazenamentoAzure(proximoArmazenamento++, gb);
    }

    @Override
    public FilaDeMensagens criarFila() {
        return new FilaAzure(proximaFila++);
    }
}
