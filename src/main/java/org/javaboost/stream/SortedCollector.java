package org.javaboost.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SortedCollector implements Collector<Employee, Collection<Employee>, ArrayList<Employee>> {

    @Override
    public Supplier<Collection<Employee>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<Collection<Employee>, Employee> accumulator() {
        return Collection::add;
    }

    @Override
    public BinaryOperator<Collection<Employee>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<Collection<Employee>, ArrayList<Employee>> finisher() {
        return ArrayList::new;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
