package lotto.domain.ticketresult;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoTicket;
import lotto.type.LottoMatchType;

public class LottoResult {
    private static final int ZERO = 0;
    private static final int MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE = 3;
    private static final int FIVE_MATCHED_SIZE = 2;
    private static final int ONE_COUNT = 1;

    private final WinningLottoNumbers winningLottoNumbers;
    private final Map<LottoMatchType, Integer> resultCounts;

    public LottoResult(WinningLottoNumbers winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.resultCounts = new HashMap<>();
        initializeLottoCounts();
    }

    private void initializeLottoCounts() {
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            resultCounts.put(lottoMatchType, ZERO);
        }
    }

    public void applyOneTicketResult(LottoTicket purchasedOneLottoTicket) {
        int countMatchedNumbers
            = getOneLottoTicketNumbersMatchedCountResult(purchasedOneLottoTicket);

        if (countMatchedNumbers < MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE) {
            return;
        }
        increaseOneCountOfLottoMatchType(countMatchedNumbers, purchasedOneLottoTicket);
    }

    private int getOneLottoTicketNumbersMatchedCountResult(LottoTicket purchasedOneLottoTicket) {
        LottoTicket winningLottoTicket = winningLottoNumbers.getWinningTicket();

        return (int) purchasedOneLottoTicket.getLottoTicketNumbers()
            .stream()
            .filter(purchasedLottoNumber -> winningLottoTicket.getLottoTicketNumbers()
                .contains(purchasedLottoNumber))
            .count();
    }

    private void increaseOneCountOfLottoMatchType(int countMatchedNumbers,
        LottoTicket purchasedOneLottoTicket) {

        List<LottoMatchType> lottoMatchTypes = LottoMatchType
            .getLottoMatchType(countMatchedNumbers);

        if (lottoMatchTypes.size() == FIVE_MATCHED_SIZE) {
            handleFiveMatchType(purchasedOneLottoTicket);
            return;
        }
        handleOtherMatchTypes(lottoMatchTypes);
    }

    private void handleFiveMatchType(LottoTicket purchasedOneLottoTicket) {
        if (purchasedOneLottoTicket.hasBonusNumber(winningLottoNumbers.getBonusNumber())) {
            Integer currentMatchedNumbersCount = resultCounts.get(FIVE_AND_BONUS_MATCH);
            resultCounts.put(FIVE_AND_BONUS_MATCH, currentMatchedNumbersCount + ONE_COUNT);
            return;
        }
        Integer currentMatchedNumbersCount = resultCounts.get(FIVE_MATCH);
        resultCounts.put(FIVE_MATCH, currentMatchedNumbersCount + ONE_COUNT);
    }

    private void handleOtherMatchTypes(List<LottoMatchType> lottoMatchTypes) {
        LottoMatchType lottoMatchType = lottoMatchTypes.get(ZERO);
        Integer currentMatchedNumbersCount = resultCounts.get(lottoMatchType);
        resultCounts.put(lottoMatchType, currentMatchedNumbersCount + ONE_COUNT);
    }

    public int getCountOfMatchedNumbersOfSpecificType(LottoMatchType lottoMatchType) {
        return resultCounts.get(lottoMatchType);
    }
}
