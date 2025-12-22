package m08_bigdecimal_precision;

// IMPORTS: Traigo las herramientas de Java que voy a necesitar.
// BigDecimal: Para dinero exacto.
// HashMap/Map: Para mi base de datos en memoria.
// Scanner: Para leer el teclado.
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// PUBLIC CLASS:
// Es 'public' porque es el archivo principal. Si no fuera pública,
// la JVM (Java Virtual Machine) no podría verla para arrancar el programa.
public class FinancialSystem {

    // --- CONSTANTES (CONFIGURACIÓN) ---

    // PRIVATE: Solo este archivo puede ver esto. Seguridad básica (Encapsulamiento).
    // STATIC: Pertenece a la clase "FinancialSystem", no a un objeto específico. Ahorra memoria.
    // FINAL: Es una constante. Nadie puede cambiar este valor mientras el programa corre.
    // BigDecimal("String"): Uso comillas "" para que la precisión sea perfecta desde el nacimiento.
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("100.00");
    private static final BigDecimal MIN_DEPOSIT = new BigDecimal("10.00");

    // --- ENTIDAD DEL DOMINIO (MODELO DE DATOS) ---

    // PUBLIC RECORD:
    // Es 'public' porque quiero que este tipo de dato 'Account' sea visible
    // por si en el futuro tengo otras clases que necesiten usarlo.
    // RECORD: Es inmutable. Una vez que nace una Account, sus datos no cambian.
    public record Account(String owner, BigDecimal balance) {}

    // --- MÉTODO PRINCIPAL (ENTRY POINT) ---

    // PUBLIC: Para que la JVM pueda entrar.
    // STATIC: Para que arranque sin tener que crear un objeto 'new FinancialSystem()'.
    // VOID: No devuelve nada, solo ejecuta.
    public static void main(String[] args) {

        // VARIABLES LOCALES (Dentro del método):
        // Nota: Aquí NO pongo 'private' ni 'public'. Las variables dentro de métodos
        // son siempre locales y temporales. Nacen y mueren aquí.

        // Mi Base de Datos: Clave (ID Usuario) -> Valor (Objeto Cuenta)
        Map<String, Account> database = new HashMap<>();

        // Mi "Oído": Para escuchar lo que escribe el usuario.
        Scanner scanner = new Scanner(System.in);

        // SEED DATA (Datos Semilla):
        // Cargo un usuario de prueba para no tener que crear uno cada vez que ejecuto.
        database.put("USER1", new Account("Alice", INITIAL_BALANCE));

        // GAME LOOP (Bucle Infinito):
        // while(true) mantiene el programa vivo esperando órdenes eternamente
        // hasta que yo le diga "return" o "break".
        while (true) {
            // UI (Interfaz de Usuario): Muestro el menú en inglés.
            System.out.println("\n=== 🏦 ENTERPRISE FINANCIAL CORE ===");
            System.out.println("1. 💰 Check Balance (Read)");
            System.out.println("2. 📥 Deposit Funds (Update)");
            System.out.println("3. 🚪 Exit");
            System.out.print("Select option: ");

            // Leo la opción del usuario
            String option = scanner.nextLine();

            // SWITCH: El controlador de tráfico. Decide a dónde ir según la opción.
            switch (option) {

                // CASO 1: LECTURA (READ)
                case "1" -> {
                    System.out.print("Enter User ID: ");
                    // Normalizo: Quito espacios (.trim) y paso a mayúsculas (.toUpperCase)
                    // Así "user1 " se convierte en "USER1".
                    String userId = scanner.nextLine().trim().toUpperCase();

                    // VALIDACIÓN: ¿Existe en el mapa?
                    if (!database.containsKey(userId)) {
                        System.out.println("❌ Error: User not found.");
                    } else {
                        // Recupero el objeto Account
                        Account account = database.get(userId);
                        // Imprimo. BigDecimal se encarga de mostrarse bonito automáticamente.
                        System.out.println("✅ Balance for " + account.owner() + ": $" + account.balance());
                    }
                }

                // CASO 2: ACTUALIZACIÓN (UPDATE) - Aquí está la magia financiera
                case "2" -> {
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine().trim().toUpperCase();

                    // GUARD CLAUSE (Cláusula de Guarda):
                    // Patrón de diseño: Verifico el error PRIMERO.
                    // Si NO existe el usuario, corto el flujo con 'continue' y vuelvo al menú.
                    // Esto evita anidar if-else gigantes (Arrow Code).
                    if (!database.containsKey(userId)) {
                        System.out.println("❌ Error: User not found.");
                        continue;
                    }

                    System.out.print("Amount to Deposit: ");
                    String amountStr = scanner.nextLine();

                    // TRY-CATCH: Manejo de Excepciones
                    // Intento convertir el texto a número. Si el usuario escribe "hola",
                    // el programa saltará al bloque 'catch' en lugar de explotar.
                    try {
                        // 1. CONVERSIÓN SEGURA
                        // String -> BigDecimal. Nunca paso por double.
                        BigDecimal depositAmount = new BigDecimal(amountStr);

                        // 2. REGLA DE NEGOCIO (Business Logic)
                        // Uso .compareTo() porque no puedo usar signos '<' o '>' con objetos.
                        // Si depositAmount es MENOR que MIN_DEPOSIT, devuelve -1.
                        if (depositAmount.compareTo(MIN_DEPOSIT) < 0) {
                            System.out.println("❌ Error: Minimum deposit is $" + MIN_DEPOSIT);
                            continue; // Corto y vuelvo al menú
                        }

                        // 3. CÁLCULO MATEMÁTICO
                        // Obtengo la cuenta actual (Vieja)
                        Account currentAccount = database.get(userId);

                        // ¡IMPORTANTE! INMUTABILIDAD:
                        // currentAccount.balance().add(...) NO modifica el saldo original.
                        // Crea un NUEVO objeto BigDecimal con el resultado.
                        // Por eso es obligatorio guardarlo en una variable nueva 'newBalance'.
                        BigDecimal newBalance = currentAccount.balance().add(depositAmount);

                        // 4. PERSISTENCIA (Guardar cambios)
                        // Como el Record 'Account' también es inmutable, no tiene setters.
                        // Tengo que crear una NUEVA Account con el mismo dueño y el saldo nuevo.
                        Account updatedAccount = new Account(currentAccount.owner(), newBalance);

                        // .put() es destructivo: Sobrescribe la entrada vieja con la nueva.
                        database.put(userId, updatedAccount);

                        System.out.println("✅ Deposit Successful.");
                        System.out.println("   Old Balance: $" + currentAccount.balance());
                        System.out.println("   New Balance: $" + newBalance);

                    } catch (NumberFormatException e) {
                        // Si falló el 'new BigDecimal(amountStr)' porque era texto inválido:
                        System.out.println("❌ Error: Invalid number format.");
                    }
                }

                // CASO 3: SALIDA
                case "3" -> {
                    System.out.println("System shutting down.");
                    return; // 'return' mata al método main completo. Fin del programa.
                }

                // DEFAULT: Por si escribe opción "4", "A", o cualquier cosa rara.
                default -> System.out.println("⚠️ Invalid option.");
            }
        }
    }
}