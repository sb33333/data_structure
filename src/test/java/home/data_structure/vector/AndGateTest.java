package home.data_structure.vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static home.data_structure.vector.VectorOperator.*;
import static home.data_structure.vector.VectorOperator.ADD;

public class AndGateTest {

    @BeforeEach
    void setUp () {
        VectorGenerator.setSeed(1L);
    }
    @AfterEach
    void tearDown() {

    }

    final List<Double> zeroVector = VectorGenerator.of(2, 0);
    final Function<List<Double>, Double> answerFunction = (x) -> x.stream().noneMatch(d -> d != 1.0) ? 1.0 : 0.0;

    final Function<Double, Double> stepFunction = (r) -> (r >= 0 ) ? 1.0 : 0.0;

    final List<List<Double>> xInputs = List.of(
            List.of(0.0, 0.0),
            List.of(0.0, 1.0),
            List.of(1.0, 0.0),
            List.of(1.0, 1.0)
    );

    @Test void answerFunctionTest () {
        xInputs.stream().map(answerFunction).forEach(System.out::println);
    }

    @Test
    void test1() {
        final int dimension = 2;
        //
        List<Double> weightVector = VectorGenerator.of(dimension, 0);
        double bias = 0;
        //


        Perceptron outFunction = (weight, input, _bias) -> stepFunction.apply(DOT.apply(weight, input) - _bias);


        Predicate<List<Double>> deltaShouldBeZero = vec -> {

            boolean result = true;
            for (int i=0;i<dimension;i++) {
                if (!result) continue;
                result = vec.get(i).equals(zeroVector.get(i));
            }
            return !result;
        };

        int count = 0;
        while(true) {
//        while(count < 10){
            boolean classified = true;
            List<List<Double>> delta_weight_history = new ArrayList<>();
            List<Double> delta_bias_history = new ArrayList<>();

            for (List<Double> xInput : xInputs) {
                double output = outFunction.apply(xInput, weightVector, bias);
                double answer = answerFunction.apply(xInput);
                List<Double> delta_weight = SCALAR.apply(
                        xInput,
                        answer - output
                );
                double delta_bias = -(answer - output);

                System.out.println(count + "/" + xInput + ":" + answer + "/" + weightVector + ":" + bias + "/" + output + ":" + (answer - output) + "/" + delta_weight + ":" + delta_bias);

                weightVector = ADD.apply(weightVector, delta_weight);
                bias = bias + delta_bias;
                delta_weight_history.add(delta_weight);
                delta_bias_history.add(delta_bias);


            }

            classified =
                    delta_bias_history.stream().noneMatch(db -> db != 0.0)
                    && delta_weight_history.stream().noneMatch(deltaShouldBeZero);
            if (classified) break;
            count++;
        }

        System.out.println(weightVector+":"+bias);
    }
}
