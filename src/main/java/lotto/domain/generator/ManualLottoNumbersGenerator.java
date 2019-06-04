package lotto.domain.generator;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.exception.NotRegisteredNumbersException;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoNumbersGenerator implements LottoNumbersGenerator {
    private static final ManualLottoNumbersGenerator INSTANCE = new ManualLottoNumbersGenerator();

    private List<Integer> numbers;

    private ManualLottoNumbersGenerator() {
        clear();
    }

    public static ManualLottoNumbersGenerator getInstance(List<Integer> numbers) {
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
            return new LottoNumbers(numbers.stream()
                    .map(LottoNumber::valueOf)
                    .collect(Collectors.toList()));
        } finally {
            clear();
        }
    }
}
