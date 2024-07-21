package home.data_structure.graph3.path;


import home.data_structure.graph3.Edge;
import home.data_structure.graph3.Path;
import home.data_structure.graph3.Vertex;

import java.util.ArrayList;
import java.util.List;

public class PathImpl<T> implements Path<T> {

    protected final Vertex<T> start;
    protected final List<Edge<T>> edges;

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
        return new PathImpl<>(this.start, copied);
    }
    @Override
    public List<Edge<T>> getVisitedEdges() {
        return new ArrayList<>(edges);
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