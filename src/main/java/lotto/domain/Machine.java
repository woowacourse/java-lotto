package lotto.domain;

import java.util.regex.Pattern;
import java.util.List;
import lotto.utils.RandomLottoGenerator;

public class Machine {
    private static final String IS_NUMBER = "\\d+";

    private final LottoTickets lottoTickets;

    public Machine(String moneyValue) {
        validateIsNumber(moneyValue);
        final int money = Integer.parseInt(moneyValue);
        this.lottoTickets = new LottoTickets( money / LottoTicket.PRICE, new RandomLottoGenerator());
    }

    private void validateIsNumber(String moneyValue) {
        if (!Pattern.matches(IS_NUMBER, moneyValue)) {
            throw new RuntimeException();
        }
    }

    public List<LottoTicket> getTickets(){
        return lottoTickets.getLottoTickets();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersValue, bonusBallValue);
        return new Result(winningNumbers, this.lottoTickets);
    }

}
