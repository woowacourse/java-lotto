package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumber;

public class RankCalculator {
	public static List<Rank> calculateMultiple(List<Lotto> lottos, WinningNumber winningNumber) {
		return lottos.stream()
			.map(x -> calculateSingle(x, winningNumber))
			.collect(Collectors.toList());
	}

	public static Rank calculateSingle(Lotto lotto, WinningNumber winningNumber) {
		int hitNumber = winningNumber.countHitNumber(lotto);
		boolean bonusNumberExist = winningNumber.hasBonusNumber(lotto);
		return Rank.getRank(hitNumber, bonusNumberExist);
	}
}
