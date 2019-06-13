package lotto.domain.lotto.generator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomaticLottoGenerator implements LottoGenerator {
    private final int count;

    public AutomaticLottoGenerator(int count) {
        this.count = count;
    }

    private Lotto generateLotto() {
        return new Lotto(generateRandomLottoNumbers());
    }

    private List<Integer> generateRandomLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < Lotto.NUMBER_LENGTH) {
            numbers.add(generateRandomLottoNumber());
        }
        return new ArrayList<>(numbers);
    }

    private int generateRandomLottoNumber() {
        return (int) (Math.random()
                * (LottoNumber.MAXIMUM_NUMBER - LottoNumber.MINIMUM_NUMBER + 1)
                + LottoNumber.MINIMUM_NUMBER);
    }

    @Override
    public Lottos generate() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return new Lottos(lottos);
    }
}
