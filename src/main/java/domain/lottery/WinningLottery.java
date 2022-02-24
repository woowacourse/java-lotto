package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.List;

import domain.Rank;

public class WinningLottery {

	private static final int MIN_LOTTERY_NUMBER = 1;
	private static final int MAX_LOTTERY_NUMBER = 45;

	final Lottery winningNumbers;
	final int bonusBall;

	public WinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		this.winningNumbers = new Lottery(winningNumbers);
		validateBonusBall(winningNumbers, bonusBall);
		this.bonusBall = bonusBall;
	}

	private void validateBonusBall(final List<Integer> winningNumbers, final int bonusBall) {
		validateRange(bonusBall);
		validateDuplicatedNumber(winningNumbers, bonusBall);
	}

	private void validateRange(final int bonusBall) {
		if (MIN_LOTTERY_NUMBER > bonusBall || bonusBall > MAX_LOTTERY_NUMBER) {
			throw new IllegalArgumentException(INVALID_RANGE_EXCEPTION.getMessage());
		}
	}

	private void validateDuplicatedNumber(final List<Integer> winningNumbers, final Integer bonusBall) {
		if(winningNumbers.stream()
			.anyMatch(winningNumber -> winningNumber.equals(bonusBall))) {
			throw new IllegalArgumentException(DUPLICATE_NUMBER_EXCEPTION.getMessage());
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
