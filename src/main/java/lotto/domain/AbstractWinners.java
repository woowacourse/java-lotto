package lotto.domain;

abstract class AbstractWinners implements WinnersImpl {
    public long rewardMoney(int reward, int winnersNumber) {
        return (long) reward * winnersNumber;
    }
}
