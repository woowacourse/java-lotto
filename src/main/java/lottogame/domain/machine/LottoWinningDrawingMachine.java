package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.utils.LottoGameUtils;

public class LottoWinningDrawingMachine {

    public static final String DELIMITER = ",";
    private LottoNumbers lottoNumbers;

    public LottoNumbers drawing(final String drawingNumbers) {
        return lottoNumbers = LottoGameUtils.getLottoNumbersByInputString(drawingNumbers);
    }

    public LottoNumber bonusDrawing(final String drawingNumber) {
        validBonusNumber(drawingNumber);
        return new LottoNumber(drawingNumber);
    }

    private void validBonusNumber(final String bonusNumber) {
        if (lottoNumbers.contains(new LottoNumber(bonusNumber))) {
            throw new IllegalArgumentException("보너스 번호가 당첨번호와 중복됩니다.");
        }
    }
}