package azure;

import recursos.ArmazenamentoObjetos;


class ArmazenamentoAzure implements ArmazenamentoObjetos {

    private final String id;
    private final int gb;

    ArmazenamentoAzure(int numero, int gb) {
        this.id = "blob://" + numero;
        this.gb = gb;
    }

    @Override

    public String getId() { 
        return id;

     }

    @Override
    public int getGb() { 
        
        return gb; }
}
