package m02_tipos_datos_memoria;

public class TipoDeDatos {
    public static void main(String[] args) {
        // Primitivos (Stack)
        byte edad = 127;
        int salario = 50000;

        // Referencias (Heap)
        String nombre = "Facundo";

        // Inferencia
        var mensaje = "Sistema de Tipos Validado";

        System.out.println(mensaje);
        System.out.println("Usuario: " + nombre + " | Edad: " + edad);

        // Prueba de paso por valor
        int a = 10;
        int b = a;
        a = 20;
        System.out.println("Valor final de b (debe ser 10): " + b);
    }
}