package lotto.domain.generator;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersGenerator {
    public static final String INPUT_LOTTO_DELIMITER = ", ";

    public static List<LottoNumber> generateLottoNumbers(final String inputLottoNumbers) {
        return Arrays.stream(inputLottoNumbers.split(INPUT_LOTTO_DELIMITER))
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }
}
