package _bootcamp_fundamentos.m05_clases_objetos;

public class IntroObjetos {

    // ✅ Record con un MÉTODO (Comportamiento)
    record Videojuego(String titulo, String genero, double precio) {

        // Este método vive DENTRO del objeto.
        // "this" se refiere a los datos de ESTA instancia específica.
        public double calcularPrecioConDescuento(int porcentajeDescuento) {
            double descuento = this.precio * (porcentajeDescuento / 100.0);
            return this.precio - descuento;
        }

        // Método para imprimir bonito (formato personalizado)
        public String fichaTecnica() {
            return """
                   --- FICHA JUEGO ---
                   Título: %s
                   Género: %s
                   Precio: $%.2f
                   -------------------
                   """.formatted(titulo(), genero(), precio()); // En records usamos nombre() no getNombre()
        }
    }

    public static void main(String[] args) {
        Videojuego eldenRing = new Videojuego("Elden Ring", "RPG", 60.00);

        // 1. Pedimos al objeto que nos dé su ficha
        System.out.println(eldenRing.fichaTecnica());

        // 2. Pedimos al objeto que calcule su descuento
        double precioFinal = eldenRing.calcularPrecioConDescuento(50); // 50% off

        System.out.println("Precio Oferta: $" + precioFinal);
    }
}