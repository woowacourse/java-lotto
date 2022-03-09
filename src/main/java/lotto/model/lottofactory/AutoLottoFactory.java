package lotto.model.lottofactory;

import static java.util.Collections.*;
import static lotto.model.LottoNumber.*;
import static lotto.model.lottofactory.Lotto.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.model.LottoNumber;

public class AutoLottoFactory implements LottoFactory {

    @Override
    public Lotto generate() {
        return new Lotto(toLottoNumbers(generateLottoIntegers()));
    }

    private List<Integer> generateLottoIntegers() {
        List<Integer> sequentialIntegers = generateSequentialIntegers();
        shuffle(sequentialIntegers);
        return sequentialIntegers.subList(0, LOTTO_SIZE);
    }

    private List<Integer> generateSequentialIntegers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }

    private Set<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toUnmodifiableSet());
    }
}
