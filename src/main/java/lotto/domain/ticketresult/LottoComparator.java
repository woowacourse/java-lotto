package lotto.domain.ticketresult;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.type.LottoMatchType;

public class LottoComparator {
    private static final int ZERO = 0;
    private static final int ONE_COUNT = 1;
    private static final int FIVE_MATCHED_SIZE = 2;

    private final WinningLottoNumbers winningLottoNumbers;
    private final Map<LottoMatchType, Integer> lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.lottoResult = new HashMap<>();
        initializeLottoResult();
    }

    private void initializeLottoResult() {
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            lottoResult.put(lottoMatchType, ZERO);
        }
    }

    public Map<LottoMatchType, Integer> getLottoResult(PurchasedLottoTickets purchasedLottoTickets) {
        for (LottoTicket purchasedOneLottoTicket : purchasedLottoTickets.getTickets()) {
            int countMatchedNumbers
                = getEachLottoResult(purchasedOneLottoTicket,
                winningLottoNumbers.getWinningTicket());
            addResult(countMatchedNumbers, purchasedOneLottoTicket);
        }
        return Collections.unmodifiableMap(new HashMap<>(lottoResult));
    }

    private int getEachLottoResult(LottoTicket purchasedOneLottoTicket,
        LottoTicket winningLottoTicket) {

        return (int) purchasedOneLottoTicket.getLottoTicketNumbers()
            .stream()
            .filter(purchasedLottoNumber -> winningLottoTicket.getLottoTicketNumbers()
                .contains(purchasedLottoNumber))
            .count();
    }

    private void addResult(int countMatchedNumbers, LottoTicket purchasedOneLottoTicket) {
        List<LottoMatchType> lottoMatchTypes = LottoMatchType
            .getLottoMatchType(countMatchedNumbers);
        if (lottoMatchTypes.size() == ZERO) {
            return;
        }
        if (lottoMatchTypes.size() == FIVE_MATCHED_SIZE) {
            handleFiveMatchType(purchasedOneLottoTicket);
            return;
        }
        handleOtherMatchTypes(lottoMatchTypes);
    }

    private void handleFiveMatchType(LottoTicket purchasedOneLottoTicket) {
        if (purchasedOneLottoTicket.hasBonusNumber(winningLottoNumbers.getBonusNumber())) {
            Integer currentMatchedNumbersCount = lottoResult.get(FIVE_AND_BONUS_MATCH);
            lottoResult.put(FIVE_AND_BONUS_MATCH, currentMatchedNumbersCount + ONE_COUNT);
            return;
        }
        Integer currentMatchedNumbersCount = lottoResult.get(FIVE_MATCH);
        lottoResult.put(FIVE_MATCH, currentMatchedNumbersCount + ONE_COUNT);
    }

    private void handleOtherMatchTypes(List<LottoMatchType> lottoMatchTypes) {
        LottoMatchType lottoMatchType = lottoMatchTypes.get(ZERO);
        Integer currentMatchedNumbersCount = lottoResult.get(lottoMatchType);
        lottoResult.put(lottoMatchType, currentMatchedNumbersCount + ONE_COUNT);
    }
}
