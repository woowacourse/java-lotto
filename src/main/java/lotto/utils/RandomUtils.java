package lotto.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomUtils {
    private static List<Integer> numbers;

    public static List<Integer> makeNumbers() {
//        generate(minValue, maxValue);
        Collections.shuffle(numbers);
        return numbers.subList(0, 6);
    }

    public static void generate(int minValue, int maxValue) {
        numbers = IntStream.range(minValue, maxValue)
                .boxed()
                .collect(Collectors.toList());
    }

//    RandomUtils.generate(); -- lottos 생성자에서 한번 불러일으키고,
//
//    Lotto lotto = new Lotto(RandomUtils.makeNumbers()); -- 그 뒤론 그냥 이렇게
}
