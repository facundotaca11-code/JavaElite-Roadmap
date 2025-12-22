package m08_bigdecimal_precision;

import java.math.BigDecimal;

public class PrecisionLab {

    public static void main(String[] args) {
        System.out.println("=== 🧪 THE FLOATING POINT HORROR SHOW ===");

        // 1. EL ERROR DEL DOUBLE
        // Intentamos sumar 0.1 diez veces. Debería dar 1.0, ¿verdad?
        double price = 0.1;
        double totalDouble = price + price + price; // 0.1 + 0.1 + 0.1

        System.out.println("Expected (Mental): 0.3");
        System.out.println("Current (Double):   " + totalDouble);

        // 2. LA SOLUCIÓN BIGDECIMAL
        // Regla de Oro: SIEMPRE usa el constructor con String ("0.1").
        // NUNCA uses new BigDecimal(0.1) porque heredas el error del double.
        BigDecimal securePrice = new BigDecimal ("0.1");

        // En BigDecimal no usamos '+' usamos métodos .add()
        BigDecimal totalBig = securePrice.add(securePrice).add(securePrice);

        System.out.println("\n--- FIXING WITH BIGDECIMAL ---");
        System.out.println("Current (BigDecimal): " + totalBig);

        // 3. COMPARACIÓN PELIGROSA
        System.out.println("\n--- COMPARISON CHECK ---");
        if (totalDouble == 0.3) {
            System.out.println("❌ Double: Math works! (Imposible)");
        } else {
            System.out.println("✅ Double: 0.3 is NOT 0.30000000000000004");
        }
    }
}