package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {

    private LottoGenerator() {}

    public static Lottos pickAutoLottos(final LottoCount lottoCount) {
        int autoCount = lottoCount.getTotalCount();
        List<Lotto> lottos = new ArrayList<>(autoCount);
        for (int i = 0; i < autoCount; i++) {
            lottos.add(new Lotto(selectRandomBalls()));
        }
        return new Lottos(lottos);
    }

    // TODO: 수동 로또 생성

    private static List<Ball> selectRandomBalls() {
        List<Ball> lottoBalls = new ArrayList<>(Ball.getTotalBalls());
        Collections.shuffle(lottoBalls);

        List<Ball> selectedBalls = selectBalls(lottoBalls);
        return selectedBalls.stream()
                .sorted(Comparator.comparing(Ball::getNumber))
                .collect(Collectors.toList());
    }

    private static ArrayList<Ball> selectBalls(final List<Ball> lottoBalls) {
        return new ArrayList<>(lottoBalls.subList(0, Lotto.BALL_COUNT));
    }
}
