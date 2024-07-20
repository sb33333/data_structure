package home.data_structure.graph3.graphImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import home.data_structure.graph.Vertex;
import home.data_structure.graph3.Edge;
import home.data_structure.graph3.Graph;

public abstract class AbstractGraph<E extends Edge<T>, T> implements Graph<E, T> {

    private final Set<Vertex<T>> vertices = new HashSet<>();
    private final Set<E> edges = new HashSet<>();

    @Override
    public boolean addEdge(E edge) {
        return edges.add(edge);
    }

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        return vertices.add(vertex);
    }

    @Override
    public void removeEdge(E edge) {
       edges.remove(edge); 
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        removeConnectedEdges(vertex);
        vertices.remove(vertex);
    }

    @Override
    public Set<E> getConnectedEdges(Vertex<T> vertex) {
        return edges.parallelStream()
                .filter(edge -> edge.getAdjacent(vertex).isPresent())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<E> getEdges() {
        return edges.parallelStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Vertex<T>> getVertices() {
        return vertices.parallelStream().collect(Collectors.toSet());
    }

    @Override
    public boolean hasVertex(Vertex<T> vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean isAdjacent(Vertex<T> source, Vertex<T> destination) {
        return edges.parallelStream()
                .map(edge -> edge.getAdjacent(source))
                .filter(Optional::isPresent)
                .anyMatch(vertex ->vertex.get().equals(destination));
    }

    

    private void removeConnectedEdges(Vertex<T> vertex) {
        for (E edge: edges) {
            if(edge.getSource().equals(vertex) || edge.getDestination().equals(vertex)) {
                removeEdge(edge);
            }
        }
    }
}
