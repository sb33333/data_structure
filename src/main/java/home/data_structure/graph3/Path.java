package home.data_structure.graph3;

import java.util.List;

/**
 * This interface represents a path in a graph. A path is a sequence of edges connecting vertices.
 *
 * @param <T> the type of the element contained in the vertices and edges.
 */
public interface Path<T> {
    /**
     * Returns the length of the path. The length is typically defined as the number of edges in the path.
     *
     * @return the length of the path.
     */
    int getLength();
    /**
     * Returns the starting vertex of the path.
     *
     * @return the starting vertex of the path.
     */
    Vertex<T> getStart();
    /**
     * Returns the ending vertex of the path.
     *
     * @return the ending vertex of the path.
     */
    Vertex<T> getEnd();
    /**
     * Adds an edge to the path and returns the updated path.
     *
     * @param edge the edge to be added to the path.
     * @return the updated path with the new edge added.
     */

    Path<T> addEdge(Edge<T> edge);
    /**
     * Returns the list of edges that have been visited in the path.
     *
     * @return the list of visited edges in the path.
     */
    List<Edge<T>> getVisitedEdges();
    /**
     * Returns the list of vertices that have been visited in the path.
     *
     * @return the list of visited vertices in the path.
     */
    List<Vertex<T>> getVisitedVertices();
    
}