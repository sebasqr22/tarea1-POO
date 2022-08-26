
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Ya tenemos el "lector"
        FuncionesMenu funcionesMenu = new FuncionesMenu();
        System.out.println("Bienvenido al aeropuerto");
        System.out.println("1-Agregar avion \n2-Modificar capacidad de asientos\n3-Excluir avion\n4-Asignar pasajeros a asientos\n5-Vaciar asiento\n" +
                "6-Vaciar avion\n7-Consultar avion\n8-Buscar pasajero\n9-Consultar asientos disponibles");
        System.out.println("Escriba el numero de la opcion que desea");
        String opcion = br.readLine();


        switch (opcion.toUpperCase()) {
            case "1":
                funcionesMenu.agregarAvion();
                break;
            case "2":
                funcionesMenu.modificarCapacidadDeAsientos();
                break;
            case "3":
                funcionesMenu.excluirAvion();
                break;
            case "4":
                funcionesMenu.asignarPasajerosAAsientos();
                break;
            case "5":
                funcionesMenu.vaciarAsiento();
                break;
            case "6":
                funcionesMenu.vaciarAvion();
                break;
            case "7":
                funcionesMenu.consultarAvion();
                break;
            case "8":
                funcionesMenu.buscarPasajero();
                break;
            case "9":
                funcionesMenu.consultarAsientosDisponibles();
                break;
            default:
                System.out.println("Error");
        }
    }
}