package leilao;

import comissao.RegraComissao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

// Observer: ConcreteSubject (só conhece a interface Interessado)
// Strategy: Context (guarda a RegraComissao vigente)
public class Lote implements Observavel {

    private static final BigDecimal AUMENTO_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoReserva;

    private final List<Interessado> interessados = new ArrayList<>();
    private final List<Lance> lances = new ArrayList<>();

    private RegraComissao regraComissao;
    private BigDecimal lanceAtual;
    private Lance maiorLance;
    private StatusLote status = StatusLote.ABERTO;
    private BigDecimal valorVenda;

    public Lote(String nome, Categoria categoria, BigDecimal precoInicial, BigDecimal precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial;                      // RF01: vale o preço inicial até o 1º lance
        this.regraComissao = categoria.getRegraPadrao();     // começa com a regra da categoria
    }

    // ---------- Observer ----------

    @Override
    public void acompanhar(Interessado interessado) {
        interessados.add(interessado);
    }

    @Override
    public void deixarDeAcompanhar(Interessado interessado) {
        interessados.remove(interessado);
    }

    @Override
    public void notificarInteressados(Lance lance) {
        for (Interessado interessado : new ArrayList<>(interessados)) {
            if (interessado != lance.getAutor()) {           // RF03: todos, exceto quem deu o lance
                interessado.receberLance(lance);
            }
        }
    }

    // ---------- Lances ----------

    public boolean darLance(Interessado autor, BigDecimal valor) {
        if (status != StatusLote.ABERTO) {
            System.out.println("[RECUSADO] " + autor.getNome() + ": o lote " + nome
                    + " está encerrado e não aceita novos lances.");
            return false;
        }
        BigDecimal minimo = lanceAtual.multiply(AUMENTO_MINIMO).setScale(2, RoundingMode.HALF_UP);
        if (valor.compareTo(minimo) < 0) {
            System.out.println("[RECUSADO] " + autor.getNome() + ": lance de R$ " + valor
                    + " no lote " + nome + " precisa ser de pelo menos R$ " + minimo
                    + " (5% acima do lance atual de R$ " + lanceAtual + ").");
            return false;
        }
        Lance lance = new Lance(autor, nome, valor);
        lances.add(lance);
        lanceAtual = valor;
        maiorLance = lance;
        notificarInteressados(lance);
        return true;
    }

    // ---------- Encerramento ----------

    public StatusLote encerrar() {
        if (status != StatusLote.ABERTO) {
            return status;
        }
        if (maiorLance != null && maiorLance.getValor().compareTo(precoReserva) >= 0) {
            status = StatusLote.VENDIDO;
            valorVenda = maiorLance.getValor();
        } else {
            status = StatusLote.NAO_VENDIDO;
        }
        return status;
    }

    // ---------- Comissão (Strategy) ----------

    public void trocarRegraComissao(RegraComissao novaRegra) {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("A regra só pode ser trocada antes do encerramento.");
        }
        this.regraComissao = novaRegra;
    }

    public BigDecimal calcularComissao() {
        if (status != StatusLote.VENDIDO) {
            throw new IllegalStateException("Só há comissão para lote vendido.");
        }
        return regraComissao.calcular(valorVenda);
    }

    // ---------- Getters ----------

    public String getNome() { return nome; }
    public Categoria getCategoria() { return categoria; }
    public BigDecimal getLanceAtual() { return lanceAtual; }
    public StatusLote getStatus() { return status; }
    public BigDecimal getValorVenda() { return valorVenda; }
    public Interessado getComprador() { return maiorLance == null ? null : maiorLance.getAutor(); }
    public List<Lance> getLances() { return List.copyOf(lances); }
}