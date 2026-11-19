package sistema;

import aws.FabricaAws;
import azure.FabricaAzure;
import fabrica.FabricaDeRecursos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import recursos.MaquinaVirtual;


public final class ProvisionadorDeAmbiente {
    private final SolicitacaoAmbiente solicitacao;
    private final Configuracao configuracao;
    private final HistoricoAuditoria auditoria;

    public ProvisionadorDeAmbiente(SolicitacaoAmbiente solicitacao) {
        this.solicitacao = solicitacao;
        this.configuracao = Configuracao.getInstancia();
        this.auditoria = HistoricoAuditoria.getInstancia();
    }

    public FabricaDeRecursos fabrica() {
        String provedor = solicitacao.getProvedor().toLowerCase(Locale.ROOT);


        switch (provedor) {
            case "aws":
            case "amazon":
            case "amazon web services":

                return new FabricaAws();
            case "azure":
            case "microsoft azure":
                return new FabricaAzure();
            default:
                throw new IllegalArgumentException(
                        "Provedor não suportado: " + solicitacao.getProvedor()
                                + ". Use AWS ou Azure.");
        }
    }

    public Ambiente provisionar() {
        FabricaDeRecursos fabrica = fabrica();

        BigDecimal custo = fabrica.getTabelaPrecos().custoMensal(
                solicitacao.getQuantidadeDeMaquinas(),
                solicitacao.getGbArmazenamento());

        if (custo.compareTo(configuracao.getOrcamentoMensalMaximo()) > 0) {
            String mensagem = "Provisionamento recusado para '" + solicitacao.getNome()

                    + "': custo estimado de R$ " + custo
                    + " excede o orçamento máximo de R$ "
                    + configuracao.getOrcamentoMensalMaximo() + ".";


            auditoria.registrar(mensagem);


            throw new IllegalStateException(mensagem);
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < solicitacao.getQuantidadeDeMaquinas(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }

        Ambiente ambiente = new Ambiente(
                solicitacao.getNome(),
                fabrica.getNomeProvedor(),
                configuracao.getRegiaoPadrao(),
                
                custo,
                maquinas,
                fabrica.criarArmazenamento(solicitacao.getGbArmazenamento()),
                fabrica.criarFila());

        ambiente.provisionar();

        auditoria.registrar("Ambiente '" + ambiente.getNome() + "' provisionado no provedor "
                + ambiente.getProvedor() + ", região " + ambiente.getRegiao()
                + ", custo mensal estimado R$ " + ambiente.getCustoMensalEstimado() + ".");



        return ambiente;
    }
}
