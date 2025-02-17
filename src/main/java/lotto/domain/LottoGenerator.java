package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int NUMBER_START_INDEX = 0;

    public List<Lotto> generateLotto(final int count) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final List<Integer> numbers = makeRandomNumbers(LOTTO_SIZE);
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    private List<Integer> makeRandomNumbers(final int size) {
        final List<Integer> numberRange = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberRange);
        final List<Integer> partialNumbers = numberRange.subList(NUMBER_START_INDEX, size);
        return partialNumbers;
    }
}
