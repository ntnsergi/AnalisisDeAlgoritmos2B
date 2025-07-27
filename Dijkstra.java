import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        int[][] grafo = new int[5][5];

        grafo[0][1] = 10;   // 1->2
        grafo[0][3] = 30;   // 1->4
        grafo[0][4] = 100;  // 1->5
        grafo[1][2] = 50;   // 2->3
        grafo[3][2] = 20;   // 4->3
        grafo[2][4] = 10;   // 3->5
        grafo[3][4] = 60;   // 4->5
        

        int origen = 0; // Nodo 1
        int[] previos = new int[5];
        int[] distancias = dijkstra(grafo, origen, previos);

        System.out.println("Distancias minimas desde el nodo 1:");
        for (int i = 0; i < distancias.length; i++) {
            System.out.print("Hasta nodo " + (i + 1) + ": ");
            if (distancias[i] == Integer.MAX_VALUE) {
                System.out.println("Inalcanzable");
            } else {
                System.out.println(distancias[i] + " mediante camino: ");
                imprimirCamino(previos, i);
                System.out.println();
            }
        }
    }

    public static int[] dijkstra(int[][] grafo, int origen, int[] previos) {
        int n = grafo.length;
        int[] distancias = new int[n];
        boolean[] visitado = new boolean[n];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(previos, -1);
        distancias[origen] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = obtenerMinimo(distancias, visitado);
            if (u == -1) break;
            visitado[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitado[v] && grafo[u][v] != 0 && distancias[u] + grafo[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + grafo[u][v];
                    previos[v] = u;
                }
            }
        }

        return distancias;
    }

    private static int obtenerMinimo(int[] distancias, boolean[] visitado) {
        int min = Integer.MAX_VALUE, indice = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (!visitado[i] && distancias[i] < min) {
                min = distancias[i];
                indice = i;
            }
        }
        return indice;
    }

    private static void imprimirCamino(int[] previos, int destino) {
        if (destino == -1) return;
        Stack<Integer> pila = new Stack<>();
        for (int at = destino; at != -1; at = previos[at]) {
            pila.push(at);
        }
        while (!pila.isEmpty()) {
            System.out.print((pila.pop() + 1));
            if (!pila.isEmpty()) System.out.print(" -> ");
        }
    }
}
