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
        List<LottoNumber> numbers = allLottoNumbers.subList(0, 6);
        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new Lotto(numbers);
    }
}
