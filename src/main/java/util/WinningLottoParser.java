package util;

import domain.WinningLotto;

public class WinningLottoParser {

    public static WinningLotto parse(String lottoInput, String bonusBallInput) {

        return WinningLotto.of(LottoParser.parse(lottoInput), NumberParser.parse(bonusBallInput));
    }
}
