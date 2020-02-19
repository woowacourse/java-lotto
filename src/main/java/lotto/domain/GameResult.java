package lotto.domain;

public enum GameResult {
    FIRST_RANK(2000000000, 6),
    SECOND_RANK(30000000, 5, true),
    THIRD_RANK(1500000, 5),
    FOURTH_RANK(50000, 4),
    FIFTH_RANK(5000, 3);

    int prize;
    int correctLottoNumber;
    boolean isCorrectBonusNumber;

    GameResult(int prize, int correctLottoNumber) {
        this(prize, correctLottoNumber, false);
    }

    GameResult(int prize, int correctLottoNumber, boolean isCorrectBonusNumber) {
        this.prize = prize;
        this.correctLottoNumber = correctLottoNumber;
        this.isCorrectBonusNumber = isCorrectBonusNumber;
    }
}
