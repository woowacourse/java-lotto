package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLottoParser {

    public static WinningLotto parseWinningLotto(String[] scannedLotto, String scannedBonusBall) {
        return new WinningLotto(LottoParser.parseLottoNumbers(scannedLotto), parseBonusBall(scannedBonusBall));
    }

    public static LottoNumber parseBonusBall(String scannedBonusBall) {
        return new LottoNumber(Integer.parseInt(scannedBonusBall));
    }
}
