package fatura;

import java.math.BigDecimal;

//deco abstract pra servir de wrapper
public abstract class ServicoAdicional implements ItemFatura {

    private final ItemFatura embrulhado;

    protected ServicoAdicional(ItemFatura embrulhado) {
        this.embrulhado = embrulhado;
    }

    protected abstract String nomeServico();



    
    protected abstract BigDecimal custoServico();

    @Override
    public String descricao() {
        return embrulhado.descricao() + "  //   " + nomeServico() + "   valor do servico R$: " + custoServico();
    }

    @Override
    public BigDecimal custo() {
        return embrulhado.custo().add(custoServico());

    }

    @Override

    public BigDecimal valorVenda() {


        return embrulhado.valorVenda();
    }
}
