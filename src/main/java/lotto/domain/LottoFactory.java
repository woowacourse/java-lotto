package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final List<LottoBall> instance;

    static {
        instance = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoBall::new)
                .collect(Collectors.toList());
    }

    public static List<LottoBall> getInstance() {
        return Collections.unmodifiableList(instance);
    }
}
