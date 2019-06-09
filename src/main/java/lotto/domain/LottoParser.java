package lotto.domain;

import java.util.*;

public class LottoParser {

    public static Set<LottoNumber> parseLottoNumbers(String[] scannedLotto) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        Arrays.stream(scannedLotto)
                .forEach(number -> lottoNumbers.add(new LottoNumber(Integer.parseInt(number.trim()))));
        return lottoNumbers;
    }
}
