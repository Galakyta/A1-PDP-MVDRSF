
package fabrica;

import aws.FabricaAws;

import azure.FabricaAzure;
import java.util.Locale;


public final class SeletorDeFabrica {

    private SeletorDeFabrica() {
        
    }

    public static FabricaDeRecursos selecionar(String provedor) {


        if (provedor == null || provedor.isBlank()) {


            throw new IllegalArgumentException(
                    "O provedor não pode ser vazio.");
        }

        String nome = provedor.trim().toLowerCase(Locale.ROOT);

        switch (nome) {

            case "aws":

            case "amazon":

            case "amazon web services":
                return new FabricaAws();


            case "azure":

            case "microsoft azure":

                return new FabricaAzure();


            default:

                throw new IllegalArgumentException(
                        "Provedor não suportado: " + provedor

                        + ". Use AWS ou Azure.");
        }
    }
}
