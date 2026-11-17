package fabrica;

import recursos.ArmazenamentoObjetos;
import recursos.FilaMensagens;
import recursos.MaquinaVirtual;

// Abstract Factory: AbstractFactory (cada implementação só cria recursos do MESMO provedor)
public interface FabricaDeRecursos {
    String getNomeProvedor();
    TabelaPrecos getTabelaPrecos();
    MaquinaVirtual criarMaquinaVirtual();
    ArmazenamentoObjetos criarArmazenamento(int gb);
    FilaMensagens criarFila();
}
