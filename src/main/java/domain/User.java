package domain;

import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class User {

    private Money spentMoney;
    private List<Ticket> tickets;
    private Map<LottoResult, Long> lottoResults;

    public User() {
        this.spentMoney = new Money(0);
        this.tickets = new ArrayList<>();
    }

    public void buyTickets(Money money) {
        this.spentMoney = money;
        this.tickets = LottoStore.generateTickets(money.getNumberOfTickets());
    }

    public void buyTicketsManually(Money money, List<Set<Integer>> myNumbers) {
        this.spentMoney = money;
        this.tickets = LottoStore.generateTickets(money.getNumberOfTickets(), myNumbers);
    }

    public Map<LottoResult, Long> confirmResult(WinningNumbers winningNumbers) {
        this.lottoResults = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> winningNumbers.getLottoResult(ticket), Collectors.counting()));
        return lottoResults;
    }

    public LottoProfit calculateProfit() {
        return LottoProfit.getProfit(this.lottoResults, this.spentMoney);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}