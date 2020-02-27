package Lotto.utils;

import Lotto.domain.LottoNumber;

import java.util.List;

public class ManualLottoNumberGenerator implements NumberGenerator{
    @Override
    public List<LottoNumber> generate(String input) {
        return NumberParser.parseIntoLottoNumbers(input);
    }
}
