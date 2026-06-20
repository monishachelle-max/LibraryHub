import java.util.Arrays;

public class DijkstraLibrary {

    static final int V = 5;

    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        String[] libraries = {
                "Main Library",
                "CSE Library",
                "AI Library",
                "ECE Library",
                "Mechanical Library"
        };

        System.out.println("Shortest Distances from Main Library:\n");

        for (int i = 0; i < V; i++) {
            System.out.println(libraries[i] + " = " + dist[i] + " km");
        }
    }

    public static void main(String[] args) {

        int graph[][] = {
                { 0, 4, 2, 0, 0 },
                { 4, 0, 0, 5, 0 },
                { 2, 0, 0, 3, 0 },
                { 0, 5, 3, 0, 2 },
                { 0, 0, 0, 2, 0 }
        };

        DijkstraLibrary obj = new DijkstraLibrary();
        obj.dijkstra(graph, 0);
    }
}