package lotto.domain.ticketresult;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.type.LottoMatchType;

public class LottoResult {
    private static final int MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE = 3;
    private static final int FIVE_MATCHED_SIZE = 2;

    private final WinningLottoNumbers winningLottoNumbers;
    private final Map<LottoMatchType, Integer> resultCounts;
    private final int purchasePrice;
    private int totalLottoWinningMoney;

    public LottoResult(WinningLottoNumbers winningLottoNumbers, UserPurchase userPurchase) {
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
        int countMatchedNumbers = getOneLottoTicketNumbersMatchedCountResult(lottoTicket);

        if (countMatchedNumbers < MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE) {
            return;
        }
        increaseOneCountOfLottoMatchType(countMatchedNumbers, lottoTicket);
    }

    private int getOneLottoTicketNumbersMatchedCountResult(LottoTicket lottoTicket) {
        LottoTicket winningLottoTicket = winningLottoNumbers.getWinningTicket();

        return (int) lottoTicket.getLottoTicketNumbers()
            .stream()
            .filter(lottoNumber -> winningLottoTicket.getLottoTicketNumbers()
                .contains(lottoNumber))
            .count();
    }

    private void increaseOneCountOfLottoMatchType(int countMatchedNumbers, LottoTicket lottoTicket) {

        List<LottoMatchType> lottoMatchTypes = LottoMatchType.getLottoMatchType(countMatchedNumbers);

        if (lottoMatchTypes.size() == FIVE_MATCHED_SIZE) {
            handleFiveMatchType(lottoTicket);
            return;
        }
        handleOtherMatchTypes(lottoMatchTypes);
    }

    private void handleFiveMatchType(LottoTicket lottoTicket) {
        if (lottoTicket.hasBonusNumber(winningLottoNumbers.getBonusNumber())) {
            Integer currentMatchedNumbersCount = resultCounts.get(FIVE_AND_BONUS_MATCH);
            resultCounts.put(FIVE_AND_BONUS_MATCH, currentMatchedNumbersCount + 1);
            totalLottoWinningMoney += FIVE_AND_BONUS_MATCH.getPrizeMoney();
            return;
        }
        Integer currentMatchedNumbersCount = resultCounts.get(FIVE_MATCH);
        resultCounts.put(FIVE_MATCH, currentMatchedNumbersCount + 1);
        totalLottoWinningMoney += FIVE_MATCH.getPrizeMoney();
    }

    private void handleOtherMatchTypes(List<LottoMatchType> lottoMatchTypes) {
        LottoMatchType lottoMatchType = lottoMatchTypes.get(0);
        Integer currentMatchedNumbersCount = resultCounts.get(lottoMatchType);
        resultCounts.put(lottoMatchType, currentMatchedNumbersCount + 1);
        totalLottoWinningMoney += lottoMatchType.getPrizeMoney();
    }

    public int getCountOfMatchedNumbersOfSpecificType(LottoMatchType lottoMatchType) {
        return resultCounts.get(lottoMatchType);
    }

    public double getProfit() {
        return (double) totalLottoWinningMoney / (double) purchasePrice;
    }
}
