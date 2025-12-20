package m06_listas_colecciones;

import java.util.ArrayList;
import java.util.List;

public class MainListas {

    // Nuestro molde de datos
    record Videojuego(String titulo, String genero, double precio) {}

    public static void main(String[] args) {
        // 1. Definición de la Lista (Diamond Operator <>)
        List<Videojuego> estanteria = new ArrayList<>();

        // 2. Ingesta de datos
        estanteria.add(new Videojuego("Elden Ring", "RPG", 60.0));
        estanteria.add(new Videojuego("Hollow Knight", "Metroidvania", 15.0));
        estanteria.add(new Videojuego("God of War", "Action", 70.0));
        estanteria.add(new Videojuego("Minecraft", "Sandbox", 30.0));

        System.out.println("--- PROCESANDO LISTA DE JUEGOS ---");

        // 3. Iteración y Lógica de Negocio
        for (Videojuego juego : estanteria) {

            // ⚔️ DESAFÍO AQUÍ:
            // Si el precio es mayor a 50, imprime: "Título ($Precio) - ¡JUEGAZO AAA!"
            // Si no, imprime solo: "Título ($Precio)"

            // Escribe tu IF aquí abajo:
            if (juego.precio() > 50) {
                System.out.println("✅ " + juego.titulo() + " ($" + juego.precio() + ") - ¡JUEGAZO AAA!");
            } else {
                System.out.println("🔹 " + juego.titulo() + " ($" + juego.precio() + ")");
            }
        }
    }
}