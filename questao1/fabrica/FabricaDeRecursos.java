package fabrica;

import recursos.ArmazenamentoObjetos;
import recursos.FilaDeMensagens;
import recursos.MaquinaVirtual;


public interface FabricaDeRecursos {
    String getNomeProvedor();

    TabelaPrecos getTabelaPrecos();

    MaquinaVirtual criarMaquinaVirtual();

    ArmazenamentoObjetos criarArmazenamento(int gb);


    
    FilaDeMensagens criarFila();
}
