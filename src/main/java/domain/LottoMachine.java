package domain;

import domain.strategy.LottoNumberStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int DEFAULT_VALUE = 0;
    private static final int INCREASE_VALUE = 1;

    public List<LottoTicket> purchaseLottoTickets(Money money, List<List<Integer>> lottoNumbers,
                                                  LottoNumberStrategy lottoNumberStrategy) {
        int autoCount = money.getPurchasableNumber() - lottoNumbers.size();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(purchaseManually(lottoNumbers));
        lottoTickets.addAll(purchaseAutomatically(autoCount, lottoNumberStrategy));

        return lottoTickets;
    }

    private List<LottoTicket> purchaseAutomatically(int count, LottoNumberStrategy strategy) {
        return IntStream.range(0, count)
                .mapToObj(index -> new LottoTicket(strategy.generate()))
                .collect(Collectors.toList());
    }

    private List<LottoTicket> purchaseManually(List<List<Integer>> ticketsNumbers) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (List<Integer> ticketNumbers : ticketsNumbers) {
            lottoTickets.add(new LottoTicket(ticketNumbers.stream()
                    .map(LottoNumber::getInstance)
                    .collect(Collectors.toList())));
        }

        return lottoTickets;
    }

    public WinningStat createWinningStat(List<LottoTicket> lottoTickets,
                                         LottoTicketNumbers winningNumbers,
                                         LottoNumber bonusNumber) {
        Map<LottoRank, Integer> ranks = new HashMap<>();

        for (LottoRank lottoRank : LottoRank.values()) {
            ranks.put(lottoRank, DEFAULT_VALUE);
        }

        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.merge(lottoTicket.rank(winningNumbers, bonusNumber), INCREASE_VALUE, Integer::sum);
        }

        return new WinningStat(ranks);
    }

    public List<LottoTicket> purchaseLottoTicketsManually(List<List<Integer>> ticketsInput) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (List<Integer> ticket : ticketsInput) {
            lottoTickets.add(new LottoTicket(ticket.stream()
                    .map(LottoNumber::getInstance)
                    .collect(Collectors.toList())));
        }

        return lottoTickets;
    }
}
