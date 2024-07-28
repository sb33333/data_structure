package home.data_structure.vector;

import static home.data_structure.vector.VectorOperator.ADD;
import static home.data_structure.vector.VectorOperator.DOT;
import static home.data_structure.vector.VectorOperator.SCALAR;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class NormalDistributionTest {

    @BeforeEach
    void setUp () {
        VectorGenerator.setSeed(2L);
        System.out.println("set seed");
        xInputs = Stream
                .concat(
                        Stream.generate(()-> VectorGenerator.generateGaussianVector(dimension)).limit(10)
                        , Stream.generate(()-> VectorGenerator.generateGaussianVector(dimension)).map(v -> ADD.apply(v, meanVector)).limit(10))
                .toList();
    }
    @AfterEach
    void tearDown() {

    }

    final int dimension = 2;
    final int numberOfData = 10;
    final List<Double> meanVector = VectorGenerator.of(dimension, 5);
    List<List<Double>> xInputs;

    final Function<Integer, Integer> answerFunction = (index) -> {
        if (index < numberOfData) return 0;
        else return 1;
    };
    @Test void answerFunctionTest() {
        for (int i=0;i<xInputs.size();i++) {
            System.out.println(answerFunction.apply(i));
        }
    }
    final Function<Double, Double> stepFunction = (r) -> (r >= 0.0 ) ? 1.0 : 0.0;
    @Test void stepFunctionTest () {
        List<Double> d = List.of(0.0, 1.0, -1.0, -0.1);
        d.stream().map(stepFunction).forEach(System.out::println);
    }
    final Perceptron outFunction = (weight, input, _bias) -> stepFunction.apply(DOT.apply(weight, input) + _bias);


    @Test
    void test1() {
        List<Double> weightVector = VectorGenerator.of(dimension, 0);
        double bias = 0;

        Predicate<List<Double>> check = vec -> vec.stream().noneMatch(r -> r.equals(0.0) || r.equals(-0.0));

        final int xInputSize = xInputs.size();
        int count = 0;
        while(true) {
//        while(count < 100){
            boolean classified = true;
            List<List<Double>> delta_weight_history = new ArrayList<>();
            List<Double> delta_bias_history = new ArrayList<>();

            for (int i=0;i<xInputSize;i++) {

                double output = outFunction.apply(xInputs.get(i), weightVector, bias);
                int answer = answerFunction.apply(i);
                List<Double> delta_weight = SCALAR.apply(
                    xInputs.get(i),
                    answer - output
                );
                double delta_bias = answer - output;

                System.out.println(count+"/"+xInputs.get(i)+":"+answer+"/"+weightVector+":"+bias+"/"+output+":"+(answer-output)+"/"+delta_weight+":"+delta_bias);

                weightVector = ADD.apply(weightVector, delta_weight);
                bias = bias + delta_bias;
                delta_weight_history.add(delta_weight);
                delta_bias_history.add(delta_bias);


            }

            classified =
                delta_bias_history.stream().noneMatch(db -> db != 0.0)
                && delta_weight_history.stream().noneMatch(check);

            if (classified) break;
            count++;
        }

        System.out.println(weightVector +":" + bias);
        Assertions.assertTrue(
                outFunction.apply(weightVector, VectorGenerator.of(2, 0), bias) == 0
                && outFunction.apply(weightVector, VectorGenerator.of(2, 5), bias) == 1
        );

    }


    @Test void noneMathTest() {
        List<Double> doubles1 = List.of(-0.0, 0.0, 0.1);
        List<Double> doubles2 = List.of(-0.0, 0.0, 0.0);
        boolean result1 = doubles1.stream().noneMatch(d -> d!=0.0);
        boolean result2 = doubles2.stream().noneMatch(d -> d!=0.0);
        System.out.println(result1+":"+result2);
    }
    @Test void noneMathTest2() {
        Predicate<List<Double>> check = vec -> {
            List<Double> zero = VectorGenerator.of(2, 0.0);
            boolean result = true;
            for (int i=0;i<2;i++) {
                if (!result) break;

                result = Double.compare(vec.get(i), zero.get(i)) == 0;
                System.out.println(result);
            }

            return result;
        };
        List<List<Double>> vectors = List.of(
                List.of(0.0, -0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0),
                List.of(0.0, 0.0)

        );

        boolean result1 = vectors.stream().noneMatch(check);

        System.out.println(result1+":");
    }

    @Test void doubleValue () {
        Double a = -0.0;
        System.out.println(a.doubleValue() == 0.0);
        System.out.println(0.0 == -0.0);
        System.out.println(Double.compare(Double.valueOf(0.0), Double.valueOf(-0.0)) == 0);
    }

}