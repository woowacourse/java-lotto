package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.utils.LottoGenerator;
import lotto.utils.StringValidator;

public class Machine {

    private final int money;

    public Machine(String moneyValue) {
        StringValidator.validateIsDigit(moneyValue);
        money = Integer.parseInt(moneyValue);
    }

    public List<LottoTicket> buyTickets(LottoGenerator lottoGenerator) {
        return IntStream.range(0, getTicketCount()).boxed()
                .map(ignored -> lottoGenerator.generateLottoTicket())
                .collect(Collectors.toList());
    }

    private int getTicketCount() {
        return money / LottoTicket.PRICE;
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue,
            List<LottoTicket> lottoTickets) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersValue, bonusBallValue);

        return new Result(winningNumbers, lottoTickets, money);
    }
}
