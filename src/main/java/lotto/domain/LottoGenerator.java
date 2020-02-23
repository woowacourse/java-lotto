package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int NUMBER_COUNT_PER_LOTTO = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final List<Ball> allLottoBalls;

    static {
        allLottoBalls = gatherAllBalls();
    }

    private static List<Ball> gatherAllBalls() {
        List<Ball> allLottoBalls = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            allLottoBalls.add(Ball.valueOf(i));
        }
        return allLottoBalls;
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
