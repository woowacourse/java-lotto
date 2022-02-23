package domain;

import java.util.List;
import utils.Separator;
import validator.WinLottoNumbersValidator;

public class WinLottoNumbers extends LottoNumbers {
    private final LottoNumber bonus;

    private WinLottoNumbers(List<Integer> lottoNumbers, int bonus) {
        super(lottoNumbers);
        this.bonus = new LottoNumber(bonus);
    }

    public static WinLottoNumbers of(String lottoNumbersText, int bonus) {
        List<Integer> numbers = Separator.separateNumbers(lottoNumbersText);
        WinLottoNumbersValidator.validate(numbers);
        WinLottoNumbersValidator.validateBonus(numbers,bonus);
        return new WinLottoNumbers(numbers, bonus);
    }

    public boolean isInNumber(LottoNumber lottoNumber) {
        return super.lottoNumbers.contains(lottoNumber);
    }

    public LottoNumber getBonus(){
        return bonus;
    }
}