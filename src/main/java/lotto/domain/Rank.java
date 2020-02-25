package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {
    FIFTH_RANK(5000, 3),
    FOURTH_RANK(50000, 4),
    THIRD_RANK(1500000, 5),
    SECOND_RANK(30000000, 5, true),
    FIRST_RANK(2000000000, 6);

    public final int prize;
    public final int correctLottoNumber;
    public final boolean isCorrectBonusNumber;

    Rank(int prize, int correctLottoNumber) {
        this(prize, correctLottoNumber, false);
    }

    Rank(int prize, int correctLottoNumber, boolean isCorrectBonusNumber) {
        this.prize = prize;
        this.correctLottoNumber = correctLottoNumber;
        this.isCorrectBonusNumber = isCorrectBonusNumber;
    }

    public static Optional<Rank> findRank(int rightNumber, boolean isCorrectBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> isSameRank(rank, rightNumber, isCorrectBonusNumber))
                .findFirst();
    }

    private static boolean isSameRank(Rank rank, int correctLottoNumber, boolean isCorrectBonusNumber) {
        return rank.correctLottoNumber == correctLottoNumber && rank.isCorrectBonusNumber == isCorrectBonusNumber;
    }
}
