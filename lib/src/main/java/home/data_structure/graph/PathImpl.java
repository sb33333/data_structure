package home.data_structure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathImpl<T> implements Path<T> {
    private final Vertex<T> start;
    private final List<Edge<T>> edges;
    public PathImpl(Vertex<T> start) {
        this.start = start;
        edges = new ArrayList<>();
        
    }
    public PathImpl(Vertex<T> start, List<Edge<T>> edges) {
        this.start = start;
        this.edges = new ArrayList<>(edges);
    }
    @Override
    public int getLength() {
        return edges.size();
    }
    @Override
    public Vertex<T> getStart() {
        return this.start;
    }
    @Override
    public Vertex<T> getEnd() {
        return edges.stream().reduce(
            start,
            (vertex, edge) -> edge.getAdjacent(vertex).get(),
            (before, after) -> after
        );
    }
    @Override
    public Path<T> addEdge(Edge<T> edge) {
        List<Edge<T>> copied = getVisitedEdges();
        copied.add(edge);
        return new PathImpl<T>(this.start, copied);
    }
    @Override
    public List<Edge<T>> getVisitedEdges() {
        return edges.stream().collect(Collectors.toList());
    }
    @Override
    public List<Vertex<T>> getVisitedVertices() {
        List<Vertex<T>> visited = new ArrayList<>();
        visited.add(start);
        edges.stream().reduce(
            start,
            (vertex, edge) -> {
                Vertex<T> next = edge.getAdjacent(vertex).get();
                visited.add(next);
                return next;
            },
            (before, after) -> after
        );
        return visited;
    }
    @Override
    public String toString() {
        return "[start=" +start+", edges="+edges+"]";
    }
}