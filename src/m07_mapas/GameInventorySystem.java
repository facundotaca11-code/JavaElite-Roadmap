package m07_mapas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameInventorySystem {

    // ENTIDAD DEL DOMINIO (Mi molde de datos)
    // Nota: Uso 'double' por simplicidad aquí, pero en el módulo m08
    // aprendí que para dinero real debo usar BigDecimal.
    public record Game(String title, double price) {}

    public static void main(String[] args) {
        // BASE DE DATOS EN MEMORIA
        // Clave (String ID) -> Valor (Objeto Game completo)
        Map<String, Game> inventory = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        // Datos semilla (para no arrancar con la base vacía)
        inventory.put("ELD", new Game("Elden Ring", 60.0));

        while (true) {
            System.out.println("\n=== 📦 GAME INVENTORY SYSTEM v1.0 ===");
            System.out.println("1. 🔎 Search Game (Read)");
            System.out.println("2. ➕ Add Game (Create)");
            System.out.println("3. ✏️ Update Price (Update)");
            System.out.println("4. 🗑️ Delete Game (Delete)");
            System.out.println("5. 🚪 Exit");
            System.out.print("Select option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> { // READ (Leer/Buscar)
                    System.out.print("Enter Game ID: ");
                    // Normalizo el input: quito espacios y paso a mayúsculas
                    String searchId = scanner.nextLine().trim().toUpperCase();

                    if (inventory.containsKey(searchId)) {
                        Game foundGame = inventory.get(searchId);
                        System.out.println("✅ Found: " + foundGame);
                    } else {
                        System.out.println("❌ Error: ID not found.");
                    }
                }

                case "2" -> { // CREATE (Crear)
                    System.out.print("New ID: ");
                    String newId = scanner.nextLine().trim().toUpperCase();

                    // CLÁUSULA DE GUARDA: Evitar duplicados
                    // Si ya existe, corto el flujo aquí para proteger los datos.
                    if (inventory.containsKey(newId)) {
                        System.out.println("⛔ Error: ID already exists. Use Update instead.");
                    } else {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Price: ");
                        // Convierto el String del teclado a double
                        double price = Double.parseDouble(scanner.nextLine());

                        // Guardo en el mapa
                        inventory.put(newId, new Game(title, price));
                        System.out.println("💾 Game saved successfully.");
                    }
                }

                case "3" -> { // UPDATE (Actualizar)
                    System.out.println("--- UPDATE PRICE ---");
                    System.out.print("Enter Game ID: ");
                    String updateId = scanner.nextLine().trim().toUpperCase();

                    // CLÁUSULA DE GUARDA CON LÓGICA NEGATIVA (!)
                    // "Si NO contiene la llave..." -> Lanzo error.
                    if (!inventory.containsKey(updateId)) {
                        System.out.println("❌ Error: Game not found.");
                    } else {
                        // 1. Recupero el juego actual para no perder el título
                        Game currentGame = inventory.get(updateId);
                        System.out.println("Current Data: " + currentGame);

                        System.out.print("New Price: ");
                        double newPrice = Double.parseDouble(scanner.nextLine());

                        // 2. Creo una copia nueva (porque el Record es inmutable)
                        Game updatedGame = new Game(currentGame.title(), newPrice);

                        // 3. Reemplazo el viejo por el nuevo en el mapa
                        inventory.put(updateId, updatedGame);
                        System.out.println("✅ Price updated.");
                    }
                }

                case "4" -> { // DELETE (Borrar)
                    System.out.println("--- DELETE GAME ---");
                    System.out.print("Enter ID to delete: ");
                    String deleteId = scanner.nextLine().trim().toUpperCase();

                    // Verifico antes de intentar borrar
                    if (!inventory.containsKey(deleteId)) {
                        System.out.println("❌ Error: Cannot delete non-existent game.");
                    } else {
                        // .remove() borra Y me devuelve el objeto eliminado
                        Game removedGame = inventory.remove(deleteId);
                        System.out.println("🗑️ Permanently deleted: " + removedGame.title());
                    }
                }

                case "5" -> {
                    System.out.println("System shutting down. Goodbye!");
                    return; // Mata el método main y termina el programa
                }

                default -> System.out.println("⚠️ Invalid option.");
            }
        }
    }
}