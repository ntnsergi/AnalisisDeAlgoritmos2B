public class GeneradorPseudoaleatorio {

    public static void main(String[] args) {
        int cantidad = 100; // se genera 100 numeros 
        long semilla = 12345; 

        double[] numeros = generarPseudoaleatorios(semilla, cantidad);

        
        System.out.println("--- Números pseudoaleatorios generados (primeros 10): ---");
        for (int i = 0; i < Math.min(10, cantidad); i++) { 
            System.out.println(numeros[i]);
        }
        System.out.println(); 

       
        System.out.println("--- Análisis de la distribución: ---");

        int count0_0_2 = 0;
        int count0_2_0_4 = 0;
        int count0_4_0_6 = 0;
        int count0_6_0_8 = 0;
        int count0_8_1_0 = 0;

        for (double num : numeros) {
            if (num >= 0.0 && num < 0.2) {
                count0_0_2++;
            } else if (num >= 0.2 && num < 0.4) {
                count0_2_0_4++;
            } else if (num >= 0.4 && num < 0.6) {
                count0_4_0_6++;
            } else if (num >= 0.6 && num < 0.8) {
                count0_6_0_8++;
            } else if (num >= 0.8 && num <= 1.0) { 
                count0_8_1_0++;
            }
        }

        System.out.println("Intervalo [0.0, 0.2): " + count0_0_2 + " números");
        System.out.println("Intervalo [0.2, 0.4): " + count0_2_0_4 + " números");
        System.out.println("Intervalo [0.4, 0.6): " + count0_4_0_6 + " números");
        System.out.println("Intervalo [0.6, 0.8): " + count0_6_0_8 + " números");
        System.out.println("Intervalo [0.8, 1.0]: " + count0_8_1_0 + " números"); 


        int totalContado = count0_0_2 + count0_2_0_4 + count0_4_0_6 + count0_6_0_8 + count0_8_1_0;
        System.out.println("Total de números contados: " + totalContado);
        System.out.println("Total de números generados: " + cantidad);
        System.out.println(); // Salto de línea


        System.out.println("--- Respuestas a las preguntas: ---");

        System.out.println("¿La distribución es aproximadamente uniforme?");

        System.out.println("Al observar los conteos anteriores, si los números en cada intervalo son aproximadamente " + (double)cantidad/5 + ", entonces la distribución es aproximadamente uniforme. En este caso la respuesta es 'SI'");
        System.out.println();

        System.out.println("¿Qué efecto tiene cambiar la semilla?");
        System.out.println("Cambiar la semilla (el valor inicial 'x') alterará completamente la secuencia de números pseudoaleatorios generada");
        
    }

    public static double[] generarPseudoaleatorios(long semilla, int cantidad) {
        // Parámetros del generador (Numerical Recipes)
        long a = 1664525;
        long c = 1013904223;
        long m = (long) Math.pow(2, 32);

        double[] resultados = new double[cantidad];
        long x = semilla;

        for (int i = 0; i < cantidad; i++) {
            x = (a * x + c) % m;
            resultados[i] = (double) x / m; // Normalización a [0, 1)
        }

        return resultados;
    }
}