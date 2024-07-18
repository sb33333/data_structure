package home.data_structure.graph;

import java.util.Set;

/**
 * The Graph interface represents a graph data structure consisting of vertices (nodes) and edges (connections between nodes).
 *
 * @param <T> The type of the value stored in the vertices.
 */
public interface Graph<T> {
    
    /**
     * Checks if the graph is directed.
     * @return true if the graph is directed, false otherwise.
     */
    boolean isDirected();
    
    /**
     * Adds a vertex (node) to the graph.
     * @param vertex The vertex to be added.
     * @return true if the vertex was added successfully, false otherwise.
     */
    boolean addVertex(Vertex<T> vertex);
    
    /**
     * Adds an edge (connection) between two vertices in the graph.
     * @param source The source vertex.
     * @param destination The destination vertex.
     * @return The edge created between the source and destination vertices.
     */
    Edge<T> addEdge(Vertex<T> source, Vertex<T> destination);
     
    /**
     * Removes a specific vertex from the graph.
     * @param vertex The vertex to be removed.
     */
    void removeVertex(Vertex<T> vertex);
     
    /**
     * Removes a specific edge from the graph.
     * @param edge The edge to be removed.
     */
    void removeEdge(Edge<T> edge);
     
    /**
     * Returns all vertices in the graph.
     * @return A set containing all vertices in the graph.
     */
    Set<Vertex<T>> getVertices();
    
    /**
     * Returns all edges in the graph.
     * @return A set containing all edges in the graph.
     */
    Set<Edge<T>> getEdges();
    
      
    /**
     * Checks if a specific vertex exists in the graph.
     * @param vertex The vertex to check.
     * @return true if the vertex exists in the graph, false otherwise.
     */
    boolean hasVertex(Vertex<T> vertex);
    
     /**
     * Checks if two vertices are adjacent (connected directly by an edge).
     *
     * @param source The source vertex.
     * @param destination The destination vertex.
     * @return true if the vertices are adjacent, false otherwise.
     */
    boolean isAdjacent(Vertex<T> source, Vertex<T> destination);
    
     /**
     * Finds all edges connected to a specific vertex.
     *
     * @param vertex The vertex to find connected edges for.
     * @return A set containing all edges connected to the specified vertex.
     */
    Set<Edge<T>> getConnectedEdges(Vertex<T> vertex);
}