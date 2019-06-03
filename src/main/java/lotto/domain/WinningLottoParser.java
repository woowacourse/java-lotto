package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLottoParser {

    public static List<LottoNumber> parseLottoNumbers(String[] scannedLotto) {
        List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        Arrays.stream(scannedLotto)
                .forEach(number -> winningLottoNumbers.add(new LottoNumber(Integer.parseInt(number.trim()))));
        return winningLottoNumbers;
    }

    public static LottoNumber parseBonusBall(String scannedBonusBall) {
        return new LottoNumber(Integer.parseInt(scannedBonusBall));
    }
}
