
import java.util.Scanner;

public class InterfazArbolAVL {
    public static void main(String[] args) {
        ArbolBinarioAVL arbol = new ArbolBinarioAVL();
        Scanner entrada = new Scanner(System.in);
        String opcion;

        System.out.println("Bienvenido al Gestor de Árboles AVL");
        System.out.println("----------------------------------");
        System.out.println("Ingrese comandos para interactuar:");
        System.out.println("'i <valor>' - Insertar un valor en el árbol.");
        System.out.println("'e <valor>' - Eliminar un valor del árbol.");
        System.out.println("'m' - Mostrar el árbol completo.");
        System.out.println("'r' - Reiniciar el árbol.");
        System.out.println("'s' - Salir del programa.");

        do {
            System.out.print("> ");
            opcion = entrada.nextLine().trim();
            String[] partes = opcion.split("\\s+");
            String comando = partes[0].toLowerCase();

            switch (comando) {
                case "i":
                    if (partes.length == 2) {
                        try {
                            int valor = Integer.parseInt(partes[1]);
                            arbol.insertar(valor);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: El valor a insertar debe ser un número entero.");
                        }
                    } else {
                        System.out.println("Error: Sintaxis incorrecta. Use 'insertar <valor>'.");
                    }
                    break;
                case "e":
                    if (partes.length == 2) {
                        try {
                            int valor = Integer.parseInt(partes[1]);
                            arbol.eliminar(valor);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: El valor a eliminar debe ser un número entero.");
                        }
                    } else {
                        System.out.println("Error: Sintaxis incorrecta. Use 'eliminar <valor>'.");
                    }
                    break;
                case "m":
                    arbol.mostrarArbolCompleto();
                    break;
                case "r":
                    arbol.reiniciar();
                    break;
                case "s":
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Comando no reconocido. Por favor, use uno de los comandos listados.");
            }
        } while (!opcion.equalsIgnoreCase("salir"));

        entrada.close();
    }
}