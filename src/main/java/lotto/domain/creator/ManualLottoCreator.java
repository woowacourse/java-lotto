package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.exception.InvalidLottoNumbersException;

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
            checkNumberIn(number);
            lottoNumbers.add(LottoNumber.valueOf(number));
        }
        return new Lotto(lottoNumbers);
    }

    private static void checkNumberIn(int number) {
        if (LottoNumber.valueOf(number) == null) {
            throw new InvalidLottoNumbersException("로또에 사용되는 숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}
