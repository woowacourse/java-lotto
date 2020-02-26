package lotto.domain.result;

import java.util.Arrays;

public enum GameResult {
    FIRST_RANK(2_000_000_000, 6),
    SECOND_RANK(30_000_000, 5),
    THIRD_RANK(1_500_000, 5),
    FOURTH_RANK(50_000, 4),
    FIFTH_RANK(5000, 3),
    NO_RANK(0, 0);

    private static final int SECOND_OR_THIRD_RANK_CORRECT_NUMBER_SIZE = 5;
    private final double prize;
    private final int correctLottoNumberSize;

    GameResult(double prize, int correctLottoNumberSize) {
        this.prize = prize;
        this.correctLottoNumberSize = correctLottoNumberSize;
    }

    public static GameResult calculateRank(int correctNumber, boolean isCorrectBonusNumber) {
        GameResult calculatedGameResult = Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.isSame(correctNumber))
                .findFirst()
                .orElse(GameResult.NO_RANK);
        if (calculatedGameResult.isThirdRank(isCorrectBonusNumber)) {
            calculatedGameResult = GameResult.THIRD_RANK;
        }
        return calculatedGameResult;
    }

    private boolean isThirdRank(boolean isCorrectBonusNumber) {
        return this.isSame(SECOND_OR_THIRD_RANK_CORRECT_NUMBER_SIZE) && isCorrectBonusNumber == false;
    }

    private boolean isSame(int collectLottoNumber) {
        return this.correctLottoNumberSize == collectLottoNumber;
    }

    public double getPrize() {
        return prize;
    }

    public int getCorrectLottoNumberSize() {
        return correctLottoNumberSize;
    }
}
