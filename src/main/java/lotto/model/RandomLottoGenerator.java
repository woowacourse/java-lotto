package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;
import static lotto.model.Lotto.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoGenerator {

    private final List<Integer> numberPool;

    public RandomLottoGenerator(int lowerBound, int upperBound) {
        numberPool = createNumberPool(lowerBound, upperBound);
    }

    private List<Integer> createNumberPool(int lowerBound, int upperBound) {
        return IntStream.rangeClosed(lowerBound, upperBound)
            .boxed()
            .collect(toUnmodifiableList());
    }

    @Override
    public Lotto createLotto() {
        List<Integer> shuffledList = shuffleNumberList();
        Queue<Integer> queue = queueFromShuffledList(shuffledList);
        List<Integer> numbers = numbers(queue);
        return Lotto.create(numbers);
    }

    private List<Integer> shuffleNumberList() {
        List<Integer> listForShuffle = new ArrayList<>(numberPool);
        Collections.shuffle(listForShuffle);
        return listForShuffle;
    }

    private Queue<Integer> queueFromShuffledList(List<Integer> shuffledList) {
        return new LinkedList<>(shuffledList);
    }

    private List<Integer> numbers(Queue<Integer> queue) {
        return IntStream.range(0, LOTTO_SIZE)
            .map(i -> queue.remove())
            .boxed()
            .collect(toUnmodifiableList());
    }
}
