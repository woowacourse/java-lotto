package lotto.domain;


public class WinningLottoParser {

    public static WinningLotto parseWinningLotto(String[] scannedLotto, String scannedBonusBall) {
        return new WinningLotto(LottoParser.parseLottoNumbers(scannedLotto), parseBonusBall(scannedBonusBall));
    }

    public static LottoNumber parseBonusBall(String scannedBonusBall) {
        return new LottoNumber(Integer.parseInt(scannedBonusBall.trim()));
    }
}
