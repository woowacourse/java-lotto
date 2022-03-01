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
    private final List<Integer> numberPool;

    public RandomLottoGenerator(int start, int end) {
        numberPool = createNumberPool(start, end);
    }

    private List<Integer> createNumberPool(int start, int end) {
        return IntStream
                .rangeClosed(start, end)
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
