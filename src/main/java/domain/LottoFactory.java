package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final List<Integer> randomNumbers = new ArrayList<>(
            IntStream.rangeClosed(1, 45)
                    .boxed()
                    .toList()
    );

    public static Lotto makeLotto() {
        Collections.shuffle(randomNumbers);
        List<Integer> numbers = randomNumbers.stream().limit(6).sorted().collect(Collectors.toList());
        return new Lotto(numbers);
    }
}
