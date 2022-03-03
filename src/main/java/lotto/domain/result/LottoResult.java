package lotto.domain.result;

import java.util.Arrays;
import java.util.HashMap;
import lotto.domain.lotto.Lotto;

public class LottoResult {

    private HashMap<Rank, Integer> result;

    public LottoResult() {
        initResult();
    }

    private void initResult() {
        result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    public void calculateWinning(final Lotto lotto, final Lotto winninglotto, final int bonusNumber) {
        int matchCount = (int) winninglotto.getLottoNumbers()
                .stream()
                .filter(lottoNumber -> lotto.contains(lottoNumber.getLottoNumber()))
                .count();
        boolean hasBonusNumber = lotto.contains(bonusNumber);
        Rank rank = Rank.matchRank(matchCount, hasBonusNumber);

        result.put(rank, result.get(rank) + 1);
    }

    public int calculateWinningMoney() {
        return Arrays.stream(Rank.values())
                .mapToInt(rank -> result.get(rank) * rank.getMoney())
                .sum();
    }

    public int getRankCount(final Rank rank) {
        return result.get(rank);
    }
}
