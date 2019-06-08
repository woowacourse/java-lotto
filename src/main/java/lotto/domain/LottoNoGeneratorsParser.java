package lotto.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoNoGeneratorsParser {
    public static LottoNoGenerators parse(String input) {
        LottoNoGenerators lottoGenerators = new LottoNoGenerators();
        if (input.isEmpty()) {
            return lottoGenerators;
        }
        for (String manualLotto : input.split("\n")) {
            lottoGenerators.add(parseLottoGenerator(manualLotto));
        }
        return lottoGenerators;
    }

    static LottoNoGenerator parseLottoGenerator(String manualLotto) {
        try {
            return new LottoNoManualGenerator(
                    Arrays.stream(manualLotto.split(", "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 6개와 쉼표로 구성되어야 합니다.");
        }
    }
}
