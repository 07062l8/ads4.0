import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Map<Vertex<T>, Double> distances;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        super(graph, source);
        this.distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    private void dijkstra() {
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        distances.put(source, 0.0);
        pq.add(new VertexDistance<>(source, 0.0));

        while (!pq.isEmpty()) {
            Vertex<T> current = pq.poll().vertex;
            marked.add(current);

            for (Map.Entry<Vertex<T>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<T> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distances.get(current) + weight;

                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, Double.POSITIVE_INFINITY);
                }

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(new VertexDistance<>(neighbor, newDist));
                }
            }
        }
    }

    private static class VertexDistance<T> implements Comparable<VertexDistance<T>> {
        Vertex<T> vertex;
        double distance;

        VertexDistance(Vertex<T> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistance<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}