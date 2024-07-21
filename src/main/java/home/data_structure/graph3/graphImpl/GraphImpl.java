package home.data_structure.graph3.graphImpl;

import home.data_structure.graph3.Graph;

public class GraphImpl<T> extends AbstractGraph<T> {

    public GraphImpl () {

    }

    public GraphImpl (Graph<T> graph) {
        graph.getEdges().forEach(this::addEdge);
        graph.getVertices().forEach(this::addVertex);
    }

}
