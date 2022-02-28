package domain;

import static constant.LottoConstant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int DEFAULT_VALUE = 0;
    private static final int INCREASE_VALUE = 1;

    private final LottoNumberStrategy lottoNumberStrategy;

    public LottoMachine(LottoNumberStrategy lottoNumberStrategy) {
        this.lottoNumberStrategy = lottoNumberStrategy;
    }

    public List<LottoTicket> purchaseLottoTicketsByManual(List<List<Integer>> lottoTicketNumbers) {
        return lottoTicketNumbers.stream()
            .map(numbers -> numbers.stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList()))
            .map(LottoTicket::new)
            .collect(Collectors.toList());
    }

    public List<LottoTicket> purchaseLottoTicketsByAuto(Money amount) {
        validateInsertAmount(amount);
        return IntStream.range(DEFAULT_VALUE, amount.getPurchasableNumber(LOTTO_TICKET_PRICE))
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
        for (LottoRank rank : LottoRank.values()) {
            ranks.put(rank, DEFAULT_VALUE);
        }

        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.merge(lottoTicket.rank(winningNumbers, bonusNumber), INCREASE_VALUE, Integer::sum);
        }

        return new WinningStat(ranks);
    }
}
