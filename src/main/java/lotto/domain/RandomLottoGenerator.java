package lotto.domain;

import static lotto.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int START_INDEX = 0;

    public List<Lotto> generate(final int count) {
        validate(count);
        return IntStream.range(0, count)
                .mapToObj(number -> makeRandomNumbers())
                .map(HashSet::new)
                .map(Lotto::new)
                .toList();
    }

    private void validate(final int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("생성할 로또의 개수는 양수여야합니다.");
        }
    }

    private List<Integer> makeRandomNumbers() {
        final List<Integer> numberRange = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberRange);
        return numberRange.subList(START_INDEX, Lotto.LOTTO_SIZE);
    }
}
