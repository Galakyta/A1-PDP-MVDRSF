package leilao;

public interface Observavel {
    void acompanhar(Interessado interessado);
    void deixarDeAcompanhar(Interessado interessado);
    
    void notificarInteressados(Lance lance);

}