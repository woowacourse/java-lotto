package lotto.model.winning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoNumber;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateDuplication(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningResultResponses calculateWinning(final List<Lotto> lottos) {
        Map<Rank, Integer> ranks = findRanks(lottos);
        return new WinningResultResponses(ranks);
    }

    private void validateDuplication(final Lotto lotto, final LottoNumber bonusNumber) {
        if (lotto.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private Map<Rank, Integer> findRanks(final List<Lotto> lottos) {
        Map<Rank, Integer> ranks = new HashMap<>();
        initRanks(ranks);
        saveMatchingRanks(lottos, ranks);
        return ranks;
    }

    private void initRanks(final Map<Rank, Integer> ranks) {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }

    private void saveMatchingRanks(final List<Lotto> lottos, final Map<Rank, Integer> ranks) {
        for (Lotto lotto : lottos) {
            int matchingCount = lotto.calculateMatchingCount(winningLotto);
            boolean bonusRequired = Rank.isNeedBonusRequired(matchingCount);
            Rank findRank = findRankByBonusRequired(lotto, bonusRequired, matchingCount);
            ranks.put(findRank, ranks.getOrDefault(findRank, 0) + 1);
        }
    }

    private Rank findRankByBonusRequired(final Lotto lotto, final boolean bonusRequired, final int matchingCount) {
        if (bonusRequired) {
            return Rank.findBy(matchingCount, lotto.hasNumber(bonusNumber));
        }
        return Rank.findBy(matchingCount, false);
    }

}
