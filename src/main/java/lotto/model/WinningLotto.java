package lotto.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(final Lotto winningLotto, final int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningResultResponses calculateWinning(final Lottos lottos) {
        Map<Rank, Integer> ranks = findRanks(lottos);
        return new WinningResultResponses(toResponses(ranks));
    }

    private Map<Rank, Integer> findRanks(final Lottos lottos) {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = lotto.calculateMatchingCount(winningLotto);
            Rank findRank = Rank.findBy(matchingCount, lotto.has(bonusNumber));
            ranks.put(findRank, ranks.getOrDefault(findRank, 0) + 1);
        }
        return ranks;
    }

    private List<WinningResultResponse> toResponses(final Map<Rank, Integer> ranks) {
        List<WinningResultResponse> responses = new ArrayList<>();
        for (Rank rank : ranks.keySet()) {
            toResponse(ranks, rank, responses);
        }
        return responses;
    }

    private void toResponse(final Map<Rank, Integer> ranks, final Rank rank,
                            final List<WinningResultResponse> responses) {
        if (rank.isNone()) {
            return;
        }
        if (rank.isSecond()) {
            WinningResultResponse winningResultResponse = new WinningResultResponse(rank.getMatchingCount(),
                    rank.getWinningAmount(), true, ranks.get(rank));
            responses.add(winningResultResponse);
            return;
        }
        WinningResultResponse winningResultResponse = new WinningResultResponse(rank.getMatchingCount(),
                rank.getWinningAmount(), false, ranks.get(rank));
        responses.add(winningResultResponse);
    }

    private void validate(final Lotto lotto, final int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplication(lotto, bonusNumber);
    }

    private void validateRange(final int bonusNumber) {
        if (bonusNumber < Lotto.MIN_LOTTO_NUMBER || bonusNumber > Lotto.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또는 1 이상 45 이하만 가능합니다.");
        }
    }

    private void validateDuplication(final Lotto lotto, final int bonusNumber) {
        if (lotto.has(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

}
