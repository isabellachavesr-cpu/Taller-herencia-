package uniquindio.edu.co.model;

import java.util.Scanner;
/**
 * Clase principal del sistema de nómina de empleados.
 * Menú interactivo que permite al usuario registrar empleados, consultar información y calcular la nómina
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Empresa empresa = new Empresa ("TechCorp S.A.S");

    public static void main(String[] args) {
      int opcion;
      do {
        mostrarMenu();
        opcion = leerEntero("Seleccione una opción");
        switch (opcion) {
            case 1:
                agregarEmpleadoPlanta();
                break;
             case 2:
                 agregarEmpleadoVentas();
                 break;
             case 3:
                 agregarEmpleadoTemporal();
                 break;
            case 4:
                empresa.mostrarTodosLosEmpleados();
                break;
            case 5:
                buscarEmpleado();
                break;
            case 6:
                empresa.mostrarEmpleadoMayorSalario();
                break;
            case 7:
                empresa.calcularNominaTotal();
                break;
            case 8:
                empresa.mostrarResumenesDePago();
                break;
            case 9:
                System.out.println("¡Hasta pronto!");
                break;
            default:
                System.out.println("Opción invalida. Intente de nuevo.");

        }
      } while (opcion != 9);
    }

    private static void mostrarMenu() {
        System.out.println("\n=====================");
        System.out.println(" SISTEMA DE NÓMINA - MENÚ ");
        System.out.println("=====================");
        System.out.println("1. Agregar empleado de planta");
        System.out.println("2. Agregar empleado de ventas");
        System.out.println("3. Agregar empleado temporal");
        System.out.println("4. Mostrar todos los empleados");
        System.out.println("5. Buscar empleado por documento");
        System.out.println("6. Mostrar empleado con mayor salario neto");
        System.out.println("7. Mostrar nómina total");
        System.out.println("8. Mostrar resumen de pagos");
        System.out.println("9. Salir");
        System.out.println("=====================");
    }

    private static void agregarEmpleadoPlanta() {
        System.out.println("\n Registro Empleado de Planta");
        try {
            String nombre = leerTexto("Nombre: ");
            String documento = leerTexto("Documento: ");
            int edad = leerEntero("Edad: ");
            float salarioBase = leerFloat("Salario base: ");
            CategoriaEmpleado categoria = leerCategoria();
            float descSalud = leerFloat("Descuento salud (%): ");
            float descPension = leerFloat("Descuento pensión (%): ");
            String cargo = leerTexto("Cargo: ");
            int horasExtra = leerEntero("Horas extra: ");
            float valorHoraExtra = leerFloat("Valor hora extra: ");
            float auxilioTransporte = leerFloat("Auxilio de transporte: ");

            EmpleadoPlanta emp = new EmpleadoPlanta(nombre, documento, edad, salarioBase,
                    categoria, descSalud, descPension, cargo, horasExtra, valorHoraExtra, auxilioTransporte);
            empresa.agregarEmpleado(emp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());


        }
    }

    private static void agregarEmpleadoVentas() {
        System.out.println("\n Registro Empleado de Ventas");
        try {
            String nombre = leerTexto("Nombre: ");
            String documento = leerTexto("Documento: ");
            int edad = leerEntero("Edad: ");
            float salarioBase = leerFloat("Salario base: ");
            CategoriaEmpleado categoria = leerCategoria();
            float descSalud = leerFloat("Descuento salud (%): ");
            float descPension = leerFloat("Descuento pensión (%): ");
            float totalVentas = leerFloat("Total ventas del mes: ");
            float porcentajeComision = leerFloat("Porcentaje de comisión (%): ");

            EmpleadoVentas emp = new EmpleadoVentas(nombre, documento, edad, salarioBase,
                    categoria, descSalud, descPension, totalVentas, porcentajeComision);
            empresa.agregarEmpleado(emp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void agregarEmpleadoTemporal() {
        System.out.println("\n Registro Empleado Temporal");
        try {
            String nombre = leerTexto("Nombre: ");
            String documento = leerTexto("Documento: ");
            int edad = leerEntero("Edad: ");
            float salarioBase = leerFloat("Salario base (referencia): ");
            CategoriaEmpleado categoria = leerCategoria();
            float descSalud = leerFloat("Descuento salud (%): ");
            float descPension = leerFloat("Descuento pensión (%): ");
            int diasTrabajados = leerEntero("Días trabajados: ");
            float valorDia = leerFloat("Valor por día: ");

            EmpleadoTemporal emp = new EmpleadoTemporal(nombre, documento, edad, salarioBase,
                    categoria, descSalud, descPension, diasTrabajados, valorDia);
            empresa.agregarEmpleado(emp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void buscarEmpleado() {
        String doc = leerTexto("Ingrese el documento a buscar: ");
        Empleado encontrado = empresa.buscarPorDocumento(doc);
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        } else {
            System.out.println("No se encontró ningún empleado con ese documento.");
        }
    }

    private static CategoriaEmpleado leerCategoria() {
        System.out.println("Categorías: 1. JUNIOR  2. SEMI_SENIOR  3. SENIOR");
        int op = leerEntero("Seleccione categoría: ");
        switch (op) {
            case 1:
                return CategoriaEmpleado.JUNIOR;
            case 2:
                return CategoriaEmpleado.SEMI_SENIOR;
            case 3:
                return CategoriaEmpleado.SENIOR;
            default:
                throw new IllegalArgumentException("Categoría inválida.");



        }


    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + mensaje);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static float leerFloat(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextFloat()) {
            System.out.print("Valor inválido. " + mensaje);
            scanner.next();
        }
        float valor = scanner.nextFloat();
        scanner.nextLine();
        return valor;
    }


}