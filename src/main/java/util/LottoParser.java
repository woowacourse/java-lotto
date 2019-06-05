package util;

import domain.Lotto;
import domain.LottoFactory;
import domain.Number;

import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {
    public static Lotto parse(String input) {
        List<String> splittedInputs = LottoSplitter.split(input);

        List<Number> numbers = splittedInputs.stream()
                .map(NumberParser::parse)
                .collect(Collectors.toList());

        return LottoFactory.createLottoFromNumbers(numbers);
    }
}
