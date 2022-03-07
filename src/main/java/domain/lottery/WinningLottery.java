package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import domain.Rank;

public final class WinningLottery {

	private final Lottery winningNumbers;
	private final LotteryNumber bonusBall;

	private WinningLottery(final Lottery winningNumbers, final LotteryNumber bonusBall) {
		validateBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = winningNumbers;
		this.bonusBall = bonusBall;
	}

	public static WinningLottery of(final Lottery winningNumbers, final LotteryNumber bonusBall) {
		return new WinningLottery(winningNumbers, bonusBall);
	}

	private void validateBonusBall(final Lottery winningNumbers, final LotteryNumber bonusBall) {
		validateDuplicatedNumber(winningNumbers, bonusBall);
	}

	private void validateDuplicatedNumber(final Lottery winningNumbers, final LotteryNumber bonusBall) {
		if (winningNumbers.isDuplicated(bonusBall)) {
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
