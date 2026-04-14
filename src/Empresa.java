package uniquindio.edu.co.model;

/**
 * Clase principal.
 * Permite registrar, buscar y consultar información de los empleados.
 */

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> listEmpleados;

    public Empresa (String nombre) {
        this.nombre = nombre;
        this.listEmpleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        listEmpleados.add(empleado);
        System.out.println("Empleado " + empleado.getNombre() + " registrado correctamente.");
    }

    public void mostrarTodosLosEmpleados() {
        if (listEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n===== LISTA DE EMPLEADOS - " + nombre + " =====");
        for (Empleado e : listEmpleados) {
            e.mostrarInformacion();
        }

    }

    public Empleado buscarPorDocumento(String documento) {
        for (Empleado e : listEmpleados) {
            if (e.getDocumento().equals(documento)) {
                return e;
            }
        }
        return null;
    }

    public void mostrarEmpleadoMayorSalario() {
        if (listEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        Empleado mayor = listEmpleados.get(0);
        for (Empleado e : listEmpleados) {
            if (e.calcularSalarioNeto() > mayor.calcularSalarioNeto()) {
                mayor = e;
            }
        }
        System.out.println("\n===== EMPLEADO CON MAYOR SALARIO NETO =====");
        mayor.mostrarInformacion();
    }

    public void calcularNominaTotal() {
        float total = 0;
        for (Empleado e : listEmpleados) {
            total += e.calcularSalarioNeto();
        }
        System.out.println("\nNómina total de la empresa " + nombre + ": $" + total);
    }

    public void mostrarResumenesDePago() {
        if (listEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\n===== RESÚMENES DE PAGO =====");
        for (Empleado e : listEmpleados) {
            ResumenPago resumen = e.generarResumenPago();
            System.out.println("----------------------------------------");
            System.out.println("Documento: " + resumen.documento());
            System.out.println("Nombre: " + resumen.nombre());
            System.out.println("Tipo: " + resumen.tipoEmpleado());
            System.out.println("Salario Bruto: $" + resumen.salarioBruto());
            System.out.println("Descuentos: $" + resumen.descuentos());
            System.out.println("Salario Neto: $" + resumen.salarioNeto());
        }
        System.out.println("----------------------------------------");
    }

    public List<Empleado> getListEmpleados() {
        return listEmpleados;
    }
}