package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoMachine implements LottoMachine {

    private final List<Integer> numbers;

    public ManualLottoMachine(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto generateLotto() {
        return new Lotto(convertLottoNumber(numbers));
    }

    private List<LottoNumber> convertLottoNumber(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getInstance(number));
        }
        return lottoNumbers;
    }
}
