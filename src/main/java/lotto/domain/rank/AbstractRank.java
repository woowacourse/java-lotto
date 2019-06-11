package lotto.domain.rank;

abstract class AbstractRank implements Rank {
    protected int winnersNumber;

    public void addWinner() {
        winnersNumber++;
    }
}
