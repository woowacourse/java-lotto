package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 46;

    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    @Override
    public List<Lotto> generate(int amount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < amount; ++i) {
            lottos.add(generateLotto());
        }
        return lottos;

    }

    private Lotto generateLotto() {
        List<Integer> numbers = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        numbers = numbers.subList(MIN_RANGE, MAX_RANGE);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

}
