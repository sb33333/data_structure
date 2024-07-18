package home.data_structure.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphImpl<T> implements Graph<T> {
    private final Set<Vertex<T>> vertices = new HashSet<>();
    private final Set<AbstractEdge<T>> edges = new HashSet<>();
    private final boolean directed;
    
    public GraphImpl(boolean directed) {
        this.directed = directed;
    }
    @Override
    public boolean isDirected() {
        return this.directed;
    }
    @Override
    public boolean addVertex(Vertex<T> vertex) {
        return this.vertices.add(vertex);
    }
    @Override
    public Edge<T> addEdge(Vertex<T> source, Vertex<T> destination) {
        if(!hasVertex(source)) addVertex(source);
        if(!hasVertex(destination)) addVertex(destination);
        AbstractEdge<T> newEdge = createNewEdge(source, destination);
        edges.add(newEdge);
        return newEdge;
    }
    @Override
    public void removeVertex(Vertex<T> vertex) {
        vertices.remove(vertex);
        removeConnectedEdges(vertex);
    }
    @Override
    public void removeEdge(Edge<T> edge) {
    
        for(Edge<T> _edge:edges) {
            if(_edge == edge) edges.remove(edge);
        }
    }
    @Override
    public Set<Vertex<T>> getVertices() {
        return vertices.parallelStream().collect(Collectors.toSet());
    }
    @Override
    public Set<Edge<T>> getEdges() {
        return edges.parallelStream().collect(Collectors.toSet());
    }
    @Override
    public boolean hasVertex(Vertex<T> vertex) {
        return vertices.contains(vertex);
    }
    @Override
    public boolean isAdjacent(Vertex<T> source, Vertex<T> destination) {
        return edges.parallelStream()
                .map(edge -> edge.getAdjacent(source))
                .filter(Optional::isPresent).anyMatch(vertex ->vertex.get().equals(destination));
    }
    @Override
    public Set<Edge<T>> getConnectedEdges(Vertex<T> vertex) {
        return edges.parallelStream()
                .filter(edge -> edge.getAdjacent(vertex).isPresent())
                .collect(Collectors.toSet());
    }
    protected AbstractEdge<T> createNewEdge(Vertex<T> source, Vertex<T> destination) {
        return EdgeFactory.createEdge(source, destination, isDirected());
    }
    private void removeConnectedEdges(Vertex<T> vertex) {
        for (Edge<T> edge: edges) {
            if(edge.getSource().equals(vertex) || edge.getDestination().equals(vertex)) {
                removeEdge(edge);
            }
        }
    }
}