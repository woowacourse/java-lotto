package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 46;

    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    private static final List<Integer> numbers = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
        .boxed().collect(Collectors.toList());

    private final int amount;

    public RandomLottoGenerator(int amount) {
        this.amount = amount;
    }

    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < amount; ++i) {
            lottos.add(generateLotto());
        }
        return lottos;

    }

    private Lotto generateLotto() {
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = new ArrayList<>(numbers.subList(MIN_RANGE, MAX_RANGE));
        Collections.sort(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

}
