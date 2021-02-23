package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lotto.utils.LottoGenerator;

public class LottoMachine2 {

    private final Money money;

    public LottoMachine2(Money money) {
        this.money = money;
    }

    public List<LottoTicket> buyTickets(LottoGenerator lottoGenerator) {
        ArrayList<LottoTicket> lottoTickets = new ArrayList<>();

        for (BigInteger bi = getTicketCount();
                bi.compareTo(BigInteger.ZERO) > 0;
                bi = bi.subtract(BigInteger.ONE)) {

            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }

        return lottoTickets;
    }

    private BigInteger getTicketCount() {
        return money.toBigInteger().divide(LottoTicket.PRICE);
    }
}
