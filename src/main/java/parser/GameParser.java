package parser;

import domain.lotto.LottoFactory;
import domain.lotto.WinningLotto;
import domain.money.LottoMoney;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GameParser {

    private static final String DELIMITER = ",";

    public LottoMoney createMoney(String moneyInput) {
        int money = parseInputToInt(moneyInput);
        return new LottoMoney(money);
    }

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

    private int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException("로또 번호는 숫자만 입력 가능합니다.");
        }
    }
}
