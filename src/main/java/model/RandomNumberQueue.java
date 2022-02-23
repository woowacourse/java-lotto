package model;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class RandomNumberQueue implements NumberQueue {
    private final Queue<Integer> queue;

    public RandomNumberQueue(int start, int end) {
        queue = new LinkedList<>(shuffle(createNumbers(start, end)));
    }

    private List<Integer> createNumbers(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(toList());
    }

    private List<Integer> shuffle(List<Integer> list) {
        Collections.shuffle(list);
        return list;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public int get() {
        return queue.remove();
    }
}
