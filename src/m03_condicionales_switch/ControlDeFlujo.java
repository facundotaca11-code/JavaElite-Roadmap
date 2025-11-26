package m03_condicionales_switch;

public class ControlDeFlujo {
    public static void main(String[] args) {
        String rol = "ADMIN";

        // Switch Expression (Java 14+)
        String permisos = switch (rol) {
            case "ADMIN" -> "Acceso Total (RWX)";
            case "EDITOR" -> "Acceso Parcial (RW)";
            case "USER", "GUEST" -> "Solo Lectura (R)";
            default -> {
                System.out.println("⚠️ Rol no reconocido: " + rol);
                yield "Sin Permisos";
            }
        };

        System.out.println("Rol: " + rol + " | Permisos: " + permisos);
    }
}