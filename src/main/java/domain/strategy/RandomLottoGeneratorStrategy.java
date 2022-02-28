package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.LottoNumber;
import domain.constant.LottoConstant;

public class RandomLottoGeneratorStrategy implements LottoGeneratorStrategy {

    private static final List<LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LottoConstant.MINIMUM_VALUE, LottoConstant.MAXIMUM_VALUE)
            .boxed()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(LOTTO_NUMBERS);

        final List<LottoNumber> pickedNumbers = new ArrayList<>();
        for (int i = 0; i < LottoConstant.LOTTO_NUMBER_SIZE; i++) {
            pickedNumbers.add(LOTTO_NUMBERS.get(i));
        }
        Collections.sort(pickedNumbers);

        return pickedNumbers;
    }
}
