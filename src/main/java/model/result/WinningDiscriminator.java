package model.result;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import model.LottoWinningNumbers;
import model.PurchasedLotto;

public class WinningDiscriminator {
    public static PrizeResult judge(PurchasedLotto purchasedLotto, LottoWinningNumbers lottoWinningNumbers) {
        EnumMap<Rank, Integer> prizeCounts = new EnumMap<>(Rank.class);
        for (int idx = 0; idx < purchasedLotto.size(); idx++) {
            Set<Integer> lottoNumbers = new HashSet<>(purchasedLotto.findLottoByCreationOrder(idx).getNumbers());
            Set<Integer> WinningLottoNumbers = new HashSet<>(lottoWinningNumbers.getWinningNumber());
            lottoNumbers.retainAll(WinningLottoNumbers);

            boolean bonus = purchasedLotto.containByIndex(idx, lottoWinningNumbers.getBonusNumber());
            int match = lottoNumbers.size();

            Rank result = Rank.judgeRank(match, bonus);
            prizeCounts.put(result, prizeCounts.getOrDefault(result, 0) + 1);
        }

        return new PrizeResult(prizeCounts, purchasedLotto.size());
    }
}