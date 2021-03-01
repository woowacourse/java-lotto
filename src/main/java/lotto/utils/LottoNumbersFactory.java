package lotto.utils;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersFactory {

    private LottoNumbersFactory() {
    }

    public static LottoNumbers createLottoNumbers(String numbers) throws IllegalArgumentException {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers.split(","))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }
}
