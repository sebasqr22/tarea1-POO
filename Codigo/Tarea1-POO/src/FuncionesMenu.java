import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Objects;

public class FuncionesMenu {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Ya tenemos el "lector"
    private Aeropuerto aeropuerto = new Aeropuerto();

    public FuncionesMenu() throws IOException {
    }
    public void inicio() throws IOException {


        System.out.println("Bienvenido al aeropuerto");
        System.out.println("1-Agregar avion \n2-Modificar capacidad de asientos\n3-Excluir avion\n4-Asignar pasajeros a asientos\n5-Vaciar asiento\n" +
                "6-Vaciar avion\n7-Consultar avion\n8-Buscar pasajero\n9-Consultar asientos disponibles\n10-Salir");
        System.out.println("Escriba el numero de la opcion que desea:");
        String opcion = br.readLine();
        switch (opcion.toUpperCase()) {
            case "1":
                agregarAvion();
                break;
            case "2":
                modificarCapacidadDeAsientos();
                break;
            case "3":
                excluirAvion();
                break;
            case "4":
                asignarPasajerosAAsientos();
                break;
            case "5":
                vaciarAsiento();
                break;
            case "6":
                vaciarAvion();
                break;
            case "7":
                consultarAvion();
                break;
            case "8":
                buscarPasajero();
                break;
            case "9":
                consultarAsientosDisponibles();
                break;
            case "10":
                System.exit(0);
            default:
                System.out.println("Error");
        }
    }

    public void agregarAvion() throws IOException {
        System.out.println("Ingresa la indentificacion que desea ponerle al avion(5 caracteres):");
        String opcion = br.readLine();
        if(opcion.length() == 5){
            if(aeropuerto.buscaIdentificacionAvion(opcion) != -1){
                System.out.println("la identificacion se encuentra en uso!!");
                agregarAvion();
            }else{
                System.out.println("identificacion permitida!!");
                while(true){

                    System.out.println("Ingrese la cantidad de filas ejecutivas");
                    String filasEjecutivas = br.readLine();
                    System.out.println("Ingrese la cantidad de columnas ejecutivas");
                    String columnasEjecutivas = br.readLine();
                    System.out.println("Ingrese la cantidad de filas Economicas");
                    String filasEconomicas = br.readLine();
                    System.out.println("Ingrese la cantidad de columnas Economicas");
                    String columnasEconomicas = br.readLine();
                    int filasEjecutivas2 = Integer.parseInt(filasEjecutivas);
                    int columnasEjecutivas2 = Integer.parseInt(columnasEjecutivas);
                    int filasEconomicas2 = Integer.parseInt(filasEconomicas);
                    int columnasEconomicas2 = Integer.parseInt(columnasEconomicas);

                    if( filasEconomicas2 * columnasEconomicas2 +  filasEjecutivas2* columnasEjecutivas2 > 142){
                        System.out.println("Demasiados pasajeros");
                        continue;
                    } else if (columnasEconomicas2 > 9 ||  columnasEjecutivas2 > 9) {
                        System.out.println("Demasidas columnas");
                        continue;
                    } else if (filasEjecutivas2* columnasEjecutivas2 > filasEconomicas2 * columnasEconomicas2 ) {
                        System.out.println("la cantidad de espacios en ejecutivo deben ser menor a los espacios en economico");
                        continue;
                    } else if (columnasEjecutivas2 > columnasEconomicas2) {
                        System.out.println("La cantidad de columnas ejecutivas debe ser menor a la cantidad de columnas economicas");
                        continue;
                    } else if (filasEconomicas2 * columnasEconomicas2  < 2) {
                        System.out.println("La cantidad de asientos en economico debe ser igual o mayor a 2");

                    }else{
                        int posicionAvion = aeropuerto.agregarAvion(filasEjecutivas2,columnasEjecutivas2,filasEconomicas2,columnasEconomicas2,opcion);
                        if(posicionAvion == -1){
                            System.out.println("El aeropuerto esta lleno");
                            break;
                        }else{
                            System.out.println("El avion fue ingresado en el espacio " + posicionAvion);
                            break;
                        }
                    }
                }
                inicio();

            }

        }
        else{
            System.out.println("La identificacion tiene que tener 5 caracteres");
            agregarAvion();
        }


    }

