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
                String indentificacion = i +"-"+ numeroLetra(j);
                matrizEconomico[i][j] = new Asiento(indentificacion,"E");
            }
        }
        if(filasEjecutivas != 0 || asientosPorFilaEjecutiva != 0){
            tieneEjecutiva = true;
            matrizEjecutivo = new Asiento[filasEjecutivas][asientosPorFilaEjecutiva];
            for (int i = 0; i < filasEjecutivas; i++) {
                int contador = matrizEconomico[0].length -matrizEjecutivo[0].length;
                for (int j = 0; j < asientosPorFilaEjecutiva; j++) {
                    String letra;

                    if(j < asientosPorFilaEjecutiva/2){
                        letra = numeroLetra(j);

                    }
                    else{
                        letra = numeroLetra(asientosPorFilaEconomica-contador);

                        contador -= 1;
                    }

                    String indentificacion = i +"-"+ letra;
                    System.out.println(indentificacion);
                    matrizEjecutivo[i][j] = new Asiento(indentificacion,"J");
                }
            }
        }
    }
    public int letraNumero(String letra){
        int result;
        letra = letra.toUpperCase();
        if(letra.length() == 1){
            result =  letra.charAt(0)-65;
        }else{
            result  = -1;
        }
        return result;
    }

    public String numeroLetra(int numero){
        char ASCII = (char) (numero + 65);
        String result = String.valueOf(ASCII);
        return result;
    }
    public int[] validaExsteAsiento(String seccion,String identificacion){
        int columna = letraNumero(String.valueOf(identificacion.charAt(2)));
        int fila = Integer.parseInt( String.valueOf(identificacion.charAt(0)) );
        if(columna >=  matrizEjecutivo.length/2 && columna >= matrizEconomico[0].length -(matrizEconomico[0].length -matrizEjecutivo[0].length)){
            columna -= (matrizEconomico[0].length -matrizEjecutivo[0].length);
        }
        else{
            return new int[]{-1, -1};
        }

        if(seccion.toUpperCase().equals("J")){

            if( fila< matrizEjecutivo.length && columna < matrizEjecutivo[0].length){
                return new int[]{fila, columna};
            }
            else{
                System.out.println("El asiento no existe");
                return new int[]{-1, -1};
            }
        }
        else{
            if(fila < matrizEconomico.length && columna < matrizEconomico[0].length){
                return new int[]{fila, columna};
            }else{
                System.out.println("El asiento no existe");
                return new int[]{-1, -1};
            }

        }

    }
    public boolean modificaCapacidadAsiento(String seccion,String identificacion){
        int columna = letraNumero(String.valueOf(identificacion.charAt(2)));
        int fila = Integer.parseInt( String.valueOf(identificacion.charAt(0)) );

        int coordenadas[] = validaExsteAsiento(seccion,identificacion);

        if(coordenadas[0] != -1){
            if(seccion.toUpperCase().equals("J")){
                    //System.out.println("El asiento J["+fila+"]["+columna +"] cambio su estado");
                    matrizEjecutivo[coordenadas[0]][coordenadas[1]].setEstado();
                    return true;

                }
            else{
                //System.out.println("El asiento E["+fila+"]["+columna +"] cambio su estado");
                matrizEconomico[fila][columna].setEstado();
                return true;
            }

        }else{
            return false;
        }

    }

    public void vaciarAsiento(String seccion, String identificacion){
        int columna = letraNumero(String.valueOf(identificacion.charAt(2)));
        int fila = Integer.parseInt( String.valueOf(identificacion.charAt(0)) );
        Asiento asiento;

        if(seccion.toUpperCase().equals("J")){
            asiento = matrizEjecutivo[fila][columna];
        }
        else{
            asiento = matrizEconomico[fila][columna];
        }

        asiento.vaciar();
    }

    public Asiento[][] get_economico(){
        return matrizEconomico;
    }

    public Asiento[][] get_ejecutivo(){
        return matrizEjecutivo;
    }
}
