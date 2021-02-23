package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.result.UsersLottoTickets;
import lotto.utils.LottoGenerator;
import lotto.utils.RandomLottoGenerator;

public class LottoMachine2 {

    private Money money;
    private final LottoGenerator lottoGenerator;

    public LottoMachine2(Money money, LottoGenerator lottoGenerator) {
        this.money = money;
        this.lottoGenerator = lottoGenerator;
    }

    public LottoMachine2(Money money) {
        this(money, new RandomLottoGenerator());
    }

    public UsersLottoTickets buyTickets(List<String> manualTicketsValue) {
        List<LottoTicket> manualTickets = getManualTickets(manualTicketsValue);
        List<LottoTicket> autoTickets = getAutoTickets();

        return new UsersLottoTickets(manualTickets, autoTickets);
    }

    private List<LottoTicket> getManualTickets(List<String> manualTicketsValue) {
        List<LottoTicket> manualLottoTickets = manualTicketsValue.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());

        decreaseMoney(getPrice(manualLottoTickets));

        return manualLottoTickets;
    }

    private BigInteger getPrice(List<LottoTicket> manualLottoTickets) {
        return LottoTicket.PRICE.multiply(BigInteger.valueOf(manualLottoTickets.size()));
    }

    private void decreaseMoney(BigInteger amount) {
        money = money.subtract(amount);
    }

    private List<LottoTicket> getAutoTickets() {
        ArrayList<LottoTicket> lottoTickets = new ArrayList<>();

        for (BigInteger ticketCount = getTicketCount();
                ticketCount.compareTo(BigInteger.ZERO) > 0;
                ticketCount = ticketCount.subtract(BigInteger.ONE)) {

            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }

        return lottoTickets;
    }

    private BigInteger getTicketCount() {
        return money.toBigInteger().divide(LottoTicket.PRICE);
    }
}
