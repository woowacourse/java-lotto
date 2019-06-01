package util;

import domain.Lotto;
import domain.LottoFactory;
import domain.WinningLotto;
import domain.Number;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoParser {
    private static final String INTEGER_SEPARATOR = ",";

    public static WinningLotto parse(String input) {
        List<String> splittedInputs = Arrays.asList(input.split(INTEGER_SEPARATOR));
        List<String> trimedInputs = splittedInputs.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        List<Number> numbers = trimedInputs.stream()
                .map(Integer::parseInt)
                .map(Number::from)
                .collect(Collectors.toList());

        Lotto lotto = LottoFactory.createLottoFromNumbers(numbers);

        // Todo: 올바른 보너스 볼 필요
        return WinningLotto.of(lotto, Number.from(Number.NUMBER_TO));
    }
}
