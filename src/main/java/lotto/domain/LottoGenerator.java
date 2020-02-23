package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int NUMBER_COUNT_PER_LOTTO = 6;
    private static final List<Ball> allLottoBalls;

    static {
        allLottoBalls = Ball.generateAllBalls();
    }

    public static List<Lotto> generate(int lottoCount) {
        List<Lotto> randomLottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            randomLottos.add(new Lotto(generateLottoBalls()));
        }
        return randomLottos;
    }

    private static List<Ball> generateLottoBalls() {
        List<Ball> cloneAllLottoBalls = new ArrayList<>(allLottoBalls);
        Collections.shuffle(cloneAllLottoBalls);
        List<Ball> resultBalls = pickLottoBalls(cloneAllLottoBalls);
        Collections.sort(resultBalls);
        return resultBalls;
    }

    private static List<Ball> pickLottoBalls(List<Ball> cloneAllLottoBalls) {
        List<Ball> resultBalls = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT_PER_LOTTO; i++) {
            resultBalls.add(cloneAllLottoBalls.get(i));
        }
        return resultBalls;
    }
}
