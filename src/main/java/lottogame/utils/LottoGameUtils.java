package lottogame.utils;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class LottoGameUtils {
    public static final String DELIMITER = ",";

    public static LottoNumbers getLottoNumbersByInputString(String lottoNumbers) {
        lottoNumbers = lottoNumbers.replaceAll(" ", "");
        final List<LottoNumber> lottoNumberGroup = new ArrayList<>();

        for (String number : lottoNumbers.split(DELIMITER)) {
            lottoNumberGroup.add(new LottoNumber(number));
        }
        return new LottoNumbers(lottoNumberGroup);
    }
}
