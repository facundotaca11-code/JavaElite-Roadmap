package _bootcamp_fundamentos.m04_bucles_iteraciones;

public class Bucles {
    public static void main(String[] args) {

        // --- 1. BUCLE FOR (Contador) ---
        System.out.println("--- Iniciando Bucle FOR ---");

        // i++ es el paso estándar (sumar 1)
        for (int i = 1; i <= 5; i++) {
            System.out.println("📧 Enviando email #" + i);
        }

        // --- 2. BUCLE WHILE (Condicional) ---
        System.out.println("\n--- Iniciando Bucle WHILE ---");

        int intentos = 0;
        int maximosIntentos = 3;
        boolean conectado = false;

        while (!conectado && intentos < maximosIntentos) {
            intentos++; // IMPORTANTE: Incremento para evitar bucle infinito
            System.out.println("🔄 Intento de conexión: " + intentos);

            if (intentos == 3) {
                System.out.println("✅ ¡Conexión Exitosa!");
                conectado = true;
            } else {
                System.out.println("❌ Fallo. Reintentando...");
            }
        }
    }
}