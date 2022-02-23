package model;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        return new LottoNumbers(List.of(sixNumbers.get(0), sixNumbers.get(1), sixNumbers.get(2), sixNumbers.get(3),
                sixNumbers.get(4), sixNumbers.get(5)));
    }

    private List<Integer> nextSixNumbers(Queue<Integer> queue) {
        return IntStream.range(0, 6)
                .map(i -> queue.remove())
                .boxed()
                .collect(toList());
    }

    private Queue<Integer> createQueueByNumberPool() {
        Queue<Integer> queue = new LinkedList<>(numberPool);
        return queue;
    }

    private void shuffleNumberPool() {
        Collections.shuffle(numberPool);
    }
}
