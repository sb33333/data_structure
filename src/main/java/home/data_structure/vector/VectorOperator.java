package home.data_structure.vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class VectorOperator {

    private static final BiPredicate<Collection<?>, Collection<?>> shouldHaveSameSize =(c1, c2) -> c1.size() == c2.size();

    private static final BiFunction<List<Double>, List<Double>, List<Double>> addEachComponent = (l1, l2) -> {
        List<Double> result = new ArrayList<>();
        int size = l1.size();
        for (int i=0;i<size;i++) {
            result.add(l1.get(i) + l2.get(i));
        }
        return result.stream().toList();
    };

    private static final BiFunction<List<Double>, List<Double>, List<Double>> multiplyEachComponent = (l1, l2) -> {
        List<Double> result = new ArrayList<>();
        int size = l1.size();
        for (int i=0;i<size;i++) {
            result.add(l1.get(i) * l2.get(i));
        }
        return result.stream().toList();
    };

    public static BiFunction<List<Double>, List<Double>, Double> DOT = (v1, v2) -> {
        if (!shouldHaveSameSize.test(v1, v2)) throw new IllegalArgumentException("should have same size");
        return multiplyEachComponent.apply(v1, v2).stream().reduce(0.0, Double::sum);
    };

    public static BiFunction<List<Double>, List<Double>, List<Double>> ADD = (v1, v2) -> {
        if (!shouldHaveSameSize.test(v1, v2)) throw new IllegalArgumentException("should have same size");
        return addEachComponent.apply(v1, v2);
    };

    public static BiFunction<List<Double>, Double, List<Double>> SCALAR = (v, r) -> v.stream().map(e -> e*r).toList();

    public static BiFunction<List<Double>, List<Double>, List<Double>> HADAMARD = (v1, v2) -> {
        if (!shouldHaveSameSize.test(v1, v2)) throw new IllegalArgumentException("should have same size");
        return multiplyEachComponent.apply(v1, v2);
    };
}

