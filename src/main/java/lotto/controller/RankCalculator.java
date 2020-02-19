package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumber;

public class RankCalculator {
	public static List<Rank> calculateMultiple(List<Lotto> lottos, WinningNumber winningNumber) {
		final List<Integer> winningNumbers = winningNumber.getWinningNumber().getNumbers();
		final int bonusNumber = winningNumber.getBonusNumber().getBonusNumber();

		return lottos.stream()
			.map(x -> calculateSingle(x, winningNumbers, bonusNumber))
			.collect(Collectors.toList());
	}

	public static Rank calculateSingle(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
		int hitNumber = countHitNumber(lotto, winningNumbers);
		boolean bonusNumberExist = hasBonusNumber(lotto, bonusNumber);
		return Rank.getRank(hitNumber, bonusNumberExist);
	}

	private static boolean hasBonusNumber(Lotto lotto, int bonusNumber) {
		return lotto.getNumbers().contains(bonusNumber);
	}

	private static int countHitNumber(Lotto lotto, List<Integer> winningNumbers) {
		return (int)lotto.getNumbers()
			.stream()
			.filter(winningNumbers::contains)
			.count();
	}
}
