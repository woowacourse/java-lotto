package domain;

import domain.strategy.LottoNumberStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int DEFAULT_VALUE = 0;
    private static final int INCREASE_VALUE = 1;

    public List<LottoTicket> purchaseLottoTickets(List<List<Integer>> lottoNumbers,
                                                  PurchaseType purchaseType,
                                                  LottoNumberStrategy lottoNumberStrategy) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(purchaseManually(lottoNumbers));
        lottoTickets.addAll(purchaseAutomatically(purchaseType, lottoNumberStrategy));

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

    private List<LottoTicket> purchaseAutomatically(PurchaseType type, LottoNumberStrategy strategy) {
        return IntStream.range(0, type.getAutomaticCount())
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
}
