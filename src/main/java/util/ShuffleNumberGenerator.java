package util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleNumberGenerator implements LottoNumberGenerator {
    private final List<Integer> candidateNumber = IntStream.range(1, 46)
            .boxed()
            .collect(Collectors.toList());

    public List<Integer> generate() {
        Collections.shuffle(candidateNumber);
        return candidateNumber.stream()
                .limit(6)
                .sorted()
                .collect(Collectors.toList());
    }
}
