package util;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomLottoUtil {

    private RandomLottoUtil() {
    }

    public static LottoBalls generateLottoNumbers() {
        List<LottoBall> lottoBalls = new ArrayList<>();
        IntStream intStream = new Random().ints(1, 46);
        intStream.distinct().limit(6).sorted()
                .forEach(i -> lottoBalls.add(new LottoBall(i)));
        return new LottoBalls(lottoBalls);
    }
}
