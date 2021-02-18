package lotto.domain;

import java.util.regex.Pattern;
import java.util.List;
import lotto.utils.CustomException;
import lotto.utils.RandomLottoGenerator;

public class Machine {
    private final LottoTickets lottoTickets;

    public Machine(String moneyValue) {
        final Money money = new Money(moneyValue);
        this.lottoTickets = new LottoTickets( money.getPossibleTicketCount(), new RandomLottoGenerator());
    }

    public List<LottoTicket> getTickets(){
        return lottoTickets.getLottoTickets();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersValue, bonusBallValue);
        return new Result(winningNumbers, this.lottoTickets);
    }

}
