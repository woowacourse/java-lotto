package domain;

import java.util.ArrayList;
import java.util.List;
import utils.Separator;
import validator.WinLottoNumbersValidator;

public class WinLottoNumbers {

    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonus;

    private WinLottoNumbers(List<Integer> lottoNumbers, int bonus) {
        List<LottoNumber> tmpLottoNumbers = new ArrayList<>();
        for (Integer lottoNumber : lottoNumbers) {
            tmpLottoNumbers.add(LottoNumber.valueOf(lottoNumber));
        }
        this.lottoNumbers = tmpLottoNumbers;
        this.bonus = LottoNumber.valueOf(bonus);
    }

    public static WinLottoNumbers of(String lottoNumbersText, int bonus) {
        List<Integer> numbers = Separator.separateNumbers(lottoNumbersText);
        WinLottoNumbersValidator.validate(numbers, bonus);
        return new WinLottoNumbers(numbers, bonus);
    }

    public boolean isInNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}