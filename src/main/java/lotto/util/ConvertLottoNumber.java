package lotto.util;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class ConvertLottoNumber {

    public static List<LottoNumber> run(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getInstance(number));
        }
        return lottoNumbers;
    }
}
