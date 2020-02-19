package lotto.domain.result;

import java.util.Arrays;

public enum GameResult {
    FIRST_RANK(2000000000, (correctLottoNumberSize, isCorrectBonusNumber)
            -> correctLottoNumberSize == 6),
    SECOND_RANK(30000000, (correctLottoNumberSize, isCorrectBonusNumber)
            -> correctLottoNumberSize == 5 && isCorrectBonusNumber),
    THIRD_RANK(1500000, (correctLottoNumberSize, isCorrectBonusNumber)
            -> correctLottoNumberSize == 5 && isCorrectBonusNumber == false),
    FOURTH_RANK(50000, (correctLottoNumberSize, isCorrectBonusNumber)
            -> correctLottoNumberSize == 4),
    FIFTH_RANK(5000, (correctLottoNumberSize, isCorrectBonusNumber)
            -> correctLottoNumberSize == 3),
    NO_RANK(0, (correctLottoNumberSize, isCorrectBonusNumber)
            -> correctLottoNumberSize < 3);

    int prize;
    GameResultStrategy gameResultStrategy;

    GameResult(int prize, GameResultStrategy gameResultStrategy) {
        this.prize = prize;
        this.gameResultStrategy = gameResultStrategy;
    }

    public static GameResult calculateRank(int correctNumber, boolean isCorrectBonusNumber) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.gameResultStrategy.calculate(correctNumber, isCorrectBonusNumber))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
