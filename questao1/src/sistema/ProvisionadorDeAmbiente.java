
package sistema;

import fabrica.FabricaDeRecursos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import recursos.MaquinaVirtual;

public final class ProvisionadorDeAmbiente {

    private final SolicitacaoAmbiente solicitacao;
    private final FabricaDeRecursos fabrica;
    private final Configuracao configuracao;
    private final HistoricoAuditoria auditoria;

    public ProvisionadorDeAmbiente(
            SolicitacaoAmbiente solicitacao,
            FabricaDeRecursos fabrica) {


        this.solicitacao = Objects.requireNonNull(
                solicitacao, "A solicitação não pode ser nula.");

        this.fabrica = Objects.requireNonNull(
                fabrica, "A fábrica não pode ser nula.");

        this.configuracao = Configuracao.getInstancia();
        this.auditoria = HistoricoAuditoria.getInstancia();
    }

    public Ambiente provisionar() {

        BigDecimal custo = fabrica.getTabelaPrecos().custoMensal(
                solicitacao.getQuantidadeDeMaquinas(),
                solicitacao.getGbArmazenamento());

        if (custo.compareTo(
                configuracao.getOrcamentoMensalMaximo()) > 0) {

            String mensagem = "Provisionamento recusado para '"

                    + solicitacao.getNome()
                    + "': custo estimado de R$ " + custo
                    + " excede o orçamento máximo de R$ "
                    + configuracao.getOrcamentoMensalMaximo() + ".";

            auditoria.registrar(mensagem);


            throw new IllegalStateException(mensagem);
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();

        for (int i = 0;
             i < solicitacao.getQuantidadeDeMaquinas();
             i++) {

            maquinas.add(fabrica.criarMaquinaVirtual());
        }

        Ambiente ambiente = new Ambiente(
                solicitacao.getNome(),

                fabrica.getNomeProvedor(),

                configuracao.getRegiaoPadrao(),

                custo,

                maquinas,
                fabrica.criarArmazenamento(
                        solicitacao.getGbArmazenamento()),
                fabrica.criarFila());

        ambiente.provisionar();

        auditoria.registrar(
                "Ambiente '" + ambiente.getNome()
                + "' provisionado no provedor "

                + ambiente.getProvedor()
                + ", região " + ambiente.getRegiao()
                + ", custo mensal estimado R$ "
                
                + ambiente.getCustoMensalEstimado() + ".");

        return ambiente;
    }
}
