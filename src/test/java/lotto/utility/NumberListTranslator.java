package lotto.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Number;

public class NumberListTranslator {

    private static final String DELIMITER = ", ";

    public static List<Number> parseToWinner(String exampleLotto) {
        String[] splittedWinningNumbers = exampleLotto.split(DELIMITER);
        return Arrays.stream(splittedWinningNumbers)
            .map(String::trim)
            .map(element -> Number.from(Integer.parseInt(element)))
            .collect(Collectors.toList());
    }

    public static List<Number> translateIntToNumber(List<Integer> candidateIntegers) {
        return candidateIntegers.stream()
            .map(Number::from)
            .collect(Collectors.toList());
    }

    public static List<Number> translateStringToNumber(List<String> candidateStrings) {
        return candidateStrings.stream()
            .map(element -> Number.from(Integer.parseInt(element)))
            .collect(Collectors.toList());
    }
}
