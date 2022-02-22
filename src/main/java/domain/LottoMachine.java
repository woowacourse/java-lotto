package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoMachine {

    private static final String INVALID_INSERT_AMOUNT = "금액은 1000원 이상이어야 합니다.";
    private static final int LOTTO_TICKET_PRICE = 1000;

    private LottoTickets lottoTickets;

    public LottoTickets purchaseLottoTickets(Money amount, NumberGenerator numberGenerator) {
        validateInsertAmount(amount);
        int size = amount.getPurchasableNumber(LOTTO_TICKET_PRICE);

        List<LottoTicket> purchasedTickets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            purchasedTickets.add(new LottoTicket(numberGenerator.generate()));
        }

        lottoTickets = new LottoTickets(purchasedTickets);
        return lottoTickets;
    }

    private void validateInsertAmount(Money amount) {
        if (!amount.isPurchasable(LOTTO_TICKET_PRICE)) {
            throw new IllegalArgumentException(INVALID_INSERT_AMOUNT);
        }
    }

    public WinningStat createWinningStat(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> ranks = lottoTickets.createRanks(winningNumbers, bonusNumber);
        return new WinningStat(ranks);
    }
}
