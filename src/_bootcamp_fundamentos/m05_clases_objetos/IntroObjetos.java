package _bootcamp_fundamentos.m05_clases_objetos;

public class IntroObjetos {

    // 1. Definimos un "Molde" (Record) para un Videojuego.
    // Esto le enseña a Java qué es un "Videojuego".
    record Videojuego(String titulo, String genero, double precio) {}

    public static void main(String[] args) {
        // ANTES (Lo que hacías en m02): Variables sueltas, difícil de manejar
        String tituloJuego1 = "Super Mario";
        String generoJuego1 = "Plataformas";
        double precioJuego1 = 59.99;

        // AHORA (POO): Agrupamos todo en un solo Objeto
        Videojuego juego1 = new Videojuego("Elden Ring", "RPG", 69.99);
        Videojuego juego2 = new Videojuego("FIFA 24", "Deportes", 49.99);

        //  Podemos imprimir todo el paquete junto
        System.out.println("Juego 1: " + juego1);

        // O acceder a partes especificas
        System.out.println("El genero de " + juego2.titulo() + " es " + juego2.genero());
    }
}