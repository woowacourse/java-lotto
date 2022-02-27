package domain;

public enum Rewards {
    FIRST_REWARD(2000000000),
    SECOND_REWARD(30000000),
    THIRD_REWARD(1500000),
    FORTH_REWARD(50000),
    FIFTH_REWARD(5000),
    NO_REWARD(0);

    private final int prize;

    Rewards(int prize) {
        this.prize = prize;
    }

    public long getPrize() {
        return this.prize;
    }
}
