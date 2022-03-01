package domain.generator;

import domain.Lotto;
import domain.LottoNumber;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_UNIT_TO_CORRECT = 1;

    private final List<Integer> numbers;

    public AutoLottoGenerator() {
        this.numbers = IntStream.rangeClosed(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)
            .boxed()
            .collect(Collectors.toList());
    }

    @Override
    public Lotto generate() {
        Collections.shuffle(numbers);
        return new Lotto(numbers.stream()
            .limit(LOTTO_SIZE)
            .map(LottoNumber::new)
            .collect(Collectors.toList()));
    }
}

