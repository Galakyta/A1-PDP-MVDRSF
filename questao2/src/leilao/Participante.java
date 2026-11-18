
package leilao;



public class Participante implements Interessado {



    private final String nome;

    public Participante(String nome) {
        this.nome = nome;


    }

    @Override
    public String getNome() { return nome; }
    

    @Override
    public void receberLance(Lance lance) {
        System.out.println(nome + " lote " + lance.getNomeLote() + " agora ta em (reais):  " + lance.getValor());
    }



}