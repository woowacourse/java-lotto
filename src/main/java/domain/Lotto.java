package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    protected final List<LottoBall> lotto;

    protected Lotto(List<LottoBall> lottoBalls) {
        this.lotto = lottoBalls;
    }

    public static Lotto fromNums(List<Integer> lottoNumbers) {
        List<LottoBall> tmpLottoBalls = toBalls(lottoNumbers);
        return new Lotto(tmpLottoBalls);
    }

    public static WinLotto toWinLotto(List<Integer> lottoNums, LottoBall bonus) {
        return new WinLotto(toBalls(lottoNums), bonus);
    }

    public static Lotto fromBalls(List<LottoBall> balls) {
        return new Lotto(balls);
    }

    private static List<LottoBall> toBalls(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoBall::from)
                .sorted()
                .collect(Collectors.toList());
    }

    public int countSameNumber(WinLotto winLottoNumbers) {
        return (int) lotto.stream()
                .filter(winLottoNumbers::isIn)
                .count();
    }

    public boolean isIn(LottoBall ball) {
        return lotto.contains(ball);
    }

    public List<LottoBall> get() {
        return lotto;
    }
}