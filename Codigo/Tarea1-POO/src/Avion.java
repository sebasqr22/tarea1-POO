public class Avion {
    Asiento[][] matrizEjecutivo;
    Asiento[][] matrizEconomico;
    String identificacion;
    boolean tieneEjecutiva = false;

    public Avion(int filasEjecutivas,int asientosPorFilaEjecutiva,int filasEconomica,int asientosPorFilaEconomica,String IdentificacionEntrante) {
        this.identificacion = IdentificacionEntrante;
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
    public boolean modificaCapacidadAsiento(String seccion,String identificacion){
        int columna = letraNumero(String.valueOf(identificacion.charAt(2)));
        int fila = Integer.parseInt( String.valueOf(identificacion.charAt(0)) );

        if(seccion.toUpperCase() == "J"){

            if( fila< matrizEjecutivo.length && columna < matrizEjecutivo[0].length){
                System.out.println("El asiento ["+fila+"]["+columna +"] cambio su estado");
                matrizEjecutivo[fila][columna].setEstado();
                return true;
            }
            else{
                System.out.println("El asiento no existe");
                return false;
            }
        }
        else{
            if(fila < matrizEconomico.length && columna < matrizEconomico[0].length){
                System.out.println("El asiento ["+fila+"]["+columna +"] cambio su estado");
                matrizEconomico[fila][columna].setEstado();
                return true;
            }else{
                System.out.println("El asiento no existe");
                return false;
            }

        }
    }
}
