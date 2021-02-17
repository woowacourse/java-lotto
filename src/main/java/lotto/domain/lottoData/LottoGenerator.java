package lotto.domain.lottoData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static List<Integer> numbers;

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

    public static void generate(int minValue, int maxValue) {
        numbers = IntStream.range(minValue, maxValue)
                .boxed()
                .collect(Collectors.toList());
    }
}
