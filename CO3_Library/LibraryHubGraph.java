import java.util.*;

public class LibraryHubGraph {

    int vertices = 5;

    LinkedList<Integer>[] adjList;

    LibraryHubGraph() {

        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {

            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {

        adjList[v].add(w);
    }

    void BFS(int start) {

        boolean visited[] = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;

        queue.add(start);

        System.out.println("BFS Traversal:");

        while (!queue.isEmpty()) {

            start = queue.poll();

            System.out.print(start + " ");

            for (int neighbor : adjList[start]) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;

                    queue.add(neighbor);
                }
            }
        }
    }

    void DFS(int start) {

        boolean visited[] = new boolean[vertices];

        System.out.println("\nDFS Traversal:");

        DFSUtil(start, visited);
    }

    void DFSUtil(int vertex, boolean visited[]) {

        visited[vertex] = true;

        System.out.print(vertex + " ");

        for (int neighbor : adjList[vertex]) {

            if (!visited[neighbor]) {

                DFSUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        LibraryHubGraph g = new LibraryHubGraph();

        //0 = DSA
        //1 = Java
        //2 = DBMS
        //3 = AI
        //4 = Networks

        g.addEdge(0, 1);//DSA -> Java
        g.addEdge(0, 2);//DSA -> DBMS
        g.addEdge(1, 3);//Java -> AI
        g.addEdge(2, 4);//DBMS -> Networks

        g.BFS(0);

        g.DFS(0);
    }
}