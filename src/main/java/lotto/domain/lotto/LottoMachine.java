package lotto.domain.lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.utils.LottoGenerator;

public class LottoMachine {

    private final Money money;

    public LottoMachine(Money money) {
        this.money = money;
    }

    public List<LottoTicket> buyTickets(LottoGenerator lottoGenerator) {
        return IntStream.range(0, getTicketCount()).boxed()
                .map(ignored -> lottoGenerator.generateLottoTicket())
                .collect(Collectors.toList());
    }

    private int getTicketCount() {
        return money.toBigInteger().intValue() / LottoTicket.PRICE;
    }
}
