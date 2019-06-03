package lotto.domain.user;

import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.lottofactory.LottoCreator;
import lotto.domain.lottofactory.LottoTicket;
import lotto.domain.lottofactory.shufflerule.Shuffle;
import lotto.utils.NullCheckUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserTicketManager {
    private static final int PLUS_COUNT = 1;
    private static final int MINIMUM_MATCHING_COUNT = 0;
    private static final int MAXIMUM_MATCHING_COUNT = 6;
    private List<LottoTicket> userLottoTickets;

    public UserTicketManager(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = getCreatedLottoTickets(purchaseAmount, shuffle);
        NullCheckUtil.checkNullLottoTickets(lottoTickets);
        this.userLottoTickets = lottoTickets;
    }

    private List<LottoTicket> getCreatedLottoTickets(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        while (!isSufficientTickets(purchaseAmount, lottoTickets)) {
            lottoTickets.add(LottoCreator.getLottoTicket(shuffle));
        }

        return lottoTickets;
    }

    private boolean isSufficientTickets(PurchaseAmount purchaseAmount, List<LottoTicket> lottoTickets) {
        return purchaseAmount.isEqualsAmount(lottoTickets.size());
    }

    public List<LottoTicket> getUserLottoTickets() {
        return Collections.unmodifiableList(userLottoTickets);
    }

    /**
     * matchedCountsToCount
     * key : rank(등수) (0개 ~ 6개)
     * value : user의 로또딀의 해당 등수의 개수
     */
    public Map<Rank, Integer> getMatchedRankCount(WinningLotto winningLotto) {
        Map<Rank, Integer> matchedRankCount = initializeMatchedRankCount();
        fillMatchedRankCount(winningLotto, matchedRankCount);

        return matchedRankCount;
    }

    private Map<Rank, Integer> initializeMatchedRankCount() {
        return IntStream.rangeClosed(MINIMUM_MATCHING_COUNT, MAXIMUM_MATCHING_COUNT)
                .boxed()
                .collect(Collectors.toMap(Rank::valueOf, i -> 0, (p1, p2) -> p1));
    }

    private void fillMatchedRankCount(WinningLotto winningLotto, Map<Rank, Integer> matchedRankCount) {
        for (LottoTicket userTicket : userLottoTickets) {
            Rank matchedRank = Rank.valueOf(winningLotto.getMatchedWinningNumbersCount(userTicket));
            Integer value = matchedRankCount.get(matchedRank);
            matchedRankCount.put(matchedRank, value + PLUS_COUNT);
        }
    }
}
