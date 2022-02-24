package domain.lottery;

import java.util.List;

import domain.Rank;
import domain.lottery.Lottery;

public class WinningLottery {

	final Lottery winningNumbers;
	final int bonusBall;

	public WinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		validateBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = new Lottery(winningNumbers);
		this.bonusBall = bonusBall;
	}

	private void validateBonusBall(final List<Integer> winningNumbers, final int bonusBall) {
		validateRange(bonusBall);
		validateDuplicatedNumber(winningNumbers, bonusBall);
	}

	private void validateRange(final int bonusBall) {
		if (0 >= bonusBall || bonusBall >= 46) {
			throw new IllegalArgumentException("보너스볼의 번호는 1~45 사이여야 합니다");
		}
	}

	private void validateDuplicatedNumber(final List<Integer> winningNumbers, final Integer bonusBall) {
		if(winningNumbers.stream()
			.anyMatch(winningNumber -> winningNumber.equals(bonusBall))) {
			throw new IllegalArgumentException("당첨번호와 보너스볼에 중복된 번호가 있으면 안됩니다.");
		}
	}

	public Rank getRank(final Lottery lottery) {
		final int winningCount = winningNumbers.countSameNumber(lottery);
		return calculateRank(winningCount, lottery);
	}

	private Rank calculateRank(final int winningCount, final Lottery lottery) {
		return Rank.getRank(winningCount, lottery.contains(this.bonusBall));
	}
}
