package constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoConstants {
    public static final int SINGLE_LOTTO_PRICE = 1000;

    public static final int LOTTO_NUMBER_START = 1;
    public static final int LOTTO_NUMBER_END = 45;

    public static final List<Integer> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LOTTO_NUMBER_START, LOTTO_NUMBER_END)
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    private LottoConstants() {
    }
}
