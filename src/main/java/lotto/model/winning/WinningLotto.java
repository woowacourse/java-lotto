package lotto.model.winning;

import java.util.LinkedHashMap;
import java.util.Map;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.Lottos;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateDuplication(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningResultResponses calculateWinning(final Lottos lottos) {
        Map<Rank, Integer> ranks = findRanks(lottos);
        return new WinningResultResponses(ranks);
    }

    private Map<Rank, Integer> findRanks(final Lottos lottos) {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        initRanks(ranks);
        saveMatchingRanks(lottos, ranks);
        return ranks;
    }

    private void validateDuplication(final Lotto lotto, final LottoNumber bonusNumber) {
        if (lotto.hasBonus(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private void initRanks(final Map<Rank, Integer> ranks) {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }

    private void saveMatchingRanks(final Lottos lottos, final Map<Rank, Integer> ranks) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = lotto.calculateMatchingCount(winningLotto);
            Rank findRank = Rank.findBy(matchingCount, lotto.hasBonus(bonusNumber));
            ranks.put(findRank, ranks.getOrDefault(findRank, 0) + 1);
        }
    }

}
