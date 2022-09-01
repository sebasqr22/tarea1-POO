public class Aeropuerto {
    private Avion espacios[];
    private int cantidadEspacios = 0;

    public Aeropuerto(int cantidadEspacios) {
        this.cantidadEspacios = cantidadEspacios;
        espacios = new Avion[cantidadEspacios];
    }

    public Aeropuerto() {
        this.cantidadEspacios = 20;
        espacios = new Avion[cantidadEspacios];
    }
    public int buscaIdentificacionAvion(String identificacion){
        int  result = -1;
        int i = 0;

        while(i< espacios.length){

            if(espacios[i] == null){
                i += 1;

                continue;

            } else if (espacios[i].identificacion.equals(identificacion)) {

                result= i;
                break;}

            i += 1;
        }
        return result;
    }

    public int obtenerIndiceVacio(){
        for (int i = 0; i < espacios.length; i++) {
            if (espacios[i] == null)
                return i;
        }
        return -1;
    }

    public int agregarAvion(int filasEjecutivas,int asientosPorFilaEjecutiva,int filasEconomica,int asientosPorFilaEconomica,String Identificacion){
        Avion avion = new Avion(filasEjecutivas,asientosPorFilaEjecutiva,filasEconomica,asientosPorFilaEconomica,Identificacion);
        if(obtenerIndiceVacio() == -1){
            return -1;
        }else {

            espacios[obtenerIndiceVacio()] = avion;
            return obtenerIndiceVacio();
        }


    }

    public Avion[] getEspacios() {
        return espacios;
    }
}
