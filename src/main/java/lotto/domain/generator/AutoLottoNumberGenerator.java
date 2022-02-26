package lotto.domain.generator;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class AutoLottoNumberGenerator implements LottoNumberGenerator {

    private static final int START_INCLUSIVE = 1;
    private static final int END_INCLUSIVE = 45;
    private static final List<Integer> LOTTO_TOTAL_NUMBERS = IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
            .boxed()
            .collect(toList());

    @Override
    public List<Integer> generate(int size) {
        List<Integer> lottoTotalNumbers = new ArrayList<>(LOTTO_TOTAL_NUMBERS);
        Collections.shuffle(lottoTotalNumbers);

        return lottoTotalNumbers.stream()
                .limit(size)
                .collect(toList());
    }
}
