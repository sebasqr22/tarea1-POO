public class Asiento {
    private String identificacion;
    private String claseAsiento;
    private String estado = "A";
    private Pasajero pasajero;

    public Asiento(String identificacion, String claseAsiento) {
        this.identificacion = identificacion;
        this.claseAsiento = claseAsiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado() {
        if(pasajero != null){
            System.out.println("No se pudo cambiar el estado porque esta ocupado");

        }
        else{

            System.out.println("El asiento cambio su estado");
            if(this.estado == "A")
                this.estado = "I";
            else
                this.estado = "A";

        }

    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void vaciar(){
        pasajero = null;
    }

}
