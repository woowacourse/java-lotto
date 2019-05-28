package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoFactory {
    private static List<Integer> lottoNumbers = generateLottoNumber();

    static Lotto generateLotto() {
        Collections.shuffle(lottoNumbers);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            numbers.add(lottoNumbers.get(i));
        }
        return new Lotto(numbers);
    }

    static List<Integer> generateLottoNumber() {
        IntStream stream = IntStream.range(1,45);
        return stream.boxed().collect(Collectors.toList());
    }

}