    public void asignarPasajerosAAsientos() throws IOException {
        System.out.println("Ingrese la identificacion del avion que desea");
        String identificacionAvion = br.readLine();
        if(aeropuerto.buscaIdentificacionAvion(identificacionAvion) != -1){
            System.out.println("Encontramos el avion");
            while(true){
                System.out.println("Ingrese la seccion en la que deseas buscar(E = econonomica J = ejecutiva)");
                String Seccion = br.readLine();
                if(Seccion.toUpperCase().equals("E") || Seccion.toUpperCase().equals("J")){
                    System.out.println("Ingrese la identificacion del asiento(columna-fila)(1-A)");

                    String identificacion = br.readLine();
                    if(verificaFormatidentificacionAsiento(identificacion)){
                        if(aeropuerto.getEspacios()[aeropuerto.buscaIdentificacionAvion(identificacionAvion)].validaExsteAsiento(Seccion,identificacion)[0] != -1){
                            System.out.println("Ingrese su Nombre");
                            String nombre = br.readLine();
                            System.out.println("Ingrese su pais de origen");
                            String pais = br.readLine();
                            String identificacionPasajero2= nombre+pais;
                            if(aeropuerto.buscaIdentificacionPasajero(identificacionPasajero2) == -1){
                                System.out.println("El pasajero puede ingresar");
                                int posAvion = aeropuerto.buscaIdentificacionAvion(identificacionAvion);
                                int columna = aeropuerto.getEspacios()[posAvion].letraNumero( String.valueOf(identificacion.charAt(2)));
                                int fila =  Integer.parseInt( String.valueOf(identificacion.charAt(0)) );

                                if(Seccion.toUpperCase().equals("J")){
                                    aeropuerto.getEspacios()[posAvion].matrizEjecutivo[fila][columna].setPasajero(new Pasajero(identificacionPasajero2,nombre,pais));
                                }
                                else{
                                    aeropuerto.getEspacios()[posAvion].matrizEconomico[fila][columna].setPasajero(new Pasajero(identificacionPasajero2,nombre,pais));

                                }

                                break;
                            }
                            else{

                                System.out.println("El pasajero ya se encuentra registrado");
                            }


                        }
                        else{
                            System.out.println("El asiento no existe");
                        }
                    }
                    else{
                        System.out.println("Formato de identificacion de asisento no permitido");
                    }

                }
                else{
                    System.out.println("El dato ingresado no es permitido");
                }
            }
            inicio();


        }
        else{
            System.out.println("No existe ese avion");
            asignarPasajerosAAsientos();
        }

    }


