package home.data_structure.graph;

/**
 * The Vertex interface represents a vertex (or node) in a graph.
 * 
 * @param <T> The type of the value stored in this vertex.
 */
public interface Vertex<T> {
    /**
     * @return the value stored in this vertex.
     */ 
    T getValue();
}