package uniquindio.edu.co.model;

public class EmpleadoVentas extends Empleado {
    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          float totalVentas, float porcentajeComision) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        if (porcentajeComision < 0 || porcentajeComision > 100)
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100.");
        if (totalVentas < 0)
            throw new IllegalArgumentException("El total de ventas no puede ser negativo.");

        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public float calcularSalarioBruto() {
        float comision = totalVentas * porcentajeComision / 100;
        return salarioBase + calcularBonificacionCategoria() + comision;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Empleado de Ventas");
        System.out.println("Total Ventas: $" + totalVentas);
        System.out.println("Comisión: %" + porcentajeComision);
        System.out.println("Comisión ($): $" + totalVentas * porcentajeComision / 100);
        System.out.println("========================================");
    }
}
