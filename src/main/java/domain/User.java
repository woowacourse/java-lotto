package domain;

import domain.lottonumber.LottoTicket;
import domain.lottonumber.WinningNumbers;
import domain.result.LottoResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {
    private Money spentMoney = new Money(0);
    private List<LottoTicket> lottoTickets = new ArrayList<>();

    public User() {
    }

    public void buyTickets(Money money) {
        this.spentMoney = money;
        this.lottoTickets = LottoStore.generateTickets(money.getNumberOfTickets());
    }

    public void buyTickets(Money money, List<Set<Integer>> myNumbers) {
        this.spentMoney = money;
        this.lottoTickets = LottoStore.generateTickets(money.getNumberOfTickets(), myNumbers);
    }

    public LottoResult confirmResult(WinningNumbers winningNumbers) {
        return LottoResult.confirmResult(this.lottoTickets, winningNumbers);
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public Money getSpentMoney() {
        return this.spentMoney;
    }
}