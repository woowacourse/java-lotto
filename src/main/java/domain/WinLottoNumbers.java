package domain;

import java.util.List;
import utils.Separator;
import validator.WinLottoNumbersValidator;

public class WinLottoNumbers extends LottoNumbers {

    private WinLottoNumbers(List<Integer> lottoNumbers) {
        super(lottoNumbers);
    }

    public static WinLottoNumbers ofString(String lottoNumbersText) {
        WinLottoNumbersValidator.validate(lottoNumbersText);
        List<Integer> numbers =  Separator.separateNumbers(lottoNumbersText);
        return new WinLottoNumbers(numbers);
    }

    public boolean isInNumber(LottoNumber lottoNumber) {
        return super.lottoNumbers.contains(lottoNumber);
    }
}