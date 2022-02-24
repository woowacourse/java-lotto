package lotto.model;

import static java.util.stream.Collectors.toList;
import static lotto.model.Lotto.LOTTO_SIZE;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoGenerator {

    private final List<Integer> numberPool;

    public RandomLottoGenerator(int start, int end) {
        numberPool = createNumberPool(start, end);
    }

    private List<Integer> createNumberPool(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(toList());
    }

    public Lotto createLotto() {
        shuffleNumberPool();
        Queue<Integer> queue = queueFromNumberPool();
        List<Integer> numbers = numbers(queue);
        return new Lotto(numbers);
    }

    private void shuffleNumberPool() {
        Collections.shuffle(numberPool);
    }

    private Queue<Integer> queueFromNumberPool() {
        return new LinkedList<>(numberPool);
    }

    private List<Integer> numbers(Queue<Integer> queue) {
        return IntStream.range(0, LOTTO_SIZE)
            .map(i -> queue.remove())
            .boxed()
            .collect(toList());
    }
}
