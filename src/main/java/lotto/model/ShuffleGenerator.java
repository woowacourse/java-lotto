package lotto.model;

import static java.util.Collections.*;
import static lotto.model.Lotto.*;
import static lotto.model.LottoNumber.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generate() {
        List<Integer> sequentialIntegers = generateSequentialIntegers();
        shuffle(sequentialIntegers);
        List<Integer> lottoNumbers = sequentialIntegers.subList(0, LOTTO_SIZE);
        sort(lottoNumbers);
        return Collections.unmodifiableList(lottoNumbers);
    }

    private List<Integer> generateSequentialIntegers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }
}
