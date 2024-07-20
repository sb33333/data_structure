package home.data_structure.graph2;


import home.data_structure.graph.Vertex;
import home.data_structure.graph2.edge.DirectedEdge;
import home.data_structure.graph2.edge.UndirectedEdge;
import home.data_structure.graph2.edge.WeightedDirectedEdge;
import home.data_structure.graph2.edge.WeightedUndirectedEdge;

public class EdgeFactory {
    
    static public <T> Edge<T> createEdge(Vertex<T> source, Vertex<T> destination, boolean directed) {
        if(directed) return new DirectedEdge<>(source, destination);
        else return new UndirectedEdge<>(source, destination);
    }
    
    static public <T> Edge<T> createEdge(Vertex<T> source, Vertex<T> destination, boolean directed, double weight) {
        
        if(directed) return new WeightedDirectedEdge<>(source, destination, weight);
        else return new WeightedUndirectedEdge<>(source, destination, weight);
    }
}