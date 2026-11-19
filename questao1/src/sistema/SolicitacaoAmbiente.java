package sistema;

import java.util.Objects;


public final class SolicitacaoAmbiente {
    private final String nome;
    private final String provedor;
    private final int quantidadeDeMaquinas;
    private final int gbArmazenamento;

    public SolicitacaoAmbiente(String nome, String provedor,
                               int quantidadeDeMaquinas, int gbArmazenamento) {
        this.nome = Objects.requireNonNull(nome, "O nome é obrigatorio.").trim();
        this.provedor = Objects.requireNonNull(provedor, "O provedor é obrigatorio.").trim();

        if (this.nome.isBlank()) {
            throw new IllegalArgumentException("O nome do ambiente não pode ser vazio.");
        }
        if (this.provedor.isBlank()) {
            throw new IllegalArgumentException("O provedor nao pode ser vazio.");
        }
        
        if (quantidadeDeMaquinas <= 0) {
            throw new IllegalArgumentException("Informe pelo menos uma máquina virtual.");
        }
        if (gbArmazenamento <= 0) {
            throw new IllegalArgumentException("O armazenamento deve ter pelo menos 1 GB.");
        }

        this.quantidadeDeMaquinas = quantidadeDeMaquinas;
        this.gbArmazenamento = gbArmazenamento;
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public int getQuantidadeDeMaquinas() {
        return quantidadeDeMaquinas;
    }

    public int getGbArmazenamento() {
        return gbArmazenamento;
    }
}
