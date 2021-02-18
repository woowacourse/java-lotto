package domain.ball;

public class BonusBall {
    private final LottoBall lottoBall;

    private BonusBall(final LottoBall lottoBall) {
        this.lottoBall = lottoBall;
    }

    public static BonusBall of(final LottoBall lottoBall) {
        return new BonusBall(lottoBall);
    }

    public static BonusBall of(final int value) {
        return new BonusBall(new LottoBall(value));
    }

    public boolean isSameNumber(final LottoBall lottoBall) {
        return this.lottoBall.equals(lottoBall);
    }
}
