public class Aeropuerto {
    private int[] espacios;
    private int cantidadEspacios = 0;

    public Aeropuerto(int cantidadEspacios) {
        this.cantidadEspacios = cantidadEspacios;
        espacios = new int[cantidadEspacios];
    }

    public Aeropuerto() {
        this.cantidadEspacios = 20;
        espacios = new int[cantidadEspacios];
    }
}
