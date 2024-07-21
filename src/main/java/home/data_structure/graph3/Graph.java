package home.data_structure.graph3;

import java.util.Set;

public interface Graph<T> {

    boolean addVertex(Vertex<T> vertex);
    boolean addEdge(Edge<T> edge);
    void removeVertex(Vertex<T> vertex);
    void removeEdge(Edge<T> edge);
    Set<Vertex<T>> getVertices();
    Set<Edge<T>> getEdges();
    boolean hasVertex(Vertex<T> vertex);
    boolean isAdjacent(Vertex<T> source, Vertex<T> destination);
    Set<Edge<T>> getConnectedEdges(Vertex<T> vertex);
    
}

