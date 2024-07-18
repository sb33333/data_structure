package home.data_structure.graph;

import java.util.Optional;

/**
 * The Edge interface represents an edge (connection) between two vertices in a graph.
 *
 * @param <T> The type of the value stored in the vertices.
 */
public interface Edge<T> {
     
    /**
     * Returns the source vertex of the edge.
     *
     * @return The source vertex.
     */
    Vertex<T> getSource();
     /**
     * Returns the destination vertex of the edge.
     *
     * @return The destination vertex.
     */
    Vertex<T> getDestination();
     /**
     * Checks if the edge is directed.
     *
     * @return true if the edge is directed, false otherwise.
     */
    boolean isDirected();
    /**
     * Checks if the edge is a loop (i.e., the source and destination vertices are the same).
     *
     * @return true if the edge is a loop, false otherwise.
     */
    boolean isLoop();
    /**
     * Returns the vertex adjacent to the specified vertex along this edge.
     *
     * @param vertex The vertex to find the adjacent vertex for.
     * @return An Optional containing the adjacent vertex if it exists, or an empty Optional if the specified vertex is not part of the edge.
     */
    Optional<Vertex<T>> getAdjacent(Vertex<T> vertex);
}