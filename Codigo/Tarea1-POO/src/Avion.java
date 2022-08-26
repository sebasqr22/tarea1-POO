public class Avion {
    Asiento[][] matrizEjecutivo;
    Asiento[][] matrizEconomico;
    boolean tieneEjecutiva = false;

    public Avion(int filasEjecutivas,int asientosPorFilaEjecutiva,int filasEconomica,int asientosPorFilaEconomica) {
        matrizEconomico = new Asiento[filasEconomica][asientosPorFilaEconomica];
        for (int i = 0; i < filasEconomica; i++) {
            for (int j = 0; j < asientosPorFilaEconomica; j++) {
                String indentificacion = 1 +"-"+ numeroLetra(j);
                matrizEconomico[i][j] = new Asiento(indentificacion,"E");
            }
        }
        if(filasEjecutivas != 0 || asientosPorFilaEjecutiva != 0){
            tieneEjecutiva = true;
            matrizEjecutivo = new Asiento[filasEjecutivas][asientosPorFilaEjecutiva];
            for (int i = 0; i < filasEjecutivas; i++) {
                int contador = 0;
                for (int j = 0; j < asientosPorFilaEjecutiva; j++) {
                    String letra;
                    if(j <= asientosPorFilaEconomica/2){
                        letra = numeroLetra(j);
                    }
                    else{
                        letra = numeroLetra(asientosPorFilaEconomica-contador);
                        contador += 1;
                    }
                    String indentificacion = 1 +"-"+ letra;
                    matrizEconomico[i][j] = new Asiento(indentificacion,"J");
                }
            }
        }
    }
    public int letraNumero(String letra){
        int result;
        if(letra.length() == 1){
            result =  letra.charAt(0)-65;;
        }else{
            result  = -1;
        }
        return 0;
    }

    public String numeroLetra(int numero){
        char ASCII = (char) (numero + 65);
        String result = String.valueOf(ASCII);
        return result;
    }
}
