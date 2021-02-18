package lottogame.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static List<Integer> numbers;

    private LottoGenerator() {
    }

    public static List<Lotto> makeLottos(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(new Lotto(makeNumbers()));
        }
        return lottos;
    }

    public static List<Integer> makeNumbers() {
        Collections.shuffle(numbers);
        List<Integer> selectNumber = numbers.subList(0, 6);
        Collections.sort(selectNumber);
        return new ArrayList<>(selectNumber);
    }

    public static void generate() {
        numbers = IntStream.range(LOTTO_MIN, LOTTO_MAX)
                .boxed()
                .collect(Collectors.toList());
    }
}
