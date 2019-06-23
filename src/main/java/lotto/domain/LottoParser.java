package lotto.domain;

import java.util.*;

public class LottoParser {

    private static final String DELIMITER = ",";

    public static Set<LottoNumber> parseLottoNumbers(String scannedLotto) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        Arrays.stream(scannedLotto.split(DELIMITER))
                .forEach(number -> lottoNumbers.add(new LottoNumber(Integer.parseInt(number.trim()))));
        return lottoNumbers;
    }
}
