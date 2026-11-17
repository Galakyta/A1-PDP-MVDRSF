package aws;

import recursos.MaquinaVirtual;


class MaquinaAws implements MaquinaVirtual {

    private final String id;

    MaquinaAws(int numero) {
        this.id = "i-" + numero;
    }

    @Override
    public String getId() {
        
        return id; 
    
    
    }
}
