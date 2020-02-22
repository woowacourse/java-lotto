package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberBundle {
    private static List<Integer> lottoNumberBundle;

    private LottoNumberBundle() {}

    static {
        lottoNumberBundle = IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean contains(int number) {
        return lottoNumberBundle.contains(number);
    }
}
