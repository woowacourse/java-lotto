package lotto.domain;

import static lotto.domain.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.domain.LottoMatchType.FIVE_MATCH;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoComparator {
    private static final int DEFAULT_COUNT = 0;
    private static final int ONE_COUNT = 1;
    private static final int EMPTY_SIZE = 0;
    private static final int FIVE_MATCHED_SIZE = 2;
    private static final int FIRST_INDEX = 0;

    private final WinningLottoNumbers winningLottoNumbers;
    private final Map<LottoMatchType, Integer> lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.lottoResult = new HashMap<>();
        initializeLottoResult();
    }

    private void initializeLottoResult() {
        for (LottoMatchType lottoMatchType : LottoMatchType.values()) {
            lottoResult.put(lottoMatchType, DEFAULT_COUNT);
        }
    }

    public Map<LottoMatchType, Integer> getLottoResult(
        PurchasedLottoTickets purchasedLottoTickets) {
        for (LottoTicket purchasedOneLottoTicket : purchasedLottoTickets.getTickets()) {
            int countMatchedNumbers
                = getEachLottoResult(purchasedOneLottoTicket,
                winningLottoNumbers.getWinningTicket());
            addResult(countMatchedNumbers, purchasedOneLottoTicket);
        }
        return lottoResult;
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

        List<LottoMatchType> lottoMatchTypes = getLottoMatchType(countMatchedNumbers);
        if (lottoMatchTypes.size() == EMPTY_SIZE) {
            return;
        }
        if (lottoMatchTypes.size() == FIVE_MATCHED_SIZE) {
            handleFiveMatchType(purchasedOneLottoTicket);
            return;
        }
        handleOtherMatchTypes(lottoMatchTypes);
    }

    private List<LottoMatchType> getLottoMatchType(int countMatchedNumbers) {
        return Arrays.stream(LottoMatchType.values())
            .filter(
                lottoMatchType -> lottoMatchType.getCountMatchedNumbers() == countMatchedNumbers)
            .collect(Collectors.toList());
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
        LottoMatchType lottoMatchType = lottoMatchTypes.get(FIRST_INDEX);
        Integer currentMatchedNumbersCount = lottoResult.get(lottoMatchType);
        lottoResult.put(lottoMatchType, currentMatchedNumbersCount + ONE_COUNT);
    }
}
