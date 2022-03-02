package domain;

public class LottoRewardLogic {

    private static final int FIRST_REWARD_CONDITION = 6;
    private static final int SECOND_THIRD_REWARD_CONDITION = 5;
    private static final int FORTH_REWARD_CONDITION = 4;
    private static final int FIFTH_REWARD_CONDITION = 3;

    public static Rewards convertToRank(int matchCount, boolean bonusNumberMatch) {
        if (matchCount == FIRST_REWARD_CONDITION) {
            return Rewards.FIRST_REWARD;
        }
        if (matchCount == SECOND_THIRD_REWARD_CONDITION && bonusNumberMatch) {
            return Rewards.SECOND_REWARD;
        }
        if (matchCount == SECOND_THIRD_REWARD_CONDITION) {
            return Rewards.THIRD_REWARD;
        }
        if (matchCount == FORTH_REWARD_CONDITION) {
            return Rewards.FORTH_REWARD;
        }
        if (matchCount == FIFTH_REWARD_CONDITION) {
            return Rewards.FIFTH_REWARD;
        }
        return Rewards.NO_REWARD;
    }
}
