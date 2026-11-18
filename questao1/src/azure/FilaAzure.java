package azure;

import recursos.FilaDeMensagens;


class FilaAzure implements FilaDeMensagens {

    private final String id;

    FilaAzure(int numero) {

        this.id = "sb-" + numero;


        
    }

    @Override
    public String getId() {
         return id; 
        }
}
