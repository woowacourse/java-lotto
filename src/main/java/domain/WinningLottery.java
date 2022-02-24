package domain;

import java.util.List;

import utils.LotteryMessage;

public class WinningLottery {

	private static final int BONUS_BALL_MIN_RAGE = 1;
	private static final int BONUS_BALL_MAX_RANGE = 45;

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
		if (BONUS_BALL_MIN_RAGE > bonusBall || bonusBall > BONUS_BALL_MAX_RANGE) {
			throw new IllegalArgumentException(LotteryMessage.BONUS_BALL_RANGE_ERROR);
		}
	}

	private void validateDuplicatedNumber(final List<Integer> winningNumbers, final Integer bonusBall) {
		if(winningNumbers.stream()
				 .anyMatch(winningNumber -> winningNumber.equals(bonusBall))) {
			throw new IllegalArgumentException(LotteryMessage.DUPLICATED_WINNING_NUMBER_WITH_BONUS_NUMBER);
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
