package lotto.model.numbergenerator;

import static java.util.Collections.*;
import static lotto.model.Lotto.*;
import static lotto.model.LottoNumber.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleGenerator implements LottoNumberGenerator {

    @Override
    public Set<Integer> generate() {
        List<Integer> sequentialIntegers = generateSequentialIntegers();
        shuffle(sequentialIntegers);
        List<Integer> lottoNumbers = sequentialIntegers.subList(0, LOTTO_SIZE);
        sort(lottoNumbers);
        return Set.copyOf(lottoNumbers);
    }

    private List<Integer> generateSequentialIntegers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }
}
