package home.data_structure.graph3;

import java.util.Set;


/**
 * The Graph interface represents a generic graph structure with vertices and edges.
 * It provides methods to add and remove vertices and edges, as well as to query the
 * graph for various properties and relationships between its components.
 * 
 * @param <T> The type of the data held by the vertices in the graph.
 */
public interface Graph<T> {

    /**
     * Adds a vertex to the graph.
     * @param vertex The vertex to be added.
     * @return true if the vertex was successfully added, false otherwise.
     */
    boolean addVertex(Vertex<T> vertex);

    /**
     * Adds an edge to the graph.
     * @param edge The edge to be added.
     * @return true if the edge was successfully added, false otherwise.
     */
    boolean addEdge(Edge<T> edge);

    /**
     * Removes a vertex from the graph.
     * @param vertex The vertex to be removed.
     */
    void removeVertex(Vertex<T> vertex);

    /**
     * Removes an edge from the graph.
     * @param edge The edge to be removed.
     */
    void removeEdge(Edge<T> edge);

    /**
     * Retrieves the set of all vertices in the graph.
     * @return A set containing all vertices in the graph.
     */
    Set<Vertex<T>> getVertices();

    /**
     * Retrieves the set of all edges in the graph.
     * @return A set containing all edges in the graph.
     */
    Set<Edge<T>> getEdges();

    /**
     * Checks if the graph contains a specific vertex.
     * @param vertex The vertex to check for.
     * @return true if the vertex is in the graph, false otherwise.
     */
    boolean hasVertex(Vertex<T> vertex);

    /**
     * Checks if two vertices are adjacent (i.e., directly connected by an edge).
     * @param source The source vertex.
     * @param destination The destination vertex.
     * @return true if the vertices are adjacent, false otherwise.
     */
    boolean isAdjacent(Vertex<T> source, Vertex<T> destination);

    /**
     * Retrieves the set of edges connected to a specific vertex.
     * @param vertex The vertex for which to get the connected edges.
     * @return A set of edges connected to the specified vertex.
     */
    Set<Edge<T>> getConnectedEdges(Vertex<T> vertex);
    
}

