import java.util.Scanner;

public class conversorDeMonedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        ConversorAPI conversorAPI = new ConversorAPI("95ff557a34eae6fbba17251d"); // Reemplaza con tu API Key válida

        while (continuar) {
            System.out.println("Bienvenido al Conversor de Monedas");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir de Dólares a Pesos Dominicanos");
            System.out.println("2. Convertir de Pesos Dominicanos a Dólares");
            System.out.println("3. Convertir de Dólares a Bolívares");
            System.out.println("4. Convertir de Bolívares a Dólares");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la cantidad en Dólares: ");
                    double dolares = scanner.nextDouble();
                    double tasaDolarAPeso = conversorAPI.obtenerTasa("USD", "DOP");
                    System.out.println("Equivalente en Pesos Dominicanos: " + (dolares * tasaDolarAPeso));
                    break;

                case 2:
                    System.out.print("Ingrese la cantidad en Pesos Dominicanos: ");
                    double pesos = scanner.nextDouble();
                    double tasaPesoADolar = conversorAPI.obtenerTasa("DOP", "USD");
                    System.out.println("Equivalente en Dólares: " + (pesos * tasaPesoADolar));
                    break;

                case 3:
                    System.out.print("Ingrese la cantidad en Dólares: ");
                    dolares = scanner.nextDouble();
                    double tasaDolarABolivar = conversorAPI.obtenerTasa("USD", "VES");
                    System.out.println("Equivalente en Bolívares: " + (dolares * tasaDolarABolivar));
                    break;

                case 4:
                    System.out.print("Ingrese la cantidad en Bolívares: ");
                    double bolivares = scanner.nextDouble();
                    double tasaBolivarADolar = conversorAPI.obtenerTasa("VES", "USD");
                    System.out.println("Equivalente en Dólares: " + (bolivares * tasaBolivarADolar));
                    break;

                case 5:
                    System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta pronto!");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }

            System.out.println(); // Salto de línea para claridad
        }

        scanner.close();
    }
}