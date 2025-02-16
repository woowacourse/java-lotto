package service;

import domain.Lottos;
import domain.PrizeResult;
import domain.Rank;
import domain.WinningLotto;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

public class Judgement {

    public static PrizeResult judge(Lottos lottos, WinningLotto winningLotto) {
        EnumMap<Rank, Integer> prizeCounts = new EnumMap<>(Rank.class);
        for (int idx = 0; idx < lottos.size(); idx++) {
            Rank result = findMatchRank(lottos, winningLotto, idx);
            prizeCounts.put(result, prizeCounts.getOrDefault(result, 0) + 1);
        }

        return new PrizeResult(prizeCounts, lottos.size());
    }

    private static Rank findMatchRank(Lottos lottos, WinningLotto winningLotto, int idx) {
        Set<Integer> lottoNumbers = new HashSet<>(lottos.getLottoByIndex(idx).getNumbers());
        Set<Integer> WinningLottoNumbers = new HashSet<>(winningLotto.getNumbers());
        lottoNumbers.retainAll(WinningLottoNumbers);

        boolean bonus = lottos.containByIndex(idx, winningLotto.getBonusNumber());
        int matchCount = lottoNumbers.size();

        return Rank.judge(matchCount, bonus);
    }
}
