package domain.lotto;

import generator.NumberGenerator;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoNumbersFactory {
    private LottoNumbersFactory() {
        throw new AssertionError();
    }

    public static LottoNumbers createLottoNumbers(NumberGenerator numberGenerator) {
        return createLottoNumbers(numberGenerator.create());
    }

    public static LottoNumbers createLottoNumbers(List<Integer> ints) {
        return ints.stream()
                .map(LottoNumberFactory::getInstance)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(TreeSet::new), LottoNumbers::new));
    }
}
