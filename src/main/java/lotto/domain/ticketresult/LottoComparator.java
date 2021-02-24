package lotto.domain.ticketresult;

import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoComparator {
    private static final int ZERO = 0;

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
        for (LottoTicket purchasedLottoTicket : purchasedLottoTickets.getTickets()) {
            int matchNumberCount = winningLottoNumbers.compare(purchasedLottoTicket);
            boolean hasBonusNumber = purchasedLottoTicket.hasNumber(winningLottoNumbers.getBonusNumber());
            addResult(matchNumberCount, hasBonusNumber);
        }
        return Collections.unmodifiableMap(new HashMap<>(lottoResult));
    }

    private void addResult(int matchNumberCount, boolean hasBonusNumber) {
        Rank rank = Rank.getLottoRank(matchNumberCount, hasBonusNumber);
        lottoResult.computeIfPresent(rank, (Rank key, Integer value) -> ++value);
    }
}
