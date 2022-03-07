package lotterymachine.domain;

public class WinningLottery {
    private static final String DUPLICATION_INPUT_BONUS_NUMBERS = "보너스 볼이 당첨 번호와 중복됩니다.";

    private final LotteryTicket lotteryTicket;
    private final LotteryNumber bonusNumber;

    public WinningLottery(LotteryTicket lotteryTicket, LotteryNumber bonusNumber) {
        validateBonusNumber(lotteryTicket, bonusNumber);
        this.lotteryTicket = lotteryTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LotteryTicket numbers, LotteryNumber bonusNumber) {
        if (numbers.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATION_INPUT_BONUS_NUMBERS);
        }
    }

    public WinningLotteryRank getWinningLotteryRank(LotteryTicket lotteryTicket) {
        int matchingNumbers = lotteryTicket.countMatchingNumbers(this.lotteryTicket);
        boolean containsBonus = lotteryTicket.containsNumber(this.bonusNumber);
        return WinningLotteryRank.find(matchingNumbers, containsBonus);
    }
}