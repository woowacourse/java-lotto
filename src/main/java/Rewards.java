public enum Rewards {
    FIRST_REWARD(1, 2000000000),
    SECOND_REWARD(2, 30000000),
    THIRD_REWARD(3, 1500000),
    FORTH_REWARD(4, 50000),
    FIFTH_REWARD(5, 5000),
    NO_REWARD(-1, 0);

    private final int ranking;
    private final int reward;

    Rewards(int ranking, int reward) {
        this.ranking = ranking;
        this.reward = reward;
    }
}
