package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Ranks {
    FIRST((matchNumber, isBonusMatch) -> matchNumber == 6) {
        @Override
        public Rewards getReward() {
            return Rewards.FIRST_REWARD;
        }
    },
    SECOND((matchNumber, isBonusMatch) -> matchNumber == 5 && isBonusMatch) {
        @Override
        public Rewards getReward() {
            return Rewards.SECOND_REWARD;
        }
    },
    THIRD((matchNumber, isBonusMatch) -> matchNumber == 5 && !isBonusMatch) {
        @Override
        public Rewards getReward() {
            return Rewards.THIRD_REWARD;
        }
    },
    FORTH((matchNumber, isBonusMatch) -> matchNumber == 4) {
        @Override
        public Rewards getReward() {
            return Rewards.FORTH_REWARD;
        }
    },
    FIFTH((matchNumber, isBonusMatch) -> matchNumber == 3) {
        @Override
        public Rewards getReward() {
            return Rewards.FIFTH_REWARD;
        }
    },
    NONE((matchNumber, isBonusMatch) -> matchNumber < 3) {
        @Override
        public Rewards getReward() {
            return Rewards.NO_REWARD;
        }
    };

    private static final String NO_RANK_EXCEPTION = "[ERROR] 해당 순위를 찾을 수 없습니다.";
    private final BiPredicate<Integer, Boolean> isMatch;

    Ranks(BiPredicate<Integer, Boolean> isMatch) {
        this.isMatch = isMatch;
    }

    public static Ranks of(int matchNumber, boolean isBonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch.test(matchNumber, isBonusMatch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_RANK_EXCEPTION));
    }

    public abstract Rewards getReward();
}
