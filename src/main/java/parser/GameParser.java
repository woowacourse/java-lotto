package parser;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import domain.lotto.LottoNumber;
import domain.lotto.LottoTicket;
import domain.lotto.WinningLotto;
import domain.money.LottoMoney;

public class GameParser {

    public LottoMoney createMoney(String moneyInput) {
        int money = parseInputToInt(moneyInput);
        return new LottoMoney(money);
    }

    public WinningLotto createWinningLotto(String winningLottoInput, String bonusNumberInput) {
        LottoTicket winningLottoTicket = parseInputToLottoTicket(winningLottoInput);
        LottoNumber bonusLottoNumber = parseInputToLottoNumber(bonusNumberInput);
        return new WinningLotto(winningLottoTicket, bonusLottoNumber);
    }

    private LottoTicket parseInputToLottoTicket(String winningLottoInput) {
        return new LottoTicket(parseInputToLottoNumbers(winningLottoInput));
    }

    private Set<LottoNumber> parseInputToLottoNumbers(String winningLottoInput) {
        return Arrays.stream(winningLottoInput.split(","))
                .map(this::parseInputToLottoNumber)
                .collect(Collectors.toSet());
    }

    private LottoNumber parseInputToLottoNumber(String input) {
        return new LottoNumber(parseInputToInt(input));
    }

    private int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException();
        }
    }
}
