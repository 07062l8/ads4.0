import java.util.*;

public class Search<T> {
    protected Set<Vertex<T>> marked;
    protected Map<Vertex<T>, Vertex<T>> edgeTo;
    protected final Vertex<T> source;

    public Search(WeightedGraph<T> graph, T source) {
        this.source = graph.getVertex(source);  // Теперь работает!
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(T v) {
        return marked.contains(new Vertex<>(v));
    }

    public Iterable<T> pathTo(T v) {
        Vertex<T> vertex = new Vertex<>(v);
        if (!marked.contains(vertex)) return null;

        LinkedList<T> path = new LinkedList<>();
        for (Vertex<T> i = vertex; !i.equals(source); i = edgeTo.get(i)) {
            path.push(i.getData());
        }
        path.push(source.getData());

        return path;
    }
}