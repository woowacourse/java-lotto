package model.generator;

import static java.util.stream.Collectors.toList;
import static model.Lotto.LOTTO_NUMBER_SIZE;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Lotto;
import model.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;
    private final List<Integer> numberPool;

    public RandomLottoGenerator() {
        numberPool = createNumberPool();
    }

    private List<Integer> createNumberPool() {
        return IntStream
                .rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(toList());
    }

    public Lotto createLotto() {
        Collections.shuffle(numberPool);
        List<LottoNumber> numbers = getLottoNumbersFrom(new LinkedList<>(numberPool));
        return Lotto.of(numbers);
    }

    private List<LottoNumber> getLottoNumbersFrom(Queue<Integer> queue) {
        return LottoNumber.convertAll(getNumbers(queue));
    }

    private List<Integer> getNumbers(Queue<Integer> queue) {
        return IntStream.range(0, LOTTO_NUMBER_SIZE)
                .map(i -> queue.remove())
                .boxed()
                .collect(Collectors.toList());
    }
}
