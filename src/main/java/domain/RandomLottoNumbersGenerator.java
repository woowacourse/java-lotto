package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import constant.LottoConstant;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(LottoConstant.MINIMUM_LOTTO_NUMBER, LottoConstant.MAXIMUM_LOTTO_NUMBER)
            .boxed()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(lottoNumbers);
        return IntStream.range(0, LottoConstant.LOTTO_NUMBER_SIZE).boxed()
            .map(lottoNumbers::get)
            .sorted()
            .collect(Collectors.toList());
    }
}
