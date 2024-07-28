package home.data_structure.vector;

import java.util.List;

@FunctionalInterface
public interface Perceptron {

    double apply(List<Double> weightVector, List<Double> inputVector, double bias);
    
}
