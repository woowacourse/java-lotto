package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Lotto;
import domain.LottoNumber;

public class RandomLottoGeneratorStrategy implements LottoGeneratorStrategy {

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(LottoNumber.MINIMUM_VALUE, LottoNumber.MAXIMUM_VALUE)
            .boxed()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);

        final List<LottoNumber> pickedNumbers = new ArrayList<>();
        for (int i = 0; i < Lotto.LOTTO_NUMBER_SIZE; i++) {
            pickedNumbers.add(lottoNumbers.get(i));
        }
        Collections.sort(pickedNumbers);

        return pickedNumbers;
    }
}
