import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<T> extends Search<T> {
    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        super(graph, source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, T source) {
        Vertex<T> sourceVertex = graph.getVertex(source);
        marked.add(sourceVertex);

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();

            for (Vertex<T> neighbor : v.getAdjacentVertices().keySet()) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, v);
                    queue.add(neighbor);
                }
            }
        }
    }
}