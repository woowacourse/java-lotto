package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIFTH_RANK(5000, 3),
    FOURTH_RANK(50000, 4),
    THIRD_RANK(1500000, 5),
    SECOND_RANK(30000000, 5, true),
    FIRST_RANK(2000000000, 6);

    public int prize;
    public int correctLottoNumber;
    public boolean isCorrectBonusNumber;
    public int count;

    Rank(int prize, int correctLottoNumber) {
        this(prize, correctLottoNumber, false, 0);
    }

    Rank(int prize, int correctLottoNumber, boolean isCorrectBonusNumber) {
        this(prize, correctLottoNumber, isCorrectBonusNumber, 0);
    }

    Rank(int prize, int correctLottoNumber, boolean isCorrectBonusNumber, int count) {
        this.prize = prize;
        this.correctLottoNumber = correctLottoNumber;
        this.isCorrectBonusNumber = isCorrectBonusNumber;
        this.count = count;
    }

    public void countUp() {
        this.count++;
    }

    public static void findRank(int rightNumber, boolean isCorrectBonusNumber) {
        Arrays.stream(Rank.values())
                .filter(rank -> isSameRank(rank, rightNumber, isCorrectBonusNumber))
                .forEach(Rank::countUp);
    }

    private static boolean isSameRank(Rank rank, int correctLottoNumber, boolean isCorrectBonusNumber) {
        return rank.correctLottoNumber == correctLottoNumber && rank.isCorrectBonusNumber == isCorrectBonusNumber;
    }
}
