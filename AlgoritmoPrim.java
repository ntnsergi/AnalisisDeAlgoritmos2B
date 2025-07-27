import java.util.*;

public class AlgoritmoPrim {

    public static void main(String[] args) {
        // Matriz de adyacencia, el grafo usado es el de las diapositivas pag 19
        int[][] grafo = {
            //1  2  3  4  5  6  7
            { 0, 1, 0, 4, 0, 0, 0 }, 
            { 1, 0, 2, 6, 4, 0, 0 }, 
            { 0, 2, 0, 0, 5, 6, 0 }, 
            { 4, 6, 0, 0, 3, 0, 4 }, 
            { 0, 4, 5, 3, 0, 8, 7 }, 
            { 0, 0, 6, 0, 8, 0, 3 }, 
            { 0, 0, 0, 4, 7, 3, 0 }  
        };

        ejecutarPrim(grafo);
    }

    public static void ejecutarPrim(int[][] grafo) {
        int cantidadNodos = grafo.length;

        boolean[] visitado = new boolean[cantidadNodos];
        int[] padre = new int[cantidadNodos];       // Arbol generado
        int[] clave = new int[cantidadNodos];       // Peso minimo hacia el nodo

        Arrays.fill(clave, Integer.MAX_VALUE);
        clave[0] = 0;        // Se comienza desde el nodo 1 (índice 0)
        padre[0] = -1;       // El nodo inicial no tiene padre

        for (int contador = 0; contador < cantidadNodos - 1; contador++) {
            int u = obtenerMinimo(clave, visitado);
            visitado[u] = true;

            // Actualizar claves y padres
            for (int v = 0; v < cantidadNodos; v++) {
                if (grafo[u][v] != 0 && !visitado[v] && grafo[u][v] < clave[v]) {
                    padre[v] = u;
                    clave[v] = grafo[u][v];
                }
            }

            // Prueba de escritorio (valores que se estan generando)
            System.out.println("Paso " + (contador + 1) + ":");
            System.out.println("Nodo añadido al arbol: " + (u + 1));
            System.out.println("Claves: " + Arrays.toString(clave));
            System.out.println("Visitados: " + Arrays.toString(visitado));
            System.out.print("Padres: [");
            for (int i = 0; i < padre.length; i++) {
                System.out.print((padre[i] == -1 ? -1 : padre[i] + 1) + (i < padre.length - 1 ? ", " : ""));
            }
            System.out.println("]");
            System.out.println("----------------------------------");
        }

        // Mostrar el arbol de recubrimiento mínimo 
        System.out.println("Arbol de Recubrimiento Minimo:");
        for (int i = 1; i < cantidadNodos; i++) {
            System.out.println((padre[i] + 1) + " - " + (i + 1) + " peso: " + grafo[i][padre[i]]);
        }
    }

    public static int obtenerMinimo(int[] clave, boolean[] visitado) {
        int minimo = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for (int i = 0; i < clave.length; i++) {
            if (!visitado[i] && clave[i] < minimo) {
                minimo = clave[i];
                indiceMinimo = i;
            }
        }

        return indiceMinimo;
    }
}
