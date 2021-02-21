package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {
    private final int[] numbers;

    public ManualLottoGenerator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto createLotto() {
        return new Lotto(createLottoNumbers());
    }

    public List<LottoNumber> createLottoNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }
}
