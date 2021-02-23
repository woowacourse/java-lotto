package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.number.Payout;
import lotto.domain.rank.Ranks;

public class LottoExchange {

    private static final int LOTTO_PRICE = 1000;
    private static final LottoExchange LOTTO_EXCHANGE = new LottoExchange();

    private LottoExchange() {
    }

    public static LottoExchange getInstance() {
        return LOTTO_EXCHANGE;
    }

    public LottoTicket buyLottoTicket(Payout payout, List<LottoNumbers> manualLottos) {
        validatePossibleOrder(payout, manualLottos.size());
        List<LottoNumbers> lottoTicket = new ArrayList<>(manualLottos);
        int autoCount = payout.getNumberOfStuff(LOTTO_PRICE) - manualLottos.size();

        Stream.generate(LottoNumbers::valueOf)
            .limit(autoCount)
            .forEach(lottoTicket::add);
        return new LottoTicket(lottoTicket);
    }

    private void validatePossibleOrder(Payout payout, int manualCount) {
        int possibleLottoTicketCount = payout.getNumberOfStuff(LOTTO_PRICE);

        if (manualCount > possibleLottoTicketCount) {
            throw new IllegalArgumentException("금액보다 수동 입력의 개수가 너무 많습니다.");
        }
    }

    public double calculateYield(Ranks ranks) {
        Long triedPrice = ranks.getRanksCount() * LOTTO_PRICE;
        return (double) ranks.getTotalWinnings() / triedPrice;
    }
}
