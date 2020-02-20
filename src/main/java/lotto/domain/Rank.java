package lotto.domain;

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


}
