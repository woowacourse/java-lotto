package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumbers {
    private static List<Integer> numbers;

    private LottoNumbers() {

    }

    public static List<Integer> getNumbers() {
        if (numbers == null) {
            numbers = new ArrayList<>();
            IntStream.range(1, 46).forEach(i -> {
                numbers.add(i);
            });
            return numbers.subList(0, 6);
        }
        return numbers.subList(0, 6);
    }
}
