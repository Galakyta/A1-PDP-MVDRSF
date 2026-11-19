import sistema.HistoricoAuditoria;
import sistema.ProvisionadorDeAmbiente;
import sistema.SolicitacaoAmbiente;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PROVISIONAMENTO EM NUVEM ===");

        try {
            SolicitacaoAmbiente solicitacaoAws = new SolicitacaoAmbiente(
                    "Ambiente de Desenvolvimento",
                    "AWS",
                    2,
                    100);

            ProvisionadorDeAmbiente provisionadorAws =
                    new ProvisionadorDeAmbiente(solicitacaoAws);
            provisionadorAws.provisionar();

            SolicitacaoAmbiente solicitacaoAzure = new SolicitacaoAmbiente(
                    "Ambiente de Testes",
                    "Azure",
                    1,
                    50);

            ProvisionadorDeAmbiente provisionadorAzure =
                    new ProvisionadorDeAmbiente(solicitacaoAzure);
            provisionadorAzure.provisionar();

        } catch (IllegalArgumentException | IllegalStateException erro) {
            System.err.println("Não foi possível provisionar o ambiente: " + erro.getMessage());
        }

        HistoricoAuditoria.getInstancia().imprimir();
    }
}
