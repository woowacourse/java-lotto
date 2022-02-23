package model;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class RandomDistinctNumberQueue implements NumberQueue {
    private final List<Integer> numberPool;

    public RandomDistinctNumberQueue(int start, int end) {
        numberPool = createNumbers(start, end);
    }

    private List<Integer> createNumbers(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(toList());
    }

    private List<Integer> shuffle(List<Integer> list) {
        Collections.shuffle(list);
        return list;
    }

    @Override
    public Set<Integer> get(int quantity) {
        shuffle(numberPool);
        Queue<Integer> queue = new LinkedList<>(numberPool);
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < quantity; i++) {
            result.add(queue.remove());
        }

        return result;
    }
}
