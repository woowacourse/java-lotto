package lotto.domain.lotto.LottoStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoStrategy implements LottoStrategy {
    private static final String DELIMITER = ",";

    private final String numbersText;

    public ManualLottoStrategy(String numbersText) {
        this.numbersText = numbersText;
    }

    private static List<String> parse(String originText) {
        return Arrays.asList(originText.split(DELIMITER));
    }

    @Override
    public List<Integer> generate() {
        return Collections.unmodifiableList(parse(numbersText).stream()
                .map(String::trim)
                .filter(x -> !x.isEmpty() || !x.isBlank())
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList())
        );
    }
}
