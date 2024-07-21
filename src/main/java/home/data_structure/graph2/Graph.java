package home.data_structure.graph2;

import java.util.Set;

import home.data_structure.graph3.Vertex;

public interface Graph<E extends Edge<T>, T> {

    boolean addVertex(Vertex<T> vertex);
    boolean addEdge(E edge);
    void removeVertex(Vertex<T> vertex);
    void removeEdge(E edge);
    Set<Vertex<T>> getVertices();
    Set<E> getEdges();
    boolean hasVertex(Vertex<T> vertex);
    boolean isAdjacent(Vertex<T> source, Vertex<T> destination);
    Set<E> getConnectedEdges(Vertex<T> vertex);
    
}
