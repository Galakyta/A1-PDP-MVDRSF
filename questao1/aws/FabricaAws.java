package aws;

import fabrica.FabricaDeRecursos;
import fabrica.TabelaPrecos;
import java.math.BigDecimal;
import recursos.ArmazenamentoObjetos;
import recursos.FilaDeMensagens;
import recursos.MaquinaVirtual;



public class FabricaAws implements FabricaDeRecursos {

    private static final TabelaPrecos precos = new TabelaPrecos(

        //10 zero ponto treis 40
            new BigDecimal("0.10"),    
            new BigDecimal("0.023"),   
            new BigDecimal("0.40"));   

 
            
            
    private int proximaMaquina = 1;
    
    private int proximoArmazenamento = 1;

    private int proximaFila = 1;

    @Override
    public String getNomeProvedor() { return "Aws"; }

    @Override
    public TabelaPrecos getTabelaPrecos() {
         return precos; 
        
        
        }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new MaquinaAws(proximaMaquina++);
    }

    @Override
    public ArmazenamentoObjetos criarArmazenamento(int gb) {
        return new ArmazenamentoAws(proximoArmazenamento++, gb);
    
    
    }

    @Override
    public FilaDeMensagens criarFila() {
        return new FilaAws(proximaFila++);
    
    
    }
}
