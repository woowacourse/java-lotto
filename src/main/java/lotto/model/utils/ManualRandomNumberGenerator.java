package lotto.model.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManualRandomNumberGenerator implements NumberGenerator {
    public static final String REQUEST_CORRECT_COUNT_MESSAGE = "로또 번호의 숫자는 여섯 개를 선택해야 합니다.";

    @Override
    public List<Integer> generate(int size, String... integers) {
        if (integers.length != size) {
            throw new IllegalArgumentException(REQUEST_CORRECT_COUNT_MESSAGE);
        }
        return Arrays.stream(integers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
