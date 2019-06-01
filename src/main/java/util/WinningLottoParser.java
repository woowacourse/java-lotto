package util;

import domain.Lotto;
import domain.LottoFactory;
import domain.WinningLotto;
import domain.Number;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoParser {

    public static WinningLotto parse(String input) {
        List<String> splittedInputs = LottoSplitter.split(input);

        List<Number> numbers = splittedInputs.stream()
                .map(NumberParser::parse)
                .collect(Collectors.toList());

        Lotto lotto = LottoFactory.createLottoFromNumbers(numbers);

        // Todo: 올바른 보너스 볼 필요
        return WinningLotto.of(lotto, Number.from(Number.NUMBER_TO));
    }
}
