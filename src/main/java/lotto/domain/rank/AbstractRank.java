package lotto.domain.rank;

abstract class AbstractRank implements RankImpl {
    public long rewardMoney(int reward, int winnersNumber) {
        return (long) reward * winnersNumber;
    }
}
