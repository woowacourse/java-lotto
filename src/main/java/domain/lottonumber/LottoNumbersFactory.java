package domain.lottonumber;

import domain.lottonumber.generator.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersFactory {
    private LottoNumbersFactory() {
        throw new AssertionError();
    }

    public static LottoNumbers createLottoNumbers(NumberGenerator numberGenerator) {
        return new LottoNumbers(numberGenerator.create());
    }

    public static LottoNumbers createLottoNumbers(List<Integer> ints) {
        return ints.stream()
                .map(LottoNumber::of)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoNumbers::new));
    }
}
