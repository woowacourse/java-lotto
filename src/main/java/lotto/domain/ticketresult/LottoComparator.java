package lotto.domain.ticketresult;

import static lotto.domain.ticketresult.Rank.SECOND;
import static lotto.domain.ticketresult.Rank.THIRD;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;

public class LottoComparator {
    private static final int ZERO = 0;
    private static final int ONE_COUNT = 1;
    private static final int FIVE_MATCHED_SIZE = 2;

    private final WinningLottoNumbers winningLottoNumbers;
    private final Map<Rank, Integer> lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.lottoResult = new HashMap<>();
        initializeLottoResult();
    }

    private void initializeLottoResult() {
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, ZERO);
        }
    }

    public Map<Rank, Integer> getLottoResult(PurchasedLottoTickets purchasedLottoTickets) {
        for (LottoTicket purchasedOneLottoTicket : purchasedLottoTickets.getTickets()) {
            int countMatchedNumbers = getEachLottoResult(purchasedOneLottoTicket);
            addResult(countMatchedNumbers, purchasedOneLottoTicket);
        }
        return Collections.unmodifiableMap(new HashMap<>(lottoResult));
    }

    private int getEachLottoResult(LottoTicket purchasedOneLottoTicket) {
        LottoTicket winningLottoTicket = winningLottoNumbers.getWinningTicket();

        return (int) purchasedOneLottoTicket.getLottoTicketNumbers()
            .stream()
            .filter(purchasedLottoNumber -> winningLottoTicket.getLottoTicketNumbers()
                .contains(purchasedLottoNumber))
            .count();
    }

    private void addResult(int countMatchedNumbers, LottoTicket purchasedOneLottoTicket) {
        List<Rank> ranks = Rank
            .getLottoMatchType(countMatchedNumbers);
        if (ranks.size() == ZERO) {
            return;
        }
        if (ranks.size() == FIVE_MATCHED_SIZE) {
            handleFiveMatchType(purchasedOneLottoTicket);
            return;
        }
        handleOtherMatchTypes(ranks);
    }

    private void handleFiveMatchType(LottoTicket purchasedOneLottoTicket) {
        if (purchasedOneLottoTicket.hasNumber(winningLottoNumbers.getBonusNumber())) {
            Integer currentMatchedNumbersCount = lottoResult.get(SECOND);
            lottoResult.put(SECOND, currentMatchedNumbersCount + ONE_COUNT);
            return;
        }
        Integer currentMatchedNumbersCount = lottoResult.get(THIRD);
        lottoResult.put(THIRD, currentMatchedNumbersCount + ONE_COUNT);
    }

    private void handleOtherMatchTypes(List<Rank> ranks) {
        Rank rank = ranks.get(ZERO);
        Integer currentMatchedNumbersCount = lottoResult.get(rank);
        lottoResult.put(rank, currentMatchedNumbersCount + ONE_COUNT);
    }
}
