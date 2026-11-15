package leilao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//big decimal pro lote e o hashmap eu to usando pelas funcoes de mapping


public class Painel implements Interessado {


    private final Map<String, BigDecimal> maiorLance = new HashMap<>();
    
    
    private final Map<String, Integer> totalLances = new HashMap<>();

    @Override

    public String getNome() {
        
        return "painel do leilao"; }

    @Override
    public void receberLance(Lance lance) {
        String lote = lance.getNomeLote();
        //pra puxar
        maiorLance.merge(lote, lance.getValor(), BigDecimal::max);
        totalLances.merge(lote, 1, Integer::sum);
        //merge pra nn dar zona padrao


        System.out.println("PAINEL:   " + lote + ": lance maior em R$:   " + maiorLance.get(lote) + " // total de lances: " + totalLances.get(lote));
    }
}