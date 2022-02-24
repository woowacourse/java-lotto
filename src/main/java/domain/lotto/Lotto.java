package domain.lotto;

import java.util.List;
import java.util.Objects;

public class Lotto {
    final List<LottoBall> lotto;

    Lotto(List<LottoBall> balls) {
        this.lotto = balls;
    }

    public static Lotto fromBalls(List<LottoBall> balls) {
        return new Lotto(balls);
    }

    public int countSameNum(WinLotto winLotto) {
        return (int) lotto.stream()
                .filter(winLotto::isIn)
                .count();
    }

    public boolean isIn(LottoBall ball) {
        return lotto.contains(ball);
    }

    public List<LottoBall> get() {
        return lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lotto=" + lotto +
                '}';
    }
}