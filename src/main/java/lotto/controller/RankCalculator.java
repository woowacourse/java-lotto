package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumber;

import java.util.List;
import java.util.stream.Collectors;

public class RankCalculator {
    public static List<Rank> calculateMultiple(List<Lotto> lottos, WinningNumber winningNumber) {
        final List<Integer> winningNumbers = winningNumber.getWinningNumber();
        final int bonusNumber = winningNumber.getBonusNumber();

        return lottos.stream()
                .map(x -> calculateSingle(x, winningNumbers, bonusNumber))
                .collect(Collectors.toList());
    }

    private static Rank calculateSingle(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int hitNumber = countHitNumber(lotto, winningNumbers);
        boolean bonusNumberExist = lotto.hasBonusNumber(bonusNumber);
        return Rank.getRank(hitNumber, bonusNumberExist);
    }

    private static int countHitNumber(Lotto lotto, List<Integer> winningNumbers) {
        return (int) lotto.getNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
