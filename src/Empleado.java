public abstract class Empleado {
    private String nombre;
    private String documento;
    private int edad;
    private float salarioBase;
    private CategoriaEmpleado categoria;
    private float descuentoSalud;
    private float descuentoPension;

    public Empleado(String nombre, String documento, int edad, float salarioBase,
                    CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {
        if (salarioBase < 0)
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        if (descuentoSalud < 0 || descuentoSalud > 100)
            throw new IllegalArgumentException("El descuento de salud debe estar entre 0 y 100.");
        if (descuentoPension < 0 || descuentoPension > 100)
            throw new IllegalArgumentException("El descuento de pensión debe estar entre 0 y 100.");

        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoria = categoria;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;

    }

    public abstract float calcularSalarioBruto();

    public float calcularBonificacionCategoria(){
        switch (categoria) {
            case JUNIOR:
                return salarioBase * 0.05f;
            case SEMI_SENIOR:
                return salarioBase * 0.10f;
            case SENIOR:
                return salarioBase * 0.15f;
            default:
                return 0;

        }

    }

    public float calcularDescuentos() {
        float bruto = calcularSalarioBruto();
        return bruto * (descuentoSalud / 100) + bruto * (descuentoPension / 100);
    }

    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    public void mostrarInformacion() {
        System.out.println("============================");
        System.out.println("Nombre: " + nombre);
        System.out.println("Documento: " + documento);
        System.out.println("Edad: " + edad);
        System.out.println("Categoria: " + categoria);
        System.out.println("Salario Base: $" + salarioBase);
        System.out.println("Salario Bruto: $" + calcularSalarioBruto());
        System.out.println("Descuentos: $" + calcularDescuentos());
        System.out.println("Salario Neto: $" + calcularSalarioNeto());

    }

    public ResumenPago generarResumenPago() {
        return new ResumenPago(
                documento,
                nombre,
                getClass().getSimpleName(),
                calcularSalarioBruto(),
                calcularDescuentos(),
                calcularSalarioNeto()

        );


    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

}