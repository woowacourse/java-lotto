package domain;

import domain.strategy.LottoNumberStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoMachine {

    private static final String INVALID_INSERT_AMOUNT = "금액은 1000원 이상이어야 합니다.";
    private static final int DEFAULT_VALUE = 0;
    private static final int INCREASE_VALUE = 1;

    public List<LottoTicket> purchaseLottoTickets(Money amount, LottoNumberStrategy lottoNumberStrategy) {
        validateInsertAmount(amount);
        int size = amount.getPurchasableNumber(LOTTO_TICKET_PRICE);

        return IntStream.range(0, size)
                .mapToObj(index -> new LottoTicket(lottoNumberStrategy.generate()))
                .collect(Collectors.toList());
    }

    private void validateInsertAmount(Money amount) {
        if (!amount.isPurchasable(LOTTO_TICKET_PRICE)) {
            throw new IllegalArgumentException(INVALID_INSERT_AMOUNT);
        }
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
