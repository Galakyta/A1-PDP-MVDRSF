package azure;

import recursos.MaquinaVirtual;


class MaquinaAzure implements MaquinaVirtual {

    private final String id;

    MaquinaAzure(int numero) {

        this.id = "vm-" + numero;
        //achei paia esse ngc dos textos terem q ser hard codados pela questao

    }

    @Override
    
    public String getId() {
        
        return id; 
    
    }
}
