package aws;

import recursos.FilaDeMensagens;




class FilaAws implements FilaDeMensagens {

    private final String id;

    FilaAws(int numero) {
        this.id = "sqs-" + numero;
    }




    @Override
    public String getId() {
         return id; 
        }
}
