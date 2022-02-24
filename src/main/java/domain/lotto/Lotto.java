package domain.lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    final List<LottoBall> lotto;

    Lotto(List<LottoBall> balls) {
        this.lotto = balls;
    }

    public static Lotto fromNums(List<Integer> lottoNumbers) {
        List<LottoBall> tmpLottoBalls = toBalls(lottoNumbers);
        return new Lotto(tmpLottoBalls);
    }

    public static Lotto fromBalls(List<LottoBall> balls) {
        return new Lotto(balls);
    }

    public static WinLotto toWinLotto(List<Integer> lottoNums, LottoBall bonus) {
        return new WinLotto(toBalls(lottoNums), bonus);
    }

    public int countSameNum(WinLotto winLotto) {
        return (int) lotto.stream()
                .filter(winLotto::isIn)
                .count();
    }

    public boolean isIn(LottoBall ball) {
        return lotto.contains(ball);
    }

    private static List<LottoBall> toBalls(List<Integer> lottoNums) {
        return lottoNums.stream()
                .map(LottoBall::from)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<LottoBall> get() {
        return lotto;
    }
}