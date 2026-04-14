package uniquindio.edu.co.model;

/**
 * Record que almacena información inmutable del pago de un empleado.
 */

public record ResumenPago(

        String documento,
        String nombre,
        String tipoEmpleado,
        float salarioBruto,
        float descuentos,
        float salarioNeto
) {}


