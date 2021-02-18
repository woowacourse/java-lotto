package lotto.domain;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;
import lotto.domain.result.Result;
import lotto.utils.LottoGenerator;
import lotto.utils.StringValidator;

public class Machine {

    private final int money;

    public Machine(String moneyValue) {
        //todo 도메인 객체가 외부의 유틸 클래스에 의존
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

        return new Result(winningNumbers, lottoTickets, BigInteger.valueOf(money));
    }
}
