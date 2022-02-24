package model;

import static java.util.stream.Collectors.toList;
import static model.LottoNumbers.LOTTO_NUMBER_SIZE;

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

    public LottoNumbers createLottoNumbers() {
        shuffleNumberPool();
        List<Integer> numbers = getNumbersFrom(createQueueByNumberPool());
        return new LottoNumbers(numbers);
    }

    private List<Integer> getNumbersFrom(Queue<Integer> queue) {
        return IntStream.range(0, LOTTO_NUMBER_SIZE)
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
