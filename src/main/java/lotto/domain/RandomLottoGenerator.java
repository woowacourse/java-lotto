package lotto.domain;

import static lotto.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    private static final int START_INDEX = 0;

    public List<Lotto> generate(final int count) {
        return IntStream.range(0, count)
                .mapToObj(number -> makeRandomNumbers())
                .map(Lotto::new)
                .toList();
    }

    private List<Integer> makeRandomNumbers() {
        final List<Integer> numbers = shuffleLottoNumbers();
        Collections.sort(numbers);
        return numbers;
    }

    private List<Integer> shuffleLottoNumbers() {
        final List<Integer> numberRange = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberRange);
        return numberRange.subList(START_INDEX, Lotto.LOTTO_SIZE);
    }
}
