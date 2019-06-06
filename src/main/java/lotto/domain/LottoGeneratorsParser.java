package lotto.domain;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LottoGeneratorsParser {
    static final Pattern LOTTO_MANUAL_PATTERN = Pattern.compile("^[0-9]+(,\\s[0-9]+){5}$");

    public static LottoGenerators parse(String input) {
        LottoGenerators lottoGenerators = new LottoGenerators();
        if (input.isEmpty()) {
            return lottoGenerators;
        }
        for (String manualLotto : input.split("\n")) {
            lottoGenerators.add(parseLottoGenerator(manualLotto));
        }
        return lottoGenerators;
    }

    static LottoGenerator parseLottoGenerator(String manualLotto) {
        try {
            return new LottoManualGenerator(
                    Arrays.stream(manualLotto.split(", "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 6개와 쉼표로 구성되어야 합니다.");
        }
    }
}
