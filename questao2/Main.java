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

        Lote quadro = new Lote("hearts are trumps", new Categoria("arte", new ComissaoArte()),
                r("1000.00"), r("1200.00"));
        Lote notebook = new Lote("dell g15", new Categoria("eletronicos", new ComissaoEletronicos()),
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

        
        System.out.println("primeiro lote");

        System.out.println("Ana vai dar 1100.00");
        quadro.darLance(ana, r("1100.00"));

        System.out.println("Bruno vai dar 1150.00");
        quadro.darLance(bruno, r("1150.00"));

        System.out.println("Bruno vai dar 1200.00");
        quadro.darLance(bruno, r("1200.00"));

        System.out.println("carla para de acompanhar");
        quadro.deixarDeAcompanhar(carla);

        System.out.println("ana da 1300 e a carla n recebe");
        quadro.darLance(ana, r("1300.00"));


        System.out.println("o quadro vai usar a regra dos eletronicos");
        System.out.println("Comissão que a regra de arte cobraria sobre 1300:"
                + new ComissaoArte().calcular(r("1300.00")));
        quadro.trocarRegraComissao(new ComissaoEletronicos());


        System.out.println("lote 2, 4k inicial reserva 6k");

        System.out.println("carla vai dar 4200.0 ");
        notebook.darLance(carla, r("4200.00"));

        System.out.println("ana vai dar 4300");
        notebook.darLance(ana, r("4300.00"));

        System.out.println("bruno vai dar 45000");
        notebook.darLance(bruno, r("4500.00"));


        System.out.println("encerrandoÇ ");
        System.out.println(quadro.getNome() + ": " + quadro.encerrar()
                + " por R$ " + quadro.getValorVenda());
        System.out.println(notebook.getNome() + ": " + notebook.encerrar()
                + " (maior lance R$ " + notebook.getLanceAtual() + " abaixo da reserva)");

        System.out.println("ana vai tentar dar 9k no notebook mas ja era");
        notebook.darLance(ana, r("9000.00"));

        
        System.out.println();
        AcertoVendedor.imprimir(quadro.getNome(), quadro.getValorVenda(), quadro.calcularComissao());

        
        System.out.println();
        ItemFatura fatura = new VendaBase(quadro.getNome(), quadro.getValorVenda());
        fatura = new SeguroTransporte(fatura);
        fatura = new CertificadoAutenticidade(fatura);
        fatura = new EntregaExpressa(fatura);
        fatura = new EntregaExpressa(fatura);        //contrata o mesmo 2 vezes
        System.out.println("Comprador: " + quadro.getComprador().getNome());
        ImpressoraFatura.imprimir(fatura);

        
        System.out.println("auditoria: " + auditor.getRegistros().size() + " de lances aceitos");
        auditor.getRegistros().forEach(System.out::println);
    }
}
