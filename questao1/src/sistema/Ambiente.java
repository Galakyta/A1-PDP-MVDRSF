package sistema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import recursos.ArmazenamentoObjetos;
import recursos.FilaDeMensagens;
import recursos.MaquinaVirtual;


public final class Ambiente {
    private final String nome;
    private final String provedor;
    private final String regiao;
    private final BigDecimal custoMensalEstimado;
    private final List<MaquinaVirtual> maquinas;
    private final ArmazenamentoObjetos armazenamento;
    private final FilaDeMensagens fila;

    public Ambiente(String nome, String provedor, String regiao,
                    BigDecimal custoMensalEstimado,
                    List<MaquinaVirtual> maquinas,
                    ArmazenamentoObjetos armazenamento,
                    FilaDeMensagens fila) {
        this.nome = Objects.requireNonNull(nome);
        this.provedor = Objects.requireNonNull(provedor);
        this.regiao = Objects.requireNonNull(regiao);
        this.custoMensalEstimado = Objects.requireNonNull(custoMensalEstimado);
        this.maquinas = Collections.unmodifiableList(new ArrayList<>(maquinas));
        this.armazenamento = Objects.requireNonNull(armazenamento);
        this.fila = Objects.requireNonNull(fila);
    }

    public void provisionar() {
        System.out.println();
        System.out.println("Ambiente provisionado com sucesso!");
        System.out.println("Nome: " + nome);
        System.out.println("Provedor: " + provedor);
        System.out.println("Região: " + regiao);
        System.out.println("Máquinas virtuais:");
        for (MaquinaVirtual maquina : maquinas) {
            System.out.println("  - " + maquina.getId());
        }
        System.out.println("Armazenamento: " + armazenamento.getId()
                + " (" + armazenamento.getGb() + " GB)");
        System.out.println("Fila de mensagens: " + fila.getId());
        System.out.println("Custo mensal estimado: R$ " + custoMensalEstimado);
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getRegiao() {
        return regiao;
    }

    public BigDecimal getCustoMensalEstimado() {
        return custoMensalEstimado;
    }

    public List<MaquinaVirtual> getMaquinas() {
        return maquinas;
    }

    public ArmazenamentoObjetos getArmazenamento() {
        return armazenamento;
    }

    public FilaDeMensagens getFila() {
        return fila;
    }
}
