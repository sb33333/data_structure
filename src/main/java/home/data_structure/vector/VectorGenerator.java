package home.data_structure.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class VectorGenerator {
    private static Random random;
    
    static {
        random = new Random();
    }

    public static List<Double> of (int dimension, double r) {
        return Stream.generate(()->r).limit(dimension).toList();
    }
    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    public static List<Double> generateGaussianVector(int dimension) {
        List<Double> result = new ArrayList<>();
        for (int i=0;i<dimension;i++){
            result.add(random.nextGaussian());
        }
        return result.stream().toList();
    }
    
}
