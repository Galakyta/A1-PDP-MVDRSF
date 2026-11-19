
import fabrica.FabricaDeRecursos;
import fabrica.SeletorDeFabrica;
import sistema.HistoricoAuditoria;
import sistema.ProvisionadorDeAmbiente;
import sistema.SolicitacaoAmbiente;
public class Main {

    public static void main(String[] args) {
          System.out.println("teste provisionamento de nuvem");

        SolicitacaoAmbiente solicitacaoAws1 = new SolicitacaoAmbiente(
                "Ambiente de Desenvolvimento",
                "AWS",
                2,
                100);

        



        SolicitacaoAmbiente solicitacaoAws2 = new SolicitacaoAmbiente(
                "Ambiente de Homologacao",
                "AWS",
                3,
                200);

        


        SolicitacaoAmbiente solicitacaoAzure = new SolicitacaoAmbiente(
                "Ambiente de Testes",
                "Azure",
                1,
                50);

        
        SolicitacaoAmbiente solicitacaoOrcamento = new SolicitacaoAmbiente(
                "Ambiente Fora do Orcamento",
                "AWS",
                1000,
                10000);

        
        Thread threadAws1 = new Thread(
                () -> executarProvisionamento(solicitacaoAws1),
                "Thread-AWS-1");

        Thread threadAws2 = new Thread(
                () -> executarProvisionamento(solicitacaoAws2),
                "Thread-AWS-2");

        Thread threadAzure = new Thread(
                () -> executarProvisionamento(solicitacaoAzure),
                "Thread-Azure");

        Thread threadOrcamento = new Thread(
                () -> executarProvisionamento(solicitacaoOrcamento),
                "Thread-Orcamento");

       

        threadAws1.start();
        threadAws2.start();
        threadAzure.start();
        threadOrcamento.start();

        
        try {
            threadAws1.join();
            threadAws2.join();
            threadAzure.join();
            threadOrcamento.join();

        } catch (InterruptedException erro) {
            Thread.currentThread().interrupt();
            System.err.println("A execução foi interrompida.");
        }

        // Exibe o histórico de auditoria
        System.out.println("historico pra auditoria: ");
        HistoricoAuditoria.getInstancia().imprimir();
    }

    private static void executarProvisionamento(
        SolicitacaoAmbiente solicitacao) {

    try {
        System.out.println(
                "\n[" + Thread.currentThread().getName()
                + "] Iniciando: " + solicitacao.getNome());

        FabricaDeRecursos fabrica =
                SeletorDeFabrica.selecionar(solicitacao.getProvedor());

        ProvisionadorDeAmbiente provisionador =
                new ProvisionadorDeAmbiente(solicitacao, fabrica);

        provisionador.provisionar();

        System.out.println(
                "[" + Thread.currentThread().getName()
                + "] Provisionamento concluido: "
                + solicitacao.getNome());

    } catch (IllegalArgumentException | IllegalStateException erro) {
        System.err.println(
                "[" + Thread.currentThread().getName()
                + "] Provisionamento nao realizado: "
                + erro.getMessage());
    }
}
}
