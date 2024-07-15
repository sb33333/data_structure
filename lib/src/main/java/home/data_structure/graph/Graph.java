package home.data_structure.graph;

import java.util.Set;

public interface Graph<T> {
    
    // check if the graph is directed or not.
    boolean isDirected();
    //adds a vertex(node)
    boolean addVertex(Vertex<T> vertex);
    // adds an edge(connecteion between two nodes)
    Edge<T> addEdge(Vertex<T> source, Vertex<T> destination);
    // removes a specific vertex
    void removeVertex(Vertex<T> vertex);
    // removes a specific edge.
    void removeEdge(Edge<T> edge);
    // returns all vertices in the graph
    Set<Vertex<T>> getVertices();
    
    Set<Edge<T>> getEdges();
    
    // checks if a specific vertex exists in the graph
    boolean hasVertex(Vertex<T> vertex);
    
    // checks if two vertices are adjacent.
    boolean isAdjacent(Vertex<T> source, Vertex<T> destination);
    
    // find edges connected to a vertex.
    Set<Edge<T>> getConnectedEdges(Vertex<T> vertex);
}