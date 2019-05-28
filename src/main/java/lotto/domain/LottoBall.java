package lotto.domain;

public class LottoBall {
    public static final int MAX_NO = 45;
    public static final int MIN_NO = 1;

    private int no;

    public LottoBall(int no) throws InvalidNumberException {
        validateRange(no);
        this.no = no;
    }

    private void validateRange(int no) throws InvalidNumberException {
        if (no < MIN_NO || no > MAX_NO) {
            throw new InvalidNumberException("잘못된 범위의 번호입니다.");
        }
    }

    public int compareTo(LottoBall lottoBall) {
        return Integer.compare(no, lottoBall.no);
    }
}