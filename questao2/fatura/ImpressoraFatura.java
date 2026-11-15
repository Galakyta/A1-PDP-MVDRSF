package fatura;

public class ImpressoraFatura {

    // pega pra qualquer fatura ent ta suave
    public static void imprimir(ItemFatura fatura) {


        System.out.println("fatura");
        System.out.println(fatura.descricao());
        System.out.println("total a ser pago R$: " + fatura.custo());
    }
}
