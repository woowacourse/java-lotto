package model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class RandomLottoNumbersGenerator extends LottoNumbersGenerator{
    private final List<Integer> numberPool;

    public RandomLottoNumbersGenerator(int start, int end) {
        numberPool = createNumberPool(start, end);
    }

    private List<Integer> createNumberPool(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(toList());
    }

    protected LottoNumbers createLottoNumbers() {
        shuffleNumberPool();
        List<Integer> sixNumbers = nextSixNumbers(createQueueByNumberPool());
        return LottoNumbers.withSixNumbers(sixNumbers.get(0), sixNumbers.get(1), sixNumbers.get(2),
                sixNumbers.get(3), sixNumbers.get(4), sixNumbers.get(5));
    }

    private List<Integer> nextSixNumbers(Queue<Integer> queue) {
        List<Integer> result = new ArrayList<>();
        IntStream.range(0, 6)
                .forEach(i -> result.add(queue.remove()));
        return result;
    }

    private Queue<Integer> createQueueByNumberPool() {
        Queue<Integer> queue = new LinkedList<>(numberPool);
        return queue;
    }

    private void shuffleNumberPool() {
        Collections.shuffle(numberPool);
    }
}
