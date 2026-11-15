// AcertoVendedor.java
package comissao;

import java.math.BigDecimal;


public class AcertoVendedor {


    public static void imprimir(String nomeLote, BigDecimal valorVenda, BigDecimal comissao) {
        BigDecimal liquido = valorVenda.subtract(comissao);


        System.out.println("acerto de quem vendeu o " + nomeLote );
        System.out.println("Valor R$: " + valorVenda);
        System.out.println("comisao cobrada  R$: " + comissao);
        System.out.println("liquido pro pagamento R$ : " + liquido);
    }
}