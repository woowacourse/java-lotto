package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottosGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lotto.domain.LottoNumber.getAllLottoNumbers;

public class LottosAutoGenerator implements LottosGenerator {
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;
    private final int countOfGenerate;

    public LottosAutoGenerator(int countOfGenerate) {
        this.countOfGenerate = countOfGenerate;
    }

    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < countOfGenerate; i++) {
            lottos.add(generateAutoLotto());
        }

        return lottos;
    }

    private Lotto generateAutoLotto() {
        List<LottoNumber> allLottoNumbers = getAllLottoNumbers();
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> numbers = allLottoNumbers.subList(FROM_INDEX, TO_INDEX);
        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new Lotto(numbers);
    }
}
