package model.result;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import model.LottoWinningNumbers;
import model.PurchasedLotto;

public class WinningDiscriminator {
    public static PrizeResult judge(PurchasedLotto purchasedLotto, LottoWinningNumbers lottoWinningNumbers) {
        EnumMap<Rank, Integer> prizeCounts = countPrizeResults(purchasedLotto, lottoWinningNumbers);
        return new PrizeResult(prizeCounts, purchasedLotto.size());
    }

    private static EnumMap<Rank, Integer> countPrizeResults(PurchasedLotto purchasedLotto,
                                                            LottoWinningNumbers lottoWinningNumbers) {
        EnumMap<Rank, Integer> prizeCounts = new EnumMap<>(Rank.class);

        for (int idx = 0; idx < purchasedLotto.size(); idx++) {
            Rank result = determineRank(purchasedLotto, lottoWinningNumbers, idx);
            prizeCounts.put(result, prizeCounts.getOrDefault(result, 0) + 1);
        }

        return prizeCounts;
    }

    private static Rank determineRank(PurchasedLotto purchasedLotto, LottoWinningNumbers lottoWinningNumbers, int idx) {
        Set<Integer> lottoNumbers = new HashSet<>(purchasedLotto.findLottoByCreationOrder(idx).getNumbers());
        Set<Integer> winningNumbers = new HashSet<>(lottoWinningNumbers.getWinningNumber());

        lottoNumbers.retainAll(winningNumbers);
        int match = lottoNumbers.size();
        boolean bonus = purchasedLotto.containByIndex(idx, lottoWinningNumbers.getBonusNumber());

        return Rank.judgeRank(match, bonus);
    }
}