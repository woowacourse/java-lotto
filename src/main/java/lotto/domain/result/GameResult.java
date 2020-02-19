package lotto.domain.result;

import java.util.Arrays;

public enum GameResult {
    FIRST_RANK(2000000000, 6),
    SECOND_RANK(30000000, 5),
    THIRD_RANK(1500000, 5),
    FOURTH_RANK(50000, 4),
    FIFTH_RANK(5000, 3),
    NO_RANK(0, 0);

    public final int prize;
    public final int correctLottoNumberSize;

    GameResult(int prize, int correctLottoNumberSize) {
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
        return this.isSame(5) && isCorrectBonusNumber == false;
    }

    private boolean isSame(int collectLottoNumber) {
        return this.correctLottoNumberSize == collectLottoNumber;
    }

    public int getPrize() {
        return prize;
    }

    public int getCorrectLottoNumberSize() {
        return correctLottoNumberSize;
    }
}
