package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Tickets {
    private final List<Ticket> tickets;

    public Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static Tickets of(int count, LottoNumbersGenerator lottoNumbersGenerator) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(lottoNumbersGenerator));
        }
        return new Tickets(tickets);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Map<Rank, Integer> getResult(WinningNumbers winningNumbers) {
        List<Rank> ranks = getRanks(winningNumbers);
        Map<Rank, Integer> result = new TreeMap<>(Comparator.comparingInt(Rank::getAmount));
        for (Rank rank : ranks) {
            result.merge(rank, 1, (value, putValue) -> value + 1);
        }
        return result;
    }

    private List<Rank> getRanks(WinningNumbers winningNumbers) {
        return tickets.stream()
                .map(winningNumbers::getTicketRank)
                .collect(Collectors.toList());
    }

    public double getYield(Amount amount, WinningNumbers winningNumbers) {
        List<Rank> ranks = getRanks(winningNumbers);
        long profit = ranks.stream()
                .mapToLong(Rank::getAmount)
                .sum();
        return Math.floor((amount.getYield(profit) * 100)) / 100.0;
    }
}
