package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.utils.LottoGenerator;

public class Machine {
    private final Money money;
    private final LottoTickets lottoTickets;

    public Machine(String moneyValue, LottoGenerator lottoGenerator) {
        this(moneyValue, new ArrayList<>(), lottoGenerator);
    }

    public Machine(String moneyValue, List<String> lottoNumbers, LottoGenerator lottoGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (String lottoNumber : lottoNumbers) {
            lottoTickets.add(new LottoTicket(lottoNumber));
        }
        this.money = new Money(moneyValue, lottoTickets);
        this.lottoTickets = new LottoTickets(lottoTickets, money.getPossibleTicketCount(),
            lottoGenerator);
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets.getLottoTickets();
    }

    public int getChange() {
        return money.getChange();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        return new Result(winningNumbersValue, bonusBallValue, lottoTickets);
    }


}
