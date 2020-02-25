package lotto.parser;

import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.WinningLotto;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GameParser {

    private static final String DELIMITER = ",";

    public WinningLotto createWinningLotto(String winningLottoInput, String bonusNumberInput) {
        Set<Integer> numbers = parseInputToNumbers(winningLottoInput);
        Integer bonusNumber = parseInputToInt(bonusNumberInput);
        return LottoFactory.publishWinningLotto(numbers, bonusNumber);
    }

    private Set<Integer> parseInputToNumbers(String winningLottoInput) {
        return Arrays.stream(winningLottoInput.split(DELIMITER))
                .map(String::trim)
                .map(this::parseInputToInt)
                .collect(Collectors.toSet());
    }

    public int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException("숫자만 입력해주세요");
        }
    }
}
