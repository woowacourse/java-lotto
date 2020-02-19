package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersFactory {
    public static LottoNumbers createLottoNumbers(NumberGenerator numberGenerator) {
        List<Integer> numbers = numberGenerator.create();
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoNumbers::new));
    }
}
