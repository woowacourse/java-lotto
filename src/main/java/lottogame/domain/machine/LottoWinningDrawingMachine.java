package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;

public class LottoWinningDrawingMachine {

    public static final String DELIMITER = ", ";
    private final LottoNumbers lottoNumbers = new LottoNumbers();

    public LottoNumbers drawing(final String drawingNumbers) {
        for (String number : drawingNumbers.split(DELIMITER)) {
            this.lottoNumbers.add(new LottoNumber(number));
        }
        return new LottoNumbers(this.lottoNumbers.toList());
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