    public boolean verificaFormatidentificacionAsiento(String identificacion){
        if(identificacion.length() == 3){
            String columna = String.valueOf(identificacion.charAt(2));
            String letras[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
            boolean terceroLetra = false;
            for (int i = 0; i < letras.length; i++) {

                if(columna.toUpperCase().equals(letras[i])){
                    terceroLetra = true;
                }
            }
            boolean primeroNumero = true;
            try {
                Integer.parseInt( String.valueOf(identificacion.charAt(0)) );
            }catch (Exception e){
                primeroNumero = false;

            }finally {
                if(primeroNumero && terceroLetra){
                    return true;
                }else{
                    return  false;
                }
            }

        }
        else {
            return false;
        }



    }
    public void excluirAvion() throws IOException {
        System.out.println("Ingrese la identificacion del avion que desea");
        String identificacionAvion = br.readLine();
        if(aeropuerto.buscaIdentificacionAvion(identificacionAvion) != -1){
            System.out.println("Encontramos el avion");
            int indiceAvion = aeropuerto.buscaIdentificacionAvion(identificacionAvion);
            boolean hayPersonas = false;
            while(true){
                Avion avionBorrar = aeropuerto.getEspacios()[indiceAvion];
                for (int i = 0; i < avionBorrar.get_economico().length; i++) {
                    for (int j = 0; j < avionBorrar.get_economico()[0].length; j++) {
                        if(avionBorrar.get_economico()[i][j].getPasajero() != null){
                            //System.out.println("sasasasssssss");
                            hayPersonas = true;
                            break;
                        }
                    }
                    if(hayPersonas){
                        break;
                    }
                }
                if(hayPersonas){
                    break;
                }
                if(avionBorrar.get_ejecutivo() != null){
                    for (int i = 0; i < avionBorrar.get_ejecutivo().length; i++) {

                        for (int j = 0; j < avionBorrar.get_ejecutivo()[0].length; j++) {
                            if(avionBorrar.get_economico()[i][j].getPasajero() != null){
                                hayPersonas = true;
                                break;
                            }
                        }
                        if(hayPersonas){
                            break;
                        }
                    }
                }
                break;
            }
            if(hayPersonas){
                System.out.println("No se puede excluir el avion porque hay gente dentro");
            }
            else{
                aeropuerto.getEspacios()[indiceAvion] = null;
                System.out.println("El avion se exluyo correctamente");

            }
            inicio();
        }
        else{
            System.out.println("No existe ese avion");
            modificarCapacidadDeAsientos();
        }
    }
    public void modificarCapacidadDeAsientos() throws IOException {
        System.out.println("Escriba la identificacion del avion en el que desea buscar");
        String identificacionAvion = br.readLine();
        if(identificacionAvion.length() == 5){
            if(aeropuerto.buscaIdentificacionAvion(identificacionAvion) != -1){
                System.out.println("Encontramos el avion");
                while(true){
                    System.out.println("Ingrese la seccion en la que deseas buscar(E = econonomica J = ejecutiva)");
                    String Seccion = br.readLine();
                    if(Seccion.toUpperCase().equals("E") || Seccion.toUpperCase().equals("J")){
                        System.out.println("Ingrese la identificacion del asiento(columna-fila)(1-A)");

                        String identificacion = br.readLine();
                        if(verificaFormatidentificacionAsiento(identificacion)){
                            if(aeropuerto.getEspacios()[aeropuerto.buscaIdentificacionAvion(identificacionAvion)].modificaCapacidadAsiento(Seccion,identificacion)){

                                break;
                            }
                            else{
                                System.out.println("El asiento no existe");
                            }
                        }
                        else{
                            System.out.println("Formato de identificacion de asisento no permitido");
                        }


                    }
                    else{
                        System.out.println("El dato ingresado no es permitido");
                    }
                }
                inicio();

            }else{
                System.out.println("No existe avion con esa identificacion");
                asignarPasajerosAAsientos();


            }

        }
        else{
            System.out.println("La identificacion tiene que tener 5 caracteres");
            asignarPasajerosAAsientos();
        }

    }
    public void vaciarAsiento() throws IOException{
        System.out.println("Escriba la identificacion del avion:");
        String identificacionAvion = br.readLine();
        if(identificacionAvion.length() == 5){
            if(aeropuerto.buscaIdentificacionAvion(identificacionAvion) != -1) {
                while(true){
                    System.out.println("Elija la opción (E = econonomica  J = ejecutiva  S = Salir):");
                    String opcion = br.readLine();
                    opcion = opcion.toUpperCase();

                    if(opcion.toUpperCase().equals("E") || opcion.toUpperCase().equals("J")) {
                        System.out.println("Ingrese la identificacion del asiento(columna-fila)(1-A)");
                        String identificacion = br.readLine();
                        if(verificaFormatidentificacionAsiento(identificacion)) {
                            Avion avion = aeropuerto.getEspacios()[aeropuerto.buscaIdentificacionAvion(identificacionAvion)];
                            //se procede a vaciar
                            if (avion.validaExsteAsiento(opcion, identificacion)[0] != -1) {//exite el asiento
                                avion.vaciarAsiento(opcion, identificacion);
                                System.out.println("Asiento " + identificacion + " vaciado correctamente!!!");
                                break;

                            }else{
                                System.out.println("No se pudo encontrar el asiento!!");
                            }
                        }

                    }
                    else{
                        break;
                    }
                }
                inicio();

            }else{
                System.out.println("Este avión no se encuentra registrado!!");
                inicio();

            }

        }else{
            System.out.println("Formato de identificación inválido!!");
            vaciarAsiento();
        }

    }

    private void vaciarMatriz(Asiento[][] lista){
        for(Asiento[] asientos : lista){
            for(Asiento asiento : asientos){
                asiento.vaciar();
            }
        }
    }

    public void vaciarAvion() throws IOException{
        System.out.println("Escriba la identificacion del avion:");
        String identificacionAvion = br.readLine();
        if(identificacionAvion.length() == 5){
            if(aeropuerto.buscaIdentificacionAvion(identificacionAvion) != -1) {
                Avion avion = aeropuerto.getEspacios()[aeropuerto.buscaIdentificacionAvion(identificacionAvion)];
                Asiento[][] ejecutivo = avion.get_ejecutivo();
                Asiento[][] economico = avion.get_economico();
                vaciarMatriz(ejecutivo);
                vaciarMatriz(economico);

                System.out.println("Avion vaciado correctamente!!");
                inicio();


            }else{
                System.out.println("Este avión no se encuentra registrado!!");
                inicio();

            }

        }else{
            System.out.println("Formato de identificación inválido!!");
            vaciarAsiento();
        }
    }
    private StringBuilder agregar_faltantes(String original){
        int faltante = 20 - original.length();
        StringBuilder aux = new StringBuilder(original);

        for(int i=0; i<faltante;i++)
            aux.append(" ");

        return aux;
    }
    private void imprimirAsientos(Asiento[][] asientos){
        if(asientos != null){
            StringBuilder info = new StringBuilder();
            for(Asiento[] fila: asientos){
                for(Asiento i: fila){
                    Pasajero pasajero = i.getPasajero();
                    if(pasajero != null){
                        if(i.getEstado() == "A"){
                            String nombre;
                            try{
                                nombre = pasajero.getNombre().substring(0, 20);
                            }
                            catch (StringIndexOutOfBoundsException e){
                                nombre = String.valueOf(agregar_faltantes(pasajero.getNombre()));
                            }

                            info.append(i.getIdentificacion()).append(" ").append(nombre).append("\t");
                        }else{
                            info.append(i.getIdentificacion()).append(" ").append("INACTIVO").append("\t");
                        }

                    }
                }
                info.append("\n");
            }
            System.out.println(info);
        }else{
            System.out.println("NO SE ENCUENTRAN ASIENTOS");
        }

    }
    public void consultarAvion() throws IOException{
        System.out.println("Escriba la identificacion del avion:");
        String identificacionAvion = br.readLine();
        if(identificacionAvion.length() == 5){
            if(aeropuerto.buscaIdentificacionAvion(identificacionAvion) != -1) {
                Avion avion = aeropuerto.getEspacios()[aeropuerto.buscaIdentificacionAvion(identificacionAvion)];
                Asiento[][] ejecutivo = avion.get_ejecutivo();
                Asiento[][] economico = avion.get_economico();

                System.out.println("CLASE EJECUTIVA");
                imprimirAsientos(ejecutivo);

                System.out.println("CLASE ECONOMICA");
                imprimirAsientos(economico);

                inicio();


            }else{
                System.out.println("Este avión no se encuentra registrado!!");
                inicio();
            }
        }
        else{
            System.out.println("Formato de identificación inválido!!");
            consultarAvion();
        }
    }

    public void buscarPasajero() throws IOException{
        System.out.println("Escriba la identificacion del pasajero:");
        String id_pasajero = br.readLine();
        Avion[] aviones = aeropuerto.getEspacios();
        Pasajero pasajero;
        Asiento[][] economicos, ejecutivos;
        Boolean encontrado = false;
        String asiento = "", id_avion = "";

        if(aeropuerto.buscaIdentificacionPasajero(id_pasajero) != -1) {

            for(Avion avion : aviones){
                if (!encontrado) {
                    economicos = avion.get_economico();
                    ejecutivos = avion.get_ejecutivo();

                    for(Asiento[] filas : economicos){
                        for(Asiento asiento1 : filas){
                            if(Objects.equals(asiento1.getPasajero().getIdentificacion(), id_pasajero)){
                                asiento = asiento1.getIdentificacion();
                                id_avion = avion.identificacion;
                                encontrado = true;
                                break;
                            }
                        }
                    }
                    if(!encontrado){
                        for(Asiento[] filas1 : ejecutivos){
                            for(Asiento asiento2 : filas1){
                                if(Objects.equals(asiento2.getPasajero().getIdentificacion(), id_pasajero)){
                                    asiento = asiento2.getIdentificacion();
                                    id_avion = avion.identificacion;
                                    encontrado = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    break;
                }

            }
            if(encontrado){
                System.out.println("El pasajero " + id_pasajero + " se encuentra en:\n --> Avion: " + id_avion + "\n --> Asiento: " + asiento + "\n");
                inicio();
            }
            else{
                System.out.println("El pasajero " + id_pasajero + " no fue encontrado!!\n");
                inicio();
            }

        }else{
            System.out.println("El pasajero " + id_pasajero + " no fue encontrado!!\n");
            inicio();
        }

    }
    public void consultarAsientosDisponibles(){

    }
}
