public class Pasajero {
    private String identificacion = "";
    private String nombre;
    private String paisOrigen;

    public Pasajero(String identificacion, String nombre, String paisOrigen) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
