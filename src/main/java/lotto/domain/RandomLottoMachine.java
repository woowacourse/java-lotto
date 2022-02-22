package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine {

    private static final List<Integer> lottoNumbers = new ArrayList<>();

    static {
        IntStream.rangeClosed(1, 45)
                .forEach(lottoNumbers::add);
    }

    public static List<Integer> createRandomLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        return IntStream.rangeClosed(0, 5)
                .boxed()
                .map(lottoNumbers::get)
                .sorted()
                .collect(Collectors.toList());
    }
}
