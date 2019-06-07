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

    @Override
    public List<Integer> generate() {
        return toNumbers(numbersText);
    }

    private static List<Integer> toNumbers(String originText) {
        List<Integer> numbers = parse(originText).stream()
                .filter(x -> !x.isEmpty())
                .map(x -> Integer.parseInt(x.trim()))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(numbers);
    }

    private static List<String> parse(String originText) {
        return Arrays.asList(originText.split(DELIMITER));
    }
}
