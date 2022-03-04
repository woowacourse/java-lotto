package domain;

import java.util.List;

public class WinningLottery {

	public static final String WINNING_NUMBER = "당첨번호";
	public static final String BONUS_BALL = "보너스볼";

	private static final String DUPLICATED_WINNING_NUMBER_WITH_BONUS_NUMBER = WINNING_NUMBER + "와 "
		+ BONUS_BALL + "에 중복된 번호가 있으면 안됩니다.";

	private final Lottery winningNumbers;
	private final LotteryNumber bonusBall;

	public WinningLottery(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		validateBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = new Lottery(winningNumbers);
		this.bonusBall = bonusBall;
	}

	private void validateBonusBall(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		final boolean hasDuplicatedNumberWithWinningNumber = winningNumbers.contains(bonusBall);
		if (hasDuplicatedNumberWithWinningNumber) {
			throw new IllegalArgumentException(DUPLICATED_WINNING_NUMBER_WITH_BONUS_NUMBER);
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
