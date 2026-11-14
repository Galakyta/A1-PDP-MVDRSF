package leilao;

import java.math.BigDecimal;

public class Lance {

    private final Interessado autor;
    private final String nomeLote;
    private final BigDecimal valor;

    public Lance(Interessado autor, String nomeLote, BigDecimal valor) {
        this.autor = autor;
        this.nomeLote = nomeLote;
        this.valor = valor;
    }

    public Interessado getAutor() { return autor; }
    public String getNomeLote() { return nomeLote; }
    public BigDecimal getValor() { return valor; }
}