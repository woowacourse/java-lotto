package model.result;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import model.LottoWinningNumbers;
import model.OwnedLotto;

public class discriminator {
    public static PrizeResult judge(OwnedLotto ownedLotto, LottoWinningNumbers lottoWinningNumbers) {
        EnumMap<Rank, Integer> prizeCounts = new EnumMap<>(Rank.class);
        for (int idx = 0; idx < ownedLotto.size(); idx++) {
            Set<Integer> lottoNumbers = new HashSet<>(ownedLotto.getLottoByIndex(idx).getNumbers());
            Set<Integer> WinningLottoNumbers = new HashSet<>(lottoWinningNumbers.getWinningNumber());
            lottoNumbers.retainAll(WinningLottoNumbers);

            boolean bonus = ownedLotto.containByIndex(idx, lottoWinningNumbers.getBonusNumber());
            int match = lottoNumbers.size();

            Rank result = Rank.judgeRank(match, bonus);
            prizeCounts.put(result, prizeCounts.getOrDefault(result, 0) + 1);
        }

        return new PrizeResult(prizeCounts, ownedLotto.size());
    }
}