package home.data_structure.graph;

import java.util.List;

public interface Path<T> {
    int getLength();
    Vertex<T> getStart();
    Vertex<T> getEnd();
    Path<T> addEdge(Edge<T> edge);
    List<Edge<T>> getVisitedEdges();
    List<Vertex<T>> getVisitedVertices();
    
}