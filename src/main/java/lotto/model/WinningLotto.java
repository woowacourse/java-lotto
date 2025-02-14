package lotto.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningResults calculateWinning(final Lottos lottos) {
        Map<Rank, Integer> ranks = findRanks(lottos);
        return new WinningResults(toResponses(ranks));
    }

    private Map<Rank, Integer> findRanks(final Lottos lottos) {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        initRanks(ranks);
        saveRanks(lottos, ranks);
        return ranks;
    }

    private void saveRanks(final Lottos lottos, final Map<Rank, Integer> ranks) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = lotto.calculateMatchingCount(winningLotto);
            Rank findRank = Rank.findBy(matchingCount, lotto.has(bonusNumber));
            ranks.put(findRank, ranks.getOrDefault(findRank, 0) + 1);
        }
    }

    private static void initRanks(final Map<Rank, Integer> ranks) {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }

    private List<WinningResult> toResponses(final Map<Rank, Integer> ranks) {
        List<WinningResult> responses = new ArrayList<>();
        for (Rank rank : ranks.keySet()) {
            toResponse(ranks, rank, responses);
        }
        return responses;
    }

    private void toResponse(final Map<Rank, Integer> ranks, final Rank rank,
                            final List<WinningResult> responses) {
        if (rank.isNone()) {
            return;
        }
        if (rank.isSecond()) {
            saveResponses(rank, true, ranks, responses);
            return;
        }
        saveResponses(rank, false, ranks, responses);
    }

    private static void saveResponses(final Rank rank, final boolean hasBonus, final Map<Rank, Integer> ranks,
                                      final List<WinningResult> responses) {
        WinningResult winningResult = new WinningResult(rank.getMatchingCount(),
                rank.getWinningAmount(), hasBonus, ranks.get(rank));
        responses.add(winningResult);
    }

    private void validate(final Lotto lotto, final LottoNumber bonusNumber) {
        validateDuplicationWithLottoAndBonusNumber(lotto, bonusNumber);
    }

    private void validateDuplicationWithLottoAndBonusNumber(final Lotto lotto, final LottoNumber bonusNumber) {
        if (lotto.has(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

}
