package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoCreator implements LottoCreator {
    private  List<Integer> numbers;

    public ManualLottoCreator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto createLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(LottoNumber.valueOf(number));
        }
        return new Lotto(lottoNumbers);
    }
}
