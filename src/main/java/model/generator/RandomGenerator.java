package model.generator;

import static java.util.stream.Collectors.toList;
import static model.Lotto.LOTTO_NUMBER_SIZE;
import static model.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static model.LottoNumber.MINIMUM_LOTTO_NUMBER;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Lotto;

public class RandomGenerator implements LottosGenerator {

    private final List<Integer> numberPool;
    private final int lottoCount;

    public RandomGenerator(int lottoCount) {
        this.lottoCount = lottoCount;
        numberPool = createNumberPool();
    }

    private List<Integer> createNumberPool() {
        return IntStream
                .rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .boxed()
                .collect(toList());
    }

    @Override
    public List<Lotto> createLottos() {
        return IntStream.range(0, lottoCount)
                .mapToObj(i -> createLotto())
                .collect(toList());
    }

    private Lotto createLotto() {
        Collections.shuffle(numberPool);
        return Lotto.of(getNumbers(new LinkedList<>(numberPool)));
    }

    private List<Integer> getNumbers(Queue<Integer> queue) {
        return IntStream.range(0, LOTTO_NUMBER_SIZE)
                .map(i -> queue.remove())
                .boxed()
                .collect(Collectors.toList());
    }
}