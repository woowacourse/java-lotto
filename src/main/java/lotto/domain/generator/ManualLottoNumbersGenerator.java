package lotto.domain.generator;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.exception.NotRegisteredNumbersException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ManualLottoNumbersGenerator implements LottoNumbersGenerator {
    private static final ManualLottoNumbersGenerator INSTANCE = new ManualLottoNumbersGenerator();
    private static final String SPLIT_REGEX = ",";

    private String numbers;

    private ManualLottoNumbersGenerator() {
        clear();
    }

    public static ManualLottoNumbersGenerator getInstance(String numbers) {
        if (numbers == null) {
            throw new NotRegisteredNumbersException();
        }
        INSTANCE.numbers = numbers;
        return INSTANCE;
    }

    private void clear() {
        numbers = null;
    }

    @Override
    public LottoNumbers generate() {
        try {
            return new LottoNumbers(Arrays.stream(numbers.split(SPLIT_REGEX))
                    .map(number -> Integer.parseInt(number.trim()))
                    .map(LottoNumber::valueOf)
                    .collect(Collectors.toList()));
        } finally {
            clear();
        }
    }
}
