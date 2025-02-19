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
    private static final int UNIT_PRICE_OF_LOTTO = 1000;

    public List<Lotto> generate(final int price) {
        validate(price);
        final int count = calculateLottoCount(price);
        return IntStream.range(0, count)
                .mapToObj(number -> makeRandomNumbers())
                .map(HashSet::new)
                .map(Lotto::new)
                .toList();
    }

    public int calculateLottoCount(final int price) {
        return price / UNIT_PRICE_OF_LOTTO;
    }

    private void validate(final int price) {
        if (price < UNIT_PRICE_OF_LOTTO) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 이상이어야 합니다.");
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
