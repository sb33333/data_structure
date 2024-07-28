package home.data_structure;

import static home.data_structure.vector.VectorOperator.ADD;
import static home.data_structure.vector.VectorOperator.DOT;
import static home.data_structure.vector.VectorOperator.SCALAR;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import home.data_structure.vector.Perceptron;
import home.data_structure.vector.VectorGenerator;

public class VectorTest {

    @BeforeEach
    void setUp () {
        VectorGenerator.setSeed(1L);
    }
    @AfterEach
    void tearDown() {

    }
    @Test
    void test1() {
        final int dimension = 2;
        final int numberOfData = 10;
        final int mean = 5;
        final List<Double> meanVector = VectorGenerator.of(dimension, mean);
        final List<List<Double>> vectors1 = Stream
                .generate(()-> VectorGenerator.generateGaussianVector(dimension))
                .limit(10)
                .toList();
        final List<List<Double>> vectors2 = Stream
                .generate(()-> VectorGenerator.generateGaussianVector(dimension))
                .map(v -> ADD.apply(v, meanVector))
                .limit(10)
                .toList();

        final List<List<Double>> xInputs = Stream.concat(vectors1.stream(), vectors2.stream()).toList();


        List<Double> weightVector = VectorGenerator.of(dimension, 0);
        double bias = 0;

        final Function<Double, Double> stepFunction = (r) -> {
            return (r >= 0 ) ? 1.0 : 0.0;
        };

        Perceptron outFunction = (weight, input, _bias) -> {
            return stepFunction.apply(DOT.apply(weight, input)) + _bias;
        };

        Function<Integer, Integer> answerFunction = (index) -> {
            if (index < numberOfData) return 0;
            else return 1;
        };

        Predicate<List<Double>> check = vec -> {
            List<Double> zero = VectorGenerator.of(dimension, 0.0);
            boolean result = true;
            for (int i=0;i<dimension;i++) {
                if (!result) continue;
                result = vec.get(i) == zero.get(i);
            }
            return !result;
        };

        boolean classified = true;
        while(true) {
            classified = true;
            List<List<Double>> delta_weight_history = new ArrayList<>();
            List<Double> delta_bias_history = new ArrayList<>();
            
            for (int i=0;i<numberOfData*2;i++) {
                List<Double> delta_weight = SCALAR.apply(
                    xInputs.get(i), 
                    answerFunction.apply(i) - outFunction.apply(xInputs.get(i), weightVector, bias)
                );
                double delta_bias = answerFunction.apply(i) - outFunction.apply(xInputs.get(i), weightVector, bias);
                weightVector = ADD.apply(weightVector, delta_weight);
                bias = bias + delta_bias;
                delta_weight_history.add(delta_weight);
                delta_bias_history.add(delta_bias);
            }
            
            classified = 
                delta_bias_history.stream().noneMatch(db -> db != 0.0) 
                && delta_weight_history.stream().noneMatch(dw -> check.test(dw));
            if (classified) break;
            else continue;
        }

        System.out.println(weightVector);
    }

}
