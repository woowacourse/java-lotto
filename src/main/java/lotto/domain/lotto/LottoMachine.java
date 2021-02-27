package lotto.domain.lotto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.result.UsersLottoTickets;
import lotto.utils.LottoGenerator;
import lotto.utils.RandomLottoGenerator;

public class LottoMachine {

    private Money money;
    private final ManualBuyAmount manualBuyAmount;
    private final LottoGenerator lottoGenerator;

    private LottoMachine(Money money, ManualBuyAmount manualBuyAmount,
            LottoGenerator lottoGenerator) {
        this.money = money;
        this.manualBuyAmount = manualBuyAmount;
        this.lottoGenerator = lottoGenerator;
    }

    public static LottoMachine getInstance(Money money, ManualBuyAmount manualAmount) {
        return new LottoMachine(money, manualAmount, new RandomLottoGenerator());
    }

    public BigInteger getManualBuyAmount() {
        return manualBuyAmount.getAmount();
    }

    public UsersLottoTickets buyTickets(List<String> manualTicketsInput) {
        List<LottoTicket> manualTickets = convertToLottoTickets(manualTicketsInput);
        List<LottoTicket> autoTickets = getAutoTickets();

        return new UsersLottoTickets(manualTickets, autoTickets);
    }

    private List<LottoTicket> convertToLottoTickets(List<String> manualTicketsInput) {
        List<LottoTicket> manualLottoTickets = manualTicketsInput.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());

        decreaseMoney(manualLottoTickets);
        return manualLottoTickets;
    }

    private List<LottoTicket> getAutoTickets() {
        ArrayList<LottoTicket> lottoTickets = new ArrayList<>();

        for (BigInteger ticketAmount = getAutoTicketAmount();
                ticketAmount.compareTo(BigInteger.ZERO) > 0;
                ticketAmount = ticketAmount.subtract(BigInteger.ONE)) {

            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }

        decreaseMoney(lottoTickets);
        return lottoTickets;
    }

    private void decreaseMoney(List<LottoTicket> manualLottoTickets) {
        money = money.subtract(getPrice(manualLottoTickets));
    }

    private BigInteger getPrice(List<LottoTicket> manualLottoTickets) {
        return LottoTicket.PRICE.multiply(BigInteger.valueOf(manualLottoTickets.size()));
    }

    private BigInteger getAutoTicketAmount() {
        return money.toBigInteger().divide(LottoTicket.PRICE);
    }
}
