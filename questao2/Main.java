import comissao.AcertoVendedor;
import comissao.ComissaoArte;
import comissao.ComissaoEletronicos;
import fatura.CertificadoAutenticidade;
import fatura.EntregaExpressa;
import fatura.ImpressoraFatura;
import fatura.ItemFatura;
import fatura.SeguroTransporte;
import fatura.VendaBase;
import java.math.BigDecimal;
import leilao.Auditor;
import leilao.Categoria;
import leilao.Lote;
import leilao.Painel;
import leilao.Participante;

public class Main {

    private static BigDecimal r(String valor) {
        return new BigDecimal(valor);
    }

    public static void main(String[] args) {

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        Painel painel = new Painel();
        Auditor auditor = new Auditor();

        Lote quadro = new Lote("Quadro Abstrato", new Categoria("Arte", new ComissaoArte()),
                r("1000.00"), r("1200.00"));
        Lote notebook = new Lote("Notebook Gamer", new Categoria("Eletrônicos", new ComissaoEletronicos()),
                r("4000.00"), r("6000.00"));

        quadro.acompanhar(ana);
        quadro.acompanhar(bruno);
        quadro.acompanhar(carla);
        quadro.acompanhar(painel);
        quadro.acompanhar(auditor);

        notebook.acompanhar(bruno);
        notebook.acompanhar(carla);
        notebook.acompanhar(painel);
        notebook.acompanhar(auditor);

        
        System.out.println("=== LOTE 1: Quadro Abstrato (preço inicial R$ 1000.00, reserva R$ 1200.00) ===");

        System.out.println("\n>> Ana dá R$ 1100.00");
        quadro.darLance(ana, r("1100.00"));

        System.out.println("\n>> Bruno dá R$ 1150.00 (abaixo de 5% acima de 1100)");
        quadro.darLance(bruno, r("1150.00"));

        System.out.println("\n>> Bruno dá R$ 1200.00");
        quadro.darLance(bruno, r("1200.00"));

        System.out.println("\n>> Carla deixa de acompanhar o quadro");
        quadro.deixarDeAcompanhar(carla);

        System.out.println("\n>> Ana dá R$ 1300.00 (Carla não recebe mais)");
        quadro.darLance(ana, r("1300.00"));


        System.out.println("\n=== CAMPANHA: o quadro (Arte) passa a usar a regra de Eletrônicos ===");
        System.out.println("Comissão que a regra de Arte cobraria sobre R$ 1300.00: R$ "
                + new ComissaoArte().calcular(r("1300.00")));
        quadro.trocarRegraComissao(new ComissaoEletronicos());


        System.out.println("\n=== LOTE 2: Notebook Gamer (preço inicial R$ 4000.00, reserva R$ 6000.00) ===");

        System.out.println("\n>> Carla dá R$ 4200.00 (exatamente 5% acima: aceito)");
        notebook.darLance(carla, r("4200.00"));

        System.out.println("\n>> Ana dá R$ 4300.00 (abaixo do mínimo)");
        notebook.darLance(ana, r("4300.00"));

        System.out.println("\n>> Bruno dá R$ 4500.00");
        notebook.darLance(bruno, r("4500.00"));


        System.out.println("\n=== ENCERRAMENTOS ===");
        System.out.println(quadro.getNome() + ": " + quadro.encerrar()
                + " por R$ " + quadro.getValorVenda());
        System.out.println(notebook.getNome() + ": " + notebook.encerrar()
                + " (maior lance R$ " + notebook.getLanceAtual() + " abaixo da reserva)");

        System.out.println("\n>> Ana tenta dar R$ 9000.00 no notebook já encerrado");
        notebook.darLance(ana, r("9000.00"));

        
        System.out.println();
        AcertoVendedor.imprimir(quadro.getNome(), quadro.getValorVenda(), quadro.calcularComissao());

        
        System.out.println();
        ItemFatura fatura = new VendaBase(quadro.getNome(), quadro.getValorVenda());
        fatura = new SeguroTransporte(fatura);
        fatura = new CertificadoAutenticidade(fatura);
        fatura = new EntregaExpressa(fatura);
        fatura = new EntregaExpressa(fatura);        // o mesmo serviço contratado duas vezes
        System.out.println("Comprador: " + quadro.getComprador().getNome());
        ImpressoraFatura.imprimir(fatura);

        
        System.out.println("\n=== HISTÓRICO DO AUDITOR (" + auditor.getRegistros().size() + " lances aceitos) ===");
        auditor.getRegistros().forEach(System.out::println);
    }
}
