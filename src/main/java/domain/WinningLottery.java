package domain;

import java.util.List;

import utils.LotteryMessage;

public class WinningLottery {

	private final Lottery winningNumbers;
	private final LotteryNumber bonusBall;

	public WinningLottery(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		validateBonusBall(winningNumbers, bonusBall);
		this.winningNumbers = new Lottery(winningNumbers);
		this.bonusBall = bonusBall;
	}

	private void validateBonusBall(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
		validateDuplicatedNumber(winningNumbers, bonusBall);
	}

	private void validateDuplicatedNumber(final List<LotteryNumber> winningNumbers, final LotteryNumber bonusBall) {
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
