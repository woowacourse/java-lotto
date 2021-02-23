package lotto.domain.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.stream.Stream;
import lotto.domain.number.ManualCount;
import lotto.domain.number.Payout;
import lotto.domain.rank.Ranks;

public class LottoExchange {

    private static final int LOTTO_PRICE = 1000;
    private static final RandomLottoGenerator RANDOM_LOTTO_GENERATOR = RandomLottoGenerator
        .getInstance();
    private static final LottoExchange LOTTO_EXCHANGE = new LottoExchange();

    private LottoExchange() {
    }

    public static LottoExchange getInstance() {
        return LOTTO_EXCHANGE;
    }

    public LottoTicket buyLottoTicket(Payout payout, ManualCount manualCount) {
        validatePossibleOrder(payout, manualCount);
        int autoCount = payout.getNumberOfStuff(LOTTO_PRICE) - manualCount.unwrap();

        return Stream.generate(RANDOM_LOTTO_GENERATOR::nextLottoNumbers)
            .limit(autoCount)
            .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    private void validatePossibleOrder(Payout payout, ManualCount manualCount) {
        int possibleLottoTicketCount = payout.getNumberOfStuff(LOTTO_PRICE);

        if (manualCount.isBiggerThan(possibleLottoTicketCount)) {
            throw new IllegalArgumentException("금액보다 수동 입력의 개수가 너무 많습니다.");
        }
    }

    public double calculateYield(Ranks ranks) {
        Long triedPrice = ranks.getRanksCount() * LOTTO_PRICE;
        return (double) ranks.getTotalWinnings() / triedPrice;
    }
}
