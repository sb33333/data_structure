package home.data_structure.graph;

public class VertexImpl<T> implements Vertex<T> {
    private final T value;
    public VertexImpl(T value) {
        super();
        this.value = value;
    }
    public static <T> VertexImpl<T> of (T value) {
        return new VertexImpl<> (value);
    }
    @Override
    public T getValue() {return this.value;}
    @Override
    public String toString(){return "/"+value.toString()+"/";}
}