import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<Vertex<T>, Vertex<T>> vertices = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(T v) {
        if (hasVertex(v)) return;
        vertices.put(new Vertex<>(v), new Vertex<>(v));
    }

    public void addEdge(T source, T dest, double weight) {
        Vertex<T> srcVertex = new Vertex<>(source);
        Vertex<T> destVertex = new Vertex<>(dest);

        if (!hasVertex(source)) {
            addVertex(source);
            srcVertex = vertices.get(srcVertex);
        } else {
            srcVertex = vertices.get(srcVertex);
        }

        if (!hasVertex(dest)) {
            addVertex(dest);
            destVertex = vertices.get(destVertex);
        } else {
            destVertex = vertices.get(destVertex);
        }

        if (hasEdge(source, dest) || source.equals(dest)) return;

        srcVertex.addAdjacentVertex(destVertex, weight);
        if (undirected) {
            destVertex.addAdjacentVertex(srcVertex, weight);
        }
    }

    public boolean hasVertex(T v) {
        return vertices.containsKey(new Vertex<>(v));
    }

    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source)) return false;
        Vertex<T> srcVertex = vertices.get(new Vertex<>(source));
        Vertex<T> destVertex = new Vertex<>(dest);
        return srcVertex.getAdjacentVertices().containsKey(destVertex);
    }

    public List<Vertex<T>> adjacencyList(T v) {
        if (!hasVertex(v)) return null;
        Vertex<T> vertex = vertices.get(new Vertex<>(v));
        return new ArrayList<>(vertex.getAdjacentVertices().keySet());
    }

    public double getWeight(T source, T dest) {
        Vertex<T> srcVertex = vertices.get(new Vertex<>(source));
        Vertex<T> destVertex = new Vertex<>(dest);
        return srcVertex.getAdjacentVertices().get(destVertex);
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> v : vertices.values()) {
            count += v.getAdjacentVertices().size();
        }
        if (undirected) count /= 2;
        return count;
    }
    public Vertex<T> getVertex(T data) {
        return vertices.get(new Vertex<>(data));
    }
}