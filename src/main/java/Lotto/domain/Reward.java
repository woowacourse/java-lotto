package Lotto.domain;

public class Reward {
    private static final int MIN = 0;
    private int reward;

    public Reward(int reward) {
        validate(reward);
        this.reward = reward;

    }

    private void validate(int reward) {
        if(reward <= MIN) {
            throw new IllegalArgumentException("상금은 0보다 커야합니다.");
        }
    }

    public int getReward() {
        return this.reward;
    }
}
