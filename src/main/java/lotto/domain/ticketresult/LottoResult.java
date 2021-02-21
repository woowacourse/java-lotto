package lotto.domain.ticketresult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.type.LottoMatchType;

public class LottoResult {
    private static final int MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE = 3;

    private final WinningTicketAndBonusNumber winningLottoNumbers;
    private final Map<LottoMatchType, Integer> resultCounts;
    private final PurchasePrice purchasePrice;
    private int totalLottoWinningMoney;

    public LottoResult(WinningTicketAndBonusNumber winningLottoNumbers, UserPurchase userPurchase) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.resultCounts = new HashMap<>();
        initializeLottoCounts();
        this.purchasePrice = userPurchase.getPurchasePrice();
        this.totalLottoWinningMoney = 0;
    }

    private void initializeLottoCounts() {
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            resultCounts.put(lottoMatchType, 0);
        }
    }

    public void applyOneTicketResult(LottoTicket lottoTicket) {
        List<LottoNumber> matchedLottoNumbers = winningLottoNumbers.getMatchedLottoNumbers(lottoTicket);
        if (matchedLottoNumbers.size() < MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE) {
            return;
        }
        increaseOneCountOfLottoMatchType(matchedLottoNumbers);
    }

    private void increaseOneCountOfLottoMatchType(List<LottoNumber> matchedLottoNumbersToGetPrize) {
        LottoMatchType lottoMatchType
            = LottoMatchType.getLottoMatchType(matchedLottoNumbersToGetPrize);
        resultCounts
            .computeIfPresent(lottoMatchType, (LottoMatchType key, Integer value) -> ++value);
    }

    public void addAllWinningMoney() {
        for (LottoMatchType lottoMatchType : resultCounts.keySet()) {
            int matchedCount = resultCounts.get(lottoMatchType);
            totalLottoWinningMoney += lottoMatchType.getPrizeMoney() * matchedCount;
        }
    }

    public int getCountOfMatchedNumbersOfSpecificType(LottoMatchType lottoMatchType) {
        return resultCounts.get(lottoMatchType);
    }

    public double getProfit() {
        return (double) totalLottoWinningMoney / (double) purchasePrice.getPurchasePrice();
    }
}
