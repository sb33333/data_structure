package home.data_structure.graph;

import java.util.Optional;

public class UndirectedEdge<T> extends AbstractEdge<T> {
    
    public UndirectedEdge(Vertex<T> source, Vertex<T> destination) {
        super(source, destination, false);
    }
    @Override
    public Optional <Vertex<T>> getAdjacent(Vertex<T> vertex) {
        if(vertex.equals(source)) return Optional.of(this.destination);
        else if (vertex.equals(destination)) return Optional.of(this.source);
        else return Optional.empty();
    }
    @Override
    public String toString(){
        return "@"+this.hashCode()+":"+source+"-"+destination;
    }
}