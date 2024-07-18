package home.data_structure.graph;

public class EdgeFactory {
    
    static public <T> AbstractEdge<T> createEdge(Vertex<T> source, Vertex<T> destination, boolean directed) {
        if(directed) return new DirectedEdge<>(source, destination);
        else return new UndirectedEdge<>(source, destination);
    }
    
    static public <T> AbstractWeightedEdge<T> createWeightedEdge(Vertex<T> source, Vertex<T> destination, boolean directed, double weight) {
        return null; // not implemented.
    }
}