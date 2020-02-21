package lotto.model;

public enum LottoResult {
    FIFTH_GRADE(3, 5000, "FIFTH"),
    FOURTH_GRADE(4, 50000, "FOURTH"),
    THIRD_GRADE(5, 150000, "THIRD"),
    SECOND_GRADE(5, 30000000, "SECOND"),
    FIRST_GRADE(6, 2000000000, "FIRST");

    private final int prize;
    private final int correct;
    private final String grade;

    LottoResult(int correct, int prize, String grade) {
        this.correct = correct;
        this.prize = prize;
        this.grade = grade;
    }

    public double prizeResult(int count) {
        return count * this.prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getCorrect() {
        return correct;
    }

    public String getGrade() {
        return grade;
    }
}
