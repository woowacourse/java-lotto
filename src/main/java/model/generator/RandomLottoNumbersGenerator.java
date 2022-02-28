package model.generator;

import static java.util.stream.Collectors.toList;
import static model.LottoNumbers.LOTTO_NUMBER_SIZE;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import model.LottoNumbers;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {
    private final List<Integer> numberPool;

    public RandomLottoNumbersGenerator(int start, int end) {
        numberPool = createNumberPool(start, end);
    }

    private List<Integer> createNumberPool(int start, int end) {
        return IntStream
                .rangeClosed(start, end)
                .boxed()
                .collect(toList());
    }

    public LottoNumbers createLottoNumbers() {
        Collections.shuffle(numberPool);
        List<Integer> numbers = getNumbersFrom(new LinkedList<>(numberPool));
        return LottoNumbers.of(numbers);
    }

    private List<Integer> getNumbersFrom(Queue<Integer> queue) {
        return IntStream.range(0, LOTTO_NUMBER_SIZE)
                .map(i -> queue.remove())
                .boxed()
                .collect(toList());
    }
}
