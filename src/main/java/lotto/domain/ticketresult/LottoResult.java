package lotto.domain.ticketresult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.type.LottoMatchType;

public class LottoResult {
    private static final int MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE = 3;
    private static final int SECOND_DECIMAL_PLACE = 2;

    private final WinningTicketAndBonusNumber winningTicketAndBonusNumber;
    private final Map<LottoMatchType, Integer> resultCounts;
    private final PurchasePrice purchasePrice;
    private long totalMoney;

    public LottoResult(WinningTicketAndBonusNumber winningTicketAndBonusNumber,
        UserPurchase userPurchase) {
        this.winningTicketAndBonusNumber = winningTicketAndBonusNumber;
        this.resultCounts = new HashMap<>();
        initializeLottoCounts();
        this.purchasePrice = userPurchase.getPurchasePrice();
        this.totalMoney = 0;
    }

    private void initializeLottoCounts() {
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            resultCounts.put(lottoMatchType, 0);
        }
    }

    public void calculateResult(LottoTicket lottoTicket) {
        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);
        if (matchedLottoNumbers.size() < MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE) {
            return;
        }
        countOneLottoMatchType(matchedLottoNumbers.getMatchType());
    }

    private void countOneLottoMatchType(LottoMatchType lottoMatchType) {
        resultCounts
            .computeIfPresent(lottoMatchType, (LottoMatchType key, Integer value) -> ++value);
    }

    public void calculateMoney() {
        for (LottoMatchType lottoMatchType : resultCounts.keySet()) {
            int matchedCount = resultCounts.get(lottoMatchType);
            totalMoney += (long) lottoMatchType.getPrizeMoney() * (long) matchedCount;
        }
    }

    public int getMatchTypeCount(LottoMatchType lottoMatchType) {
        return resultCounts.get(lottoMatchType);
    }

    public BigDecimal getProfit() {
        return new BigDecimal(String.valueOf(totalMoney))
            .divide(new BigDecimal(String.valueOf(purchasePrice.getPrice())),
                SECOND_DECIMAL_PLACE, BigDecimal.ROUND_HALF_UP);
    }
}
