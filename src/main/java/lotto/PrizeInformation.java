package lotto;

public class PrizeInformation {
    private final Prize prize;
    private final int count;

    public PrizeInformation(Prize prize, int count) {
        this.prize = prize;
        this.count = count;
    }

    public int pickAmount() {
        return this.prize.pickAmount(count);
    }
}
